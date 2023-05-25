package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_rubyKey;
import java.text.DecimalFormat;

public class UI {
	
	GamePanel gp;
	
	Font arial_40, arial_80B;
	BufferedImage rubyKeyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI (GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);

		OBJ_rubyKey key = new OBJ_rubyKey();
		rubyKeyImage = key.image;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
		
	}
		
	public void draw(Graphics2D g2) {
		
		if (gameFinished == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x; 
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x = gp.screenWidth/2 - textLength/2;
			 y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text,x,y);
			
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x = gp.screenWidth/2 - textLength/2;
			 y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text,x,y);
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			text = "Your time taken was: " + dFormat.format(playTime) + " Seconds!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			 x = gp.screenWidth/2 - textLength/2;
			 y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text,x,y);
			
			gp.gameThread = null;
		}
		
		
		
		
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(rubyKeyImage, 0, 5, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasrubyKey, 50, 35);
			
			//Time
			playTime += (double)1/60;
			g2.drawString("playtime: " + dFormat.format(playTime) , 475, 35);
			
			//Message 
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(20F));
				g2.drawString(message, 10, 500);
				
				messageCounter++;
				if (messageCounter >= 120) {
					messageCounter = 0;
					messageOn = false;
				}
		}

			
		} 
	}
}
