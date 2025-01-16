//Copyright POWGameStd
package GameContent.DataSave;

import POWJ.GamePanel;
import POWJ.GameSaverComponent.SaveUtility;

public class SaveLoad
{
    public boolean save()
    {
        DataStorage dataStorage = new DataStorage();
        //player
        dataStorage.health = GamePanel.GetInst().getPlayer().getCurrentHealth();
        dataStorage.map = GamePanel.GetInst().currentMapIndex;
        dataStorage.playerX=GamePanel.GetInst().getPlayer().worldX;
        dataStorage.playerY = GamePanel.GetInst().getPlayer().worldY;

        return SaveUtility.SaveToSlot(dataStorage,"GameSave");
    }
    public boolean load()
    {
        if(SaveUtility.DoesGameSaveExist("GamaSave")) return false;
        DataStorage dataStorage = (DataStorage) SaveUtility.GetGameSaveFromSlot("GameSave");
        //player
        GamePanel.GetInst().getPlayer().setCurrentHealth(dataStorage.health);
        GamePanel.GetInst().currentMapIndex = dataStorage.map;
        GamePanel.GetInst().getPlayer().worldX = dataStorage.playerX;
        GamePanel.GetInst().getPlayer().worldY = dataStorage.playerY;
        GamePanel.GetInst().getPlayer().UpdateStateWD();
        //obj on maps

        return true;
    }
}
