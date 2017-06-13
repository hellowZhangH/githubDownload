import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

/**
 * POI 读取 word 2003 和 word 2007 中文字内容的测试类<br />
 * @createDate 2009-07-25
 * @author Carl He
 */
public class Test {
	public static void main(String[] args) {
		try {
			////word 2003： 图片不会被读取
			InputStream is = new FileInputStream(new File("files\\2003.doc"));
			WordExtractor ex = new WordExtractor(is);//is是WORD文件的InputStream 
			String text2003 = ex.getText().trim().replace("\n", "").replace(" ", "").replace("\t", "").replace("\r", "").replace("\\s*","");
			System.out.println(text2003);

			//word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
			OPCPackage opcPackage = POIXMLDocument.openPackage("files\\2007.docx");
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText().trim().replace("\n", "").replace(" ", "").replace("\t", "").replace("\r", "").replace("\\s*","");
			System.out.println(text2007);
			
			//word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
			OPCPackage opcPackage1 = POIXMLDocument.openPackage("files\\test.docx");
			POIXMLTextExtractor extractor1 = new XWPFWordExtractor(opcPackage1);
			String text20071 = extractor1.getText().trim().replace("\n", "").replace(" ", "").replace("\t", "").replace("\r", "").replace("\\s*","");
			System.out.println(text20071);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}