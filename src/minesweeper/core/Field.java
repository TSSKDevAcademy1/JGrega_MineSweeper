package minesweeper.core;
import java.util.*;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	String alphabet = " ABCDEFGHIJKLMNOPRSTQXYZ";
	String numbers = "0123456789";
    /**
     * Playing field tiles.
     */
    private final Tile[][] tiles;

    /**
     * Field row count. Rows are indexed from 0 to (rowCount - 1).
     */
    private final int rowCount;

    /**
     * Column count. Columns are indexed from 0 to (columnCount - 1).
     */
    private final int columnCount;

    /**
     * Mine count.
     */
    private final int mineCount;

    /**
     * Game state.
     */
    private GameState state = GameState.PLAYING;

    /**
     * Constructor.
     *
     * @param rowCount    row count
     * @param columnCount column count
     * @param mineCount   mine count
     */
    public Field(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
        tiles = new Tile[rowCount][columnCount];

        //generate the field content
        generate();
    }

    /**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
        Tile tile = tiles[row][column];
        if (tile.getState() == Tile.State.CLOSED) {
            tile.setState(Tile.State.OPEN);
            if (tile instanceof Mine) {
                setState(GameState.FAILED);
                return;
            }

            if (isSolved()) {
                setState(GameState.SOLVED);
                return;
            }
        }
    }

    /**
     * Marks tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void markTile(int row, int column) {
    	Tile tile = tiles[row][column];
    	for(int r = 0; r < rowCount; r++){
    		for(int c = 0; c < columnCount; c++){
    			if(tile.getState() == Tile.State.CLOSED){
    				tile.setState(Tile.State.MARKED);
    			}
    			else if(tile.getState() == Tile.State.MARKED){
    				tile.setState(Tile.State.CLOSED);
    			}
    		}
    	}
    }

    /**
     * Generates playing field.
     */
    // Nahodne vygeneruje riadok/stlpec a ak tam nieje Mina tak ju tam ulozi
    private void generate() {
    	//int mineCount = 0;
    	Random randomize = new Random();
    	    		
    	for(int i = 0; i < mineCount; i++){
	    	int r = randomize.nextInt(rowCount);
	    	int c = randomize.nextInt(columnCount);
	    	
	    	if(tiles[r][c] == null){
	    		tiles[r][c] = new Mine();
	    	}
	    	else{
	    		i--;
	    	}
    	}
    	for(int r = 0; r < rowCount; r++){
    		for(int c = 0; c < columnCount; c++){
    			if(tiles[r][c] == null){
    				tiles[r][c] = new Clue(countAdjacentMines(r,c));
    			}
    		}
    	}
    	
    	
    	
    	
    
    }

    /**
     * Returns true if game is solved, false otherwise.
     *
     * @return true if game is solved, false otherwise
     */
    private boolean isSolved() {
        throw new UnsupportedOperationException("Method isSolved not yet implemented");
    }

    /**
     * Returns number of adjacent mines for a tile at specified position in the field.
     *
     * @param row    row number.
     * @param column column number.
     * @return number of adjacent mines.
     */
    private int countAdjacentMines(int row, int column) {
        int count = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            int actRow = row + rowOffset;
            if (actRow >= 0 && actRow < getRowCount()) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    int actColumn = column + columnOffset;
                    if (actColumn >= 0 && actColumn < getColumnCount()) {
                        if (tiles[actRow][actColumn] instanceof Mine) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

	private int getRowCount() {
		return rowCount;
	}

	private int getColumnCount() {
		return columnCount;
	}

	private int getMineCount() {
		return mineCount;
	}

	private GameState getState() {
		return state;
	}

	private void setState(GameState state) {
		this.state = state;
	}

	private Tile getTiles(int row, int column) {
		return tiles[row][column];
	}

	@Override
	public String toString() { // algoritmus vykreslenia
		String sweeper = "";
		int rows = rowCount + 1;
		int columns = columnCount + 1;
		for(int r = 0; r < rows; r++){				
			for(int c = 0; c < columns; c++){
				if(r == 0){ 
					if(c == 0)
						sweeper += "  " + Character.toString(numbers.charAt(c));
					else if (c < columns - 1)
					sweeper += " " + Character.toString(numbers.charAt(c ));
				}else{
					if(c == 0)
						sweeper += Character.toString(alphabet.charAt(r));
					else
						sweeper += " " + tiles[r-1][c-1] ;
				}
			}
			sweeper += "\n";
		}
		return sweeper;
	}	
	
}
