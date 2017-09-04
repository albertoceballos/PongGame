/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.entities.creature.paddle;

import java.awt.Graphics;
import pong.Handler;
import pong.entities.creature.Creature;
import pong.gfx.Assets;
import pong.tile.Tile;

/**
 *
 * @author aac088
 */
public class Player extends Creature{
    
    private Handler handler;
    private int id;
    
    public Player(Handler handler ,float x, float y,int id){
        super(handler,x,y,20,100);
        this.handler=handler;
        this.id=id;
    }

    @Override
    public void update() {   
        getInput();
        move();
        /* old way to move
        if(game.getKeyManager().up){
            setY(getY() - 3);
        }
        if(game.getKeyManager().down){
            setY(getY() + 3);
        }
        if(game.getKeyManager().right){
            x+=3;
        }
        if(game.getKeyManager().left){
            x-=3;
        }*/
        
    }
    
    private void getInput(){
        //xMove=0;
        //yMove=0;
        if(id==1){
            if(handler.getKeyManager().w && y>Tile.TILEHEIGHT){
                setY(getY() - getSpeed());
            }
             if(handler.getKeyManager().s && y<handler.getHeight()-Tile.TILEHEIGHT-this.height){
                setY(getY() + getSpeed());
            }
        }
        if(id==2){
            if(handler.getKeyManager().up && y>Tile.TILEHEIGHT){
                setY(getY() - getSpeed());
            }
            if(handler.getKeyManager().down && y<handler.getHeight()-Tile.TILEHEIGHT-this.height){
                 setY(getY() + getSpeed());
            }
        }
        
        /*if(game.getKeyManager().left){
            xMove = -speed;
        }
        if(game.getKeyManager().right){
            xMove = speed;
        }*/
    }

    @Override
    public void render(Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(Assets.paddle1, (int)getX(), (int)getY(), width,height,null);
        
       
    }
    
}
