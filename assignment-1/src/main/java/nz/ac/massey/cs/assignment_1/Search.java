package nz.ac.massey.cs.assignment_1;

import java.util.ArrayList;

import javax.swing.text.BadLocationException;

public class Search {
	public static ArrayList<ArrayList<Integer>> search(String textInText, String keyword) throws BadLocationException {
		int index = textInText.indexOf(keyword);
		ArrayList<ArrayList<Integer>> i_indexList= new ArrayList<ArrayList<Integer>>();
		
		while (index != -1 & !keyword.equals("") ) {
			ArrayList<Integer> j_indexList= new ArrayList<Integer>();
			j_indexList.add(index);			
			j_indexList.add(index + keyword.length());
			i_indexList.add(j_indexList);
			index=textInText.indexOf(keyword, index + keyword.length());
		}
				
		return i_indexList;
	}
}
