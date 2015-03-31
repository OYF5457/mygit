package com.czx.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocParser {
	public static Document docToText(String file) {
		Document document = new Document();
		File f = new File(file);
		String contents = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			WordExtractor wextExtractor = new WordExtractor(fis);
			contents = wextExtractor.getText();
			fis.close();
		} catch (IOException e) {
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
