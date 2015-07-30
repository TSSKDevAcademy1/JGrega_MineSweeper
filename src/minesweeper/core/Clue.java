package minesweeper.core;

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

	private int getValue() {
		return value;
	}

	@Override
	public String toString() {
		if(getState() == Tile.State.OPEN){
			return getValue() + "";
		}
		else{
			return super.toString();
		}
	}
	
	
	
}
