import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {
    JPanel container = new JPanel();
    public static int width = 500;
    public static int height = 300;
    Snake snake;

    JLabel loose =new JLabel();

    public Frame() {
        this.setTitle("Snake");
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo((null));
        createGame();

    }
    public void createGame(){
        container.setBackground(Color.CYAN);
        this.addKeyListener(this);
        loose.setBounds((width/2)-40,0,100,20);
        this.setContentPane(container);
        this.setVisible(true);
        snake = new Snake(this.getGraphics(), 4);
        //container.repaint();
    }
    void startGame()
    {
        createGame();
        snake.play();
        loose();
    }

    public void loose(){
        loose.setText("You loose !");
        container.add(loose);
        container.repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.direction != 3) {
            snake.direction = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.direction != 1) {
            snake.direction = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.direction != 4) {
            snake.direction = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.direction != 2) {
            snake.direction = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
