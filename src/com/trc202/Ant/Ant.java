package com.trc202.Ant;

import java.awt.Point;

public interface Ant {
    public Point getLocation();
    public void setLocation(Point newLocation);
    public void setLifespan(int lifespan);
    public void decrementLifespan();
    public boolean isDead();
    public void headCloserToHome();
}
