package co.za.wethinkcode.view;

import co.za.wethinkcode.model.maps.InitMap;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    static final int originX = 8;
    static final int originY = 8;
    static final int cellSide = 12;
    private static int rows;
    private static int cols;
    private InitMap map;
    private int playerRow;
    private int playerCol;

    public MapPanel(InitMap map, LayoutManager layout) {
        super(layout);
        this.map = map;
        this.rows = map.getRows();
        this.cols = map.getColumns();
    }

    public MapPanel(){}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        this.drawGrid(g2);
        this.drawEnemies(g2);
        this.drawPlayer(g2);
    }

    private void drawEnemies(Graphics2D g2){
        g2.setColor(Color.RED);
        for(int r = 0; r < map.getRows(); r++)
        {
            for(int c = 0; c < map.getColumns(); c++)
            {
                if(map.getWholeMap()[r][c] == "E")
                    g2.fillRoundRect(originX + c * cellSide, originY + r * cellSide, cellSide, cellSide, 30, 30);
            }
        }
    }

    private void drawPlayer(Graphics2D g2){
        playerCol = map.getPlayerPosition().getPlayerColumn();
        playerRow = map.getPlayerPosition().getPlayerRow();

        g2.setColor(new Color(2, 102, 32));
        g2.fillRoundRect(originX + playerCol * cellSide, originY + playerRow * cellSide, cellSide, cellSide, 30, 30);
    }

    private void drawGrid(Graphics2D g2){
//        g2.setColor(Color.WHITE);

        for (int i = 0; i < rows + 1; i++) {
            g2.drawLine(originX, originY + i * cellSide, originX + cols * cellSide, originY + i * cellSide);
        }

        for (int i = 0; i < cols + 1; i++) {
            g2.drawLine(originX + i * cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
        }
    }
}
