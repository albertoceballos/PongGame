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
public class ModeSelectorState extends States{
    
    private UIManager uiManager2;

    public ModeSelectorState(Handler handler) {
        super(handler);
        uiManager2 = new UIManager(handler);
        
        uiManager2.addObject(new UIImageButton(350,320,200,75,Assets.oneplayer,new ClickListener(){//oneplayer button
            @Override
            public void onClick() {
                handler.setMode(1);
                //handler.getGame().gameState.createWorld();
                States.setState(handler.getGame().selectDifficultyState);
            }
        }));
        
        uiManager2.addObject(new UIImageButton(650,320,200,75,Assets.twoplayer,new ClickListener(){//two player button
            @Override
            public void onClick() {
                handler.setMode(2);
                States.setState(handler.getGame().selectDifficultyState);
            }
        }));
        
        
    }

    @Override
    public void update() {
        System.out.println(handler.getMouseManager().getMouseX() + "  "+ handler.getMouseManager().getMouseY());
        handler.getMouseManager().setUIManager(uiManager2);
        uiManager2.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        g.setColor(Color.white);
        //Select
        //g.drawRect(300, 150, 600, 100);//Select Mode
        g.drawImage(Assets.select_mode, 300, 150,600,100, null);
        
        //Modes
        g.drawRect(350,320,200,75);//1-p
        g.drawRect(650,320,200,75);//2-p        
        uiManager2.render(g);
    }
    
}
