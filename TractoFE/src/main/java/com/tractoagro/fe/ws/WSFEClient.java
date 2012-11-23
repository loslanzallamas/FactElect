package com.tractoagro.fe.ws;

import FEV1.dif.afip.gov.ar.FEAuthRequest;
import FEV1.dif.afip.gov.ar.FECAERequest;
import FEV1.dif.afip.gov.ar.FECAEResponse;

public interface WSFEClient {
	
	public FECAEResponse obtenerCAE(FEAuthRequest authRequest, FECAERequest fecaeRequest) throws Exception;
	
}
