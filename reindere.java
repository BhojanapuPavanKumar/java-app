package java-app;


import java.awt.*;	
import javax.swing.*;
public class reindere extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Game1.obj.repaint(g);
	}
}
