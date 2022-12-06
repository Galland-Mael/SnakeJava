import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Snake extends JPanel {
    Graphics graph;
    public int length;
    public boolean play = true;
    public int nbApples = 3;
    ArrayList<SnakeBody> body = new ArrayList<SnakeBody>();
    ArrayList<Apple> apples = new ArrayList<Apple>();
    Scanner scanner;

    public int direction =4;

    public Snake(Graphics g, int length) {
        this.graph = g;
        this.length = length;
        scanner = new Scanner(System.in);
    }


    public void createApple() {
        int x, y;
        boolean dispo = true;
        while (apples.size() < nbApples) {
            dispo = true;
            int widthFrame = ((Frame.width - 20) / 10) - 2;
            int heightFrame = ((Frame.height - 20) / 10) - 2;
            x = (int) ((Math.random() * (widthFrame)) + 3);
            y = (int) ((Math.random() * (heightFrame)) + 3);
            x = (x * 10);
            y = (y * 10);

            for (int i = 0; i < body.size(); i++) {
                SnakeBody exists = body.get(i);
                if (x == exists.posX && y == exists.posY) {
                    dispo = false;
                }
            }
            if (dispo == true) {
                apples.add(new Apple(x, y, Color.RED));
            }
        }
    }


    public void createSnake() {
        for (int i = 0; i < this.length; i++) {
            int height;
            height = ((int) Frame.height / 2) / 10;
            height = height * 10;
            body.add(new SnakeBody(Frame.height / 2 + ((i) * 10), height, Color.lightGray));
        }
    }

    public void drawApple() {
        for (int i = 0; i < apples.size(); i++) {
            Apple apple = apples.get(i);
            graph.setColor(apple.color);
            graph.fillOval(apple.posX, apple.posY, 10, 10);
        }
    }

    public void drawSnake(){
        for (int i = 0; i < body.size(); i++) {
            SnakeBody snakeBody;
            snakeBody=body.get(i);
            graph.setColor(snakeBody.color);
            graph.fillRect(snakeBody.posX,snakeBody.posY,10,10);
        }
    }
    public void play(){
        createSnake();
        while(play==true){
            graph.setColor(Color.CYAN);
            graph.fillRect(0,0,Frame.width,Frame.height);

            createApple();
            drawApple();
            drawSnake();

            wait(100);
            move();
            checkDefeat();
        }
    }

    public void wait(int timer){
        try{
            Thread.sleep(timer);
        }catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    public void move(){
        int posX,posY;
        for (int i = body.size()-1; i >0; i--) {
            SnakeBody snakeBody;
            snakeBody =body.get(i-1);
            posX= snakeBody.posX;
            posY= snakeBody.posY;
            snakeBody =body.get(i);
            snakeBody.posX=posX;
            snakeBody.posY=posY;
        }
        SnakeBody snakeBody2 = body.get(0);
        if(direction==1){
            snakeBody2.posY-=10;
        } if(direction==2){
            snakeBody2.posX+=10;
        } if(direction==3){
            snakeBody2.posY+=10;
        } if(direction==4){
            snakeBody2.posX-=10;
        }
    }
    public void checkDefeat(){
        for (int i = 0; i < apples.size(); i++) {
            Apple checkApple = apples.get(i);
            SnakeBody checkSnake = body.get(i);
            SnakeBody lastPosition = body.get(body.size()-1);

            if(checkApple.posX==checkSnake.posX && checkApple.posY==checkSnake.posY){
                   apples.remove(i);
                   body.add(new SnakeBody(200+(lastPosition.posX+20),0,Color.lightGray));
            }
        }

        for (int i = 1; i < body.size(); i++) {
            SnakeBody snakeBody = body.get(i);
            SnakeBody headSnake = body.get(0);
         if(headSnake.posX==snakeBody.posX && headSnake.posY==snakeBody.posY) {
             play = false;
          }
        }
          SnakeBody headSnake =body.get(0);
        if (headSnake.posX<10){
            play=false;
        }      if (headSnake.posX>Frame.width-20){
            play=false;
        }      if (headSnake.posY<10){
            play=false;
        }      if (headSnake.posY>Frame.height-20){
            play=false;
        }
    }
}
