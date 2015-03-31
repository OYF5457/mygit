package com.czx.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfParser {	
	
	public static Document pdfToText(String file) {
		Document document = new Document();
		PDFTextStripper stripper = null;
		String contents = null;
		
		try {
			PDDocument pdfDocument = PDDocument.load(file);
			stripper = new PDFTextStripper();
			contents = stripper.getText(pdfDocument);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("打开PDF文件异常");
		}
		String[] strings = file.split("/");
		Field title = new Field("filename", strings[strings.length - 1], Store.YES, Index.ANALYZED);
		Field content = new Field("content", contents, Store.YES, Index.ANALYZED);
		document.add(title);
		document.add(content);
		return document;
	}

}
