package com.tractoagro.fe.service;

import FEV1.dif.afip.gov.ar.FECAERequest;
import FEV1.dif.afip.gov.ar.FECAEResponse;

public interface GenFECAERequest {
	
	public FECAERequest generarFECAERequest(String tipo_doc);
	
	public String guardarCAE(FECAEResponse fecaeResponse);

}
