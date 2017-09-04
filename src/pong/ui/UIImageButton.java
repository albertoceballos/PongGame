/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pong.gfx.Assets;

/**
 *
 * @author aac088
 */
public class UIImageButton extends UIObject{
    
    private ClickListener clicker;
    private BufferedImage image;

    public UIImageButton(float x, float y, int width, int height,BufferedImage image,ClickListener clicker) {
        super(x, y, width, height);
        this.clicker=clicker;
        this.image=image;
    }
    
    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x,(int) y, width,height,null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
    
}
