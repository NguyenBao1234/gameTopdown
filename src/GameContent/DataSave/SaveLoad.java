package GameContent.DataSave;

import CoreGame.GamePanel;

import java.io.*;


public class SaveLoad {
    GamePanel GamePanel;
    public SaveLoad ( GamePanel GamePanel){
        this.GamePanel = GamePanel;

    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Resource/SaveLoad.dat"));
        DataStorage dataStorage = new DataStorage();
        //player
        dataStorage.health = GamePanel.player.getHealth();
        dataStorage.map = GamePanel.currentMapIndex;
        dataStorage.playerX=GamePanel.GetInst().player.worldX;
        dataStorage.playerY = GamePanel.GetInst().player.worldY;

        //obj on maps
        oos.writeObject(dataStorage);
    }
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Resource/SaveLoad.dat"));

        DataStorage dataStorage = (DataStorage)ois.readObject();
        //player
        GamePanel.player.setHealth(dataStorage.health);
        GamePanel.currentMapIndex = dataStorage.map;
        GamePanel.GetInst().player.worldX = dataStorage.playerX;
        GamePanel.GetInst().player.worldY = dataStorage.playerY;

        //obj on maps
    }
}
