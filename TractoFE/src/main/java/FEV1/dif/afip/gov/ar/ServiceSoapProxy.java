package FEV1.dif.afip.gov.ar;

public class ServiceSoapProxy implements FEV1.dif.afip.gov.ar.ServiceSoap {
  private String _endpoint = null;
  private FEV1.dif.afip.gov.ar.ServiceSoap serviceSoap = null;
  
  public ServiceSoapProxy() {
    _initServiceSoapProxy();
  }
  
  public ServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceSoapProxy();
  }
  
  private void _initServiceSoapProxy() {
    try {
      serviceSoap = (new FEV1.dif.afip.gov.ar.ServiceLocator()).getServiceSoap();
      if (serviceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceSoap != null)
      ((javax.xml.rpc.Stub)serviceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public FEV1.dif.afip.gov.ar.ServiceSoap getServiceSoap() {
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap;
  }
  
  public FEV1.dif.afip.gov.ar.FECAEResponse FECAESolicitar(FEV1.dif.afip.gov.ar.FEAuthRequest auth, FEV1.dif.afip.gov.ar.FECAERequest feCAEReq) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECAESolicitar(auth, feCAEReq);
  }
  
  public FEV1.dif.afip.gov.ar.FERegXReqResponse FECompTotXRequest(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECompTotXRequest(auth);
  }
  
  public FEV1.dif.afip.gov.ar.DummyResponse FEDummy() throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEDummy();
  }
  
  public FEV1.dif.afip.gov.ar.FERecuperaLastCbteResponse FECompUltimoAutorizado(FEV1.dif.afip.gov.ar.FEAuthRequest auth, int ptoVta, int cbteTipo) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECompUltimoAutorizado(auth, ptoVta, cbteTipo);
  }
  
  public FEV1.dif.afip.gov.ar.FECompConsultaResponse FECompConsultar(FEV1.dif.afip.gov.ar.FEAuthRequest auth, FEV1.dif.afip.gov.ar.FECompConsultaReq feCompConsReq) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECompConsultar(auth, feCompConsReq);
  }
  
  public FEV1.dif.afip.gov.ar.FECAEAResponse FECAEARegInformativo(FEV1.dif.afip.gov.ar.FEAuthRequest auth, FEV1.dif.afip.gov.ar.FECAEARequest feCAEARegInfReq) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECAEARegInformativo(auth, feCAEARegInfReq);
  }
  
  public FEV1.dif.afip.gov.ar.FECAEAGetResponse FECAEASolicitar(FEV1.dif.afip.gov.ar.FEAuthRequest auth, int periodo, short orden) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECAEASolicitar(auth, periodo, orden);
  }
  
  public FEV1.dif.afip.gov.ar.FECAEASinMovConsResponse FECAEASinMovimientoConsultar(FEV1.dif.afip.gov.ar.FEAuthRequest auth, java.lang.String CAEA, int ptoVta) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECAEASinMovimientoConsultar(auth, CAEA, ptoVta);
  }
  
  public FEV1.dif.afip.gov.ar.FECAEASinMovResponse FECAEASinMovimientoInformar(FEV1.dif.afip.gov.ar.FEAuthRequest auth, int ptoVta, java.lang.String CAEA) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECAEASinMovimientoInformar(auth, ptoVta, CAEA);
  }
  
  public FEV1.dif.afip.gov.ar.FECAEAGetResponse FECAEAConsultar(FEV1.dif.afip.gov.ar.FEAuthRequest auth, int periodo, short orden) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FECAEAConsultar(auth, periodo, orden);
  }
  
  public FEV1.dif.afip.gov.ar.FECotizacionResponse FEParamGetCotizacion(FEV1.dif.afip.gov.ar.FEAuthRequest auth, java.lang.String monId) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetCotizacion(auth, monId);
  }
  
  public FEV1.dif.afip.gov.ar.FETributoResponse FEParamGetTiposTributos(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposTributos(auth);
  }
  
  public FEV1.dif.afip.gov.ar.MonedaResponse FEParamGetTiposMonedas(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposMonedas(auth);
  }
  
  public FEV1.dif.afip.gov.ar.IvaTipoResponse FEParamGetTiposIva(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposIva(auth);
  }
  
  public FEV1.dif.afip.gov.ar.OpcionalTipoResponse FEParamGetTiposOpcional(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposOpcional(auth);
  }
  
  public FEV1.dif.afip.gov.ar.ConceptoTipoResponse FEParamGetTiposConcepto(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposConcepto(auth);
  }
  
  public FEV1.dif.afip.gov.ar.FEPtoVentaResponse FEParamGetPtosVenta(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetPtosVenta(auth);
  }
  
  public FEV1.dif.afip.gov.ar.CbteTipoResponse FEParamGetTiposCbte(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposCbte(auth);
  }
  
  public FEV1.dif.afip.gov.ar.DocTipoResponse FEParamGetTiposDoc(FEV1.dif.afip.gov.ar.FEAuthRequest auth) throws java.rmi.RemoteException{
    if (serviceSoap == null)
      _initServiceSoapProxy();
    return serviceSoap.FEParamGetTiposDoc(auth);
  }
  
  
}