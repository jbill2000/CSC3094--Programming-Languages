package Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Panel extends JPanel
{
   //Interpreter interp;
   static IImage image;
   
   int scale;
   
    Interpreter interp;

   public Panel(String filename, int scale)
   {
      super();
      this.scale = scale;
      
      image = new IImage(100,100);
      
      interp = new Interpreter();
      interp.load(filename);
      interp.printProgram();
           
      Timer t = new Timer(10,new TL());
      t.start();
   }

   
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      image.draw(g,scale);  
   }    
   
   //use this method to get the image you are "drawing" too. This should be called in the interpreter.
   public static IImage getImage()
   {
      return image;
   }
   
   double gravityAmount=0;
   
   public class TL implements ActionListener
   {
      public void actionPerformed(ActionEvent ae)
      {
         image.clearImage();
         interp.run();  
         repaint();
      }
   }
}