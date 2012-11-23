package com.tractoagro.fe.ws;

import FEV1.dif.afip.gov.ar.FEAuthRequest;
import FEV1.dif.afip.gov.ar.FECAERequest;
import FEV1.dif.afip.gov.ar.FECAEResponse;
import FEV1.dif.afip.gov.ar.Service;
import FEV1.dif.afip.gov.ar.ServiceLocator;
import FEV1.dif.afip.gov.ar.ServiceSoap;

public class WSFEClientImp implements WSFEClient {

	public FECAEResponse obtenerCAE(FEAuthRequest authRequest,
			FECAERequest fecaeRequest) throws Exception {
		// TODO Auto-generated method stub
		try {
							
		Service servicio = new ServiceLocator();
		
		ServiceSoap port = servicio.getServiceSoap();
		
		FECAEResponse fecaeResponse = port.FECAESolicitar(authRequest, fecaeRequest);
		
		//FEDetResponse [] feDetResponses = fecaeResponse.getFeDetResp();
		
		return fecaeResponse;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		return null;
	}

}
