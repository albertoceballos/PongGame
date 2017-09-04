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
public class GameOverState extends States{
    
    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        
        UIImageButton resetbutton = new UIImageButton(300,400,300,100,Assets.reset,new ClickListener(){
            @Override
            public void onClick() {
                handler.getWorld().getEntityManager().getBall().setScore1(0);
                handler.getWorld().getEntityManager().getBall().setScore2(0);
                handler.getWorld().getEntityManager().getBall().setWinner("none");
                States.setState(handler.getGame().gameState);
            }
        });//reset button
        
        UIImageButton quit = new UIImageButton(700,400,300,100,Assets.menu_quit_button,new ClickListener(){
            @Override
            public void onClick() {
                System.exit(0);
            }
        });
        
        uiManager.addObject(quit);
        uiManager.addObject(resetbutton);
    }

    @Override
    public void update() {
        System.out.println(handler.getMouseManager().getMouseX() + "  "+ handler.getMouseManager().getMouseY());
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
       // g.drawString("Game Over",500,300);
       g.setColor(Color.black);
       g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
       g.setColor(Color.white);
        //g.drawRect(300,50, 600, 100);//Game Over
        g.drawImage(Assets.game_over_title,300,50,600,100,null);//Game Over Title
        //g.drawRect(300,200,300,100);//Winner]
        g.drawImage(Assets.winnertag,300,200,300,100,null);
        //g.drawRect(750, 200, 300, 100);//who won (player1,player2,ai)
        
        if(handler.getWorld().getEntityManager().getBall().getWinner().equals("player 1")){
            g.drawImage(Assets.player1win, 600, 205, 300,100,null);
        }
        if(handler.getWorld().getEntityManager().getBall().getWinner().equals("player 2")){
            g.drawImage(Assets.player2win, 600, 205, 300,100,null);
        }
        if(handler.getWorld().getEntityManager().getBall().getWinner().equals("ai")){
            g.drawImage(Assets.aiwin, 600, 205, 300,100,null);
        }
        
        
        //g.drawString("Winner is " , 500, 400);
        g.drawRect(300,400,300,100);//restart button
        g.drawRect(700,400,300,100);//quit button
        uiManager.render(g);
    }
    
}
