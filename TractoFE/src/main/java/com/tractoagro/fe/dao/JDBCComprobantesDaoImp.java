package com.tractoagro.fe.dao;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tractoagro.fe.domain.Comprobante;
import com.tractoagro.fe.domain.ComprobanteDet;

@Repository
public class JDBCComprobantesDaoImp implements
		ComprobantesDao {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Comprobante findComprobante(int tc_codigo, int su_codigo,
			int fa_codigo) {

		final Comprobante comprobante = new Comprobante();
		
		String sql = "SELECT * FROM facturas WHERE tc_codigo = ? AND su_codigo = ? AND fa_codigo = ?";

		try {

			jdbcTemplate.query(sql, new Object[] { tc_codigo, su_codigo,
					fa_codigo }, new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					comprobante.setTc_codigo(rs.getInt("tc_codigo"));
					comprobante.setSuc_codigo(rs.getInt("su_codigo"));
					comprobante.setFa_codigo(rs.getInt("fa_codigo"));
					comprobante.setFa_fecha(rs.getString("fa_fecha"));
					comprobante.setCl_codigo(rs.getInt("cl_codigo"));
					comprobante.setFa_cliente(rs.getString("fa_cliente"));
					comprobante.setFa_domicilio(rs.getString("fa_domici"));
					comprobante.setFa_localidad("");
					comprobante.setFa_provincia("");
					comprobante.setFa_respon("");
					comprobante.setFa_document(rs.getInt("fa_documen"));
					comprobante.setFa_nro_doc(rs.getString("fa_nro_doc"));
					comprobante.setFa_total(rs.getDouble("fa_total"));
					comprobante.setFa_subtotal(rs.getDouble("fa_subtota"));
					comprobante.setFa_iva(rs.getDouble("fa_subtota"));
				}
			});

			final List<ComprobanteDet> comprobanteDets = new ArrayList<ComprobanteDet>();

			sql = "SELECT * FROM itfact WHERE tc_codigo = ? AND su_codigo = ? AND fa_codigo";

			jdbcTemplate.query(sql, new Object[] { tc_codigo, su_codigo,
					fa_codigo }, new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					while (rs.next()) {
						ComprobanteDet comprobanteDet = new ComprobanteDet();

						comprobanteDet.setIfa_codigo(rs.getInt("ifa_codigo"));
						comprobanteDet.setAr_codigo(rs.getString("ar_codigo"));
						comprobanteDet.setIfa_descripcion(rs
								.getString("ifadescri"));
						comprobanteDet.setIfa_cantidad(rs
								.getDouble("ifa_cantid"));
						comprobanteDet.setIfa_unipre(rs.getDouble("ifa_unipre"));
						comprobanteDet.setIfa_porbon(rs.getDouble("ifa_porbon"));
						comprobanteDet.setIfa_bonifi(rs.getDouble("ifa_bonifi"));
						comprobanteDet.setIfa_aliiva(rs.getDouble("ifa_aliiva"));

						comprobanteDets.add(comprobanteDet);
					}
				}
			});

			comprobante.setComprobanteDets(comprobanteDets
					.toArray(new ComprobanteDet[0]));

		} catch (Exception e) {
			e.getStackTrace();
		}

		return comprobante;
	}

	public Collection<Comprobante> findComprobantes() {
		String sql = "SELECT * FROM facturas WHERE su_codigo = 1";
		List<Comprobante> comprobantes = this.jdbcTemplate.query(sql, new RowMapper<Comprobante>(){
			public Comprobante mapRow(ResultSet rs, int rowNum) throws SQLException{
				Comprobante comprobante = new Comprobante();
				comprobante.setTc_codigo(rs.getInt("tc_codigo"));
				comprobante.setSuc_codigo(rs.getInt("su_codigo"));
				comprobante.setFa_codigo(rs.getInt("fa_codigo"));
				comprobante.setFa_fecha(rs.getString("fa_fecha"));
				comprobante.setCl_codigo(rs.getInt("cl_codigo"));
				comprobante.setFa_cliente(rs.getString("fa_cliente"));
				comprobante.setFa_domicilio(rs.getString("fa_domici"));
				comprobante.setFa_localidad("");
				comprobante.setFa_provincia("");
				comprobante.setFa_respon("");
				comprobante.setFa_document(rs.getInt("fa_documen"));
				comprobante.setFa_nro_doc(rs.getString("fa_nro_doc"));
				comprobante.setFa_total(rs.getDouble("fa_total"));
				comprobante.setFa_subtotal(rs.getDouble("fa_subtota"));
				comprobante.setFa_iva(rs.getDouble("fa_subtota"));
				return comprobante;
			}
		});
		return comprobantes;
	}

	public Collection<Comprobante> findComprobantesCae(String tipo_doc) {
		
		String sql = null;
		
		if(tipo_doc.equals("FACT")){
			sql = "SELECT TOP 10 * FROM facturas WHERE NOT fa_otros2 LIKE \"CAE%\" AND su_codigo = 3 AND tc_codigo = 1 ORDER BY fa_codigo";
		}else if((tipo_doc.equals("NC"))){
			sql = "SELECT TOP 10 * FROM facturas WHERE NOT fa_otros2 LIKE \"CAE%\" AND su_codigo = 3 AND tc_codigo = 33 ORDER BY fa_codigo";
		}else if((tipo_doc.equals("ND"))){
			sql = "SELECT TOP 10 * FROM facturas WHERE NOT fa_otros2 LIKE \"CAE%\" AND su_codigo = 3 AND tc_codigo = 2 ORDER BY fa_codigo";
		}
		
		List<Comprobante> comprobantes = this.jdbcTemplate.query(sql, new RowMapper<Comprobante>(){
			public Comprobante mapRow(ResultSet rs, int rowNum) throws SQLException{
				Comprobante comprobante = new Comprobante();
				comprobante.setTc_codigo(rs.getInt("tc_codigo"));
				comprobante.setSuc_codigo(rs.getInt("su_codigo"));
				comprobante.setFa_codigo(rs.getInt("fa_codigo"));
				comprobante.setFa_fecha(rs.getString("fa_fecha"));
				comprobante.setCl_codigo(rs.getInt("cl_codigo"));
				comprobante.setFa_cliente(rs.getString("fa_cliente"));
				comprobante.setFa_domicilio(rs.getString("fa_domici"));
				comprobante.setFa_localidad("");
				comprobante.setFa_provincia("");
				comprobante.setFa_respon("");
				comprobante.setFa_document(rs.getInt("fa_documen"));
				comprobante.setFa_nro_doc(rs.getString("fa_nro_doc"));
				comprobante.setFa_total(rs.getDouble("fa_total"));
				comprobante.setFa_subtotal(rs.getDouble("fa_subtota"));
				comprobante.setFa_iva(rs.getDouble("fa_iva"));
				comprobante.setFa_otros2(rs.getString("fa_otros2"));
				return comprobante;
			}
		});
		return comprobantes;
	}

	public String saveCae(int tc_codigo, int su_codigo, int fa_codigo, String strCae, String strCaeVto) {
		// TODO Auto-generated method stub
		String resultado = "0";
		
		try {
			
			if(tc_codigo==3)
				tc_codigo = 33;
			
			resultado = String.valueOf(this.jdbcTemplate.update("UPDATE facturas SET fa_otros2=? WHERE tc_codigo=? AND su_codigo=? AND fa_codigo=?", "CAE " + strCae + "-" + strCaeVto ,tc_codigo ,su_codigo ,fa_codigo));
			
			resultado = "CAEes guardados = " + resultado;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al actualizar la tabla facturas.dbf: " + e.getMessage());
			return "Ha ocurrido un error al intentar guardar los CAEes. Verifique los mismos antes de cerrar esta página";
		} 
		return resultado;
	}

}
