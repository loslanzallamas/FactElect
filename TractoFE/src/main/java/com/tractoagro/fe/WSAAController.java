package com.tractoagro.fe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import FEV1.dif.afip.gov.ar.FEAuthRequest;
import FEV1.dif.afip.gov.ar.FERecuperaLastCbteResponse;
import FEV1.dif.afip.gov.ar.Service;
import FEV1.dif.afip.gov.ar.ServiceLocator;
import FEV1.dif.afip.gov.ar.ServiceSoap;

import com.tractoagro.fe.ws.WSAAClient;

@Controller
@RequestMapping("/wsaa")
public class WSAAController {

	private WSAAClient wsaaClient;
	
	@Autowired
	public WSAAController(WSAAClient wsaaClient) {
		this.wsaaClient = wsaaClient;
	}
	
	
	@RequestMapping(method=RequestMethod.GET)	
	public String obtenerAutorizacion(Model model){
		
		try {
			FEAuthRequest feAuthRequest = wsaaClient.obtenerTokenAndSign();
			
			model.addAttribute("token", feAuthRequest.getToken());
			model.addAttribute("sign", feAuthRequest.getSign());
			model.addAttribute("respuesta", feAuthRequest.getToken());
			
			
			Service servicio = new ServiceLocator();

			ServiceSoap port = servicio.getServiceSoap();
			
			FERecuperaLastCbteResponse feRecuperaLastCbteResponse = port
			.FECompUltimoAutorizado(feAuthRequest, 3, 2);

			model.addAttribute("feRecuperaLastCbteResponse",
			feRecuperaLastCbteResponse);
			
		} catch (Exception e) {
			
		}
		
		return "wsaa";
	}


	
}
