package com.tractoagro.fe.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class PdfComprobante extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Table table = new Table(1);
		
		table.addCell("Ejemplo");
		
		ByteArrayOutputStream baos= new ByteArrayOutputStream();
		PrintWriter printWriter = new PrintWriter(baos);
		PdfReader pdfReader = new PdfReader("c:/certs/private/modelo.pdf");
		System.out.println("Nro de pag: " + pdfReader.getNumberOfPages());
		AcroFields formulario = pdfReader.getAcroFields();
		Set<String> fields = formulario.getFields().keySet();
		
		System.out.println("Antes del for");
		
		for (String key : fields) {
			System.out.println("Dentro del for");
			printWriter.print(key + ": ");
			switch (formulario.getFieldType(key)) {
			case AcroFields.FIELD_TYPE_CHECKBOX:
				printWriter.println("Checkbox");
				
			break;
			case AcroFields.FIELD_TYPE_COMBO:
				printWriter.println("Combobox");
			break;
			case AcroFields.FIELD_TYPE_LIST:
				printWriter.println("List");
			break;
			case AcroFields.FIELD_TYPE_NONE:
				printWriter.println("None");
			break;
			case AcroFields.FIELD_TYPE_PUSHBUTTON:
				printWriter.println("Pushbutton");
			break;
			case AcroFields.FIELD_TYPE_RADIOBUTTON:
				printWriter.println("Radiobutton");
			break;
			case AcroFields.FIELD_TYPE_SIGNATURE:
				printWriter.println("Signature");
			break;
			case AcroFields.FIELD_TYPE_TEXT:
				printWriter.println("Text");
				System.out.println("texto");
			break;
			default:
				printWriter.println("?");
			}
		}	
		
		document.add(table);
		
	}

}
