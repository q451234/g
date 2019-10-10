package nz.ac.massey.cs.assignment_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Open {
	public static String open(String address) throws IOException {
		FileReader fileReader = new FileReader(address);
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String str = "";
		String strAll = "";	
		while((str = bufferedReader.readLine()) != null)
		{
			strAll += str + "\r\n";
		}		
		return strAll.substring(0,strAll.length()-2);	
}
}
