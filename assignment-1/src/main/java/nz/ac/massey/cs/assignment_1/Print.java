package nz.ac.massey.cs.assignment_1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JTextArea;

public class Print {
	public static void print(JTextArea informationArea) throws PrintException {
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

        PrintService service = null;    

        service = ServiceUI.printDialog(null, 100, 100, printService, defaultService, flavor, pras);
        if (service!=null)
        {
            DocPrintJob job = service.createPrintJob(); 
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(informationArea.getText().getBytes(), flavor, das);
            job.print(doc, pras); 
        }	
        
	
	}
}
