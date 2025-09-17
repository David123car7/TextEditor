package dataStructures;

public class GapBuffer {
	public char[] gapBuffer;
	private int gapLeft; //position of the left gap also the cursor
	private int gapRight; //position of the right gap
	private int gapSize;
	private char gapChar;
	
	/**
	 * Constructs a new GapBuffer with a specified total length and  gap size.
	 *
	 * @param length   the total size of the buffer 
	 * @param gapSize  the size of the gap 
	 */
	public GapBuffer(int lenght, int gapSize) {
		gapBuffer = new char[lenght];
		gapLeft = 0;
		gapRight = 0;
		gapChar = '_';
		this.gapSize = gapSize;
	}
	
	/**
	 * Sets the internal gap buffer to the given character array.
	 *
	 * @param array the character array to replace the current gap buffer
	 * @return -1 if the given array is null, 1 if the operation succeeds
	 */
	public int SetGapBuffer(char[] array) {
		if(array == null)
			return -1;
		
		gapBuffer = array;
		return 1;
	}
	
	/**
	 * Grows the gap buffer by inserting an additional gap 
	 * at the specified position. 
	 *
	 * @param position the index in the buffer where the new gap should be inserted
	 * @return -1 if the position is invalid or the buffer is null, 1 if successful
	 */
	public int Grow(int position) {
		if(position < 0 || gapBuffer == null)
			return -1;
		
		char[] newGapBuffer = new char[gapBuffer.length + gapSize];
		System.arraycopy(gapBuffer, 0, newGapBuffer, 0, position);
		
		char[] gap = new char[gapSize];
		for(int i=0; i<gapSize; i++) {
			gap[i] = gapChar;
		}
		System.arraycopy(gap, 0, newGapBuffer, position, gapSize);
		
		System.arraycopy(gapBuffer, position, newGapBuffer, position + gapSize, gapBuffer.length - position);

		
		gapBuffer = newGapBuffer;
		gapLeft = position;
		gapRight = position + gapSize;
		return 1;
	}
	
	/**
	 * Moves the gap one position to the right by swapping the character at the right
	 * boundary into the gap.
	 *
	 * @return -1 if the buffer is not initialized,
	 *          0 if the gap is already at the end of the buffer (cannot move further),
	 *          1 if the gap was successfully moved
	 */
	public int MoveGapRight() {
		if(gapBuffer == null)
			return -1;
		
		if(gapRight == gapBuffer.length)
			return 0;
		
		char aux = gapBuffer[gapRight];
		gapBuffer[gapRight] = gapBuffer[gapLeft];
		gapBuffer[gapLeft] = aux;
		gapLeft++;
		gapRight++;
		
		return 1;
	}
	
	/**
	 * Moves the gap to the right until its left boundary reaches the given position.
	 *
	 * @param position the target index where the left side of the gap should move
	 * @return -1 if the buffer is null or the position is outside the buffer,
	 *          0 if the gap is already at the requested position,
	 *          1 if the gap was moved successfully
	 */
	public int MoveGapRight(int position) {
		if(gapBuffer == null || position > gapBuffer.length)
			return -1;
		
		if(position == gapLeft)
			return 0;
		
		while(gapLeft < position ) {
			char aux = gapBuffer[gapRight];
			gapBuffer[gapRight] = gapBuffer[gapLeft];
			gapBuffer[gapLeft] = aux;
			gapLeft++;
			gapRight++;
		}
		return 1;
	}
	
	/**
	 * Moves the gap one position to the left by swapping characters.
	 *
	 * @return -1 if the buffer is not initialized, 1 if the gap is moved successfully
	 */
	public int MoveGapLeft() {
		if(gapBuffer == null)
			return -1;
		
		if(gapLeft == 0)
			return 0;
		
		char aux = gapBuffer[gapLeft-1];
		gapBuffer[gapLeft-1] = gapBuffer[gapRight-1];
		gapBuffer[gapRight-1] = aux;
		gapLeft--;
		gapRight--;
		
		return 1;
	}
	
	/**
	 * Moves the gap to the left until its left boundary reaches the given position.
	 *
	 * @param position the target index where the left side of the gap should move
	 * @return -1 if the buffer is not initialized,
	 *          0 if the gap is already at the start (cannot move further),
	 *          1 if the gap was moved successfully
	 */
	public int MoveGapLeft(int position) {
		if(gapBuffer == null || position > gapBuffer.length)
			return -1;
		
		if(gapLeft == 0)
			return 0;
		
		while(gapLeft > position) {
			char aux = gapBuffer[gapLeft-1];
			gapBuffer[gapLeft-1] = gapBuffer[gapRight-1];
			gapBuffer[gapRight-1] = aux;
			gapLeft--;
			gapRight--;
		}

		return 1;
	}
	
	/**
	 * Moves the cursor to the given position by shifting the gap.
	 * If the target position is to the right of the gap, the gap is moved right.
	 * If the target position is to the left of the gap, the gap is moved left.
	 *
	 * @param position the target index for the cursor (gap's left boundary)
	 * @return -1 if the position is invalid (negative),
	 *          0 if the cursor is already at the requested position,
	 *          1 if the cursor was successfully moved
	 */
	public int MoveCursor(int position) {
		if(position < 0)
			return -1;
		
		if(position  == gapLeft)
			return 0;
		
		if(position > gapLeft)
			MoveGapRight(position);
		else if(position < gapLeft) {
			MoveGapLeft(position);
		}
		
		return 1;
	}
	
	/**
	 * Inserts a character into the gap buffer at the specified position.
	 *
	 * @param c        the character to insert
	 * @param position the position where the character should be inserted
	 * @return -1 if the position is invalid (negative),
	 *          1 if the character was successfully inserted
	 */
	public int InsertChar(char c, int position) {
		if(position < 0)
			return -1;
		
		if(gapLeft != position)
			MoveCursor(position);
		
		if(gapLeft == gapRight)
			Grow(position); //position + 1???
		
		gapBuffer[gapLeft] = c;
		gapLeft++;
		
		return 1;
	}
	
	/**
	 * Prints the contents of the gap buffer to the terminal.
	 * 
	 * @param showGap if true, prints the entire buffer including the gap
	 *                (represented by the placeholder character, e.g., '_').
	 *                If false, prints only the actual text (ignoring gap chars).
	 */
	public void ShowGapBufferTerminal(boolean showGap) {
		for(int i=0; i<gapBuffer.length;i++) {
			if(showGap)
				System.out.print(gapBuffer[i]);
			else {
				if(gapBuffer[i] != gapChar)
					System.out.print(gapBuffer[i]);
			}
		}
	}
	
	/**
	 * Prints debugging information about the gap, showing the current
	 * positions of gapLeft and gapRight in the buffer.
	 */
	public void ShowGapInfoTerminal() {
		System.out.print(" GapLeft: " + gapLeft);
		System.out.print(" GapRight: " + gapRight);
	}
}
