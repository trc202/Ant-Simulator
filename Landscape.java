import java.awt.Point;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.*;

public class Landscape extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6199879591483921321L;
	private Tile[][] tile;
    private int numRows;
    private int numCols;
    
    public Landscape(int rows, int cols) {
        setLayout(new GridLayout(rows, cols));
        numRows = rows;
        numCols = cols;
        tile = new Tile[rows][cols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                tile[i][j] = new Tile();
                add(tile[i][j]);
            }
        }
    }
    public Tile getTile(int row, int col) {
        return tile[row][col];
    }
    public Tile getTile(Point p)
    {
    	return getTile(p.x,p.y);
    }
    
    private List<Point> getNeighbors(int r, int c) {
        if(r >= 0 && r < numRows && c >= 0 && c < numCols) {
            List<Point> list = new ArrayList<Point>();
            for(int i = -1; i <= 1; i++) {
                for(int j = -1; j <= 1; j++) {
                    if(r+i >= 0 && r+i < numRows &&
                       c+j >= 0 && c+j < numCols) {
                        if(i != 0 || j != 0) {
                            Point p = new Point();
                            p.x = r + i;
                            p.y = c + j;
                            list.add(p);
                        }
                    }
                }
            }
            return list;
        }
        else return null;
    }
    public Point getHighestLocation(int row, int col) {
        List<Point> neighbors = getNeighbors(row, col);
        Collections.shuffle(neighbors);
        Point highest = neighbors.get(0);
        for(Point p : neighbors) {
            if(tile[p.x][p.y].getPheromone() > 
               tile[highest.x][highest.y].getPheromone()) {
                highest = p;
            }
        }
        return highest;
    }
    public Point getHighestLocation(Point p) {
    	return getHighestLocation(p.x,p.y);
    }
    
    public void evaporate() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                tile[i][j].setPheromone(tile[i][j].getPheromone() * 0.95);//Was .95
                if(tile[i][j].getPheromone() < 1) {
                    tile[i][j].setPheromone(0);
                }
            }
        }
    }
    
    public boolean anyFoodLeft() {
        boolean foodLeft = false;
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                if(tile[i][j].getFood() > 0) foodLeft = true;
            }
        }
        return foodLeft;
    }


}  











 