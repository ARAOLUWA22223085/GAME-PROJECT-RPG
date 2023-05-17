package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; //16px means 16x16 tiles
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48 tile size; essentially scaling up the initial sprite sizes to be more visible in a modern computer program
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // Screen width in 16*48px wide
	public final int screenHeight = tileSize * maxScreenRow; // Screen Height is 12*48px tall
	public final int maxScreenSize = maxScreenCol * maxScreenRow;

	//WORLD MAP PARAMETERS
	public final int maxWorldCol = 100;
	public final int maxWorldRow = 100;
//	public final int worldWidth = tileSize * maxWorldCol;
//	public final int maxHeight = tileSize * maxWorldRow;
	
	//FPS Setter
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;

	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[30];
	
	
	
	public GamePanel()	{
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground (Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener (keyH);
		this.setFocusable(true);
	
	}

	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(0);
	}
	
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
// A variant of the 60FPS loop code I used, apparently there were issues with the "sleep.Thread" function
//not being entirely accurate so i used an alternative
//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000/FPS; //draws the screen every 0.01666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		
//		while(gameThread != null) {
//			
//			long currentTime = System.nanoTime();	
//			System.out.println("current time is, "  + currentTime);
//						
//			// 1 Update: Update information such as character and enemy positions
//			update();
//			
//			
//			
//			// 2 Draw: draw the screen with the updated information
//			repaint();
//			
//			
//			try {
//				
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//					
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval; 
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			 
//			//	If pressing the "S" key moves the down by 3 pixels the constant updating means that the next version of the game panel displayed 
//			//	will now show the character 3 pixels lower down than it was previously using the sequential still images to create the idea of movement
//			}
//		}
//	
	
	
//Segment below is dedicated to making sure that the computer which is able to do the coordinate change calculations exceedingly fast is able
//to slow down to a speed at which it allows us to be able to see the movement of the character.
//
//It works by first using the system time in nanoseconds divided by 60 to figure out the time to would take to display one frame at a speed of 60 FPS
//Then, using that value, we take the system time at the moment of initialisation and the current time  both converted to seconds and divides it by 
//the drawInterval, 
//
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0 ;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
		
			if (delta >= 1){
				try {
					update();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
				
				delta --; 
				drawCount ++ ;
				
				if (timer >= 1000000000) {
//					System.out.println("FPS: "+ drawCount);
					drawCount = 0;
					timer = 0;
				}
			}
		}
	}
	
	public void update() throws InterruptedException {
	
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
//		Tiles
		tileM.draw(g2);

//		Objects
		for (int i = 0; i < obj.length; i++) {
		
			if(obj[i] != null) {
		
				obj[i].draw(g2, this);
			}
		}
		
//		Player
		player.draw(g2);
		
		g2.dispose(); 
		
//		UI
		ui.draw(g2);
	}

	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic(int i) {
		music.stop();
	}
	
	public void playSE (int i) {
		se.setFile(i);
		se.play();
	}
	
}