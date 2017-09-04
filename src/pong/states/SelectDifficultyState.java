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
public class SelectDifficultyState extends States{
    
    private UIManager uiManager;
    
    private void setDiff(){
       handler.getWorld().getEntityManager().getBall().setBallSpeed();
       handler.getWorld().getEntityManager().getAIpaddle().setAISpeed();
    }
   

    public SelectDifficultyState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        
        //easy button
        UIImageButton easy_but = new UIImageButton(260,325,200,75,Assets.easy,new ClickListener(){//easy mode button
            @Override
            public void onClick() {
                handler.setDiff(1);
                handler.getGame().gameState.createWorld();
                setDiff();
                States.setState(handler.getGame().gameState);
            }
        });
        
        //med button
       UIImageButton med_but= new UIImageButton(500,320,200,75,Assets.med,new ClickListener(){//med mode button
            @Override
            public void onClick() {
                handler.setDiff(2);
                handler.getGame().gameState.createWorld();
                setDiff();
                States.setState(handler.getGame().gameState);
            }
        });
        
        //hard button
        UIImageButton hard_but= new UIImageButton(730,320,200,75,Assets.hard,new ClickListener(){//hard mode button
            @Override
            public void onClick() {
                handler.setDiff(3);
                handler.getGame().gameState.createWorld();
                setDiff();
                States.setState(handler.getGame().gameState);
            }
        });
        
        uiManager.addObject(easy_but);
        uiManager.addObject(med_but);
        uiManager.addObject(hard_but);
    }

    @Override
    public void update() {
        System.out.println(handler.getMouseManager().getMouseX() + "  "+ handler.getMouseManager().getMouseY());
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.setColor(Color.white);
        
        //Select Difficulty
        //g.drawRect(300,150,600,100);
        g.drawImage(Assets.select_diff,300,150,600,100,null);
        //Modes
        g.drawRect(260,320,200,75);//easy
        g.drawRect(500,320,200,75);//med
        g.drawRect(730,320,200,75);//hard
        
        uiManager.render(g);
    }
    
}
