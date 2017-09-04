/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import pong.Handler;
import pong.entities.creature.paddle.AIpaddle;
import pong.entities.creature.paddle.Player;
import pong.entities.creatures.ball.Ball;

/**
 *
 * @author aac088
 */
public class EntityManager {
    private Handler handler;
    private Player player,player2;
    private AIpaddle ai;
    private Ball ball;
    private ArrayList<Entity> entities;
    
    public EntityManager(Handler handler, Player player,AIpaddle ai, Ball ball,Player player2){
        this.handler=handler;
        entities = new ArrayList<>();
        this.player=player;
        this.ai=ai;
        this.player2 = player2;
        this.ball=ball;
        addEntity(player);
        
        if(handler.getMode()==1){
            addEntity(ai);
        }
        if(handler.getMode()==2){
            addEntity(player2);
        }
        
        addEntity(ball);
    }
    
    public void update(){
        
        
            for(int i=0;i<entities.size();i++){
                Entity e = entities.get(i);
                e.update();
            }
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }
    
    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }

    /**
     * @return the handler
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * @param handler the handler to set
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * @return the entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @param entities the entities to set
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

   
    /**
     * @return the ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * @param ball the ball to set
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }
    
    public void setAIpaddle(AIpaddle ai){
        this.ai=ai;
    }
    
    public AIpaddle getAIpaddle() {
        return ai;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setPlayer2(Player player2){
        this.player2 = player2;
    }
    
    public Player getPlayer2(){
        return player2;
    }
    
    
}
