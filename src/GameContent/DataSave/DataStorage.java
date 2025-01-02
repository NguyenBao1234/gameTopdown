package GameContent.DataSave;

import java.io.Serializable;

public class DataStorage implements Serializable {
    //player stats
    float health;
    int map;
    int playerX;
    int playerY;

    // subject on maps
    String mapObjectNames [][];
    int mapObjectWorldX [][];
    int mapObjectWorldY [][];
    boolean mapObjectOpened[][];
}
