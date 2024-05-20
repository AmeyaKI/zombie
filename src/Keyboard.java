import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// NOPE THIS DOESN'T WORK - IGNORE
public class Keyboard extends JFrame implements KeyListener { // Imported Keyboard class for fun
    private Player player;

    public Keyboard(Player player) {
        this.player = player;
        this.setTitle("Arrow Key Listener");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (player.canMove("up")) {
                    player.move("up");
                }
                break;
            case KeyEvent.VK_DOWN:
                if (player.canMove("down")) {
                    player.move("down");
                }
                break;
            case KeyEvent.VK_LEFT:
                if (player.canMove("left")) {
                    player.move("left");
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (player.canMove("right")) {
                    player.move("right");
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        // Not used
    }
}