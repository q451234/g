package nz.ac.massey.cs.assignment_1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * Hello world!
 *
 */
public class Text_Editor{
	static JTextField content= new JTextField();
	static JTextArea information = new JTextArea();
	public static void main(final String[] args) {
		JFrame frame = new JFrame();
		JPanel toolbar = new JPanel(); 
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton New=new JButton("New");
		JButton Open=new JButton("Open");
		JButton Save=new JButton("Save");
		JButton Search=new JButton("Search");
		JButton Exit=new JButton("Exit");
		JButton About=new JButton("About");
		JButton Print=new JButton("Print");
		JButton Cut=new JButton("Cut");
		JButton Copy=new JButton("Copy");
		JButton Paste=new JButton("Paste");
		JButton Pdf=new JButton("Pdf");

		
		final JTextArea information = new JTextArea();
		toolbar.add(New);
		toolbar.add(Open); 	
		toolbar.add(Save); 
		toolbar.add(Search);
		toolbar.add(Exit);
		toolbar.add(About);
		toolbar.add(Print);
		toolbar.add(Copy);
		toolbar.add(Cut);
		toolbar.add(Paste);
		toolbar.add(Pdf);

		
		
		frame.getContentPane().add(toolbar,BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);

        information.setBounds(0, 5, 900, 600);

        panel.add(information);
        
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        frame.add(panel);    
     	frame.setVisible(true);	
		frame.setTitle("Text_Editor  "+timeDateFormat.format(new Date()));
		frame.setLocation(100, 100); 
		frame.setSize(900,600);
				
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Exit.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		Save.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser choose = new JFileChooser();
				choose.setSelectedFile(new File(".txt"));
				choose.setDialogTitle("Save as");
				choose.showSaveDialog(null);
				choose.setVisible(true);
				try {
					FileWriter fileWriter = new FileWriter(choose.getSelectedFile().getAbsolutePath());

					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					bufferedWriter.write(information.getText());

					bufferedWriter.close();
					fileWriter.close();
				} catch (Exception e2) {
					e2.printStackTrace();			
				}
				
			}
		});
		
		Open.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser choose= new JFileChooser();
				choose.setDialogTitle("choose");
				choose.showOpenDialog(null);
				choose.setVisible(true);
				
				String address = choose.getSelectedFile().getAbsolutePath();
				
				String fileTyle=address.substring(address.lastIndexOf("."),address.length());
				
				if(fileTyle.compareToIgnoreCase(".odt")==0) {				
					File txtFile = new File("view.txt");
					OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
					try {
						connection.connect();
					} catch (ConnectException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(choose.getSelectedFile(), txtFile);					
					connection.disconnect();
					
					try {
						Desktop.getDesktop().open(txtFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				else {	
					try {
						Desktop.getDesktop().open(choose.getSelectedFile());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});


		New.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Text_Editor aEditor=new Text_Editor();
				aEditor.main(args);
				// TODO Auto-generated method stub
			}
		});
		
		Search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame frame = new JFrame("file name");
				frame.setVisible(true);
				frame.setLocation(200, 200); 
				frame.setSize(300,100);	
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JLabel label = new JLabel("content");	
				content.setBounds(90, 20, 80, 30);
				frame.add(content);
				frame.add(label);
				content.addActionListener(new ActionListener() {				
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String keyword=content.getText();
						String textInText = information.getText();
						int index = textInText.indexOf(keyword);
						if (index != -1) {							
							information.getHighlighter().removeAllHighlights();
							while (index != -1 & !keyword.equals("") ) {
								try {
									information.getHighlighter().addHighlight(index, index + keyword.length(),DefaultHighlighter.DefaultPainter);
									index = textInText.indexOf(keyword, index + keyword.length());
								} catch (BadLocationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Can't find keyword:" + keyword);
						}
					}
				});	
			}
		});
		
		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component jPanel = null;
				JOptionPane.showMessageDialog(jPanel, "menber1 WenShi   \n member2 Zifan Wang", "About",JOptionPane.WARNING_MESSAGE);
			}
		});
		
		Cut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				information.cut();
			}
		});

		Copy.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				information.copy();
			}
		});
		
		Paste.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				information.paste();
			}
		});
		
		Print.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try
				{  

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

		               Doc doc = new SimpleDoc(information.getText().getBytes(), flavor, das);

		                job.print(doc, pras); 
		             }
		         }catch(Exception e1)
		          {
		        	 JOptionPane.showInputDialog(this,"fail");
		          }				
			}
		});

		
		Pdf.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				choose.setSelectedFile(new File(".pdf"));

				choose.setDialogTitle("Save as");
				choose.showSaveDialog(null);
				choose.setVisible(true);

				Document document = new Document();
		        OutputStream os;
				try {
					
					os = new FileOutputStream(choose.getSelectedFile().getAbsolutePath());
					PdfWriter.getInstance(document, os);
				} catch (FileNotFoundException e2) {

					e2.printStackTrace();
				} catch (DocumentException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
		        document.open();		        
				try {
									
					FileWriter fileWriter = new FileWriter(new File("convert.txt"));

					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(information.getText());
					bufferedWriter.close();
					fileWriter.close();
					
			        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File("convert.txt")), "GBK");

					BufferedReader bufferedReader = new BufferedReader(isr);
			        String str = "";
			        while ((str = bufferedReader.readLine())!= null) {
			            try {
			            	
							document.add(new Paragraph(str));
						} catch (DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }
			        bufferedReader.close();
			        
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					 // TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        document.close();
		        File ggFile=new File("convert.txt");
		        ggFile.delete();
		        
			}
		});	
	}
}
