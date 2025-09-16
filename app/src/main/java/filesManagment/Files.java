package filesManagment;

import java.io.File;
import java.io.FileNotFoundException;  
import java.io.IOException; 
import java.io.FileReader;

public class Files {
	/**
	 * Reads the contents of a file into a character array of the specified size.
	 *
	 * @param fileName The full path to the file to read.
	 * @param size The maximum number of characters to read.
	 * @return A character array containing the contents of the file.
	 */
	public static char[] ReadFile(String fileName, int size) {
		char[] charArray = new char[size];
	    try {
			File file = new File(fileName);
			FileReader reader = new FileReader(file);
			int x = 0, y = 0, k = 0;
			while((k = reader.read()) != -1) { //while it did not reach the end of the file
				if(x >= size) {
					char[] newCharArray = new char[charArray.length + size];
					System.arraycopy(charArray, 0, newCharArray, 0, charArray.length); 
					charArray = newCharArray;
				}
				char c = (char) k; 
				charArray[x] = c;
	            x++;
			}
			reader.close();
	    } 
	    catch (FileNotFoundException e) {
	    	System.out.println("An error occurred.");
	    	e.printStackTrace();
	    } catch (IOException e) {
			System.out.println("IO error while reading: " + fileName);
			e.printStackTrace();
	    }
	    return charArray;
	}
}
