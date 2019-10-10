package nz.ac.massey.cs.assignment_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
	public static void save(String path,String text) throws IOException {
	FileWriter fileWriter = new FileWriter(path);
	
	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	bufferedWriter.write(text);

	bufferedWriter.close();
	fileWriter.close();	
}
}
