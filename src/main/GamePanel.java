package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Mechanics;

public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originTileSize = 16;  // 16x16 tile
    final int scale = 3;

    public final int tileSize = originTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;  // 4:3 ratio
    public final int screenWidth = tileSize * maxScreenCol;  // 768 pixels
    public final int screenHeitght = tileSize * maxScreenRow;  // 576 pixels

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Mechanics mechanics = new Mechanics(this,keyH);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeitght));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        
        while(gameThread != null) {
            
            double drawInterval = Math.pow(10, 9)/FPS;  // 0.01666 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;

            // 1. UPDATE: update location of the snake and food
            update();

            // 2. DRAW: draw the screen with the updated snake and food positions
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / Math.pow(10, 6);

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {

        mechanics.update();
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        mechanics.draw(g2);

        g2.dispose();
    }
}
