/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.world;

import java.awt.Graphics;
import pong.Handler;
import pong.entities.EntityManager;
import pong.entities.creature.paddle.AIpaddle;
import pong.entities.creature.paddle.Player;
import pong.entities.creatures.ball.Ball;
import pong.tile.Tile;
import pong.utils.Utils;

/**
 *
 * @author aac088
 */
public class World {
    
    private int width,height;
    private int spawnplayerX, spawnplayerY;
    private int spawnpaddle2X, spawnpaddle2Y;
    private int spawnballX,spawnballY;
    private int[][] tiles;
    private EntityManager entityManager;
    private Handler handler;
    private Ball ball;
    private Player player,player2;
    private AIpaddle ai;
    
    public World(String path,Handler handler){
        this.handler=handler;
        ball = new Ball(handler,390,190);
        player=new Player(handler,45,190,1);
        player2=new Player(handler,1125,150,2);
        ai=new AIpaddle(handler,1125,150,ball);
        
        entityManager = new EntityManager(handler,player,ai,ball,player2);
        
        loadWorld(path);
        entityManager.getPlayer().setX(spawnplayerX);
        entityManager.getPlayer().setX(spawnplayerY);
        entityManager.getBall().setX(spawnballX);
        entityManager.getBall().setY(spawnballY);
        
    }
    
    public void update(){
        getEntityManager().update();
    }
    
    public void render(Graphics g){
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
               getTile(x,y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
        getEntityManager().render(g);
    }
    
    public Tile getTile(int x, int y){
        
        if(x<0 || y<0 || x>=width || y>=height){
            return Tile.backgroundTile;
        }
        
        Tile t = Tile.tiles[tiles[x][y]];
        if(t==null){
            return Tile.wallTile;
        }
        else{
            return t;
        }
    }
    
    private void loadWorld(String path){
        
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnplayerX = Utils.parseInt(tokens[2]);
        spawnplayerY = Utils.parseInt(tokens[3]);
        spawnpaddle2X=Utils.parseInt(tokens[4]);
        spawnpaddle2Y=Utils.parseInt(tokens[5]);
        spawnballX=Utils.parseInt(tokens[6]);
        spawnballY=Utils.parseInt(tokens[7]);
        
        tiles = new int[width][height];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                tiles[x][y]=Utils.parseInt(tokens[(x+y * width)+8]);
            }
        }
        //not permanent code (testing code)
        /*width =5;
        height =5;
        tiles = new int[width][height];
        
        for(int x = 0; x<width;x++){
            for(int y=0; y<height;y++){
                tiles[x][y] = 0;
            }
        }*/
        
        //actual load world from file
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
