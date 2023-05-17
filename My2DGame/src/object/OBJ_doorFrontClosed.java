package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_doorFrontClosed extends SuperObject {
	
	public OBJ_doorFrontClosed() {
		
		name = "OBJ_doorFrontClosed";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/doorFrontClosed.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}

}
