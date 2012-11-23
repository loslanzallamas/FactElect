package com.tractoagro.fe.domain;

public class Comprobante {

	Integer tc_codigo;
	Integer suc_codigo;
	Integer fa_codigo;
	String fa_fecha;
	Integer cl_codigo;
	String fa_cliente;
	String fa_domicilio;
	String fa_localidad;
	String fa_provincia;
	String fa_respon;
	Integer fa_document;
	String fa_nro_doc;
	Double fa_total;
	Double fa_subtotal;
	Double fa_iva;
	String fa_otros2;
	ComprobanteDet [] comprobanteDets;
	
	public Comprobante(Integer tc_codigo, Integer suc_codigo,
			Integer fa_codigo, String fa_fecha, Integer cl_codigo,
			String fa_cliente, String fa_domicilio, String fa_localidad,
			String fa_provincia, String fa_respon, Integer fa_document,
			String fa_nro_doc, Double fa_total, Double fa_subtotal,
			Double fa_iva, String fa_otros2,
			ComprobanteDet[] comprobanteDets) {
		super();
		this.tc_codigo = tc_codigo;
		this.suc_codigo = suc_codigo;
		this.fa_codigo = fa_codigo;
		this.fa_fecha = fa_fecha;
		this.cl_codigo = cl_codigo;
		this.fa_cliente = fa_cliente;
		this.fa_domicilio = fa_domicilio;
		this.fa_localidad = fa_localidad;
		this.fa_provincia = fa_provincia;
		this.fa_respon = fa_respon;
		this.fa_document = fa_document;
		this.fa_nro_doc = fa_nro_doc;
		this.fa_total = fa_total;
		this.fa_subtotal = fa_subtotal;
		this.fa_iva = fa_iva;
		this.fa_otros2 = fa_otros2;
		this.comprobanteDets = comprobanteDets;
	}
	
	public Comprobante() {
		// TODO Auto-generated constructor stub
	}

	public Integer getTc_codigo() {
		return tc_codigo;
	}
	public void setTc_codigo(Integer tc_codigo) {
		this.tc_codigo = tc_codigo;
	}
	public Integer getSuc_codigo() {
		return suc_codigo;
	}
	public void setSuc_codigo(Integer suc_codigo) {
		this.suc_codigo = suc_codigo;
	}
	public Integer getFa_codigo() {
		return fa_codigo;
	}
	public void setFa_codigo(Integer fa_codigo) {
		this.fa_codigo = fa_codigo;
	}
	public String getFa_fecha() {
		return fa_fecha;
	}
	public void setFa_fecha(String fa_fecha) {
		this.fa_fecha = fa_fecha;
	}
	public Integer getCl_codigo() {
		return cl_codigo;
	}
	public void setCl_codigo(Integer cl_codigo) {
		this.cl_codigo = cl_codigo;
	}
	public String getFa_cliente() {
		return fa_cliente;
	}
	public void setFa_cliente(String fa_cliente) {
		this.fa_cliente = fa_cliente;
	}
	public String getFa_domicilio() {
		return fa_domicilio;
	}
	public void setFa_domicilio(String fa_domicilio) {
		this.fa_domicilio = fa_domicilio;
	}
	public String getFa_localidad() {
		return fa_localidad;
	}
	public void setFa_localidad(String fa_localidad) {
		this.fa_localidad = fa_localidad;
	}
	public String getFa_provincia() {
		return fa_provincia;
	}
	public void setFa_provincia(String fa_provincia) {
		this.fa_provincia = fa_provincia;
	}
	public String getFa_respon() {
		return fa_respon;
	}
	public void setFa_respon(String fa_respon) {
		this.fa_respon = fa_respon;
	}
	public Integer getFa_document() {
		return fa_document;
	}
	public void setFa_document(Integer fa_document) {
		this.fa_document = fa_document;
	}
	public String getFa_nro_doc() {
		return fa_nro_doc;
	}
	public void setFa_nro_doc(String fa_nro_doc) {
		this.fa_nro_doc = fa_nro_doc;
	}
	public Double getFa_total() {
		return fa_total;
	}
	public void setFa_total(Double fa_total) {
		this.fa_total = fa_total;
	}
	public Double getFa_subtotal() {
		return fa_subtotal;
	}
	public void setFa_subtotal(Double fa_subtotal) {
		this.fa_subtotal = fa_subtotal;
	}
	public Double getFa_iva() {
		return fa_iva;
	}
	public void setFa_iva(Double fa_iva) {
		this.fa_iva = fa_iva;
	}
	public String getFa_otros2() {
		return fa_otros2;
	}
	public void setFa_otros2(String fa_otros2) {
		this.fa_otros2 = fa_otros2;
	}
	public ComprobanteDet[] getComprobanteDets() {
		return comprobanteDets;
	}
	public void setComprobanteDets(ComprobanteDet[] comprobanteDets) {
		this.comprobanteDets = comprobanteDets;
	}
	
}
