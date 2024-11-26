package CoreGame;

import Object.OBJ_Chest;
import Object.OBJ_Door;
import Object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter (GamePanel gp)
    {
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 10 * gp.tileSize;
        gp.obj[0].worldY = 4 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 15 * gp.tileSize;
        gp.obj[1].worldY =  4 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 20 * gp.tileSize;
        gp.obj[2].worldY = 4 * gp.tileSize;

    }
}
