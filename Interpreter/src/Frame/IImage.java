package Frame;

import javax.swing.*;
import java.awt.*;

//grayscale images only. Was rgb colors, but wanted to keep it easier.
public class IImage
{
   int [][] pixels;

   public IImage(int xsize, int ysize)
   {
      pixels = new int[xsize][ysize];
      for(int i=0;i<xsize;i++)
      {
         for(int j=0;j<ysize;j++)
         {
            pixels[i][j] = 0;
         }
      }
   }
   
   public void clearImage()
   {
      for(int i=0;i<pixels.length;i++)
      {
         for(int j=0;j<pixels[0].length;j++)
         {
            pixels[i][j] = 0;
         }
      }   
   }
   
   public int getColor(int x, int y)
   {
      return pixels[x][y];
   }
   public void setColor(int x, int y, int color) //greyscale color
   {
      pixels[x][y] = color;
   }
   public void draw(Graphics g, int scale)
   {
   
      for(int i=0;i<pixels.length;i++)
      {
         for(int j=0;j<pixels[0].length;j++)
         {
            g.setColor(new Color( pixels[i][j], pixels[i][j], pixels[i][j]));
            g.fillRect(i*5*scale,j*5*scale,5*scale,5*scale);
         }
      }  
   }
}