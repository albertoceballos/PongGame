/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author aac088
 */
public class KeyManager implements KeyListener{

    private boolean[] keys;
    public boolean up,down; //left,right;
    public boolean w,s,p;
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    public void update(){
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        w=keys[KeyEvent.VK_W];
        s=keys[KeyEvent.VK_S];
        p=keys[KeyEvent.VK_P];
        //left = keys[KeyEvent.VK_LEFT];
        //right =keys[KeyEvent.VK_RIGHT];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       keys[e.getKeyCode()] = true;
       
       System.out.println("Pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()]=false;
        System.out.println("Released");
    }
    
}
