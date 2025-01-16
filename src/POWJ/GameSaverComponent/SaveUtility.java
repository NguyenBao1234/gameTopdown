//Copyright POWGameStd
package POWJ.GameSaverComponent;

import java.io.*;
import java.net.URL;

public class SaveUtility
{
    /**
     * Save GameSaver object as file 'SlotName'.powsav
     * @param GameSaverObject GameSaver type object
     * @param SlotName Name of file save
     */
    static public boolean SaveToSlot(GameSaver GameSaverObject, String SlotName)
    {
        if(SlotName.isBlank()||GameSaverObject == null) return false;
        String outputFileName = SlotName.endsWith(".powsav") ? SlotName : SlotName + ".powsav";
        String saveDirectoryPath = System.getProperty("user.dir") + File.separator + "save";

        File saveDirectory = new File(saveDirectoryPath);
        if (!saveDirectory.exists() && !saveDirectory.mkdirs()) //Tao thu muc
        {
            throw new RuntimeException("Failed to create save directory: " + saveDirectoryPath + "; Something block this progress");
        }
        String fullFilePath = saveDirectoryPath + File.separator + outputFileName;
        try {
            ObjectOutputStream objectOStream = new ObjectOutputStream(new FileOutputStream(fullFilePath));
            objectOStream.writeObject(GameSaverObject);
            System.out.println("Save!"+fullFilePath);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check whether File save " 'SlotName'.powsav" exist or not
     * @param SlotName Name of file save
     * @return true if file exists, otherwise false
     */
    static public boolean DoesGameSaveExist(String SlotName)
    {
        String fullFileName = SlotName.endsWith(".powsav") ? SlotName : SlotName + ".powsav";
        String saveDirectoryPath = System.getProperty("user.dir") + File.separator + "save";
        File saveDirectory = new File(saveDirectoryPath);
        File saveFile = new File(saveDirectory,fullFileName);
        System.out.println(saveDirectoryPath);
        return saveFile.exists();
    }

    /**
     * Get Game Saver object from file 'SlotName'.powsav
     * @param SlotName Name of file save
     * @return GameSaver object
     */
    static public GameSaver GetGameSaveFromSlot(String SlotName)
    {
        String fullFileName = SlotName.endsWith(".powsav") ? SlotName : SlotName + ".powsav";
        String saveDirectoryPath = System.getProperty("user.dir") + File.separator + "save";
        String FileSavePath = saveDirectoryPath + File.separator + fullFileName;

        ObjectInputStream objectIStream = null;
        try
        {
            objectIStream = new ObjectInputStream(new FileInputStream(FileSavePath));

            if(objectIStream == null)
            {
                System.out.println(SlotName+" hasn't existed yet");
                return null;
            }
            GameSaver GameSaverObj = (GameSaver) objectIStream.readObject();
            System.out.println(SlotName+" Success Load");
            return GameSaverObj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
