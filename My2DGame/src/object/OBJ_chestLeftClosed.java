package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_chestLeftClosed extends SuperObject{

	public OBJ_chestLeftClosed() {
		
		name = "OBJ_chestLeftClosed";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chestLeftClosed.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
