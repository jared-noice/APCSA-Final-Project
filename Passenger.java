public class Passenger
{
   int start;        //stores the starting location of the passenger       //stores the starting floor
   int end;          //stores the desired floor of the passenger
   
   public Passenger(int s, int e)
   {
      start = s;
      end = e;
   }
   
   public int getStart()
   {
      return start;      
   }
   
   public int getEnd()
   {
      return end;
   }
   
   public void setStart(int s)
   {
      start = s;
   }
   
   public void setEnd(int e)
   {
      end = e;
   }
   
   public String toString()
   {
      return start + ":" + end;
   }
      
}