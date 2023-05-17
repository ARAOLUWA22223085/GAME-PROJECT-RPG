package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
		
	public final int screenX;
	public final int screenY;
	
	public int hasrubyKey = 0;
	int hasdemonSword = 0;
	int hasheroSword = 0;
	int hashellStompers = 0;


	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x ;
		solidAreaDefaultY  = solidArea.y;		
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}

public void setDefaultValues() {
	//Set player's default position and speed	
	worldX = 10 * gp.tileSize;
	worldY = 10 * gp.tileSize;
	speed = 3;
	direction = "down";
	
	}
	
public void getPlayerImage() {
	
	try {
		
		up0 = ImageIO.read(getClass().getResource("/player/doomKnightBack0.png"));
		up1 = ImageIO.read(getClass().getResource("/player/doomKnightBack1.png"));
		up2 = ImageIO.read(getClass().getResource("/player/doomKnightBack2.png"));
		
		down0 = ImageIO.read(getClass().getResource("/player/doomKnightFront0.png"));
		down1 = ImageIO.read(getClass().getResource("/player/doomKnightFront1.png"));
		down2 = ImageIO.read(getClass().getResource("/player/doomKnightFront2.png"));

		left0 = ImageIO.read(getClass().getResource("/player/doomKnightLeft0.png"));
		left1 = ImageIO.read(getClass().getResource("/player/doomKnightLeft1.png"));
		left2 = ImageIO.read(getClass().getResource("/player/doomKnightLeft2.png"));

		right0 = ImageIO.read(getClass().getResource("/player/doomKnightRight0.png"));
		right1 = ImageIO.read(getClass().getResource("/player/doomKnightRight1.png"));
		right2 = ImageIO.read(getClass().getResource("/player/doomKnightRight2.png"));

		
	}catch(IOException e) {
		e.printStackTrace();
	}
}



	public void update() throws InterruptedException {
		
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			
		if(keyH.upPressed == true ) {
			direction = "up";
		}
		else if(keyH.downPressed == true ) {
			direction = "down";
		}
		else if(keyH.leftPressed == true ) {
			direction = "left";
		}
		
		else if(keyH.rightPressed == true ) {
			direction = "right";
		}

//			check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
//			check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
						
//			if tile collision is false, player can move
			
			if(collisionOn == false) {
				
				switch(direction) {
				case "up": worldY -= speed; break;

				case "down": worldY += speed; break;
					
				case "left": worldX -= speed; break;
					
				case "right": worldX += speed; break;
									
				}
						
		}
		
		spriteCounter ++ ;
		
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 4;
			}
			else if (spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
			}
		}	
		else if (keyH.upPressed == false || keyH.downPressed == false || keyH.leftPressed == false || keyH.rightPressed == false){
			spriteNum = 1;
		}
	}
	
	public void pickUpObject(int i) throws InterruptedException {
		
		if(i != 999) {
			
			String objectName = gp.obj[i].name;
			
			switch (objectName) {

			case "OBJ_hellStompers":
				
				gp.playSE(2);
				
				hashellStompers ++ ;

				System.out.println("You have found the boots of demon renown for his speed!");
				System.out.println("You put them on and feel demonic energy swell in you legs and feet!");
				
				System.out.println("You are now able to run faster!");
				
				if (hashellStompers >= 0) 
				{
					speed = speed + (speed/2);
				}
				
				
				gp.obj[i] = null;
				
				break;
			
			case "OBJ_rubyKey":
				
				gp.playSE(1);
				
				hasrubyKey++;

				System.out.println("You have obtained a ruby Key!");
				System.out.println("you can probably use it somewhere in this courtyard...");
				System.out.println("Keys currently held: " + hasrubyKey);

				gp.obj[i] = null;
				
				break;
				
			case "OBJ_doorFrontClosed":
				
				if (hasrubyKey == 0) {
					System.out.println("hmm, the door seems to be locked... Maybe a key is somewhere around here...");
				}
				if (hasrubyKey != 0) {
					gp.playSE(3);
					hasrubyKey--;
					System.out.println("You have used a ruby Key to unlock the door!");
					gp.obj[i] = null;
				}
				
				break;

			case "OBJ_DemonSword":

				gp.playSE(1);
				
				hasdemonSword++;
				System.out.println("You have obtained a forgotten Demon's Sword!");
				Thread.sleep(2000);
				System.out.println("You overflow a with malignant, Demonic energy!");
				Thread.sleep(2000);
				System.out.println("Demon's Sword currently held: " + hasdemonSword);
				Thread.sleep(2000);
				gp.obj[1] = null;
				System.out.println("The other sword crumbles. you hear a faint voice from it's direction before it does");
				System.out.println("'You have chosen the path of evil...'");
				Thread.sleep(2000);
				System.out.println("'You shall reap what you sow O young one'");
				Thread.sleep(5000);
				gp.obj[0] = null;

				break;
				
			case "OBJ_Sword":
				
				gp.playSE(1);
				
				hasheroSword++;
				System.out.println("You have obtained a forgotten Hero's Sword!");
				Thread.sleep(2000);
				System.out.println("You are imbued with a Pure, Divine energy!");
				Thread.sleep(2000);
				System.out.println("Hero's Sword currently held: " + hasheroSword);
				Thread.sleep(2000);
				gp.obj[1] = null;
				System.out.println("The other sword crumbles. you hear a faint voice from it's direction before it does");
				Thread.sleep(2000);
				System.out.println("'You have chosen the path of righteousness!?'");
				Thread.sleep(2000);
				System.out.println("'You shall reap what you sow O foolish child of man...'");
				Thread.sleep(5000);
				gp.obj[0] = null;
				
				break;

			case "":
				break;
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {


//		g2.setColor(Color.white);
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
//		white rectangle used in testing to confirm character movement
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up0;
			}
			if(spriteNum == 2) {
				image = up1;
			}

			if(spriteNum == 3) {
				image = up0;	
			}

			if(spriteNum == 4) {
				image = up2;	
			}
			
			break;
			
		case "down":
			if(spriteNum == 1) {
				image = down0;
			}
			if(spriteNum == 2) {
				image = down1;
			}

			if(spriteNum == 3) {
				image = down0;
			}
			
			if(spriteNum == 4) {
				image = down2;
			}
			
			break;
			
		case "left":
			if(spriteNum == 1) {
				image = left0;
			}
			if(spriteNum == 2) {
				image = left1;
			}

			if(spriteNum == 3) {
				image = left2;
			}
			
			if(spriteNum == 4) {
				image = left0;
			}
			
			break;
			
		case "right":
			if(spriteNum == 1) {
				image = right0;
			}
			if(spriteNum == 2) {
				image = right1;
			}

			if(spriteNum == 3) {
				image = right2;
			}
			
			if(spriteNum == 4) {
				image = right0;
			}
		
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null);
	}
	
}
