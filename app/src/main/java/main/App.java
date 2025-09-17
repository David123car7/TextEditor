package main;
import filesManagment.Files;
import dataStructures.GapBuffer;

public class App {
	public static void main(String[] args) {
		GapBuffer gapBuffer = new GapBuffer(1000, 4);
		gapBuffer.SetGapBuffer(Files.ReadFile("text.txt", 1000));
		gapBuffer.Grow(0);
		gapBuffer.InsertChar('X', 4);
		gapBuffer.InsertChar('X', 0);














		gapBuffer.ShowGapBufferTerminal(true);
		gapBuffer.ShowGapInfoTerminal();
	}
}
