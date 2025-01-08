package GameContent.DataSave;

import CoreGame.GamePanel;

import java.io.*;


public class SaveLoad
{
    public void save() throws IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Resource/SaveLoad.dat"));
        DataStorage dataStorage = new DataStorage();
        //player
        dataStorage.health = GamePanel.GetInst().player.getCurrentHealth();
        dataStorage.map = GamePanel.GetInst().currentMapIndex;
        dataStorage.playerX=GamePanel.GetInst().player.worldX;
        dataStorage.playerY = GamePanel.GetInst().player.worldY;

        //obj on maps
        oos.writeObject(dataStorage);
    }
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Resource/SaveLoad.dat"));

        DataStorage dataStorage = (DataStorage)ois.readObject();
        //player
        GamePanel.GetInst().player.setCurrentHealth(dataStorage.health);
        GamePanel.GetInst().currentMapIndex = dataStorage.map;
        GamePanel.GetInst().player.worldX = dataStorage.playerX;
        GamePanel.GetInst().player.worldY = dataStorage.playerY;

        //obj on maps
    }
}
