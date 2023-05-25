package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_chestFrontClosed extends SuperObject{

	public OBJ_chestFrontClosed() {
		
		name = "OBJ_chestFrontClosed";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chestFrontClosed.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
