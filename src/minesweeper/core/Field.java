package minesweeper.core;

import java.util.*;
/**
 * Field represents playing field and game logic.
 */
public class Field {
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
        
      //  System.out.ToString();
    }

    /**
     * Opens tile at specified indeces.
     *
     * @param row    row number
     * @param column column number
     */
    public void openTile(int row, int column) {
        Tile tile = tiles[row][column]; //getTiles()
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
    	// Metoda sluzi na oznacenie/odznacenie dlazdice specifikovanej riadkom a stlpcom
    	Tile tile = tiles[row][column];
    	
    	if(tile.getState() == Tile.State.CLOSED){ // Ak je dlazdica CLOSED potom zmen na MARKED
    			tile.setState(Tile.State.MARKED);
    		}
    	else if(tile.getState() == Tile.State.MARKED){ // Ak je dlazdica MARKED zmen na CLOSED
    			tile.setState(Tile.State.CLOSED);
    		}
    }

    /**
     * Generates playing field.
     */
    // Funkcia aby v hernom poli "tiles" nahodne rozlozila miny,,, pocet min-mineCount
    private void generate() {
    	//int countMine = 0;
    	//Tile numMine = new Tile();
    	
    	Random gen = new Random();
    	for(int i = 0; i < mineCount; i++){
    		int c = gen.nextInt(columnCount); 
    		int r = gen.nextInt(rowCount);
    		
    		if(tiles[r][c] == null){ // ak je na danej pozicii NULL prida Minu
    			tiles[r][c] = new Mine();
    		}
    		else{ // Ak nie dekrementuje premennu "i" a sprava sa ako keby dana iteracia nebola vykonana
    			i--;
    		}    			
    	}
    	for(int r = 0; r < rowCount; r++){ // Ak tam nieje mina prida pocet Min v okoli
    		for(int c = 0; c < columnCount; c++){
    			if(tiles[r][c] == null){
    				//Tile numMine = Tile(countAdjacentMines(r,c));
    				tiles[r][c] = new Clue(countAdjacentMines(r,c));
    			}
    		}
    	}
    }
    
    private Tile Tile(int countAdjacentMines) {
		// TODO Auto-generated method stub
		return null;
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
                        if (tiles[actRow][actColumn] instanceof Mine) { // instanceof - zisti ci je mina-true a false ak nieje
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
	public String toString() {
		String MineStr = "";
		for(int i = 0; i < rowCount; i++){
			for(int j = 0; j < columnCount; j++){
				MineStr += tiles[i][j];
			}
			MineStr += "\n";
		}
		return MineStr;
	}
	
}
