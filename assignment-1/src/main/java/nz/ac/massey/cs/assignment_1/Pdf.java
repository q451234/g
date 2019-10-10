package nz.ac.massey.cs.assignment_1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf {	
	public static void pdf(JTextArea informationArea, JFileChooser choose) throws FileNotFoundException, DocumentException {
		Document document = new Document();
        String text = informationArea.getText();

		PdfWriter writer = null;
		writer = PdfWriter.getInstance(document, new FileOutputStream(choose.getSelectedFile()));

		document.open();
		
		document.add(new Paragraph(text));


		document.close();

		writer.close();		
	}
}
