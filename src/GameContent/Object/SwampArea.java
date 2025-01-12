package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import GameContent.MainPlayer;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import HelpDevGameTool.ImageUtility;

public class SwampArea extends ObjectPendOnPlayer implements InteractInterface {
    private float speedReduction = 0.5f; // -50% speed
    private MainPlayer slowedPlayer;
    private boolean isSlowing = false;

    public SwampArea() {
        Sprite = ImageUtility.LoadImage("/Objects/Swamp/swamp-ed2.png");
        SpriteRenderSizeX = 64 * GamePanel.scale;
        SpriteRenderSizeY = 64 * GamePanel.scale;
        setCollisionMode(Collision.Overlap);
        setCollisionArea(-64*3/2 + 48,-64*3/2 + 48, 64*GamePanel.scale,64*GamePanel.scale);
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity) {
        if (otherEntity instanceof MainPlayer) {
            slowerPlayer((MainPlayer) otherEntity);
        }
    }

    private void slowerPlayer(MainPlayer player) {
        System.out.println("Swamp slowed");
        slowedPlayer = player;
        isSlowing = true;
        player.currentSpeedFactor = speedReduction;
    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {
        if (otherEntity instanceof MainPlayer && isSlowing) {
            System.out.println("Swamp out");
            MainPlayer player = (MainPlayer) otherEntity;
            player.currentSpeedFactor = 1.0f; // Reset to normal speed
            isSlowing = false;
        }
    }
    @Override
    public void interact() {

    }
}
