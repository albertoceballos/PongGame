/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.entities.creatures.ball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import pong.Handler;
import pong.audio.AudioPlayer;
import pong.entities.creature.Creature;
import pong.gfx.Animation;
import pong.gfx.Assets;
import pong.states.States;
import pong.tile.Tile;

/**
 *
 * @author aac088
 */
public class Ball extends Creature{
    
    private HashMap<String,AudioPlayer> sfx;

    private Handler handler;
    private Animation anim;
    private int score1=0,score2=0;
    
    private float init_x,init_y;
    
    public Ball(Handler handler ,float x, float y){
        super(handler,x,y,20,100);
        init_x=this.x;
        init_y=this.y;
        this.handler=handler;
        bounds.x= 6;
        bounds.y= 8;
        bounds.width = 50;
        bounds.height = 50;
        anim = new Animation(500,Assets.ball_ani);
        sfx = new HashMap<String, AudioPlayer>();
        sfx.put("bounce",new AudioPlayer("/SFX/ball_beep.wav"));
        sfx.put("score", new AudioPlayer("/SFx/point_score.wav"));
        sfx.put("game_over", new AudioPlayer("/SFX/game_over.wav"));
        handler.playGameMusic(1);
    }
    
    public void setBallSpeed(){
        if(handler.getDiff()==1){
        setSpeed(5);
        setMoves();
        }
        if(handler.getDiff()==2){
        setSpeed(10);
        setMoves();
        }
        if(handler.getDiff()==3){
        setSpeed(30);
        setMoves();
        }
    }
    
    public void setScore1(int i){
        score1=i;
    }
    
    public void setScore2(int i){
        score2=i;
    }
    
    public void setMoves(){
        setxMove(speed);
        setyMove(speed);
    }
    
    private String winner="none";
    
    public String getWinner(){
        return winner;
    }
    
    public void setWinner(String as){
        winner=as;
    }
    
    protected int counter;
    
    @Override
    public void update() { 
        counter++;
        if(counter %(14*60)==0){
            handler.playGameMusic(2);
            handler.playGameMusic(1);
            counter=0;
        }
        
        if(score1==3 || score2==3){
            if(score1==3){
                winner="player 1";
            }
            if(score2==3 && handler.getMode()==2){
                winner="player 2";
            }
            if(score2==3 && handler.getMode()==1){
                winner="ai";
            }
            handler.playGameMusic(2);
            sfx.get("game_over").play();
            States.setState(handler.getGame().gameOverState);
        }
        if(crash){
            anim.update();
            
        }
        if(!crash){
            move();
        }
        
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
    
    private boolean crash=false;

    @Override
    public void moveX(){
        if(getxMove() > 0){//Moving right
            
            int tx = (int) (getX()+getxMove()+bounds.x+bounds.width) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile(tx,(int) (y+bounds.y) /Tile.TILEHEIGHT)
                    && !collisionWithTile(tx,(int)(y+bounds.y+bounds.height) / Tile.TILEHEIGHT)){
                setX(getX() + getxMove());
            }
            if(collisionWithTile(tx,(int) (getY()+bounds.y) / Tile.TILEHEIGHT ) //if collision with solid tile then invert direction and continue
                    && collisionWithTile(tx,(int)(getY()+bounds.y+bounds.height) / Tile.TILEHEIGHT )){
                sfx.get("score").play();
                score1++;
                crash=true;
                setxMove(-getxMove());
                setX(getX() + getxMove());
            }
            if(checkEntityCollision(xMove,0f)){
                sfx.get("bounce").play();
                setxMove(-getxMove());
                setX(getX() + getxMove());
            }
        }else if(xMove<0){//Moving left
            int tx = (int) (getX()+xMove+bounds.x) /Tile.TILEWIDTH;
            
            if(!collisionWithTile(tx,(int) (y+bounds.y) /Tile.TILEHEIGHT)
                    && !collisionWithTile(tx,(int)(y+bounds.y+bounds.height) / Tile.TILEHEIGHT)){
                setX(getX() + getxMove());
            }
            if(collisionWithTile(tx,(int) (getY()+bounds.y)  /Tile.TILEHEIGHT )
                    && collisionWithTile(tx,(int)(getY()+bounds.y+bounds.height)  /Tile.TILEHEIGHT)){
                sfx.get("score").play();
                crash=true;
                score2++;
                setxMove(-getxMove());
                setX(getX() + getxMove());
            }
            if(checkEntityCollision(xMove,0f)){
                sfx.get("bounce").play();
                setxMove(-getxMove());
                setX(getX() + getxMove());
            }
            
        }
    }
    
    @Override
    public void moveY(){
        if(getyMove() <0 ){//up
            int ty = (int) (getY() + yMove + bounds.y) /Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH,ty)
                    && !collisionWithTile((int) (x+bounds.x +bounds.width) / Tile.TILEWIDTH,ty)){
                setY(getY() + getyMove());
            }
            if(collisionWithTile((int) (getX()+bounds.x) / Tile.TILEWIDTH,ty)
                    && collisionWithTile((int) (getX()+bounds.x +bounds.width) / Tile.TILEWIDTH,ty)){
                sfx.get("bounce").play();
                setyMove(-getyMove()); 
                setY(getY() + getyMove());
            }
            if(checkEntityCollision(0f,yMove)){
                sfx.get("bounce").play();
                setyMove(-getyMove()); 
                setY(getY() + getyMove());
            }
           
            
            
        }else if(getyMove() > 0){ //down
            int ty = (int) (getY() + getyMove() + bounds.y + bounds.height) /Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x+bounds.x) / Tile.TILEWIDTH,ty)
                    && !collisionWithTile((int) (x+bounds.x +bounds.width) / Tile.TILEWIDTH,ty)){
                setY(getY() + getyMove());
            }
            if(collisionWithTile((int) (getX()+bounds.x) / Tile.TILEWIDTH,ty)
                    && collisionWithTile((int) (getX()+bounds.x +bounds.width) / Tile.TILEWIDTH,ty)){
                sfx.get("bounce").play();
                setyMove(-getyMove()); 
                setY(getY() + getyMove());
            }
            if(checkEntityCollision(0f,yMove)){
                sfx.get("bounce").play();
                setyMove(-getyMove()); 
                setY(getY() + getyMove());
            }
            
            
        }
    }
    private int timer;
    @Override
    public void render(Graphics g) {
        if(crash){
            g.drawImage(anim.getCurrentFrame(), (int)getX(), (int)getY(), DEFAULT_CREATURE_WIDTH,DEFAULT_CREATURE_HEIGHT,null);
            timer++;
            if(timer%60 ==0){
                crash=false;
                timer=0;
                setX(init_x);
                setY(init_y);
            }
        }else{
            g.drawImage(Assets.ball, (int)getX(), (int)getY(), DEFAULT_CREATURE_WIDTH,DEFAULT_CREATURE_HEIGHT,null);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,50));
        g.drawString(Integer.valueOf(score1).toString(), handler.getWidth() / 2  - 150 ,handler.getHeight() /2 -200);
        g.drawString(Integer.valueOf(score2).toString(),handler.getWidth() / 2 +80,handler.getHeight() / 2 - 200);
    }
}
