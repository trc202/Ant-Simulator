import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.trc202.Ant.Ant;
import com.trc202.Ant.WorkerAnt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class AntSim extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2773539430197646019L;
	
	private ArrayList<Ant> ants;
    private Landscape landscape;
    private Random random = new Random();
    private StatusFrame statusFrame;
    private Timer timer;
    private int foodStock;
    private boolean spawnFood;
	private int maxSpawnRate;
    

	public AntSim(StatusFrame statusFrame) {
		spawnFood = true;
		maxSpawnRate = 1;
        ants = new ArrayList<Ant>();
        for(int i = 0; i < 20; i++) {
            ants.add(new WorkerAnt());
        }
        this.statusFrame = statusFrame;
        landscape = new Landscape(20, 20);
        landscape.getTile(12, 4).setFood(100);
        seedFood(20);
        seedFood(20);
        seedFood(20);
        seedFood(20);
        seedFood(20);
        timer = new Timer(100, this);
        add(landscape);
        //timer.start();
    }
    public void actionPerformed(ActionEvent event) {
    	spawnAnts();
    	if(spawnFood)seedFood(100);
        landscape.evaporate();
        updateStatus();
        if(ants.isEmpty())
        {
        	timer.stop();
        }
    	Iterator<Ant> itr = ants.iterator();
    	while(itr.hasNext())
    	{
    		Ant a = itr.next();
            a.decrementLifespan();
            if(a.isDead())
            {
            	killAnt(a);
            	itr.remove();
            }
            else if(a instanceof WorkerAnt)
            {
            	actOnWorkerAntAlgorithm((WorkerAnt) a);
            }
          //  else
            {
            	//Do nothing, invalid ant
            }
            
            
        }
    }
    
    public void actOnWorkerAntAlgorithm(WorkerAnt a)
    {
    	Point antLoc = a.getLocation();
    	Tile t = landscape.getTile(antLoc.x, antLoc.y);
    	if(a.hasFood()) {
            t.setAnt(null);
            t.setPheromone(t.getPheromone() + a.getPheromone() * 0.1);
            a.setPheromone(a.getPheromone() * 0.9);
            a.headCloserToHome();
            landscape.getTile(a.getLocation()).setAnt(a);
            if(a.getLocation().distance(0, 0) == 0) {
                a.setFood(false);
                a.setPheromone(100);
                foodStock = foodStock + 1;
            }
        }
        else if(t.getFood() > 0) {
            a.setFood(true);
            t.setFood(t.getFood() - 1);
        }
        else {
            Point p = landscape.getHighestLocation(a.getLocation());
        	landscape.getTile(a.getLocation()).setAnt(null);
        	a.setLocation(p);
        	landscape.getTile(a.getLocation()).setAnt(a);
        	tramplePheramones(a);
        }
    }
    private void updateStatus() {
    	statusFrame.setTurnsTaken(statusFrame.getTurnsTaken() + 1);
    	statusFrame.setCurrentAntPopulation(ants.size());
    	statusFrame.setFoodStock(this.foodStock);
	}
    
	private void seedFood(int food) {
    	Tile t = landscape.getTile(random.nextInt(20), random.nextInt(20));
                t.setFood(food + t.getFood());
                }
 
	/**
     * To be used when an ant moves into a new space. Reduces the pheramones in the ants space by some amount
     * @param a
     */
    private void tramplePheramones(Ant a)
    {
    	Point antLoc = a.getLocation();
    	Tile t = landscape.getTile(antLoc.x,antLoc.y);
    	t.setPheromone(t.getPheromone() * .5);
    	
    }

    public void spawnAnts()
    {
    	for(int i = 0; i < maxSpawnRate; i++)
    	{
    		if(foodStock > 0)
    		{
    			ants.add(new WorkerAnt());
    			foodStock = foodStock -1;
    		}
    	}
    }
    
    public void killAnt(Ant a)
    {	
		statusFrame.setAntsDead(statusFrame.getAntsDead() + 1);
		Tile t = landscape.getTile(a.getLocation());
		t.setAnt(null);
		if(a instanceof WorkerAnt)
		{
			WorkerAnt wa = (WorkerAnt)a;
			if(wa.hasFood())t.setFood(t.getFood() +1);
		}
    }
    
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public Timer getTimer() {
		return timer;
	}

    public int getFoodStock() {
		return foodStock;
	}
	public void setFoodStock(int foodStock) {
		this.foodStock = foodStock;
	}
	public void setSpawnFood(boolean b) {
		spawnFood = b;
		
	}
	public void setMaxSpawnRate(int maxSpawnRate) {
		this.maxSpawnRate = maxSpawnRate;
		// TODO Auto-generated method stub
		
	}
}











