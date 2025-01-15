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
        dataStorage.health = GamePanel.GetInst().getPlayer().getCurrentHealth();
        dataStorage.map = GamePanel.GetInst().currentMapIndex;
        dataStorage.playerX=GamePanel.GetInst().getPlayer().worldX;
        dataStorage.playerY = GamePanel.GetInst().getPlayer().worldY;

        //obj on maps
        oos.writeObject(dataStorage);
    }
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Resource/SaveLoad.dat"));

        DataStorage dataStorage = (DataStorage)ois.readObject();
        //player
        GamePanel.GetInst().getPlayer().setCurrentHealth(dataStorage.health);
        GamePanel.GetInst().currentMapIndex = dataStorage.map;
        GamePanel.GetInst().getPlayer().worldX = dataStorage.playerX;
        GamePanel.GetInst().getPlayer().worldY = dataStorage.playerY;
        GamePanel.GetInst().getPlayer().UpdateStateWD();

        //obj on maps
    }
}
