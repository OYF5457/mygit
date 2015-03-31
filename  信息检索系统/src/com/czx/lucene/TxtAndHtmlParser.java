package com.czx.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class TxtAndHtmlParser {
	public static Document txtAndHtmlToText(String file) {
		Document document = new Document();
		File f = new File(file);
		String contents = "";
		String line  = "";
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(f)));
			while((line = br.readLine())!=null){
				sb.append(line);
			}
			contents = sb.toString();
			br.close();
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
