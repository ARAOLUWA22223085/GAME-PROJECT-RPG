package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_chestRightClosed extends SuperObject{

	public OBJ_chestRightClosed() {
		
		name = "OBJ_chestRightClosed";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chestRightClosed.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
