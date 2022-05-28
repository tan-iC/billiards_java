package model;

import view_controller.ViewSpecs;

public class Ball {
		//friction
		public static final double e = 0.003;
		
		//Ball's number
		public int num;
		
		//alive flag
		public boolean alive = true;
		public boolean lateAlive = true;
		
		//the latest coordinates
		public double lateX;
		public double lateY;
		
		//coordinates
		public double x;
		public double y;
		
		//velocity
		public double vx;
		public double vy;
		
		//constructor
		public Ball(int num, double x, double y, double vx, double vy) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.vx = vx;
			this.vy = vy;
		}
		
		//Ball is the Cue, or not.
		public boolean isCue() {
			if(num == 0) {
				return true;
			}
			else {
				return false;
			}
		}
		
		//Ball is on the pool, or not.
		public boolean isAlive() {
			if(alive = true) {
				return true;
			}else {
				return false;
			}
		}
		
		//hitting a and b
		public void hitByAnotherBall(Ball b) {
			//velocity
			double one_vx = this.vx;
			double one_vy = this.vy;
			double another_vx = b.vx;
			double another_vy = b.vy;
			
			
			//[[STEP1]]
			/*resolve one's velocity before collision 
			 * into velocity of one and another after collision*/ 
			
			//the angle between the center of one and another
			double angle0 
			= Math.atan2(b.y - this.y, b.x - this.x);
			
			//the length of one's velocity
			double onePaceBefore 
			= Math.sqrt(one_vx * one_vx + one_vy * one_vy);
			
			//the angle of one's going
			double oneAngleBefore 
			= Math.atan2(one_vy, one_vx);
			
			//the difference of angle 
			//between one's going and another's going to go
			double angleDiff = oneAngleBefore - angle0;
			
			
			//the velocity of another after collision
			double s1AnotherPaceAfter 
			= Math.abs(onePaceBefore * Math.cos(angleDiff));
			
			//another_vx after collision
			double s1AnotherVxAfter 
			= s1AnotherPaceAfter * Math.cos(angle0);
			
			//another_vy after collision
			double s1AnotherVyAfter 
			= s1AnotherPaceAfter * Math.sin(angle0);
			
			
			//the velocity of one after collision
			double onePaceAfter 
			= Math.abs(onePaceBefore * Math.sin(angleDiff));
			
			double s1OneVxAfter, s1OneVyAfter;
			
			if(Math.sin(angleDiff) < 0) {
				//the one's velocity of x
				s1OneVxAfter 
				= onePaceAfter * Math.cos(angle0 - Math.PI / 2);
				
				//the one's velocity of y
				s1OneVyAfter 
				= onePaceAfter * Math.cos(angle0 - Math.PI / 2);
				
			}else {
				//the one's velocity of x
				s1OneVxAfter 
				= onePaceAfter * Math.cos(angle0 + Math.PI / 2);
				
				//the one's velocity of y
				s1OneVyAfter 
				= onePaceAfter * Math.cos(angle0 + Math.PI / 2);
				
			}
			
			
			
			//[[STEP2]]
			/*resolve the velocity of another before collision 
			 * into the velocity of one and another after collision*/ 
			
			//the angle between the center of another and one
			double angle1 
			= Math.atan2(this.y - b.y, this.x - b.x);
					
			//the length of another's velocity
			double anotherPaceBefore 
			= Math.sqrt(square(another_vx, 0) + square(another_vy, 0));
			
			//the angle of another's going
			double anotherAngleBefore
			= Math.atan2(another_vy, another_vx);
			
			//the difference of angle 
			//between another's going and one's going to go
			angleDiff = anotherAngleBefore - angle1;
			
			
			//the velocity of one after collision
			double s2OnePaceAfter
			= Math.abs(anotherPaceBefore * Math.cos(angleDiff));
			
			//one's velocity of x after collision
			double s2OneVxAfter
			= s2OnePaceAfter * Math.cos(angle1);
				
			//one's velocity of y after collision
			double s2OneVyAfter
			= s2OnePaceAfter * Math.sin(angle1);
			
			
			//the velocity of another after collision
			double s2AnotherPaceAfter
			= Math.abs(anotherPaceBefore * Math.sin(angleDiff));
			
			double s2AnotherVxAfter, s2AnotherVyAfter;
				
			if(Math.sin(angleDiff) < 0) {
				//the another's velocity of x
				s2AnotherVxAfter
				= s2AnotherPaceAfter * Math.cos(angle1 - Math.PI / 2);
				
				//the one's velocity of y
				s2AnotherVyAfter
				= s2AnotherPaceAfter * Math.cos(angle1 - Math.PI / 2);
				
			}else {
				//the another's velocity of x
				s2AnotherVxAfter
				= s2AnotherPaceAfter * Math.cos(angle1 + Math.PI / 2);
						
				//the one's velocity of y
				s2AnotherVyAfter
				= s2AnotherPaceAfter * Math.cos(angle1 + Math.PI / 2);
						
			}
					
					
					
			//[[STEP3]]
			/*synthesize the results of STEP1 and STEP2	 */
					
			//a = one
			this.vx = s1OneVxAfter + s2OneVxAfter;
			this.vy = s1OneVyAfter + s2OneVyAfter;
			
			//b = another
			b.vx = s1AnotherVxAfter + s2AnotherVxAfter;
			b.vy = s1AnotherVyAfter + s2AnotherVyAfter;
			
		}
		
		//move ball
		public void move(double friction) {
			this.x += this.vx;
			this.y += this.vy;
			
			//friction
			this.vx *= 1 - friction;
			this.vy *= 1 - friction;
			
			//stop ball
			if(Math.sqrt(this.vx * this.vx + this.vy * this.vy) < 0.8) {
				this.vx = 0;
				this.vy = 0;
			}
		}
		
		
		//reflect by crushing cushions
		public void reflect() {
			//right top corner
			if(425 <= this.x && 275 >= this.y ) {
				//right
				if(825 - this.x <= ViewSpecs.ballRadius) {
					this.vx *= -1;
				}
				//top
				else if(this.y - 25 <= ViewSpecs.ballRadius) {
					this.vy *= -1;
				}
				
			}
			//left top corner
			else if(425 > this.x && 275 >= this.y) {
				//left
				if(this.x - 25 <= ViewSpecs.ballRadius) {
					this.vx *= -1;
				}
				//top
				else if(this.y - 25 <= ViewSpecs.ballRadius) {
					this.vy *= -1;
				}
				
			}
			//right bottom corner
			else if(425 <= this.x && 275 < this.y) {
				//right
				if(825 - this.x <= ViewSpecs.ballRadius) {
					this.vx *= -1;
				}
				//bottom
				else if(525 - this.y <= ViewSpecs.ballRadius) {
					this.vy *= -1;
				}
				
			}
			//left bottom corner
			else {
				//left
				if(this.x - 25 <= ViewSpecs.ballRadius) {
					this.vx *= -1;
				}
				//bottom
				else if(525 - this.y <= ViewSpecs.ballRadius) {
					this.vy *= -1;
				}
			}
		}
		
		
		//drop into pockets
		public void drop() {
			//right top corner
			if(425 <= this.x && 275 >= this.y ) {
				//right top pocket
				if(Math.abs(ViewSpecs.rightTopX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.rightTopY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
					
					//??
					this.vx = 0;
					this.vy = 0;
				}
				//top center pocket
				else if(Math.abs(ViewSpecs.topCenterX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.topCenterY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
				}
					
			}
			//left top corner
			else if(425 > this.x && 275 >= this.y) {
				//left top pocket
				if(Math.abs(ViewSpecs.leftTopX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.leftTopY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
					
					this.vx = 0;
					this.vy = 0;
				}
				//top center pocket
				else if(Math.abs(ViewSpecs.topCenterX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.topCenterY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
					
					this.vx = 0;
					this.vy = 0;
				}
						
			}
			//right bottom corner
			else if(425 <= this.x && 275 < this.y) {
				//right bottom pocket
				if(Math.abs(ViewSpecs.rightBottomX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.rightBottomY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;

					this.vx = 0;
					this.vy = 0;
				}
				//bottom center pocket
				else if(Math.abs(ViewSpecs.BottomCenterX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.BottomCenterY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
					
					this.vx = 0;
					this.vy = 0;
				}
						
			}
			//left bottom corner
			else {
				//left bottom pocket
				if(Math.abs(ViewSpecs.leftBottomX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.leftBottomY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
					
					this.vx = 0;
					this.vy = 0;
				}
				//bottom center pocket
				else if(Math.abs(ViewSpecs.BottomCenterX - this.x) <= ViewSpecs.pocketRadius
						&& Math.abs(ViewSpecs.BottomCenterY - this.y) <= ViewSpecs.pocketRadius) {
					this.alive = false;
					
					this.vx = 0;
					this.vy = 0;
				}
			}			
		}
		
		//calculate square of difference between a and b
		static double square(double a, double b) {
			return (a - b) * (a - b);
		}
}
