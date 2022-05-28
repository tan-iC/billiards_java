package view_controller;

public class ViewSpecs {

	//flame
	public static final int flameWidth = 850;
	public static final int flameHeight = 600;
	
	//buttons
	public static final int buttonWidth = 75;
	public static final int buttonHeight = 25;
	
	//pool
	public static final int poolWidth = 800;
	public static final int poolHeight = 500;
	
	//pockets
	public static final double pocketRadius = 37;
	
	public static final double leftTopX 		= 37;
	public static final double leftTopY 		= 37;
	public static final double topCenterX 		= 425;
	public static final double topCenterY 		= 37;
	public static final double rightTopX 		= 850 - 37;
	public static final double rightTopY 		= 37;
	public static final double leftBottomX		= 37;
	public static final double leftBottomY 		= 550 - 37;
	public static final double BottomCenterX	= 425;
	public static final double BottomCenterY	= 550 - 37;
	public static final double rightBottomX		= 850 - 37;
	public static final double rightBottomY		= 550 - 37;
	
	
	//balls and their center coordinates
	public static final double c = 625;
	public static final double s = 275;
	
	public static final double ballRadius = 25 / 2;
	
	public static final double x0 = 225;
	public static final double y0 = s;
	
	public static final double x1 
	= c - 4 * ballRadius * Math.cos(Math.PI / 6);
	public static final double y1 = s;
	
	public static final double x2 
	= c - 2 * ballRadius * Math.cos(Math.PI / 6);
	public static final double y2 
	= s - 2 * ballRadius * Math.sin(Math.PI / 6);
	
	public static final double x3 = c;
	public static final double y3 = s - 2 * ballRadius;
	
	public static final double x4
	= c + 2 * ballRadius * Math.cos(Math.PI / 6);
	public static final double y4
	= s - 2 * ballRadius * Math.sin(Math.PI / 6);
	
	public static final double x5 
	= c + 4 * ballRadius * Math.cos(Math.PI / 6);
	public static final double y5 = s;
	
	public static final double x6 
	= c + 2 * ballRadius * Math.cos(Math.PI / 6);
	public static final double y6
	= s + 2 * ballRadius * Math.sin(Math.PI / 6);
	
	public static final double x7 = c;
	public static final double y7 = s + 2 * ballRadius;
	
	public static final double x8 
	= c - 2 * ballRadius * Math.cos(Math.PI / 6);
	public static final double y8
	= s + 2 * ballRadius * Math.sin(Math.PI / 6);
	
	public static final double x9 = c;
	public static final double y9 = s;
	
	//arrays of coordinates
	public static final double[] x
	= {x0, x1, x2, x3, x4, x5, x6, x7, x8, x9}; 
	
	public static final double[] y 
	= {y0, y1, y2, y3, y4, y5, y6, y7, y8, y9};
	
	
	
}
