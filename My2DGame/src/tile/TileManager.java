package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class TileManager extends Entity{

	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum [][];
	
	
	
	public TileManager(GamePanel gp){
		
		this.gp = gp;
		
		tile = new Tile[30];
		mapTileNum = new int[gp.maxWorldCol] [gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/testMap01.txt");
	}


	public void getTileImage() {
		
		try {
//			For moving sprites			
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
			
// Where ID's are assigned to various sprites I drew up	
			
//			grass tiles
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass0.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass1.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));
			
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));
			
//			water tiles
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water0.png"));
			tile[3].collision = true;

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water1.png"));
			tile[4].collision = true;

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water2.png"));
			tile[5].collision = true;

//			wall tiles
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/leftWall1.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/topWall1.png"));
			tile[7].collision = true;

			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottomWall1.png"));
			tile[8].collision = true;

			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottomWallLeft1.png"));
			tile[9].collision = true;

			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/bottomWallRight1.png"));
			tile[10].collision = true;

			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/topWallLeft1.png"));
			tile[11].collision = true;

			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/topWallRight1.png"));
			tile[12].collision = true;

			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rightWall1.png"));
			tile[13].collision = true;

//			Door tiles
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/doorFrontClosed1.png"));
			tile[14].collision = true;

			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/doorFrontOpen1.png"));
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/doorBack1.png"));		

//			misc Environment tiles	
			
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));		
			
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree1.png"));		
			tile[19].collision = true;
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/chestRightClosed2.png"));		
			tile[20].collision = true;
			
			tile[21] = new Tile();
			tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/SwordInStone1.png"));		
			tile[21].collision = true;

			tile[22] = new Tile();
			tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cobbleStone1.png"));		
			
			tile[23] = new Tile();
			tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cobbleStone2.png"));		
			
			tile[24] = new Tile();
			tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cobbleStone3.png"));		
			
			
			
		}catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
// Reads the text files and uses them to make maps easily	
	public void loadMap(String filepath){
		
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			
			int col = 0;
			int row = 0;

			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					
					col++;
					
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;	
				}		
			}
			br.close();
			
		}catch (Exception e){
			
		}
	}
	
// Draws a full grass background for areas of the map that aren't specified to have particular tiles on them
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
//limits the amount of map rendered to a managable amount allowing the game to run smoother
//The fans in my PC stopped trying to help my laptop take flight after this :D
			if (worldX + gp.tileSize > (gp.player.worldX - gp.player.screenX)&&
				worldX - gp.tileSize < (gp.player.worldX + gp.player.screenX)&&
				worldY + gp.tileSize > (gp.player.worldY - gp.player.screenY)&& 
				worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY)) {
			
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				
			}
			
			worldCol++;

			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
				 
			}
		}
	}
}


