package view_controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

import model.Ball;

public class ViewConstructor {
	//model
	static Ball[] model = new Ball[10];
		
	//constructor
	ViewConstructor(Ball[] a){
		for(int i = 0; i < 10; i++) {
			model[i] = a[i];
		}
	}
		
	synchronized public static void drawBall(Graphics2D g2) {
		//flame
		g2.setPaint(Color.BLUE);
		g2.fillRect(0, 0, 850, 550);
		
		//pool
		g2.setPaint(new Color(0, 100, 64));
		g2.fillRect(25, 25, ViewSpecs.poolWidth, ViewSpecs.poolHeight);
		
		//pockets
		g2.setPaint(Color.BLACK);
		//left top corner
		g2.fillOval(0, 0, 2 * (int)ViewSpecs.pocketRadius, 2 * (int)ViewSpecs.pocketRadius);
		//top center
		g2.fillOval(425 - 37, 0, 2 * (int)ViewSpecs.pocketRadius, 2 * (int)ViewSpecs.pocketRadius);
		//right top corner
		g2.fillOval(850 - 2 * 37, 0, 2 * (int)ViewSpecs.pocketRadius, 2 * (int)ViewSpecs.pocketRadius);
		//left bottom corner
		g2.fillOval(0, 550 - 2 * 37, 2 * (int)ViewSpecs.pocketRadius, 2 * (int)ViewSpecs.pocketRadius);
		//bottom center
		g2.fillOval(425 - 37, 550 - 2 * 37, 2 *(int)ViewSpecs.pocketRadius, 2 * (int)ViewSpecs.pocketRadius);
		//right bottom corner
		g2.fillOval(850 - 2 * 37, 550 - 2 * 37, 2 *(int)ViewSpecs.pocketRadius, 2 *(int)ViewSpecs.pocketRadius);
		
		//cushion
		g2.setPaint(Color.DARK_GRAY);
		g2.fillRect(0, 0, 25, 550);
		g2.fillRect(850 - 25, 0, 25, 550);
		g2.fillRect(25, 0, 800, 25);
		g2.fillRect(25, 550 - 25, 800, 25);
		
		//characters
		g2.setColor(Color.BLACK);
		
		//font
		g2.setFont(new Font("Arial", Font.BOLD, 12));
		
		//balls
		
		final float[] dist = {0.0f, 0.25f, 1.0f};
		
		final Color[][] colors = {
	            {Color.WHITE, Color.WHITE, Color.GRAY}, 	
	        	{Color.WHITE, Color.WHITE, Color.ORANGE}, 	
	        	{Color.WHITE, Color.WHITE, Color.BLUE},		
	        	{Color.WHITE, Color.WHITE, Color.RED},		
	        	{Color.WHITE, Color.WHITE, Color.MAGENTA},
	        	{Color.WHITE, Color.WHITE, Color.ORANGE},	
	        	{Color.WHITE, Color.WHITE, Color.GREEN},	
	        	{Color.WHITE, Color.WHITE, Color.RED},		
	        	{Color.WHITE, Color.WHITE, Color.BLACK},	
	        	{Color.WHITE, Color.WHITE, Color.YELLOW}	
	        };
		
		g2.setColor(Color.GRAY);
		for(int i = 0; i < 10; i++) {
			
			if(model[i].alive == true) {
				RadialGradientPaint gradient = new RadialGradientPaint(
	        			(int)(model[i].x - ViewSpecs.ballRadius * 2 / 8),
	        			(int)(model[i].y - ViewSpecs.ballRadius * 2 / 8),
	        			(int)(ViewSpecs.ballRadius),
	        			dist,
	        			colors[model[i].num]
	        		);
				
	        	g2.setPaint(gradient);
				
				g2.fillOval((int)(model[i].x - 12.5), (int)(model[i].y - 12.5), 25, 25);
				
				//number change
				
				g2.setColor(Color.BLACK);
	        	g2.setFont(new Font("Arial", Font.BOLD, 16));
	        	g2.drawString(String.format("%d",i), (int)model[i].x - 4,
	        			(int)model[i].y + 4);
			}	
		}
	}
}
