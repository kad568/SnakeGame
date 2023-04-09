package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Mechanics {
    
    GamePanel gp;
    KeyHandler keyH;
    Random rand = new Random();
    Snake snake = new Snake();
    Food food = new Food();
    int score = 0;
    int highScore;
    boolean atGridX, atGridY;
    int cellSizeX, cellSizeY;  



    public Mechanics(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }
    public void setDefaultValues() {

        // SNAKE
        snake.x = 100;
        snake.y = 100;
        snake.speed = 15;

        // FOOD
        food.x = 400;
        food.y = 400;

        // SCORE
        score = 0;

    }
    public void update() {

        //  MOVEMENT

        if(keyH.direction == "up") {
            snake.y -= snake.speed;
        }
        else if(keyH.direction == "down") {
            snake.y += snake.speed;
        }
        else if(keyH.direction == "left") {
            snake.x -= snake.speed;
        }
        else if(keyH.direction == "right") {
            snake.x += snake.speed;
        }

        // WALL COLLISION
        if(snake.y < 0 || snake.y > gp.screenHeitght - gp.tileSize || snake.x < 0 || snake.x > gp.screenWidth - gp.tileSize) {
            if(score > highScore) {
                highScore = score;
            }
            setDefaultValues();
        }

        // FOOD COLLISION
        if((snake.y >= food.y - gp.tileSize && snake.y <= food.y + gp.tileSize) && (snake.x >= food.x - gp.tileSize && snake.x <= food.x + gp.tileSize)) {
            
            score += 1;

            food.x = rand.nextInt(gp.screenWidth - gp.tileSize);
            food.y = rand.nextInt(gp.screenHeitght - gp.tileSize);
        }


    }
    public void draw(Graphics2D g2) {

        g2.setColor(Color.GREEN);

        g2.fillRect(snake.x, snake.y, gp.tileSize, gp.tileSize);

        g2.setColor(Color.RED);

        g2.fillRect(food.x, food.y, gp.tileSize, gp.tileSize);

        g2.setColor(Color.WHITE);

        g2.drawString("SCORE: " + String.format("%d",score), 20, 20);

        g2.drawString("HIGH SCORE: " + String.format("%d",highScore), 100, 20);
    }
}