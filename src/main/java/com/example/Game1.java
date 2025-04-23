package com.example;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Game1 implements ActionListener, KeyListener, MouseListener {
    public static Game1 obj;
    JFrame jf;
    int WIDTH = 600;
    int HEIGHT = 600;
    int flag = 0;
    int score = 0;
    int speed = 4;
    Reindere panel;
    Rectangle car;
    ArrayList<Rectangle> cars;
    Random rand;
    boolean started, gameover;
    Image myCar, oppCar;

    // Explicitly importing javax.swing.Timer to avoid ambiguity
    javax.swing.Timer ti;

    Game1() {
        jf = new JFrame("Car Game");
        panel = new Reindere();
        rand = new Random();
        car = new Rectangle((WIDTH / 2) - 25, HEIGHT - 150, 50, 80);
        cars = new ArrayList<>();
        myCar = Toolkit.getDefaultToolkit().getImage("images/bluecar.jpeg");
        oppCar = Toolkit.getDefaultToolkit().getImage("images/pink car.jpeg");

        jf.add(panel);
        jf.addKeyListener(this);
        jf.addMouseListener(this);
        jf.setSize(WIDTH, HEIGHT);
        jf.setResizable(false);
        jf.setVisible(true);
        
        // Using javax.swing.Timer for game timer
        ti = new javax.swing.Timer(20, this);
        ti.start();
    }

    public static void main(String[] args) {
          System.setProperty("java.awt.headless", "true");
        EventQueue.invokeLater(() -> obj = new Game1());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        speed = 3;
        if (score > 0 && score < 10)
            speed = 5;
        else if ((score >= 10) && (score < 15))
            speed = 8;
        else if ((score >= 15) && (score < 20))
            speed = 10;
        else
            speed++;

        if (started) {
            for (Rectangle r : cars) {
                r.y += speed;
            }

            for (int i = 0; i < cars.size(); i++) {
                Rectangle rect = cars.get(i);
                if (rect.y + 150 > car.y + 200) {
                    score++;
                    cars.remove(i);
                    flag++;
                    if ((flag % 2) == 0)
                        addCars(false);
                }
            }
        }

        for (Rectangle r : cars) {
            if (r.intersects(car))
                gameover = true;
        }

        panel.repaint();
    }

    public void addCars(boolean b) {
        int xi = rand.nextInt(100);
        int width = 50;
        int height = 80;
        if (b) {
            cars.add(new Rectangle((WIDTH / 4) + xi, -cars.size() * 150, width, height));
            cars.add(new Rectangle((WIDTH / 4) + xi + 120, -(cars.size() - 1) * 150, width, height));
        } else {
            cars.add(new Rectangle((WIDTH / 4) + xi, -(cars.size() - 1) * 150 - 150, width, height));
            cars.add(new Rectangle((WIDTH / 4) + xi + 120, -(cars.size() - 2) * 150 - 150, width, height));
        }
    }

    public void paintCars(Graphics g, Rectangle rect) {
        g.setColor(Color.blue);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        g.drawImage(oppCar, rect.x, rect.y, rect.width, rect.height, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        started = true;
        gameover = false;
        addCars(true);
        addCars(true);
        addCars(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (car.x >= 150)
                car.x -= 10;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (car.x < 396)
                car.x += 10;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (car.y < 600)
                car.y -= 10;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (car.x > 0) {
                car.y += 10;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        started = true;
        gameover = false;
        addCars(true);
        addCars(true);
        addCars(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // Inner class for drawing
    class Reindere extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            repaint(g);
        }

        public void repaint(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.green.darker());
            g.fillRect(0, 0, WIDTH / 4, HEIGHT);
            g.fillRect((WIDTH / 4) * 3, 0, WIDTH / 4, HEIGHT);
            g.setColor(Color.red.darker().darker());
            g.fillRect((WIDTH / 4) - 20, 0, 20, HEIGHT);
            g.fillRect((WIDTH / 4) * 3, 0, 20, HEIGHT);
            g.setColor(Color.red);
            g.fillRect(car.x, car.y, car.width, car.height);
            g.drawImage(myCar, car.x, car.y, car.width, car.height, null);
            for (Rectangle r : cars) {
                paintCars(g, r);
            }
            if (gameover) {
                started = false;
                cars.clear();
            }
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            if (!started) {
                g.drawString("Click here to Start", 215, 100);
            }
            if (gameover) {
                g.drawString("GameOver! Your score is : " + score, 170, 200);
            }
            if (started && !gameover) {
                g.drawString("Score : " + score, 10, 300);
            }
        }
    }
}
