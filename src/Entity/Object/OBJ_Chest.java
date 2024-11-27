package Entity.Object;

import javax.imageio.ImageIO;

import java.io.IOException;

public class OBJ_Chest extends BaseObject
{
    public OBJ_Chest()
    {
        name = "Chest";
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
