package main;

import object.OBJ_DemonSword;
import object.OBJ_Sword;
//import object.OBJ_chestBackClosed;
import object.OBJ_chestFrontClosed;
//import object.OBJ_chestLeftClosed;
//import object.OBJ_chestRightClosed;
import object.OBJ_doorFrontClosed;
import object.OBJ_hellStompers;
import object.OBJ_rubyKey;

public class AssetSetter {

	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		
		this.gp = gp;	
	} 
	 public void setObject(){
		 
		 gp.obj[0] = new OBJ_Sword();
		 gp.obj[0].worldX = 12 * gp.tileSize;
		 gp.obj[0].worldY =  20 * gp.tileSize;
		 
		 gp.obj[1] = new OBJ_DemonSword();
		 gp.obj[1].worldX = 12 * gp.tileSize;
		 gp.obj[1].worldY =  22 * gp.tileSize;
		 
		 gp.obj[2] = new OBJ_doorFrontClosed();
		 gp.obj[2].worldX = 10 * gp.tileSize;
		 gp.obj[2].worldY =  9 * gp.tileSize;
		 
		 gp.obj[3] = new OBJ_rubyKey();
		 gp.obj[3].worldX = 11 * gp.tileSize;
		 gp.obj[3].worldY =  21 * gp.tileSize;
		 
		 gp.obj[4] = new OBJ_hellStompers();
		 gp.obj[4].worldX = 35 * gp.tileSize;
		 gp.obj[4].worldY = 12 * gp.tileSize;
		 
		 gp.obj[5] = new OBJ_chestFrontClosed();
		 gp.obj[5].worldX = 14 * gp.tileSize;
		 gp.obj[5].worldY = 4 * gp.tileSize;
		 
//		 gp.obj[6] = new OBJ_chestLeftClosed();		 			 
//		 gp.obj[7] = new OBJ_chestRightClosed();
//		 gp.obj[8] = new OBJ_chestBackClosed();





	 }
	
}
