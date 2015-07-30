package minesweeper.core;

/**
 * Mine tile.
 */
public class Mine extends Tile {

	@Override
	public String toString() {
		String MineStr = "";
		
		if(getState() == Tile.State.OPEN)
			MineStr = "X";
		else
			return super.toString() ;
		return MineStr;
	}
	
}
