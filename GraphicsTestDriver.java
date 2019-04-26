import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GraphicsTestDriver
{  
   public static GraphicsTest screen;      //**going to be used later
   
   public static void main(String...args)
   {
      screen = new GraphicsTest();
      JFrame frame = new JFrame("Try moving the elevator");
      frame.setSize(800, 800);
      frame.setLocation(100, 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(screen);
      frame.setVisible(true);
      frame.addKeyListener(new listen());
      
   }
   
   public static class listen implements KeyListener
   {
      public void keyTyped(KeyEvent e)
      {
      
      }
      
      public void keyPressed(KeyEvent e)
      {
         screen.processUserInput(e.getKeyCode());
      }
      
      public void keyReleased(KeyEvent e)
      {
      
      }
   }
}