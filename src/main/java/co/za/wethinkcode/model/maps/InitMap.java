package co.za.wethinkcode.model.maps;

import java.util.Random;

public class InitMap {
    public int rows;
    public int columns;
    private String [][] wholeMap;
    private int level;
    public boolean entry;
    public boolean metEnemy;
    private PlayerPosition playerPosition;

    public InitMap(int level) {
        this.entry = true;
        this.level = level;
        this.rows = (level-1)*5+10-(level%2);
        this.columns = this.rows;
        this.createMap();
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String[][] getWholeMap() {
        return wholeMap;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    protected void createMap(){
        wholeMap = new String[this.rows][this.columns];

        for (int r = 0; r < this.rows; r++){
            for (int c = 0; c < this.columns; c++)
                wholeMap[r][c] = ".";
        }
    }

    public void updatePlayerPosition(int row, int column){
        for(int r = 0; r < this.getRows(); r++)
        {
            for(int c = 0; c < this.getColumns(); c++)
            {
                if(wholeMap[r][c].equals("H"))
                    wholeMap[r][c] = ".";
                if((r == row) && (c == column))
                {
                    if (wholeMap[r][c].equals("E"))
                        this.metEnemy = true;
                    wholeMap[r][c] = "H";
                }
            }
        }

    }

    public void updatePlayerPosition(PlayerPosition position){
        int row = position.getPlayerRow();
        int column = position.getPlayerColumn();

        for(int r = 0; r < this.getRows(); r++)
        {
            for(int c = 0; c < this.getColumns(); c++)
            {
                if(wholeMap[r][c].equals("H"))
                    wholeMap[r][c] = ".";
                if((r == row) && (c == column))
                {
                    if (wholeMap[r][c].equals("E"))
                        this.metEnemy = true;
                    wholeMap[r][c] = "H";
                }
            }
        }

        this.playerPosition = position;
    }

    public void playerPosition(int row, int column){
        for(int r = 0; r < this.getRows(); r++)
        {
            for(int c = 0; c < this.getColumns(); c++)
            {
                if((r == row) && (c == column))
                {
                    wholeMap[r][c] = "H";
                }
            }
        }
    }

    public void setRandomEnemies(){
        int row;
        int column;
        Random rand = new Random();

        for (int i = 0; i < this.level +2; i++){
            row = rand.nextInt(this.rows-1) + 1;
            column = rand.nextInt(this.columns-1) + 1;
            if (wholeMap[row][column].equals("H"))
                i--;
            else wholeMap[row][column] = "E";
        }
    }

    public void resetEnemy(int row, int col) {
        wholeMap[row][col] = "E";
    }
}