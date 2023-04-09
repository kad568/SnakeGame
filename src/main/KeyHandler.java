package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public String direction;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP && direction != "down") {
            direction = "up";
        }
        if(code == KeyEvent.VK_DOWN && direction != "up") {
            direction = "down";
        }
        if(code == KeyEvent.VK_LEFT && direction != "right") {
            direction = "left";
        }
        if(code == KeyEvent.VK_RIGHT && direction != "left") {
            direction = "right";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
