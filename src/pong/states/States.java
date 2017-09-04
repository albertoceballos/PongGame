/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.states;

import java.awt.Graphics;
import pong.Game;
import pong.Handler;

/**
 *
 * @author aac088
 */
public abstract class States {
    
    //Create game state manager
    private static States currentState = null;
    
    public static void setState(States state){
        currentState = state;
    }
    
    public static States getState(){
        return currentState;
    }
    
    //CLASS
    
    protected Handler handler;
    
    public States(Handler handler){
        this.handler = handler;
    }
    
    public abstract void update();
    
    public abstract void render(Graphics g);
    
}
