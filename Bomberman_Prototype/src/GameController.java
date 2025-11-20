import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class GameController extends JPanel {

    //BOUNDARIES-CHECK(FRAME WALL)
    public boolean isWalls(int x, int y, int size, JFrame frame) {
        Insets insets = frame.getInsets();//return borders to block the overtaking

        return x >= insets.left &&
                y >= insets.top &&
                x + size <= frame.getWidth() - insets.right &&
                y + size <= frame.getHeight() - insets.bottom;
    }

    //OBJECT-CHECK(ENEMIES, BARRIERS AND EXPLOSION)
    public boolean checkCollision(int charX, int charY, int charSize,
                                  ArrayList<Object> collidables, GameWindow gameWindow) {
        for (Object obj : collidables) {
            if (obj instanceof Barrier barrier) {
                if (charX < barrier.x + barrier.size &&
                        charX + charSize > barrier.x &&
                        charY < barrier.y + barrier.size &&
                        charY + charSize > barrier.y) {
                    return true;
                }
            }
            else if (obj instanceof Enemies enemy) {
                if (charX < enemy.x + enemy.size &&
                        charX + charSize > enemy.x &&
                        charY < enemy.y + enemy.size &&
                        charY + charSize > enemy.y) {
                    gameWindow.gameOver(gameWindow);
                    return true;
                }
            }
        }
        return false;
    }
    //collision with explosion
    public boolean startContinuousCollisionCheck(Bomberman bomberman, ArrayList<Fire> fire, GameWindow gameWindow) {
        Timer collisionTimer = new Timer(100, e -> {
            int charX = bomberman.getX();
            int charY = bomberman.getY();
            int charSize = bomberman.getSize();

            for (Fire fires : fire) {
                int fireLength = Fire.getLength();

                boolean centerCollision = charX < fires.x + fires.size &&
                        charX + charSize > fires.x &&
                        charY < fires.y + fires.size &&
                        charY + charSize > fires.y;

                boolean topCollision = charX < fires.x + fires.size &&
                        charX + charSize > fires.x &&
                        charY < fires.y &&
                        charY + charSize > fires.y - fireLength;

                boolean bottomCollision = charX < fires.x + fires.size &&
                        charX + charSize > fires.x &&
                        charY < fires.y + fires.size + fireLength &&
                        charY + charSize > fires.y + fires.size;

                boolean leftCollision = charX < fires.x &&
                        charX + charSize > fires.x - fireLength &&
                        charY < fires.y + fires.size &&
                        charY + charSize > fires.y;

                boolean rightCollision = charX < fires.x + fires.size + fireLength &&
                        charX + charSize > fires.x + fires.size &&
                        charY < fires.y + fires.size &&
                        charY + charSize > fires.y; {if (centerCollision || topCollision || bottomCollision || leftCollision || rightCollision) {
                    gameWindow.gameOver(gameWindow);
                    return;
                }
                }
            }
        });
        collisionTimer.start();
        return false;
    }

    //EXPLODE BOMB
    public boolean bombTimer(int charX, int charY, int charSize,
                          ArrayList<Bomb> bombs, ArrayList<Fire> fire) {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (charX < bomb.x + bomb.size &&
                    charX + charSize > bomb.x &&
                    charY < bomb.y + bomb.size &&
                    charY + charSize > bomb.y) {

                final int index = i;
                Timer timer = new Timer(3000, e -> {
                    bombs.remove(index);

                    Fire newFire = new Fire(bomb.getX(), bomb.getY(), 60, Color.RED, 20, Fire.getLength());
                    fire.add(newFire);

                    new Timer(1000, e2 -> {
                        fire.remove(newFire);
                    }).start();
                });
                timer.setRepeats(false);
                timer.start();
                return true;
            }
        }
        return false;
    }

    //POWER-AMPLIFY
    public boolean isGift(int charX, int charY, int charSize,
                          ArrayList<Gift> gifts, GameWindow gameWindow) {
        for (int i = 0; i < gifts.size(); i++) {
            Gift gift = gifts.get(i);
            if (charX < gift.x + gift.size &&
                    charX + charSize > gift.x &&
                    charY < gift.y + gift.size &&
                    charY + charSize > gift.y) {

                gifts.remove(i);
                gameWindow.repaint();

                //increase the fire length
                int currentLength = Fire.getLength();
                Fire.setLength(currentLength + 50);
                return true;
            }
        }
        return false;
    }

    //DEFEAT-CHECK(DEFEAT ENEMY)

    //GAME-WINS
}
