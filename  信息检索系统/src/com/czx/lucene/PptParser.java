package com.czx.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PptParser {
	public static Document pptToText(String file) {
		Document document = new Document();
		File f = new File(file);
		String contents = null;
		StringBuilder sb;
		try {
			FileInputStream fis = new FileInputStream(f);
			SlideShow ss = new SlideShow(new HSLFSlideShow(fis));
			Slide[] slides = ss.getSlides();
			sb = new StringBuilder();
			for (int i = 0; i < slides.length; i++) {
				TextRun[] tr = slides[i].getTextRuns();
				for (int j = 0; j < tr.length; j++) {
					sb.append(tr[j].getText() + "\n");
				}
				sb.append(slides[i].getTitle() + "\n");
			}
			contents = sb.toString();
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
