package com.trc202.Ant;

import java.awt.Point;

public class WorkerAnt implements Ant {
	private Point currentLocation;
	private boolean food;
	private int lifespan;
	private double pheromone;
	
	public WorkerAnt()
	{
		currentLocation = new Point(0,0);
		food = false;
		lifespan = 100;
		pheromone = 100;
	}
	WorkerAnt(int lifespan)
	{
		currentLocation = new Point(0,0);
		food = false;
		this.lifespan = lifespan;
	}


	public boolean hasFood() {
		return food;
	}


	@Override
	public Point getLocation() {
		return currentLocation;
	}

	@Override
	public void setLocation(Point newLocation) {
		this.currentLocation = newLocation;
	}

	@Override
	public void setLifespan(int lifespan) {
		this.lifespan = lifespan;
	}

	@Override
	public void decrementLifespan() {
		lifespan = lifespan -1;
	}

	@Override
	public boolean isDead() {
		return lifespan <= 0;
	}

	public void setFood(boolean food) {
		this.food = food;
	}
	@Override
	public void headCloserToHome() {
	   Point p = getLocation();
	   p.x =  Math.max(p.x - 1, 0);
	   p.y =  Math.max(p.y - 1, 0);
	}
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
	}
	public double getPheromone() {
		return pheromone;
	}


}
