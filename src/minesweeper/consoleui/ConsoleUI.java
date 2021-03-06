package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.UserInterface;
import minesweeper.core.Field;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /** Playing field. */
    private Field field;
    
    /** Input reader. */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Reads line of text from the reader.
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.Field)
	 */
    @Override
	public void newGameStarted(Field field) {
        this.field = field;
        do {
            update();
            break;
         //   processInput(); // odstranene aby sa necyklylo
       //     throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while(true);
    }
    
    /* (non-Javadoc)
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
    @Override
	public void update() {
   //     throw new UnsupportedOperationException("Method update not yet implemented");
    	System.out.printf(field.toString()); ///////
    }
    
    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
    //    throw new UnsupportedOperationException("Method processInput not yet implemented");
    }
}
