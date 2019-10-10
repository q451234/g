package nz.ac.massey.cs.assignment_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.BadLocationException;

import junit.framework.TestCase;
import junit.framework.TestSuite;


public class Test extends TestCase
	{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public Test( String testName )
	{
	    super( testName );
	}
	
	/**
	 * @return the suite of tests being tested
	 */
	public static TestSuite suite()
	{
	    return new TestSuite( Test.class );
	}
	
	/**
	 * Rigourous Test :-)
	 * @throws IOException 
	 */
	public void testopen() throws IOException
	{
		String information ="This is a test";
		
		File testFile=new File("testopen.file");
		
		FileWriter writer=new FileWriter(testFile);
		
		writer.write(information);
		writer.close();
		
		String getString=Open.open(testFile.getAbsolutePath());
		
		assertEquals(information, getString);
	}
	
	public void testsave() throws IOException {
		String information ="This is a test";
		File testFile=new File("testsave.file");
		
		Save.save(testFile.getAbsolutePath(), information);				
		String getString=Open.open(testFile.getAbsolutePath());
		
		assertEquals(information, getString);
	}
	
	public void testsearch() throws BadLocationException {
		String text = "abcdabce";
		String keyword ="c";
		
		ArrayList<ArrayList<Integer>> indexList= new ArrayList<ArrayList<Integer>>();
		
		indexList=Search.search(text, keyword);
		
		int i=0;
		while (i<indexList.size()) {
			assertEquals(keyword,text.substring(indexList.get(i).get(0),indexList.get(i).get(1)));
			
			i+=1;
		}			
		text=text.replaceAll(keyword,"\n");
		assertEquals(false, text.contains(keyword));
	}
	
}

