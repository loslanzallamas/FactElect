package com.tractoagro.fe;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import FEV1.dif.afip.gov.ar.DummyResponse;
import FEV1.dif.afip.gov.ar.FEAuthRequest;
import FEV1.dif.afip.gov.ar.FECAERequest;
import FEV1.dif.afip.gov.ar.FECAEResponse;
import FEV1.dif.afip.gov.ar.FECompConsResponse;
import FEV1.dif.afip.gov.ar.FECompConsultaReq;
import FEV1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
import FEV1.dif.afip.gov.ar.Service;
import FEV1.dif.afip.gov.ar.ServiceLocator;
import FEV1.dif.afip.gov.ar.ServiceSoap;

import com.tractoagro.fe.dao.ComprobantesDao;
import com.tractoagro.fe.service.GenFECAERequest;
import com.tractoagro.fe.ws.WSAAClient;
import com.tractoagro.fe.ws.WSFEClient;

@Controller
@RequestMapping("/cae")
public class CAEController {

	private WSAAClient wsaaClient;
	private WSFEClient wsfeClient;
	private GenFECAERequest genFECAERequest;

	ComprobantesDao comprobantesDao;

	@Autowired
	public CAEController(WSAAClient wsaaClient, WSFEClient wsfeClient,
			GenFECAERequest genFECAERequest, ComprobantesDao comprobantesDao) {
		this.wsaaClient = wsaaClient;
		this.wsfeClient = wsfeClient;
		this.genFECAERequest = genFECAERequest;
		this.comprobantesDao = comprobantesDao;

	}

	@RequestMapping(method = RequestMethod.GET)
	public String resolverCae(@RequestParam("tipo_doc") String tipo_doc,
			Model model) {

		// Invoke Afip WSFE and get CAE
		try {

			// Acceso al Web Service
			Service servicio = new ServiceLocator();

			ServiceSoap port = servicio.getServiceSoap();

			// Consulta de estado del web service
			DummyResponse dummyResponse = port.FEDummy();

			System.out.println("Respuesta dummy "
					+ dummyResponse.getAppServer());

			// Obtener ticket de acceso
			FEAuthRequest authRequest = wsaaClient.obtenerTokenAndSign();

			/*
			 * 
			 *Solicitar autorización por tipo de comprobante
			 * 
			*/
			String[] compHabil = { "FACT", "NC", "ND" };

			Collection<FECAEResponse> fecaeResponses = new ArrayList<FECAEResponse>();

			for (int i = 0; i < compHabil.length; i++) {
				FECAERequest fecaeRequest = genFECAERequest
						.generarFECAERequest(compHabil[i]);

				FECAEResponse fecaeResponse = wsfeClient.obtenerCAE(
						authRequest, fecaeRequest);

				genFECAERequest.guardarCAE(fecaeResponse);
				
				fecaeResponses.add(fecaeResponse);
				
			}

			// Datos obtenidos desde el WS
			model.addAttribute("fecaeResponse", fecaeResponses);
			
			
			for (int i = 0; i < compHabil.length; i++) {
			// Recupera el último comprobante autorizado
			FERecuperaLastCbteResponse feRecuperaLastCbteResponse = port
					.FECompUltimoAutorizado(authRequest, 3, compHabil[i]);

			model.addAttribute("feRecuperaLastCbteResponse",
					feRecuperaLastCbteResponse);

			// Recupera el CAE del último comprobante autorizado
			FECompConsResponse feCompConsResponse = port.FECompConsultar(
					authRequest,
					new FECompConsultaReq(feRecuperaLastCbteResponse
							.getCbteTipo(), feRecuperaLastCbteResponse
							.getCbteNro(), feRecuperaLastCbteResponse
							.getPtoVta())).getResultGet();
			}
			model.addAttribute("feCompConsResponse", feCompConsResponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("logSistema", e.getMessage());
		}

		return "cae";
	}

}
