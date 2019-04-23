//mostly borrowed from Oberle Graphics Example 4 - Smooth Animation
import javax.swing.*;
    
public class Elevator
{
   private String name;
   	
   private ImageIcon picture;
      
   private int row;			//start row for the player
   private int col;			//start col for the player
      
   private int moveIncrX;	//pixel increment for transitioning between array coordinates
   private int moveIncrY;	//pixel increment for transitioning between array coordinates
   
   private int tempX;	//save locations for graphic position of where the player is to be used to draw the player in motion when transitioning from one cell to another
   private int tempY;
   
   private long currTime;    //saves the current time of the last stop for the elevators to pause in place   
   
   private boolean [] moveDir;	//flags to know which direction we want to move
   private static final int UP = 0;		//movement directions to use as index for moveDir array
   private static final int RIGHT = 1;
   private static final int DOWN = 2;
   private static final int LEFT = 3;
   
   private boolean picked;    //determines if the elevator is traversing to the start or end point
   private boolean paused;    //if the elevator is paused, then this will return true;
   
   
   public Elevator(String n, int r, int c, String image)
   {
      name = n;
      picture = new ImageIcon(image);
      row = r;
      col = c;
      moveIncrX = 0;
      moveIncrY = 0;
      tempX = 0;
      tempY = 0;
      moveDir = new boolean[4];							//reset movement flags and increments					
      for(int i=0; i<moveDir.length; i++)
         moveDir[i] = false;
      picked = false;
   }
   
   public void clearDirections()
   {
      for(int i=0; i<moveDir.length; i++)
         moveDir[i] = false;
   }
   
   //pre:  dir is 0,1,2,3 or 4
   public void setDirection(int dir)
   {
      for(int i=0; i<moveDir.length; i++)
         moveDir[i] = false;
      if(dir >= 0 && dir < moveDir.length)
         moveDir[dir] = true;
   }
      
   	//pre: dir is "up", "right", "down" or "left"
   public void setDirection(String dir)
   {
      for(int i=0; i<moveDir.length; i++)
         moveDir[i] = false;
      if(dir.equals("up"))
         moveDir[UP] = true;
      else
         if(dir.equals("right"))
            moveDir[RIGHT] = true;
         else
            if(dir.equals("down"))
               moveDir[DOWN] = true;
            else
               if(dir.equals("left"))
                  moveDir[LEFT] = true;
   }
   
   public int getDirection()
   {
      if(moveDir[0])
         return 0;
      else if(moveDir[1])
         return 1;
      else if(moveDir[2])
         return 2;
      else //if(moveDir[3])
         return 3;           
   }
   	
   public boolean isMoving()
   {
      for(int i=0; i<moveDir.length; i++)
         if(moveDir[i])
            return true;
      return false;
   }
   
   public boolean isMovingUp()
   {
      return moveDir[UP];
   }
   	
   public boolean isMovingRight()
   {
      return moveDir[RIGHT];
   }
   
   public boolean isMovingDown()
   {
      return moveDir[DOWN];
   }
   
   public boolean isMovingLeft()
   {
      return moveDir[LEFT];
   }
   
   public ImageIcon getPicture()
   {
      return picture;
   }
   
   public int getMoveIncrX()
   {
      return moveIncrX;
   }
     
   public int getMoveIncrY()
   {
      return moveIncrY;
   }
      
   public void setMoveIncrX(int x)
   {
      moveIncrX = x;
   }
   	
   public void setMoveIncrY(int y)
   {
      moveIncrY = y;
   }
   
   public void addMoveIncrX(int x)
   {
      moveIncrX += x;
   }
   
   public void addMoveIncrY(int y)
   {
      moveIncrY += y;
   }
   
   public void resetTime()             //resets the elevator's pause time
   {
      currTime = System.currentTimeMillis();
   }
   
   public long getTime()               //returns the time that the elevator saved for pausing
   {
      return System.currentTimeMillis();
   }  
   
   public void setPaused(boolean b)       //allows user to set if the elevator is paused
   {
      paused = b;
   }
   
   public boolean getPaused()    //returns if the elevator is paused
   {
      return paused;
   }
   
   public void setName(String n)
   {
      name = n;
   }
   
   public void setImage(ImageIcon image)        //sets the image of the elevator (shows right picture)
   {
      picture = image;
   }
   
   public void setRow(int r)
   {
      row = r;
   }
   	
   public void setCol(int c)
   {
      col = c;
   }
   	
   public void setCoord(int r, int c)
   {
      row = r;
      col = c;
   }
   
   public void setPicked(boolean b)    //sets if the elevator has picked up a passenger
   {
      picked = b;
   }
   
   public boolean getPicked()          //returns if the elevator has picked up a passenger
   {
      return picked;
   }
   	
   public String getName()
   {
      return name;
   }
   	
   public int getRow()
   {
      return row;
   }
   	
   public int getCol()
   {
      return col;
   }
   
   public int getTempX()
   {
      return tempX;
   }
   
   public int getTempY()
   {
      return tempY;
   }
   
   public void setTempX(int x)
   {
      tempX = x;
   }
   
   public void setTempY(int y)
   {
      tempY = y;
   }  
      
   //pre:  SIZE is the pixel size of the player
   //post: returns the actual x coordinate in pixel space
   public int findX(int SIZE)
   {
      return (SIZE*this.getCol()) + this.getMoveIncrX();
   }
   
   //pre:  SIZE is the pixel size of the player
   //post: returns the actual y coordinate in pixel space
   public int findY(int SIZE)
   {
      return (SIZE*this.getRow()) + this.getMoveIncrY();
   }
   
}