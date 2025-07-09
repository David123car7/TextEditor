import DataStructures.GapBuffer;

public class Main {
	public static void main(String[] args) {
		GapBuffer gapBuffer = new GapBuffer(100);
		gapBuffer.LoadFromFile("/home/david123car7/Documents/JavaProjects/TextEditor/src/");
		gapBuffer.Grow(3);

		//Prints the gap buffer
	    for(int i = 0; i< gapBuffer.gapBuffer.length; i++) {
	    	System.out.print(gapBuffer.gapBuffer[i]);
	    }
	}
}
