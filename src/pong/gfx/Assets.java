/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author aac088
 */
public class Assets {
    
    private static final int width =40, height =40;//grid spaces
    
    public static BufferedImage paddle1,paddle2,ball,background,wall,line;
    public static BufferedImage[] ball_ani;
    public static BufferedImage menu_title, menu_start_button,menu_quit_button;
    public static BufferedImage select_mode,select_diff,oneplayer,twoplayer,easy,med,hard;
    public static BufferedImage game_over_title,reset,player1win,player2win,aiwin,winnertag;
    
    public static void init(){
        SpriteSheet ballsheet = new SpriteSheet(ImageLoader.loadImage("/textures/Icons/ball_sprite_sheet.png"));
        SpriteSheet modediffsheet = new SpriteSheet(ImageLoader.loadImage("/textures/SelectModeState/modediff_sprite_sheet.png"));
        SpriteSheet easymedsheet = new SpriteSheet(ImageLoader.loadImage("/textures/SelectModeState/easymed_sprite_sheet.png"));
        SpriteSheet gameOverSheet = new SpriteSheet(ImageLoader.loadImage("/textures/GameOverState/Game_Over_sprite_sheet.png"));
        
        //GameOver
        game_over_title=ImageLoader.loadImage("/textures/GameOverState/Game_Over_title.png");
        winnertag=gameOverSheet.crop(0, 0, 300, 100);
        reset=gameOverSheet.crop(300,0,300,100);
        aiwin=gameOverSheet.crop(0,100,300,100);
        player2win=gameOverSheet.crop(0,100*2,300,100);
        player1win=gameOverSheet.crop(300,100*2,300,100);
        
        //Select
        select_mode = modediffsheet.crop(0, 0, 600, 100);
        select_diff = modediffsheet.crop(600,0,600,100);
        
        
        oneplayer=easymedsheet.crop(0, 0, 200, 75);
        twoplayer=easymedsheet.crop(200,0,200,75);
        easy=easymedsheet.crop(200*2,0,200,75);
        med=easymedsheet.crop(200*3, 0, 200, 75);
        hard=easymedsheet.crop(200*4,0,200,75);
        
        //Anim
        ball_ani = new BufferedImage[3];
        ball_ani[0] = ballsheet.crop(0,0, width, height);
        ball_ani[1]= ballsheet.crop(width,0,width,height);
        ball_ani[2] = ballsheet.crop(width *2 , 0 ,width, height);
        
        //Menu
        menu_title=ImageLoader.loadImage("/textures/MenuState/Pong_title.png");
        menu_start_button = ImageLoader.loadImage("/textures/MenuState/Pong_start_button.png");
        menu_quit_button = ImageLoader.loadImage("/textures/MenuState/Pong_exit_button.png");
        
        //Paddle+ball
        paddle1=ImageLoader.loadImage("/textures/Icons/Paddle.png");
        paddle2=ImageLoader.loadImage("/textures/Icons/Paddle.png");
        ball=ImageLoader.loadImage("/textures/Icons/Ball.png");
        
        //Walls
        background=ImageLoader.loadImage("/textures/Walls/background_pong.png");
        wall = ImageLoader.loadImage("/textures/Walls/wall.png");
        line=ImageLoader.loadImage("/textures/Walls/line.png");
    }
}
