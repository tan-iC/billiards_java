package view_controller;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import common.CommonSpecs;
import model.Ball;
import model.Action;

public class MVCApp extends Frame implements ActionListener, MouseListener, MouseMotionListener{
	
		//buttons
		private Button resetButton;
		private Button redoButton;
		private Button showDemoButton;
		
		//panels
		private Panel panel;
		
		//models
		private static Ball[] model = new Ball[10];
		
		//timer
		private Timer timer;
		
		//status
		private enum Status {STOP, DEMO, SIMU};
		private Status status = Status.STOP;
		
		double friction = 0;
		double dos;
		
		//main
		public static void main(String[] args) {
			//model
			for(int i = 0; i < 10; i++) {
				model[i] = new Ball(i, ViewSpecs.x[i], ViewSpecs.y[i], 0, 0);
			}
			
			
			//ViewConstructor
			new ViewConstructor(model);
			
			//MVCApp
			new MVCApp();
		}
		
		//constructor of MVCApp
		MVCApp(){
			//window tab
			super("billiards model");
			
			//modify window
			setSize(ViewSpecs.flameWidth, ViewSpecs.flameHeight + 25);
			setResizable(false);
			setLayout(null);
			
			addWindowListener(
					new WindowAdapter(){
						public void windowClosing(WindowEvent we) {
							System.exit(0);
						}
			});
			
			//modify font
			this.setFont(new Font("Arial", Font.BOLD, 10));
			
			//modify buttons
			resetButton = new Button("Reset");
			resetButton.setBounds(12, 37, ViewSpecs.buttonWidth, ViewSpecs.buttonHeight);
			showDemoButton = new Button("Demo");
			showDemoButton.setBounds(112, 37, ViewSpecs.buttonWidth, ViewSpecs.buttonHeight);
			redoButton = new Button("Redo");
			redoButton.setBounds(212, 37, ViewSpecs.buttonWidth, ViewSpecs.buttonHeight);
			
			//modify panels
			panel = new Panel();
			panel.setBounds(0, 75, 850, 550);
			panel.addMouseListener(this);
		    panel.addMouseMotionListener(this);
			
			//activate buttons
			add(resetButton);
			add(showDemoButton);
			add(redoButton);
			
			//activate panels
			add(panel);
			
			resetButton.addActionListener(this);
			showDemoButton.addActionListener(this);
			redoButton.addActionListener(this);
			
			//timer
			timer = new Timer(CommonSpecs.temporalEventPeriod, this);
			timer.start();
			
			//become visible
			setVisible(true);
			
			//execute
			try {
				Thread.sleep(50);
			} 
			catch (InterruptedException e) {
				
			}
			
			//paint the first condition
			repaint();
		}
		
		
		
		//View of MVC part 1
		//repaint of those images by buffering
		public void paint(Graphics g) {
			
			//
			timer.stop();
			
			BufferedImage bufimg 
			= new BufferedImage(850, 550, BufferedImage.TYPE_INT_BGR);
			
			Graphics bg = bufimg.createGraphics();
			Graphics2D g2;
			
			g2 = (Graphics2D) bg;
			
			ViewConstructor.drawBall(g2);
			
			//panel
			g2 = (Graphics2D) panel.getGraphics();
			if(bufimg != null) {
				g2.drawImage(bufimg, 0, 0, this);
			}
			
			//message
			if (!model[0].alive) {
	        	java.awt.Toolkit.getDefaultToolkit().beep();
	        	Label label = new Label("Reset");
	        	label.setForeground(Color.BLACK);
	        	JOptionPane.showMessageDialog(this, label);
	        	Action.reset(model);
	        }
			
			//
			timer.restart();
			
		}
		
		@Override
		//controller of MVC
		public void actionPerformed(ActionEvent e) {
			
			//if-then rules for each state
			if(e.getSource() == resetButton) {
				if(status == Status.STOP || status == Status.DEMO) {
					//change status and stop event
					
					status = Status.STOP;
					
					//replace balls into the first condition
					Action.reset(model);
				}
			}
			else if(e.getSource() == showDemoButton) {
				if(status == Status.STOP) {
					//
					timer.stop();
					
					//message
		        	Label label = new Label("No conflict Demonstration");
		        	label.setForeground(Color.BLACK);
		        	JOptionPane.showMessageDialog(this, label);
		        	
		        	//
		        	timer.restart();
					
					//change status and start event with timer
					status = Status.DEMO;
					timer.start();
					
					//replace balls into the first condition
					Action.reset(model);
					
					//set velocity
					Action.showDemo(model[0]);
					
					//no friction
					friction = 0;
					model[0].move(friction);
				}
			}
			else if(e.getSource() == redoButton) {
				if(status == Status.STOP) {
					//re-do
					Action.redo(model);
				}
			}
			else {
				
				dos = 0;
				for(int i = 0; i < 10; i++) {
					dos = Math.abs(model[i].vx) + Math.abs(model[i].vy);
					if(dos > 0) {
						break;
					}
				}
				
				if(model[0].alive == false) {
					//simulation is stopped
					status = Status.STOP;
				}
				
				else if(dos == 0) {
					
					//simulation is stopped
					status = Status.STOP;
					
				}
				
				
				
				//check hit, or not
				for(int i = 0; i < 9; i++) {
					for(int j = 1 + i; j < 10; j++) {
						
						if(model[i].alive == true && model[j].alive == true) {
							//constructor
							Action check = new Action(model[i], model[j]);
							
							//distance between centers of two balls
							if(check.distance <= 2 * ViewSpecs.ballRadius - 5) {
								check.conduct();
							}
						}	
						
					}
				}
				
				for(int i = 0; i < 10; i++) {
					
					//move
					model[i].move(friction);
					
					//drop
					model[i].drop();
					
					//reflect
					model[i].reflect();
				}
				
				//effect of friction and stop
				
				
			}
			//refresh the monitor
			repaint();
			
		}
		
		//
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(e.getSource() == panel && dos == 0 && status != Status.DEMO) {
				//set current coordinates as the latest
				for(int j = 0; j < 10; j++) {
					model[j].lateX = model[j].x;
					model[j].lateY = model[j].y;
					model[j].lateAlive = model[j].alive;
				}
				
				//change status and start event with timer
				status = Status.SIMU;
				
				//friction??
				friction = Ball.e;
				
				//set velocity of cue
				Action.clicked(e.getX(), e.getY(), model[0]);
				
				//
				timer.start();
					
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
