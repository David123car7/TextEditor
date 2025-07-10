import DataStructures.GapBuffer;

public class Main {
	public static void main(String[] args) {
		GapBuffer gapBuffer = new GapBuffer(1000);
		gapBuffer.LoadFromFile("/home/david123car7/Documents/JavaProjects/TextEditor/src/");
		gapBuffer.InsertChar(' ', 2);





		//gapBuffer.MoveCursor(3);


		//Prints the gap buffer
	    for(int i = 0; i< gapBuffer.gapBuffer.length; i++) {
	    	if(gapBuffer.gapBuffer[i] != '_')
	    		System.out.print(gapBuffer.gapBuffer[i]);
	    }
	}
}
