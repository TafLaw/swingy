package co.za.wethinkcode.model.maps;

public class PlayerPosition {
    private static int playerRow;
    private static int playerColumn;

    public PlayerPosition(int row, int column) {
        this.playerRow = (row/2);
        this.playerColumn = (column/2);
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

    public int getPlayerColumn() {
        return playerColumn;
    }

    public void setPlayerColumn(int playerColumn) {
        this.playerColumn = playerColumn;
    }
}
