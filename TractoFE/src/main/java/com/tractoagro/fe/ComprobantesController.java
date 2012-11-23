package com.tractoagro.fe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tractoagro.fe.dao.ComprobantesDao;
import com.tractoagro.fe.domain.Comprobante;

@Controller
@RequestMapping("/comprobantes/*")
public class ComprobantesController {

	ComprobantesDao comprobantesDao;

	@Autowired
	public ComprobantesController(ComprobantesDao comprobantesDao) {
		this.comprobantesDao = comprobantesDao;
	}

	@RequestMapping(value = "imprimir", method = RequestMethod.GET)
	public String generarPDF(Model model)
	/*,
			@RequestParam("suc_cod") String suc_cod,
			@RequestParam("tc_codigo") String tc_codigo,
			@RequestParam("fa_codigo") String fa_codigo)*/ {

		String suc_cod = "1";
		String tc_codigo = "1";
		String fa_codigo = "13541";
		
		
		try {

			comprobantesDao.findComprobantes();
			Comprobante comprobante = comprobantesDao.findComprobante(
					Integer.parseInt(tc_codigo), Integer.parseInt(suc_cod),
					Integer.parseInt(fa_codigo));

			if (comprobante != null) {
				model.addAttribute("comprobante", comprobante);
			}

			System.out.println("Welcome to PDF!");
			System.out.println(comprobante.getFa_cliente());
			return "comprobantePdf";

		} catch (Exception e) {

			e.getMessage();
			return "comprobante";

		}
	}
	
	@RequestMapping
	public void comprobanteList(){
		
		List<Comprobante> comprobantes = java.util.Collections.emptyList();
		//model.addAttribute("comprobantes", comprobantes);

		}

}
