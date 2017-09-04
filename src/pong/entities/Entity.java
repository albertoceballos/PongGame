/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import pong.Handler;

/**
 *
 * @author aac088
 */
public abstract class Entity {
    
    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x+bounds.x +xOffset), (int)(y+bounds.y+yOffset),bounds.width,bounds.height);
    }
    
    public boolean checkEntityCollision(float xOffset, float yOffset){
        
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset))){
                return true;
            }
        }
        return false;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle bounds;
    protected Handler handler;
    
    public Entity(Handler handler,float x, float y, int width, int height){
        this.x =x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.handler = handler;
        bounds = new Rectangle(0,0,width,height);
    }
    
    public abstract void update();
    
    public abstract void render(Graphics g);
}
