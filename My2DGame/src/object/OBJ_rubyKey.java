package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_rubyKey extends SuperObject{

	public OBJ_rubyKey() {
		
		name = "OBJ_rubyKey";
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/rubyKey.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;

	}
	
}
