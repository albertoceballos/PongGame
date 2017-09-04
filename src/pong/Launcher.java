/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

/**
 *
 * @author aac088
 */
public class Launcher {
    public static void main(String[] args){
        Game game = new Game("Pong Game", 1200,640);
        game.start();
    }
}
