import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class GraphicsTest extends JPanel
{
   /**************      MY OBJECTS*/

   private ArrayList<Passenger> list;     //this ArrayList stores the passengers, their current locations, and what floor they want to go to

   /**************/

   //images to use
   private ImageIcon elevator = new ImageIcon("_Elevator_.gif");
   private ImageIcon empty = new ImageIcon("Empty.png");
   private ImageIcon zero = new ImageIcon("zero.gif");
   private ImageIcon one = new ImageIcon("one.gif");
   private ImageIcon two = new ImageIcon("two.gif");
   private ImageIcon three = new ImageIcon("three.gif");
   private ImageIcon four = new ImageIcon("four.gif");
   private ImageIcon five = new ImageIcon("five.gif");
   private ImageIcon six = new ImageIcon("six.gif");
   private ImageIcon seven = new ImageIcon("seven.gif");
   private ImageIcon eight = new ImageIcon("eight.gif");
   private ImageIcon nine = new ImageIcon("nine.gif");

   private static final int SIZE = 60;       //size of the cells to be drawn

   private static final int DELAY = 10;      //#milliseconds delay between frames of animation
   private static final int SPEED = 1;      //how many pixels the images move each frame

   private static int[][] board;       //will eventually be filled with ints determining the image to be drawn

   Elevator[] elevators;         //array of elevator objects

   private static final int UP = 0;
   private static final int RIGHT = 1;
   private static final int DOWN = 2;
   private static final int LEFT = 3;

   private Timer t;

   public GraphicsTest()
   {
   /***********/
      list = new ArrayList<>();
      list.add(new Passenger(1, 5));
      list.add(new Passenger(4, 6));
      list.add(new Passenger(7, 2));
      list.add(new Passenger(3, 1));
      list.add(new Passenger(5, 3));
      list.add(new Passenger(0, 0));
   /***********/
   
      int numRows = 9;              //CHANGE THESE TO CHANGE BOARD SIZE
      int numColumns = 8;
      board = new int[numRows][numColumns];
      for(int i = 0; i < board.length; i++)
      {
         for(int j = 0; j < board[0].length; j++)
         {
            board[i][j] = 0;
         }
      }
      elevators = new Elevator[2];                    //CHANGE THIS TO CHANGE ELEVATOR COUNT;
   
      /*for(int i = 0; i < board[0].length; i++)         //this will be used to set elevators at EVEN intervals in the array
      {
         if(i % 2 == 0)
            board[i][board.length-1] = 1;
      } */
      elevators[0] = new Elevator("E", board.length-1, 4, "_Elevator_.gif");     //change this for the starting point of the character
      elevators[1] = new Elevator("BOT", board.length-1, 6, "one.gif");
      t = new Timer(DELAY, new Listener());     //starts the timer to set the interval of animation
      t.start();
   }

   public void showBoard(Graphics g)
   {
      int x = 0;
      int y = 0;
   
      //y += SIZE;
      g.drawImage(nine.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(eight.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(seven.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(six.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(five.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(four.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(three.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(two.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
      g.drawImage(one.getImage(), x, y, SIZE, SIZE, null);
      y += SIZE;
   
      y = 0;
   
   
      for(int i = 0; i < board.length; i++)
      {
         x = SIZE;
         for(int j = 0; j < board[0].length-1; j++)
         {
            if(board[i][j] == 0)    //0 is an empty image
            {
               g.drawImage(empty.getImage(), x, y, SIZE, SIZE, null);   //"scaled image"
            }
            else if(board[i][j] == 1)     //1 is a player image
            {
               g.drawImage(elevator.getImage(), x, y, SIZE, SIZE, null);   //"scaled image"
            }
            x += SIZE;
         }
         y += SIZE;
      }
      for(int i=0; i<elevators.length; i++)
      {
         Elevator curr = elevators[i];
         g.drawImage(curr.getPicture().getImage(), curr.findX(SIZE), curr.findY(SIZE), SIZE, SIZE, null);  //"scaled image"
      }
   }

   public void processUserInput(int k)
   {
      if(k==KeyEvent.VK_Q || k==KeyEvent.VK_ESCAPE)					//End the program
         System.exit(1);
      Elevator curr = elevators[0];		//players[0] is person playing the game
         //cancel move order if we are already moving from one space to the next
         /*********/
         //if(elevators[0].getDir
         //System.out.println(elevators[0].getRow());
         /*********/
      if(curr.isMoving())
         return;
      curr.clearDirections();
      curr.setMoveIncrX(0);
      curr.setMoveIncrY(0);
      if(k==KeyEvent.VK_SPACE)	//toggle the value of the array at that position
         board[curr.getRow()][curr.getCol()] = (board[curr.getRow()][curr.getCol()]+1) % 3;
      else
         if(k==KeyEvent.VK_UP && curr.getRow()>0 && board[curr.getRow()-1][curr.getCol()]!=2)
         {
            curr.setDirection(UP);
            System.out.println("On floor " + (9 - (elevators[0].getRow() - 1) + 1));
         }
         else
            if(k==KeyEvent.VK_DOWN && curr.getRow()<board.length-1 && board[curr.getRow()+1][curr.getCol()]!=2)
            {
               curr.setDirection(DOWN);
               System.out.println("On floor " + (9 - (elevators[0].getRow() + 1) + 1));
            }
            else
               if(k==KeyEvent.VK_LEFT && curr.getCol()>0 && board[curr.getRow()][curr.getCol()-1]!=2)
                  curr.setDirection(LEFT);
               else
                  if(k==KeyEvent.VK_RIGHT && curr.getCol()<board[0].length-1 && board[curr.getRow()][curr.getCol()+1]!=2)
                     curr.setDirection(RIGHT);
      repaint();			//refresh the screen
   }

   public void movePlayerSmoothly()
   {
      for(int i=0; i < elevators.length; i++)
      {
         Elevator curr = elevators[i];
         if(Math.abs(curr.getMoveIncrX()) >= SIZE || Math.abs(curr.getMoveIncrY()) >= SIZE)
         {
            curr.setMoveIncrX(0);
            curr.setMoveIncrY(0);
            if(curr.isMovingUp() && curr.getRow()>0 && board[curr.getRow()-1][curr.getCol()]!=2)
               curr.setRow(curr.getRow()-1);
            else
               if(curr.isMovingDown() && curr.getRow()<board.length-1 && board[curr.getRow()+1][curr.getCol()]!=2)
                  curr.setRow(curr.getRow()+1);
               else
                  if(curr.isMovingLeft() && curr.getCol()>0 && board[curr.getRow()][curr.getCol()-1]!=2)
                     curr.setCol(curr.getCol()-1);
                  else
                     if(curr.isMovingRight() && curr.getCol()<board[0].length-1 && board[curr.getRow()][curr.getCol()+1]!=2)
                        curr.setCol(curr.getCol()+1);
            curr.clearDirections();
         }
         else
         {
            if(curr.isMovingUp() && curr.getRow()>0)
               curr.addMoveIncrY(-1*SPEED);
            else
               if(curr.isMovingDown() && curr.getRow()<board.length-1)
                  curr.addMoveIncrY(SPEED);
               else
                  if(curr.isMovingLeft() && curr.getCol()>0)
                     curr.addMoveIncrX(-1*SPEED);
                  else
                     if(curr.isMovingRight() && curr.getCol()<board[0].length-1)
                        curr.addMoveIncrX(SPEED);
         }
      }
   }

   public void makeElevatorMove()
   {
      int toPick = 0, toEnd = 0;
      for(int i = 1; i < elevators.length; i++)
      {
         Elevator curr = elevators[i];
         if(curr.isMoving())
            continue;
         curr.clearDirections();
         curr.setMoveIncrX(0);
         curr.setMoveIncrY(0);
      
         if(list.size() == 0)          //if the list is empty, then there are no more passengers to give rides to, therefore stopping the elevator
            curr.clearDirections();
         else
         {
            toPick = list.get(0).getStart();      //toPick is the floor the next passenger is on
            toEnd = list.get(0).getEnd();          //toEnd is the floor the passenger wants to go to
         }
      
      
         /*Because the rows are backwards compared to the floor numbers, toPick and
         toEnd must be subtracted from 8 to find the relationship between them and
         the elevator*/
         if(curr.getRow() == 8 - (toPick - 1) && !curr.getPicked())
         {
            curr.setPicked(true);
         }
         if(curr.getPicked())
         {                     
            if(curr.getRow() == 8 - (toEnd - 1))
            {
               list.remove(0);
               curr.setPicked(false);
               continue;
            }
            else
               if(curr.getRow() > 8 - (toEnd - 1))
                  curr.setDirection(UP);
               else //if(curr.getRow() < 8 - (toEnd - 1))
                  curr.setDirection(DOWN);
         }
         else
            if(curr.getRow() > 8 - (toPick - 1))
               curr.setDirection(UP);
            else //if(curr.getRow() < 8 - (toPick - 1))
               curr.setDirection(DOWN);
      }
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      g.setColor(Color.blue);		//draw a blue boarder around the board
      g.fillRect(0, 0, (board[0].length*SIZE), (board.length*SIZE));
      showBoard(g);					//draw the contents of the array board on the screen
   }

   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)	//this is called for each timer iteration - make the enemy move randomly
      {
         movePlayerSmoothly();
         makeElevatorMove();
         repaint();
      }
   }
}