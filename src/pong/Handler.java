/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import pong.audio.AudioPlayer;
import pong.input.KeyManager;
import pong.input.MouseManager;
import pong.states.States;
import pong.world.World;

/**
 *
 * @author aac088
 */
public class Handler {
    protected int mode;//1=single player, 2=multiplayer
    protected int diff;//1=easy,2=med,3=hard
    private AudioPlayer bgMusic;
    private AudioPlayer menuMusic;
    
    public void playGameMusic(int i){
        bgMusic = new AudioPlayer("/Music/game_bg_music_maybe.wav");
        menuMusic = new AudioPlayer("/Music/menu_bg_music.wav");
        if(i==1){
            bgMusic.play();
        }
        if(i==2){
            bgMusic.stop();
        }
    }
    
    public void playSettingMusic(int i){
        
        menuMusic = new AudioPlayer("/Music/menu_bg_music.wav");
        if(i==1){
            menuMusic.play();
        }
        if(i==2){
            menuMusic.stop();
        }
    }
    
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * @param world the world to set
     */
    public void setWorld(World world) {
        this.world = world;
    }
    private Game game;
    private World world;
   
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    
    public int getHeight(){
        return game.getHeight();
    }
    
    public Handler(Game game){
        this.game=game;
    }

    public void setMode(int i) {
        mode=i;
    }
    
    public int getMode(){
        return mode;
    }

    public void setDiff(int i) {
        diff=i;
    }
    
    public int getDiff(){
        return diff;
    }
}
