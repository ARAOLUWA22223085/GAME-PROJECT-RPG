package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_hellStompers extends SuperObject{

	public OBJ_hellStompers() {
		
		name = "OBJ_hellStompers";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/hellStompers.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
