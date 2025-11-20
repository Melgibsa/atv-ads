import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameMovements {
    private Bomberman bomberman;
    private GameController gameController;
    private GameWindow gameWindow;
    private ArrayList<Barrier> barriers;
    private ArrayList<Enemies> enemies;
    private ArrayList<Object> collidables;
    private Timer timer;
    private ArrayList <Bomb> bomb;
    private ArrayList <Fire> fire;
    private ArrayList <Gift > gifts;

    public GameMovements(Bomberman bomberman, GameController gameController,  GameWindow gameWindow,
                         ArrayList<Barrier> barriers,  ArrayList<Enemies> enemies, ArrayList <Bomb> bomb,
                         ArrayList <Fire> fire, ArrayList <Gift> gifts) {
        this.bomberman = bomberman;
        this.gameController = new GameController();
        this.gameWindow = gameWindow;
        this.barriers = barriers;
        this.enemies = enemies;
        this.fire = fire;
        this.bomb = bomb;
        this.gifts = gifts;

        this.collidables = new ArrayList<>();
        this.collidables.addAll(barriers);
        this.collidables.addAll(enemies);
        this.collidables.addAll(fire);

        startEnemyMovement();
    }

    //BOMBERMAN CONTROL
    //walk
    public void handleInput(int keyCode) {
        int currentX = bomberman.getX();
        int currentY = bomberman.getY();
        int step = bomberman.getStep();
        int size = bomberman.getSize();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                //the char can move if only the way is free
                if (gameController.isWalls(currentX, currentY - step, bomberman.getSize(), gameWindow) &&
                        !gameController.checkCollision(currentX, currentY - step, size, collidables, gameWindow) &&
                !gameController.startContinuousCollisionCheck(bomberman, fire, gameWindow))
                {bomberman.moveUp();}
                gameController.isGift(bomberman.getX(), bomberman.getY(), size, gifts, gameWindow);
                break;

            case KeyEvent.VK_DOWN:
                if (gameController.isWalls(currentX, currentY + step, bomberman.getSize(), gameWindow) &&
                        !gameController.checkCollision(currentX, currentY + step, size,  collidables, gameWindow) &&
                        !gameController.startContinuousCollisionCheck(bomberman, fire, gameWindow))
                {bomberman.moveDown();}
                gameController.isGift(bomberman.getX(), bomberman.getY(), size, gifts, gameWindow);
                break;

            case KeyEvent.VK_LEFT:
                if (gameController.isWalls(currentX - step, currentY, bomberman.getSize(), gameWindow) &&
                        !gameController.checkCollision(currentX - step, currentY, size, collidables, gameWindow) &&
                        !gameController.startContinuousCollisionCheck(bomberman, fire, gameWindow))
                {bomberman.moveLeft();}
                gameController.isGift(bomberman.getX(), bomberman.getY(), size, gifts, gameWindow);
                break;

            case KeyEvent.VK_RIGHT:
                if (gameController.isWalls(currentX + step, currentY, bomberman.getSize(), gameWindow) &&
                        !gameController.checkCollision(currentX + step, currentY, size, collidables, gameWindow) &&
                        !gameController.startContinuousCollisionCheck(bomberman, fire, gameWindow))
                {bomberman.moveRight();}
                gameController.isGift(bomberman.getX(), bomberman.getY(), size, gifts, gameWindow);
                break;

            //droping bomb
            case KeyEvent.VK_D:
                if(bomb.isEmpty()){
                    Bomb newBomb = new Bomb(bomberman.getX(), bomberman.getY(), 50, Color.BLACK, 25);
                    bomb.add(newBomb);

                    gameController.bombTimer(currentX, currentY, size, bomb, fire);
                    break;
                }
        }
    }

    //ENEMIES MOVEMENT
    private void startEnemyMovement() {
        timer = new Timer(1000, e -> {
            moveEnemiesAutomatically();
        });

        timer.start();
    }
    private void moveEnemiesAutomatically() {
        if (enemies.size() >= 2) {
            verticalEnemy(enemies.get(0));
            horizontalEnemy(enemies.get(1));
        }
        gameWindow.repaint();
    }

    private void verticalEnemy(Enemies enemy){
        int currentY = enemy.getY();
        int currentX = enemy.getX();
        int step = enemy.getStep();

        if(currentY <= 300 && gameController.isWalls(currentX, currentY - step, bomberman.getSize(), gameWindow)){
            enemy.moveUp();
        } else if(currentY >= 500 && gameController.isWalls(currentX, currentY + step, bomberman.getSize(), gameWindow)){
            enemy.moveDown();
        }
    }
    private void horizontalEnemy(Enemies enemy){
        int currentX = enemy.getX();
        int currentY = enemy.getY();
        int step = enemy.getStep();

        if(currentX <= 500 && gameController.isWalls(currentX + step, currentY, bomberman.getSize(), gameWindow)){
            enemy.moveRight();
        } else if(currentX >= 300 && gameController.isWalls(currentX - step, currentY, bomberman.getSize(), gameWindow)){
            enemy.moveLeft();
        }
    }
}



