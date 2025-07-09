package DataStructures;
import Files.Files;

public class GapBuffer {
	public char[] gapBuffer; //public for now just to test
	private int gapLeft = 0;
	public int gapRight = 0; //public for now just to test
	private int gapSize = 4;
	
	/**
	 * Constructs a GapBuffer with a specified maximum character capacity.
	 *
	 * @param maxChars The total capacity of the gap buffer.
	 */
	public GapBuffer(int maxChars) {
		gapBuffer = new char[maxChars];
	}
		
	/**
	 * Expands the gap buffer by creating a larger buffer with additional gap space
	 * at the specified cursor position. The content from the old buffer is copied 
	 * into the new buffer, preserving text before and after the gap. The gap itself 
	 * is filled with placeholder characters (e.g., '_' or '\0').
	 * 
	 * @param position The position at which to insert the new gap (the current cursor).
	 */
	public void Grow(int position) {
		char[] newGapBuffer = new char[gapBuffer.length + gapSize];
		
		gapLeft = position;
		gapRight = gapLeft + gapSize;

		
		System.arraycopy(gapBuffer, 0, newGapBuffer, 0, position); //copies the chars left of the cursor from the buffer
		
		for(int i = 0; i < gapSize; i++) { //adds the gap
			newGapBuffer[position+i] = '_'; //maybe add '\0' and not _
		}
		
		System.arraycopy(gapBuffer, position, newGapBuffer, gapRight, gapBuffer.length - position); //copies the chars left of the cursor from the buffer
		
		gapBuffer = newGapBuffer;
	}
	
	/**
	 * Moves the gap to the left toward the specified position.
	 *
	 * @param position The target position to move the gap.
	 */
	public void MoveGapleft(int position) {
		while(position < gapLeft) {
			char x = gapBuffer[gapLeft-1];
			gapBuffer[gapLeft-1] = gapBuffer[gapRight-1];
			gapBuffer[gapRight-1] = x;
			gapLeft--;
			gapRight--;
		}
	}
	
	/**
	 * Moves the gap to the right toward the specified position.
	 *
	 * @param position The target position to move the gap.
	 */
	public void MoveGapRight(int position) {
		while(position > gapRight) {
			char x = gapBuffer[gapRight];
			gapBuffer[gapRight] = gapBuffer[gapLeft];
			gapBuffer[gapLeft] = x;
			gapLeft++;
			gapRight++;
		}
	}
	
	/**
	 * Loads the contents of the given file into the gap buffer.
	 *
	 * @param filename The path to the file to load.
	 */
	public void LoadFromFile(String filename) {
		gapBuffer = Files.ReadFile("/home/david123car7/Documents/JavaProjects/TextEditor/src/kazzio.txt", gapBuffer.length);
	}
}