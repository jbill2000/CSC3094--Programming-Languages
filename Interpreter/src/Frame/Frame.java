package Frame;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame
{
   
   int scale=1; //if looks too small, change to 2 or 3. (1 = 500x500, 2 = 1000x 1000, 3 = 1500x1500)
   
   public Frame(String[] args)
   {
      super("The Ducks rule. But lost to OU :(... ok... my ISS3073 final's prediction was wrong...");

      Container contents = getContentPane();

      Panel p = new Panel(args[0], scale);
      contents.add(p);
   
      Dimension d = p.getSize(); 
   
      setSize(500*scale,500*scale);
      setVisible(true);      
   }

   public static void main(String[] args)
   {
      Frame theFrame = new Frame(args);
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}