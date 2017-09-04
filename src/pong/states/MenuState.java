/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.states;

import java.awt.Color;
import java.awt.Graphics;
import pong.Handler;
import pong.gfx.Assets;
import pong.ui.ClickListener;
import pong.ui.UIImageButton;
import pong.ui.UIManager;

/**
 *
 * @author aac088
 */
public class MenuState extends States{
    
    private UIManager uiManager;
    private UIImageButton start_button,quit_button;
    
    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        
        quit_button =new UIImageButton(425,450,350,100,Assets.menu_quit_button, new ClickListener(){//adds quit button
            @Override
            public void onClick() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                handler.getMouseManager().setUIManager(null);
                System.exit(0);
            }
        });
        
        start_button = new UIImageButton(425, 280,350,100,Assets.menu_start_button,new ClickListener(){//adds start button
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.playSettingMusic(2);
                States.setState(handler.getGame().modeSelectorState);
            }
        });
        
        uiManager.addObject(start_button);
        
        uiManager.addObject(quit_button);
        handler.playSettingMusic(1);
    }

    private int counter;
    
    @Override
    public void update() {
        System.out.println(handler.getMouseManager().getMouseX() + "  "+ handler.getMouseManager().getMouseY());
        counter++;
        if(counter%(60*15)==0){
            handler.playSettingMusic(2);
            handler.playSettingMusic(1);
            counter=0;
        }
        /*if(handler.getMouseManager().isLeftPressed()){
            States.setState(handler.getGame().gameState);
        }*/
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
       
       g.setColor(Color.black);
       g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
       g.setColor(Color.white);
       uiManager.render(g);
        //g.drawRect(handler.getWidth() / 2 - 300, 100, 600, 100);
       g.drawImage(Assets.menu_title, handler.getWidth() / 2 - 300, 100,600,100, null);
       g.drawRect(450,275,290,100);
       g.drawRect(505,450, 190, 90);
    }
    
}
