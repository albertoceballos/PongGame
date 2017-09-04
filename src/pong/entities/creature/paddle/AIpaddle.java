/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.entities.creature.paddle;

import java.awt.Graphics;
import java.util.Random;
import pong.Handler;
import pong.entities.creature.Creature;
import pong.entities.creatures.ball.Ball;
import pong.gfx.Assets;
import pong.tile.Tile;

/**
 *
 * @author aac088
 */
public class AIpaddle extends Creature{
    private Handler handler;
    private Ball ball;
    private int mistake;
    
    public AIpaddle(Handler handler, float x, float y,Ball ball) {
        super(handler, x, y, 20, 100);
        this.handler=handler;
        this.ball=ball;
        r = new Random();
    }
    
    public void setAISpeed(){
        if(handler.getDiff()==1){
            setSpeed(5);
            setMoves();
        }
        if(handler.getDiff()==2){
            setSpeed(10);
            setMoves();
        }
        if(handler.getDiff()==3){
            setSpeed(20);
            setMoves();
        }
    }
    
    public void setMoves(){
        setxMove(speed);
        setyMove(speed);
    }
    
    private Random r;
    
    @Override
    public void move(){
        
        if(ball.getX()> handler.getWidth() / 2){
            mistake=r.nextInt(5);
            if(ball.getY() > y && y<handler.getHeight()-Tile.TILEHEIGHT-this.height){//down
                setSpeed(ball.getSpeed()-mistake);
                setY(getY()+getyMove());
            }
            if(ball.getY() <y && y>Tile.TILEHEIGHT){//up
                setSpeed(ball.getSpeed()-mistake);
                setY(getY()-getyMove());
            }
        }
        
    }
    
    
    @Override
    public void update() {
       move();
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.paddle2, (int)getX(), (int)getY(),width,height, null);
        
    }
    
}
