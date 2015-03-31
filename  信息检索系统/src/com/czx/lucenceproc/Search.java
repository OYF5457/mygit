package com.czx.lucenceproc;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Search {
	public static void search(String keyWord, String indexPath) {
		try {
			File indexFile = new File(indexPath);
			Directory mDirectory = FSDirectory.open(indexFile);
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
			IndexReader reader = IndexReader.open(mDirectory);
			IndexSearcher searcher = new IndexSearcher(reader);
			String[] fields = {"filename", "content"};
			QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_CURRENT, fields, analyzer);
			Query query = queryParser.parse(keyWord);
			ScoreDoc[] docs = searcher.search(query, null, 100).scoreDocs;
			for (int i = 0;docs != null && i < docs.length; i++) {
				Document document = searcher.doc(docs[i].doc);
				String filename = document.get("filename");
				System.out.println(filename);
				
			}
			reader.close();
			searcher.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String srcString = "/Users/chenzhixiao/Desktop/docs/";
		String deString = "/Users/chenzhixiao/Desktop/index";
		CreateIndex.createIndex(srcString, deString);
		Search.search("bupt", deString);
		
	}

}
