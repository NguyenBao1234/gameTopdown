package GameContent.DataSave;

import CoreGame.GamePanel;
import GameContent.MainPlayer;

import java.io.*;

public class SaveLoad {
    GamePanel GamePanel;
    public SaveLoad (){

    }
    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Resource/SaveLoad.dat"));
        DataStorage dataStorage = new DataStorage();
        dataStorage.health = GamePanel.player.getHealth();
        dataStorage.map = GamePanel.currentMapIndex;

        oos.writeObject(dataStorage);
    }
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Resource/SaveLoad.dat"));

        DataStorage dataStorage = (DataStorage)ois.readObject();
        GamePanel.player.setHealth(dataStorage.health);
        GamePanel.currentMapIndex = dataStorage.map;
    }
}
