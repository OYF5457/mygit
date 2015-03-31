package com.czx.lucenceproc;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.czx.lucene.*;


public class CreateIndex {

    public static void delete(File f){
        if(f.isDirectory()){
            File[] files = f.listFiles();
            for(int i=0;i<files.length;i++)
            {
               if( files[i].isDirectory()){
                   delete(files[i]);
               }else{
                   files[i].delete();
               }
            }
            f.delete();
        }
        else f.delete();
    }
    
	public static void createIndex(String filePath, String indexPath) {
		IndexWriter writer;
		File docDir = new File(filePath);
		if (!docDir.exists() || !docDir.canRead()) {
            System.out.println("建立索引出错，文档路径不存在或不可读。");
        }
		File indexDir = new File(indexPath);
		if(indexDir.exists()){
            delete(indexDir);
        }
		try {
            writer = new IndexWriter(FSDirectory.open(indexDir), new StandardAnalyzer(Version.LUCENE_CURRENT),
                                     true, IndexWriter.MaxFieldLength.LIMITED);
            CreateIndex.indexDocs(writer, docDir);
            writer.optimize();
            writer.close();
        } catch (Exception ex) {
            System.out.println("建立索引出错，索引过程出现异常。");
        }
        System.out.println("建立索引完成。");
    }
	
	public static Document getDocument(String srcFile) {
		Document document = new Document();
		if (srcFile.endsWith(".txt")
				|| srcFile.endsWith(".html")) {
			document = TxtAndHtmlParser.txtAndHtmlToText(srcFile);
		} else if (srcFile.endsWith(".xml")) {
			document = XmlParser.xmlToText(srcFile);
		} else if (srcFile.endsWith(".xls")) {
			document = XlsParser.xlsToText(srcFile);
		} else if (srcFile.endsWith(".ppt")) {
			document = PptParser.pptToText(srcFile);
		} else if (srcFile.endsWith(".pdf")) {
			document = PdfParser.pdfToText(srcFile);
		} else if (srcFile.endsWith(".doc")) {
			document = DocParser.docToText(srcFile);
		} else {
			System.out.println("不支持的文件类型");
		}
		return document;
	}
	public static void indexDocs(IndexWriter writer, File file) {
		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocs(writer, new File(file, files[i]));
					}
				}
			} else {
				try {
					Document doc = CreateIndex.getDocument(file.getAbsolutePath());
					if (doc != null) {
						writer.addDocument(doc);
						System.out.println("正在添加文件 " + file +"进索引");
					} else {
						System.out.println("添加文件 "
								+ file + "进索引时出错");
					}
				} catch (IOException ex) {
					;
				}
			}
		}
	}
}
