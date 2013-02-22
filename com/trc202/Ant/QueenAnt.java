package com.trc202.Ant;

import java.awt.Point;

public class QueenAnt implements Ant {
	private Point currentLocation;
	private int storedFood;
	private int lifespan;
	private double pheromone;
	
	public QueenAnt()
	{
		currentLocation = new Point(0,0);
		storedFood = 50;
		lifespan = 100;
		pheromone = 100;
	}
	QueenAnt(int lifespan)
	{
		currentLocation = new Point(0,0);
		storedFood = 100;
		this.lifespan = lifespan;
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

	@Override
	public void headCloserToHome() {
	}
	
	public void setPheromone(double pheromone) {
		this.pheromone = pheromone;
	}
	public double getPheromone() {
		return pheromone;
	}
	public void setStoredFood(int storedFood) {
		this.storedFood = storedFood;
	}
	public int getStoredFood() {
		return storedFood;
	}
	public boolean canProduceAnt()
	{
		return (storedFood > 0);
	}

}
