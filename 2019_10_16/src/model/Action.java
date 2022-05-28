package model;

import view_controller.ViewSpecs;

public class Action {
	Ball a, b;
	public double distance;
	
	//constructor
	public Action(Ball p, Ball q) {
		a = p;
		b = q;
		distance = Math.sqrt(a.square(a.x, b.x)+ a.square(a.y, b.y));
	}
	
	//avoid counting over
	public void conduct() {
		//calculate the new coordinate
		double angle = Math.atan2(a.y - b.y, a.x - b.x);
		
		double newX = (distance + 1) * Math.cos(angle) + a.x;
		double newY = (distance + 1) * Math.sin(angle) + a.y;
		
		//set the new coordinates
		a.x = newX;
		a.y = newY;
		
		//a hits b
		a.hitByAnotherBall(b);
	}
	
	
	//reset
	public static void reset(Ball[] model) {
		for(int i = 0; i < 10; i++) {
			model[i].x = ViewSpecs.x[i];
			model[i].y = ViewSpecs.y[i];
			model[i].vx = 0;
			model[i].vy = 0;
			model[i].alive = true;
		}
	}
	
	//re:do
	public static void redo(Ball[] model) {
		for(int i = 0; i < 10; i++) {
			model[i].x = model[i].lateX;
			model[i].y = model[i].lateY;
			
			model[i].vx = 0;
			model[i].vy = 0;
			
			model[i].alive = model[i].lateAlive;
		}
	}
	
	//show demonstration
	public static void showDemo(Ball model) {
		//velocity of Cue
		int fx = 20;
		int fy = 0;
		
		//modify
		model.vx = fx;
		model.vy = fy;
	}
	
	//clicked and decide the first velocity
	public static void clicked(double x, double y, Ball model) {
		double mx = x - model.x;
		double my = y - model.y;
		
		//distance between cue and clicked point
		double cdis = Math.sqrt(mx * mx + my * my);
		
		if(cdis > 0) {
			//normalize
			mx /= cdis;
			my /= cdis;
		}
		
		if(cdis > 2 * ViewSpecs.ballRadius) {
			cdis = ViewSpecs.ballRadius;
		}
		
		//scale
		mx *= cdis;
		my *= cdis;
		
		model.vx = mx;
		model.vy = my;
		
		}
	
}
