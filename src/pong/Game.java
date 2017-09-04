/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import pong.gfx.Assets;
import pong.input.KeyManager;
import pong.input.MouseManager;
import pong.states.GameOverState;
import pong.states.GameState;
import pong.states.MenuState;
import pong.states.ModeSelectorState;
import pong.states.SelectDifficultyState;
import pong.states.States;

/**
 *
 * @author aac088
 */
public class Game implements Runnable{

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    private Display display;
    private int width, height;
    public String title;
    
    
    private Thread thread;
    private boolean running=false;
    
    private BufferStrategy bs;//creates buffer for images
    private Graphics g; //creates graphics
    
    //States
    public GameState gameState;
    public States menuState;
    public States modeSelectorState;
    public States selectDifficultyState;
    public States gameOverState;
    
    //input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    //handler
    private Handler handler;
    
    public Game(String title, int width, int height){
        this.width=width;
        this.height=height;
        this.title=title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    @Override
    public void run() { //calls this method many times per second
      init();
      
      int fps = 60;
      double timePerUpdate =1000000000 / fps;
      double delta = 0;
      long now;
      long lastTime = System.nanoTime();
      long timer=0;
      int ticks=0;
      
      while(running){//game loop
          now=System.nanoTime();
          delta += (now - lastTime) / timePerUpdate;
          timer += now - lastTime;
          lastTime = now;
          
          
          if(delta >= 1){
            update();
            render();
            ticks++;
            delta--;
          }
          
          if(timer >=1000000000){
              System.out.println("Ticks and Frames: " + ticks);
              ticks=0;
              timer=0;
          }
          
      }
      stop();//stops the thread if not stopped
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    private void update(){//reposition everything
        keyManager.update();
        
        if(States.getState() != null){
            States.getState().update();
        }
    }
    
    private void render(){//draws stuff
        bs = display.getCanvas().getBufferStrategy(); //gets buffer
        if(bs == null){
            display.getCanvas().createBufferStrategy(3); //creates buffer. Don't user more than 3
            return;
        }
        g = bs.getDrawGraphics();
        
        //Clear Screen
        g.clearRect(0, 0, getWidth(), getHeight()); //to clear the screen. clears the whole scren
        //Draw Starts
        if(States.getState() != null){
            States.getState().render(g);
        }
        //g.fillRect(0, 0, width, height);
        /*
        g.drawImage(Assets.paddle1,0,height /2 -80,null);
        g.drawImage(Assets.ball, width / 2, height /2 - 40, null);
        g.drawImage(Assets.paddle2, width-30, height / 2 -80, null);
        */
        
        /*g.setColor(Color.BLUE); changes the color of the object below until reseted
        g.drawRect(10, 50, 50, 70); draws small rectangle
        g.fillRect(0,0,width,height); this creates the object (rectangle)*/
        
        
        //Draw Ends
        bs.show(); //shows the object to screen
        g.dispose(); //housekeeping purposes
    }
    
    public synchronized void start(){
        if(running){//prevents erros
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(!running){
            return;
        }
        running=false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         display = new Display(title, getWidth(), getHeight());
         display.getFrame().addMouseListener(mouseManager);
         display.getFrame().addMouseMotionListener(mouseManager);
         display.getCanvas().addMouseListener(mouseManager);
         display.getCanvas().addMouseMotionListener(mouseManager);
         display.getFrame().addKeyListener(keyManager);
         Assets.init();
         
         handler= new Handler(this);
         
         gameState = new GameState(handler);
         menuState = new MenuState(handler);
         modeSelectorState = new ModeSelectorState(handler);
         selectDifficultyState = new SelectDifficultyState(handler);
         gameOverState = new GameOverState(handler);
         States.setState(menuState);
    }
    
}
