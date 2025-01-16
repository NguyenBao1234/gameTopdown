package GameContent.DataSave;

import POWJ.GameSaverComponent.GameSaver;


public class DataStorage extends GameSaver {
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
