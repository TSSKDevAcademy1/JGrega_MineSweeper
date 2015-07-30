package minesweeper.core;

import javafx.scene.control.TitledPane;

/**
 * Clue tile.
 */
public class Clue  extends Tile {
    /** Value of the clue. */
    private final int value;
      

	/**
     * Constructor.
     * @param value  value of the clue
     */
    public Clue(int value) {
        this.value = value;
    }

	public int getValue() {
		return value;
	}
    
    @Override
	public String toString() {
		String MineStr = "";
		if(getState() == Tile.State.OPEN)
		{
			MineStr =  getValue() + "";
			return MineStr;
		}
		else{
			return super.toString();
		}
		
	}
    
}
