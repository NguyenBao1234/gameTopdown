package Entity.Object;

import CoreGame.Enums.Collision;

import javax.imageio.ImageIO;

import java.io.IOException;

public class OBJ_Key extends BaseObject
{
    public OBJ_Key()
    {
        name = "key";
        try{
            sprite = ImageIO.read(getClass().getResourceAsStream("/Objects/key.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        collisionMode = Collision.Overlap;
    }

}
