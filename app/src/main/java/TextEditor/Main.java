package TextEditor;

import Data.GapBuffer;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter max file size: ");
	    String maxSize = scanner.nextLine();  
	    System.out.println("Enter file name: ");
	    String fileName = scanner.nextLine();  
	    
		int bufferSize = Integer.parseInt(maxSize);
		GapBuffer gapBuffer = new GapBuffer(bufferSize);
		gapBuffer.LoadFromFile(fileName);

		//Prints the gap buffer
	    for(int i = 0; i< gapBuffer.gapBuffer.length; i++) {
	    	if(gapBuffer.gapBuffer[i] != '_')
	    		System.out.print(gapBuffer.gapBuffer[i]);
	    }
	    
        String userInput = scanner.nextLine();
        
        if (userInput == "Exit") {
            return;

        }
	}
}


