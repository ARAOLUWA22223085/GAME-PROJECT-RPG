package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_chestBackClosed extends SuperObject{

	public OBJ_chestBackClosed() {
		
		name = "OBJ_chestBackClosed";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chestBackClosed.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
