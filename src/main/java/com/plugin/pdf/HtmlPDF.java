package com.plugin.pdf;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Component("htmlPDF")
public class HtmlPDF {

	/*
	 * 1cm = 28.346px;
	 */
	public static final float cm2px = 28.346f;

	public float urx = 21f*cm2px;
	public float ury = 29.7f*cm2px;



	/**
	 * 
	 * @param htmlString
	 * @param os
	 */
	public void pdfOutputStream(String htmlString, OutputStream os) {
		
		
		Document document = new Document();
		Rectangle rectangle = new Rectangle(urx, ury);
		document.setPageSize(rectangle);
		document.setMargins(20, 20, 20, 20);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, os);
			document.open();
			XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
			xmlWorkerHelper.parseXHtml(writer, document, new ByteArrayInputStream(htmlString.getBytes()), StandardCharsets.UTF_8, new AsianFontProvider());
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

}
