package main;

import filesManagment.Files;
import dataStructures.GapBuffer;

import org.jline.terminal.*;

public class App {
    public static void main(String[] args) {

        GapBuffer gapBuffer = new GapBuffer(1000, 10);
        gapBuffer.SetGapBuffer(Files.ReadFile(args[0], 1000));

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

            terminal.writer().println("Text editor made by CrZ. Press 'CTRL+Q' to quit.");
            gapBuffer.ShowGapBufferTerminal(terminal);
            terminal.writer().flush();

            while (true) {
                int ch = reader.read(); // blocking read of one character
                char c = (char) ch;
                
                gapBuffer.HandleGrow();


                if (ch == -1) break; // EOF
                else if (ch == 17) { //CTRL+Q
                    terminal.writer().println("Exiting Text Editor");
                    terminal.flush();
                    break;
                }
                else if(ch == 8) { //CTRL+H
                	terminal.writer().println("Text Editor Short-Keys");
                	terminal.writer().println("- Close Text Editor --> CTRL+Q");
                	terminal.writer().println("- Save File --> CTRL+S");
                    terminal.flush();
                }
                else if (ch == 27) { // ESC
                    int next1 = reader.read();
                    if (next1 == 91) { // '['
                        int next2 = reader.read();
                        if (next2 == 68) { // left
                            gapBuffer.MoveGapLeft();
                        } else if (next2 == 67) { // right
                            gapBuffer.MoveGapRight();
                        }
                    }
                } 
                else if (ch == 127 || ch == 8) { // Backspace
                    gapBuffer.DeleteChar();
                }
                else if (!Character.isISOControl(c)) {
                    gapBuffer.InsertChar(c);
                }
                
                gapBuffer.HandleGrow();

                terminal.writer().print("\033[H\033[2J"); // clear screen
                terminal.writer().println("Text editor made by CrZ. Press 'q' to quit.");
                terminal.writer().flush();
                gapBuffer.ShowGapBufferTerminal(terminal);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
