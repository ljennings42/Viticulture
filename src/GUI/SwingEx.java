package GUI;
import javax.swing.*; 
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

public class SwingEx implements Runnable{

	   final int WIDTH = 1000;
	   final int HEIGHT = 700;
	   
	   //Top level container. contains other containers or components
	   JFrame frame;
	   //A Canvas component represents a blank rectangular area of the screen onto which 
	   //the application can draw or from which the application can trap input events from the user.
	   Canvas canvas;
	   //what it sounds like. read api for the details
	   BufferStrategy bufferStrategy;
	   
	   public SwingEx(){
	      frame = new JFrame("Basic SwingGameExample");
	      
	      JPanel panel = (JPanel) frame.getContentPane();
	      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	      panel.setLayout(null);
	      
	      canvas = new Canvas();
	      canvas.setBounds(0, 0, WIDTH, HEIGHT);
	      canvas.setIgnoreRepaint(true);
	      
	      panel.add(canvas);
	      
	      canvas.addMouseListener(new MouseControl());
	      
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.pack();
	      frame.setResizable(false);
	      frame.setVisible(true);
	      
	      canvas.createBufferStrategy(2);
	      bufferStrategy = canvas.getBufferStrategy();
	      
	      canvas.requestFocus();
	   }
	   
	        
	   private class MouseControl extends MouseAdapter{
	      
	   }
	   
	   long desiredFPS = 60;
	   long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
	    
	   boolean running = true;
	   
	   public void run(){
	      
	      long beginLoopTime;
	      long endLoopTime;
	      long currentUpdateTime = System.nanoTime();
	      long lastUpdateTime;
	      long deltaLoop;
	      
	      while(running){
	         beginLoopTime = System.nanoTime();
	         
	         render();
	         
	         lastUpdateTime = currentUpdateTime;
	         currentUpdateTime = System.nanoTime();
	         update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));
	         
	         endLoopTime = System.nanoTime();
	         deltaLoop = endLoopTime - beginLoopTime;
	           
	           if(deltaLoop > desiredDeltaLoop){
	               //Do nothing. We are already late.
	           }else{
	               try{
	                   Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
	               }catch(InterruptedException e){
	                   //Do nothing
	               }
	           }
	      }
	   }
	   
	   private void render() {
	      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
	      g.clearRect(0, 0, WIDTH, HEIGHT);
	      render(g);
	      g.dispose();
	      bufferStrategy.show();
	   }
	   
	   //TESTING
	   private double x = 0;
	   
	   /**
	    * Rewrite this method for your SwingGameExample
	    */
	   protected void update(int deltaTime){
	      x += deltaTime * 0.2;
	      while(x > 500){
	         x -= 500;
	      }
	   }
	   
	   /**
	    * Rewrite this method for your SwingGameExample
	    */
	   protected void render(Graphics2D g){
	      g.fillRect((int)x, 0, 200, 200);
	   }
	   
	   public static void main(String [] args){
	      SwingEx ex = new SwingEx();
	      new Thread(ex).start();
	   }

	}

	           

