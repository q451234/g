package nz.ac.massey.cs.assignment_1;

import java.io.File;
import java.net.ConnectException;

import javax.swing.JFileChooser;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class Openodt {
	public static void openodt(JFileChooser choose, File txtFile) throws ConnectException {
		
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		connection.connect();
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(choose.getSelectedFile(), txtFile);					
		connection.disconnect();		
	}
}
