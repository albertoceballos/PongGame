/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author aac088
 */
public class Tile {
    
    //STATIC 
    
    public static Tile[] tiles = new Tile[400];
    public static Tile backgroundTile = new BackgroundTile(0);
    public static Tile wallTile = new WallTile(1);
    public static Tile scoreTile = new ScoreTile(2);
    public static Tile lineTile = new LineTile(3);

    //CLASS
    
    public static final int TILEWIDTH = 40, TILEHEIGHT = 40;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id){
        this.texture=texture;
        this.id=id;
        
        tiles[id]=this;
    }
    
    public boolean isSolid(){
        return false;
    }
    
    public void update(){
    
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH,TILEHEIGHT,null);
    }
    
    public int getId(){
        return id;
    }
    
    
}
