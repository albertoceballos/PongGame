/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.entities.creature;

import pong.Handler;
import pong.entities.Entity;
import pong.tile.Tile;

/**
 *
 * @author aac088
 */
public abstract class Creature extends Entity{
    
    public void moveX(){
        if(xMove > 0){//Moving right
            
            int tx = (int) (x+xMove+bounds.x+bounds.width) /Tile.TILEWIDTH;
            
            if(!collisionWithTile(tx,(int) (y+bounds.y) /Tile.TILEHEIGHT)
                    && !collisionWithTile(tx,(int)(y+bounds.y+bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
            
        }else if(xMove<0){//Moving left
            int tx = (int) (x+xMove+bounds.x) /Tile.TILEWIDTH;
            
            if(!collisionWithTile(tx,(int) (y+bounds.y) /Tile.TILEHEIGHT)
                    && !collisionWithTile(tx,(int)(y+bounds.y+bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }
        }
        
    }
    
    public void moveY(){
        if(yMove <0 ){//up
            int ty = (int) (y + yMove + bounds.y) /Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH,ty)
                    && !collisionWithTile((int) (x+bounds.x +bounds.width) / Tile.TILEWIDTH,ty)){
                y += yMove;
            }
            
        }else if(yMove > 0){ //down
            int ty = (int) (y + yMove + bounds.y + bounds.height) /Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH,ty)
                    && !collisionWithTile((int) (x+bounds.x +bounds.width) / Tile.TILEWIDTH,ty)){
                y += yMove;
            }
        }
        
    }
    
    /**
     * @return the xMove
     */
    public float getxMove() {
        return xMove;
    }

    /**
     * @param xMove the xMove to set
     */
    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    /**
     * @return the yMove
     */
    public float getyMove() {
        return yMove;
    }

    /**
     * @param yMove the yMove to set
     */
    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public static final float DEFAULT_SPEED=6;
    public static final int DEFAULT_CREATURE_WIDTH=64;
    public static final int DEFAULT_CREATURE_HEIGHT=64;
    
    protected float speed;
    protected float xMove;
    protected float yMove;
            
            
    public Creature(Handler handler,float x, float y, int width, int height) {
        super(handler,x, y,width,height);
        speed=DEFAULT_SPEED;
        xMove=0;
        yMove=0;
    }
    
    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
    public void move(){
        if(!checkEntityCollision(xMove,0f)){
            moveX();
        }
        
        if(!checkEntityCollision(0f,yMove)){
            moveY();
        }
    }
    
    
    
    
}
