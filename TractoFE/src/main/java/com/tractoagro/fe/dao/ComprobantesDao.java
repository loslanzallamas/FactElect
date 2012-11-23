package com.tractoagro.fe.dao;

import java.util.Collection;

import com.tractoagro.fe.domain.Comprobante;

public interface ComprobantesDao {
	
	public Comprobante findComprobante(int tc_codigo, int su_codigo, int fa_codigo);
	
	public String saveCae(int tc_codigo, int su_codigo, int fa_codigo, String strCae, String strCaeVto);
	
	public Collection<Comprobante> findComprobantes();
	
	public Collection<Comprobante> findComprobantesCae(String tipo_comp);
	
}
