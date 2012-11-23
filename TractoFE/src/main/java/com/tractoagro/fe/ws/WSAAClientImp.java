package com.tractoagro.fe.ws;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.rpc.ParameterMode;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.Base64;
import org.apache.axis.encoding.XMLType;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import FEV1.dif.afip.gov.ar.FEAuthRequest;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class WSAAClientImp implements WSAAClient {

	public FEAuthRequest obtenerTokenAndSign() {
		String LoginTicketResponse = null;
		FEAuthRequest authRequest = new FEAuthRequest();
		
		Long ticketLong = new Long("36000");

		String p12file = "c:/certs/private/servidorstore.p12";
		
		//Para homologación
		//byte [] LoginTicketRequest_xml_cms = create_cms(p12file, "1234", "servidor", "cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239", "wsfe", ticketLong);
		//byte [] LoginTicketRequest_xml_cms = create_cms(p12file, "1234", "test", "cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239", "wsfe", ticketLong);
		
		//Para producción
		byte [] LoginTicketRequest_xml_cms = create_cms(p12file, "1234", "servidor", "cn=wsaa,o=afip,c=ar,serialNumber=CUIT 33693450239", "wsfe", ticketLong);
		
		// Invoke AFIP WSAA and get LoginTicketResponse
		try {
			//Para homologación
			//LoginTicketResponse = invoke_wsaa ( LoginTicketRequest_xml_cms, "https://wsaahomo.afip.gov.ar/ws/services/LoginCms" );
			//Para produccion
			LoginTicketResponse = invoke_wsaa ( LoginTicketRequest_xml_cms, "https://wsaa.afip.gov.ar/ws/services/LoginCms" );
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// Get token & sign from LoginTicketResponse
		try {
			Reader tokenReader = new StringReader(LoginTicketResponse);
			Document tokenDoc = new SAXReader(false).read(tokenReader);
			
			authRequest.setToken(tokenDoc.valueOf("/loginTicketResponse/credentials/token"));
			authRequest.setSign(tokenDoc.valueOf("/loginTicketResponse/credentials/sign"));
			authRequest.setCuit(Long.parseLong("30708320893"));
			
			System.out.println(LoginTicketResponse);
			System.out.println("TOKEN: " + authRequest.getToken());
			System.out.println("SIGN: " + authRequest.getSign());
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		return authRequest;
	}
	
	public String invoke_wsaa(byte[] LoginTicketRequest_xml_cms, String endpoint)
			throws AxisFault,Exception {
		
		String LoginTicketResponse = null;
		
		try {

			Service service = new Service();
			org.apache.axis.client.Call call = (org.apache.axis.client.Call) service.createCall();

			//
			// Prepare the call for the web service
			//
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("loginCms");
			call.addParameter("request", XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);

			//
			// Make the actual call and assign the answer to a string
			//
			LoginTicketResponse = (String) call.invoke(new Object[] { Base64
					.encode(LoginTicketRequest_xml_cms) });
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return LoginTicketResponse;
	}

	//
	// Create the CMS Message
	// p12file = Keystore en formato PKCS#12 que posee el certificado del
	// computador
	// que solicita el acceso
	// p12pass = Clave de acceso al keystore
	//
	// signer = Signer del certificado en el keystore
	//
	// dstDN = Destino del Servicio
	//
	// service = Servicio para el cual se requiere el ticket de acceso
	//
	// TicketTime = Tiempo de vida del ticket requerido
	//
	public byte[] create_cms(String p12file, String p12pass, String signer,
			String dstDN, String service, Long TicketTime) {

		PrivateKey pKey = null;
		X509Certificate pCertificate = null;
		byte[] asn1_cms = null;
		CertStore cstore = null;
		String LoginTicketRequest_xml;
		String SignerDN = null;

		//
		// Manage Keys & Certificates
		//
		try {
			// Create a keystore using keys from the pkcs#12 p12file
			KeyStore ks = KeyStore.getInstance("pkcs12");
			FileInputStream p12stream = new FileInputStream(p12file);
			ks.load(p12stream, p12pass.toCharArray());
			p12stream.close();

			// Get Certificate & Private key from KeyStore
			pKey = (PrivateKey) ks.getKey(signer, p12pass.toCharArray());
			pCertificate = (X509Certificate) ks.getCertificate(signer);
			SignerDN = pCertificate.getSubjectDN().toString();

			// Create a list of Certificates to include in the final CMS
			ArrayList<X509Certificate> certList = new ArrayList<X509Certificate>();
			certList.add(pCertificate);

			if (Security.getProvider("BC") == null) {
				Security.addProvider(new BouncyCastleProvider());
			}

			cstore = CertStore.getInstance("Collection",
					new CollectionCertStoreParameters(certList), "BC");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		//
		// Create XML Message
		//
		LoginTicketRequest_xml = create_LoginTicketRequest(SignerDN, dstDN,
				service, TicketTime);

		//
		// Create CMS Message
		//
		try {
			// Create a new empty CMS Message
			CMSSignedDataGenerator gen = new CMSSignedDataGenerator();

			// Add a Siggner to the Message
			gen.addSigner(pKey, pCertificate,
					CMSSignedDataGenerator.DIGEST_SHA1);

			// Add the Certificate to the Message
			gen.addCertificatesAndCRLs(cstore);

			// Add the data (XML) to the Message
			CMSProcessable data = new CMSProcessableByteArray(
					LoginTicketRequest_xml.getBytes());

			// Add a Sign of the Data to the Message
			CMSSignedData signed = gen.generate(data, true, "BC");

			asn1_cms = signed.getEncoded();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return (asn1_cms);
	}

	// Create XML Message fo0r AFIP wsaa
	public String create_LoginTicketRequest(String SignerDN, String dstDN,
			String service, Long TicketTime) {
		String LoginTicketRequest_xml;

		Date GenTime = new Date();
		GregorianCalendar genTime = new GregorianCalendar();
		GregorianCalendar expTime = new GregorianCalendar();
		String UniqueId = new Long(GenTime.getTime() / 1000).toString();

		expTime.setTime(new Date(GenTime.getTime() + TicketTime));

		XMLGregorianCalendarImpl XMLGenTime = new XMLGregorianCalendarImpl(
				genTime);
		XMLGregorianCalendarImpl XMLExpTime = new XMLGregorianCalendarImpl(
				expTime);

		LoginTicketRequest_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<loginTicketRequest version=\"1.0\">"
				+ "<header>"
				+ "<source>"
				+ SignerDN
				+ "</source>"
				+ "<destination>"
				+ dstDN
				+ "</destination>"
				+ "<uniqueId>"
				+ UniqueId
				+ "</uniqueId>"
				+ "<generationTime>"
				+ XMLGenTime
				+ "</generationTime>"
				+ "<expirationTime>"
				+ XMLExpTime
				+ "</expirationTime>"
				+ "</header>"
				+ "<service>"
				+ service
				+ "</service>"
				+ "</loginTicketRequest>";
		
		return (LoginTicketRequest_xml);

	}


}
