package TextEditor;
import Data.GapBuffer;

public class Main {
	public static void main(String[] args) {
		String fileName = args[0];
		GapBuffer gapBuffer = new GapBuffer(1000);
		gapBuffer.LoadFromFile(fileName);

		//Prints the gap buffer
	    for(int i = 0; i< gapBuffer.gapBuffer.length; i++) {
	    	if(gapBuffer.gapBuffer[i] != '_')
	    		System.out.print(gapBuffer.gapBuffer[i]);
	    }
	}
}


