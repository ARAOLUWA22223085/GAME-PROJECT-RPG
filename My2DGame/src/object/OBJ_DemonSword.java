package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_DemonSword extends SuperObject{

	public OBJ_DemonSword() {
		
		name = "OBJ_DemonSword";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/demonSwordInStone.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
