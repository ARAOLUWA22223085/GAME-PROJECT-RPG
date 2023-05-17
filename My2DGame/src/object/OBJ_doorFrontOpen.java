package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_doorFrontOpen extends SuperObject {
	
	public OBJ_doorFrontOpen() {
		
		name = "OBJ_doorFrontOpen";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/doorFrontOpen.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}	
		
		collision = true;
	}

}
