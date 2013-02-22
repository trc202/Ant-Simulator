import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.trc202.Ant.Ant;
import com.trc202.Ant.WorkerAnt;
import com.trc202.Helpers.Helper;

public class Tile extends JPanel implements MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9169987033470770611L;
    private static final Color EMPTY_COLOR = Color.green;
    private static final Color WORKER_COLOR = Color.RED;
    private JLabel foodLabel;
    private Ant ant;
    private int food;
    private double pheromone;
    
    public Tile() {
        foodLabel = new JLabel();
        setPreferredSize(new Dimension(30, 30));
        add(foodLabel);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setAnt(null);
        setFood(0);
        setPheromone(0);
        addMouseListener(this);
    }
    
    public Ant getAnt() { return ant; }
    public int getFood() { return food; }
    public double getPheromone() { return pheromone; }
    public void setAnt(Ant a) { 
        ant = a; 
        if(ant == null) setBackground(EMPTY_COLOR);
        else if(ant instanceof WorkerAnt && ((WorkerAnt) ant).hasFood())
        {
        	setBackground(Color.blue);
        }
        else if(ant instanceof WorkerAnt)
        {
        	setBackground(WORKER_COLOR);
        }
        else setBackground(Color.WHITE);
    }
    public void setFood(int f) { 
        food = f; 
        if(food > 0) foodLabel.setText(String.valueOf(food));
        else foodLabel.setText("");
    }
    public void setPheromone(double p) { pheromone = p; }
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setToolTipText("Pheromone: " + String.valueOf(pheromone));
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		String possableNum = (JOptionPane.showInputDialog("Enter the amount of food: ", Integer.valueOf(food)));
		if(Helper.isInteger(possableNum))
		{
			setFood(Integer.valueOf(possableNum));
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}










