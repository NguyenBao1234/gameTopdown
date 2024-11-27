package Entity.Object;

import CoreGame.Enums.Collision;
import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends BaseObject
{
    public OBJ_Door()
    {
        name = "Door";
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        collisionMode = Collision.Block;
    }
}
