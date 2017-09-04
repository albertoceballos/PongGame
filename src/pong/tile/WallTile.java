/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.tile;

import pong.gfx.Assets;

/**
 *
 * @author aac088
 */
public class WallTile extends Tile{
    
    public WallTile(int id) {
        super(Assets.wall,id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
}
