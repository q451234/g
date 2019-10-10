package nz.ac.massey.cs.assignment_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.print.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import com.itextpdf.text.DocumentException;


public class TextEditor{

	static JTextField content= new JTextField();
	static JTextArea information = new JTextArea();
	public static void main(final String[] args){
		JFrame frame = new JFrame();
		JPanel toolbar = new JPanel(); 
		toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		final JButton news=new JButton("New");
		final JButton open=new JButton("Open");
		final JButton save=new JButton("Save");
		final JButton search=new JButton("Search");
		final JButton exit=new JButton("Exit");
		final JButton about=new JButton("About");
		final JButton print=new JButton("Print");
		final JButton cut=new JButton("Cut");
		final JButton copy=new JButton("Copy");
		final JButton paste=new JButton("Paste");
		final JButton pdf=new JButton("Pdf");		
		final JTextArea information = new JTextArea();
		toolbar.add(news);
		toolbar.add(open); 	
		toolbar.add(save); 
		toolbar.add(search);
		toolbar.add(exit);
		toolbar.add(about);
		toolbar.add(print);
		toolbar.add(copy);
		toolbar.add(cut);
		toolbar.add(paste);
		toolbar.add(pdf);
		


		
		frame.getContentPane().add(toolbar,BorderLayout.NORTH);		
	

		JScrollPane scrollPane = new JScrollPane(information);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        information.setBounds(0, 30, 900, 600);
        information.setLineWrap(true);
    	JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		p.add(information,BorderLayout.CENTER);
		
		frame.getContentPane().add(p);
        JScrollPane scroll = new JScrollPane(information);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		p.add(scroll,BorderLayout.CENTER);
		frame.add(p);
				
				
        SimpleDateFormat timeDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
  
        
       
        
     	frame.setVisible(true);	
		frame.setTitle("Text_Editor  "+timeDateFormat.format(new Date()));
		frame.setLocation(100, 100); 
		frame.setSize(900,600);				
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		exit.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		open.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose= new JFileChooser();
				choose.setDialogTitle("choose");
				choose.showOpenDialog(null);
				choose.setVisible(true);
				
				String address = choose.getSelectedFile().getAbsolutePath();				
				String fileTyle=address.substring(address.lastIndexOf("."),address.length());
				File txtFile = new File("view.txt");
				if(fileTyle.compareToIgnoreCase(".odt")==0) {				
					try {
						Openodt.openodt(choose, txtFile);
					} catch (ConnectException e1) {
						e1.printStackTrace();
					}
					
					try {
						String contentString=Open.open(txtFile.getAbsolutePath());
						information.setText(contentString);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				else {	
					try {
						String contentString=Open.open(address);
						information.setText(contentString);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		save.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				choose.setSelectedFile(new File(".txt"));
				choose.setDialogTitle("Save as");
				choose.showSaveDialog(null);
				choose.setVisible(true);
							
				try {
//					Save save=new Save();
					Save.save(choose.getSelectedFile().getAbsolutePath(),information.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}			
			}
		});
		
		
		news.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				TextEditor aEditor=new TextEditor();
				aEditor.main(args);
			}
		});
		
		
		search.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
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
						String keyword=content.getText();
						String textInText = information.getText();
						int index = textInText.indexOf(keyword);					
						if (index != -1) {
							information.getHighlighter().removeAllHighlights();							
							try {
								ArrayList<ArrayList<Integer>> indexList= new ArrayList<ArrayList<Integer>>();
								indexList=Search.search(textInText,keyword);
								int i=0;
								while (i<indexList.size()) {
									information.getHighlighter().addHighlight(indexList.get(i).get(0), indexList.get(i).get(1),DefaultHighlighter.DefaultPainter);
									i+=1;
								}
							} catch (BadLocationException e1) {
								
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Can't find keyword:" + keyword);
						}
					}
				});	
			}
		});
		
		print.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				try {
					Print.print(information);
				} catch (PrintException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component jPanel = null;
				JOptionPane.showMessageDialog(jPanel, "menber1 WenShi   \n member2 Zifan Wang", "About",JOptionPane.WARNING_MESSAGE);
			}
		});

		
		cut.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				information.cut();
			}
		});

		
		copy.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				information.copy();
			}
		});

		
		paste.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				information.paste();
			}
		});
		
		
		pdf.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				choose.setSelectedFile(new File(".pdf"));
				choose.setDialogTitle("Save as");
				choose.showSaveDialog(null);
				choose.setVisible(true);

				try {
					Pdf.pdf(information, choose);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}		        
			}
		});		
		
	}
	private static Container getContentPane() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

