package com.tractoagro.fe.domain;

public class ComprobanteDet {
	int ifa_codigo;
	String ar_codigo;
	String ifa_descripcion;
	Double ifa_cantidad;
	Double ifa_unipre;
	Double ifa_porbon;
	Double ifa_bonifi;
	Double ifa_aliiva;
	
	public ComprobanteDet(int ifa_codigo, String ar_codigo,
			String ifa_descripcion, Double ifa_cantidad, Double ifa_unipre,
			Double ifa_porbon, Double ifa_bonifi, Double ifa_aliiva) {
		super();
		this.ifa_codigo = ifa_codigo;
		this.ar_codigo = ar_codigo;
		this.ifa_descripcion = ifa_descripcion;
		this.ifa_cantidad = ifa_cantidad;
		this.ifa_unipre = ifa_unipre;
		this.ifa_porbon = ifa_porbon;
		this.ifa_bonifi = ifa_bonifi;
		this.ifa_aliiva = ifa_aliiva;
	}
	
	public ComprobanteDet() {
		// TODO Auto-generated constructor stub
	}

	public int getIfa_codigo() {
		return ifa_codigo;
	}
	public void setIfa_codigo(int ifa_codigo) {
		this.ifa_codigo = ifa_codigo;
	}
	public String getAr_codigo() {
		return ar_codigo;
	}
	public void setAr_codigo(String ar_codigo) {
		this.ar_codigo = ar_codigo;
	}
	public String getIfa_descripcion() {
		return ifa_descripcion;
	}
	public void setIfa_descripcion(String ifa_descripcion) {
		this.ifa_descripcion = ifa_descripcion;
	}
	public Double getIfa_cantidad() {
		return ifa_cantidad;
	}
	public void setIfa_cantidad(Double ifa_cantidad) {
		this.ifa_cantidad = ifa_cantidad;
	}
	public Double getIfa_unipre() {
		return ifa_unipre;
	}
	public void setIfa_unipre(Double ifa_unipre) {
		this.ifa_unipre = ifa_unipre;
	}
	public Double getIfa_porbon() {
		return ifa_porbon;
	}
	public void setIfa_porbon(Double ifa_porbon) {
		this.ifa_porbon = ifa_porbon;
	}
	public Double getIfa_bonifi() {
		return ifa_bonifi;
	}
	public void setIfa_bonifi(Double ifa_bonifi) {
		this.ifa_bonifi = ifa_bonifi;
	}
	public Double getIfa_aliiva() {
		return ifa_aliiva;
	}
	public void setIfa_aliiva(Double ifa_aliiva) {
		this.ifa_aliiva = ifa_aliiva;
	}
	
}
