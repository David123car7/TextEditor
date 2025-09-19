package main;

import filesManagment.Files;
import dataStructures.GapBuffer;

import org.jline.terminal.*;

public class App {
    public static void main(String[] args) {

        GapBuffer gapBuffer = new GapBuffer(1000, 4);
        gapBuffer.SetGapBuffer(Files.ReadFile("/home/david123car7/Documents/JavaProjects/TextEditor/app/text.txt", 1000));

        try {
        	Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();

            // Switch to raw mode 
            terminal.enterRawMode();
            
            // Reader for single characters
            java.io.Reader reader = terminal.reader();

            //terminal.writer().print("\033[H\033[2J"); // clear screen
            //terminal.writer().flush();

            terminal.writer().println("Text editor made by CrZ. Press 'q' to quit.");
            terminal.writer().flush();

            while (true) {
                int ch = reader.read(); // blocking read of one character
                char c = (char) ch;

                if (ch == -1) break; // EOF
                else if (ch == 'q') {
                    terminal.writer().println("Exiting raw mode.");
                    terminal.flush();
                    break;
                } 
                else if (ch == 27) { // ESC
                    int next1 = reader.read();
                    if (next1 == 91) { // '['
                        int next2 = reader.read();
                        if (next2 == 68) { // left
                            gapBuffer.MoveGapLeft();
                        	gapBuffer.ShowGapInfoTerminal(terminal);

                        } else if (next2 == 67) { // right
                            gapBuffer.MoveGapRight();
                        	gapBuffer.ShowGapInfoTerminal(terminal);

                        }
                    }
                } 
                else if (!Character.isISOControl(c)) {
                    gapBuffer.InsertChar(c);
                }

                terminal.writer().print("\033[H\033[2J"); // clear screen
                terminal.writer().flush();
                gapBuffer.ShowGapBufferTerminal(terminal);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
