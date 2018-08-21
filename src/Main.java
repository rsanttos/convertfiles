import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class Main {

	public static void main(String[] args)
			throws Exception {
		String caminhoArquivoIn = "/home/ramonsantos/dev/residenciatjrn/arquivos/bacen.png";
		String caminhoArquivoOut = "/home/ramonsantos/dev/residenciatjrn/arquivos/bacen.pdf";
		String caminhoArquivoHtml = "/home/ramonsantos/dev/residenciatjrn/arquivos/teste.html";
		String html = getHtmlContent("https://google.com");
		gravaEmArquivo(caminhoArquivoHtml, html);
		generatePDFFromHTML(caminhoArquivoHtml);
	}

	private static void gravaEmArquivo(String caminho, String conteudo) throws IOException {
		FileWriter arq = new FileWriter(caminho);
	    PrintWriter gravarArq = new PrintWriter(arq);
	    gravarArq.println(conteudo);
	    arq.close();
	}
	
	private static void generatePDFFromImage(String inputFile, String outputFile)
			throws BadElementException, MalformedURLException, DocumentException, IOException {
		float left = 0;
		float right = 0;
		float top = 0;
		float bottom = 0;
		Document document = new Document(PageSize.A4, left, right, top, bottom);
		String input = inputFile;
		String output = outputFile;
		FileOutputStream fos = new FileOutputStream(output);
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		writer.open();
		document.open();
		Image img = Image.getInstance(input);
		// img.setAbsolutePosition(0, 0);
		img.scaleToFit(PageSize.A4);
		document.add(img);
		document.close();
		writer.close();
	}

	private static void generatePDFFromHTML(String filename) throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("/home/ramonsantos/dev/residenciatjrn/arquivos/html.pdf"));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(filename));
		document.close();
	}

	private static String getHtmlContent(String theURL) throws Exception, IOException {
		URL url = new URL(theURL);
		InputStream is = url.openStream();
		int ptr = 0;
		StringBuffer buffer = new StringBuffer();
		while ((ptr = is.read()) != -1) {
			buffer.append((char) ptr);
		}
		return buffer.toString();
	}
}
