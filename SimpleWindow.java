import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
   TODO Write a one-sentence summary of your class here.
   TODO Follow it with additional details about its purpose, what abstraction
   it represents, and how to use it.

   @author  TODO Your Name
   @version TODO Date

   Period - TODO Your Period
   Assignment - TODO Name of Assignment

   Sources - TODO list collaborators
 */
public class SimpleWindow extends JPanel implements KeyListener
{
  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;

  private Mario mario;
  private MovingImage platform;

  public SimpleWindow () {
	  super();
	  mario = new Mario(380,0);
	  platform = new MovingImage("bricks.png",200,500,400,50);
	  setBackground(Color.CYAN);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    int width = getWidth();
    int height = getHeight();

    double ratioX = (double)width/DRAWING_WIDTH;
    double ratioY = (double)height/DRAWING_HEIGHT;

    ((Graphics2D)g).scale(ratioX,ratioY);

    platform.draw(g,this);
    mario.draw(g,this);

  }


  public void keyPressed(KeyEvent e) {
  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		mario.walkLeft(true);
  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		mario.walkRight(true);
  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
  		mario.jump();
  	}
  }

  public void keyReleased(KeyEvent e) {
  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		mario.walkLeft(false);
  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		mario.walkRight(false);

  	}
  }

  public void keyTyped(KeyEvent e) {

  }


  public void run() {
  	while(true) {
  		// MAKE A CHANGE

  		if(mario.testPlatformHit(platform)){
  			mario.fall(false);
  		}else{
  			mario.fall(true);
  		}


  		mario.act();
  		checkMario();



  		// SHOW THE CHANGE
  		repaint();

  		// WAIT
  		try{
  			Thread.sleep(20);

  		}catch (InterruptedException e) {}
  	}
  }

  public void checkMario() {
  	int x = mario.getX() + mario.getWidth()/2;
  	int y = mario.getY() + mario.getHeight()/2;
  	if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)
  		mario = new Mario(380,0);
  }


  // As your program grows, you may want to...
  //   1) Move this main method into its own 'main' class
  //   2) Customize the JFrame by writing a class that extends it, then creating that type of object in your main method instead
  public static void main(String[] args)
  {
    JFrame w = new JFrame("Simple Window");
    w.setBounds(100, 100, 640, 480);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SimpleWindow panel = new SimpleWindow();
    w.addKeyListener(panel);
    w.add(panel);
    w.setResizable(true);
    w.setVisible(true);

    panel.run();
  }
}
