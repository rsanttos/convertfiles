import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;


public class Main {

	public static void main(String[] args) throws BadElementException, MalformedURLException, DocumentException, IOException {
		String caminhoArquivoIn = "/home/ramonsantos/dev/residenciatjrn/arquivos/bacen.png";
		String caminhoArquivoOut = "/home/ramonsantos/dev/residenciatjrn/arquivos/bacen.pdf";
		File dir = new File("/home/ramonsantos/dev/residenciatjrn/arquivos/teste/");
		int i = 1;
		for(File f : dir.listFiles()) {
			String outFile = String.format("/home/ramonsantos/dev/residenciatjrn/arquivos/teste/%d.pdf", i);
			generatePDFFromImage(f.getAbsolutePath(), outFile);
			i++;
		}
	}
	
	private static void generatePDFFromImage(String inputFile, String outputFile) throws BadElementException, MalformedURLException, DocumentException, IOException {
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
	    //img.setAbsolutePosition(0, 0);
	    img.scaleToFit(PageSize.A4);
	    document.add(img);
	    document.close();
	    writer.close();
	}
}
