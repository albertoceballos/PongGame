/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.states;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import pong.Handler;
import pong.world.World;

/**
 *
 * @author aac088
 */
public class GameState extends States{
    
    private World world;
    public boolean pause=false;
    
    public GameState(Handler handler) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super(handler);
    }
    
    public void createWorld(){
        world= new World("res/worlds/world1.txt",handler);
        handler.setWorld(world);
       
    }

    @Override
    public void update() {
        
        if(!pause){
            world.update();
            if(handler.getKeyManager().p){
                pause=true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            System.out.println("pause");
            if(handler.getKeyManager().p){
                pause=false;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //g.drawImage(Assets.ball, 0, 0, null);
        world.render(g);
        if(pause){
            g.drawString("Pause", 500, 300);
        }
        
        //Tile.tiles[0].render(g,0,0); to access tiles
    }
    
}
