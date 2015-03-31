package com.czx.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XmlParser {
	public static Document xmlToText(String file) {
		Document document = new Document();
		File f = new File(file);
		String contents = null;
		try {
			SAXBuilder builder = new SAXBuilder();
			org.jdom.Document document2 = builder.build(f);
			Element conElement = document2.getRootElement();
			contents = conElement.getText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] strings = file.split("/");
		Field title = new Field("filename", strings[strings.length - 1], Store.YES, Index.ANALYZED);
		Field content = new Field("content", contents, Store.YES, Index.ANALYZED);
		document.add(title);
		document.add(content);
		return document;
	}
}
