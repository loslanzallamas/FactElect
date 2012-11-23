package com.tractoagro.fe.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.tractoagro.fe.dao.ComprobantesDao;
import com.tractoagro.fe.domain.Comprobante;

import FEV1.dif.afip.gov.ar.AlicIva;
import FEV1.dif.afip.gov.ar.FECAECabRequest;
import FEV1.dif.afip.gov.ar.FECAEDetRequest;
import FEV1.dif.afip.gov.ar.FECAEDetResponse;
import FEV1.dif.afip.gov.ar.FECAERequest;
import FEV1.dif.afip.gov.ar.FECAEResponse;

public class GenFECAERequestImp implements GenFECAERequest {

	ComprobantesDao comprobantesDao;

	@Autowired
	public void setComprobantesDao(ComprobantesDao comprobantesDao) {
		this.comprobantesDao = comprobantesDao;
	}


	public FECAERequest generarFECAERequest(String tipo_doc) {
		
		Collection<FECAEDetRequest> fecaeDetRequests = new ArrayList<FECAEDetRequest>();
		
		try {
			try {
			Comprobante [] comprobantes =  comprobantesDao.findComprobantesCae(tipo_doc).toArray(new Comprobante[0]);
			
			
			
			int i = 0;
			
			for (Comprobante comprobante : comprobantes) {
				FECAEDetRequest fecaeDetRequest = new FECAEDetRequest();
				fecaeDetRequest.setConcepto(1);
				fecaeDetRequest.setDocTipo(comprobante.getFa_document());
				fecaeDetRequest.setDocNro(Long.parseLong(comprobante.getFa_nro_doc()));
				fecaeDetRequest.setCbteDesde(comprobante.getFa_codigo());
				fecaeDetRequest.setCbteHasta(comprobante.getFa_codigo());
				fecaeDetRequest.setCbteFch(comprobante.getFa_fecha().replace("-", ""));
				fecaeDetRequest.setImpTotal(comprobante.getFa_total());
				fecaeDetRequest.setImpNeto(comprobante.getFa_subtotal());
				fecaeDetRequest.setImpOpEx(0);
				fecaeDetRequest.setImpTrib(0);
				fecaeDetRequest.setImpIVA(comprobante.getFa_iva());
				fecaeDetRequest.setMonId("PES");
				fecaeDetRequest.setMonCotiz(1);
				AlicIva [] alicIvas = {new AlicIva(5, comprobante.getFa_subtotal(), comprobante.getFa_iva())};
				fecaeDetRequest.setIva(alicIvas);
				
				fecaeDetRequests.add(fecaeDetRequest);
				
				i = i + 1;
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int cab_tipo_doc = 0; 
			
			if(tipo_doc.equals("FACT")){
				cab_tipo_doc = 1;
			}else if(tipo_doc.equals("NC")){
				cab_tipo_doc = 3;
			}else if(tipo_doc.equals("ND")){
				cab_tipo_doc = 2;
			}
			
			FECAECabRequest feCabReq = new FECAECabRequest(fecaeDetRequests.size(),3,cab_tipo_doc);
			
			FECAEDetRequest [] detRequests = fecaeDetRequests.toArray(new FECAEDetRequest[0]);
			
			FECAERequest fecaeRequest = new FECAERequest(feCabReq, detRequests);
			
			return fecaeRequest;
			
		} catch (Exception e) {
			e.getStackTrace();
		}
				
		return null;
	}
	
	public String guardarCAE(FECAEResponse fecaeResponse){
		
		for (FECAEDetResponse fecaeDetResponse : fecaeResponse.getFeDetResp()) {
			
			if(!fecaeDetResponse.getResultado().equals("R")){
				try {
					String resultadoCAE;
					resultadoCAE = comprobantesDao.saveCae(fecaeResponse.getFeCabResp().getCbteTipo(),
							fecaeResponse.getFeCabResp().getPtoVta(),
							(int) fecaeDetResponse.getCbteDesde(),
							fecaeDetResponse.getCAE(),
							fecaeDetResponse.getCAEFchVto());
					return resultadoCAE;
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error al intentar salvar el CAE " + e.getMessage());
					return e.getMessage()+ "-" + fecaeDetResponse.getCbteDesde();
				}
			}	
		}
		return "";
		
	}

}
