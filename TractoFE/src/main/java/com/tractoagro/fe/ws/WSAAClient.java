package com.tractoagro.fe.ws;

import FEV1.dif.afip.gov.ar.FEAuthRequest;

public interface WSAAClient {
	
	public FEAuthRequest obtenerTokenAndSign();
	
	public String invoke_wsaa (byte [] LoginTicketRequest_xml_cms, String endpoint) throws Exception;
	
	public byte[] create_cms(String p12file, String p12pass, String signer,
			String dstDN, String service, Long TicketTime);
	
}
