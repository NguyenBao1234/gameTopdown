package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import CoreGame.PlayerComponent.Player;
import CoreGame.WidgetComponent.HUD;
import GameContent.MainPlayer;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

public class FootTrap extends ObjectPendOnPlayer implements InteractInterface {
    private boolean isActive = false;
    private boolean isOpen = true;
    private final int requiredInteractions = 5; // Number of presses needed to escape
    private int currentInteractions = 0;
    private MainPlayer trappedPlayer;
    private NarrativeMessageWD DialogueWD;

    public FootTrap()
    {
        SetupAnimation();
        setCollisionMode(Collision.Overlap);
        setCollisionArea(0, 32, 10 * GamePanel.scale, 10 * GamePanel.scale);
        //setCollisionArea(x,y,a,b); chinh lai de nhan vat di sau vao hon
    }

    private void SetupAnimation() {
      if (isOpen) { Sprite = ImageUtility.LoadImage("/Objects/FootTrap/foottrap.png");}
      else
      {Sprite = ImageUtility.LoadImage("/Objects/FootTrap/foottrap-closed-end.png");}
    }

    @Override
    public void OnBeginOverlapped(Entity otherEntity)
    {
        if(otherEntity instanceof Player) {
            immobilizePlayer((MainPlayer) otherEntity);
            ApplyDamage(otherEntity, this, 20);
        }
    }

    private void immobilizePlayer(MainPlayer player)
    {
        System.out.println("Can't move");
        DialogueWD = new NarrativeMessageWD("Haha You are trapped! Spam E to escape");
        HUD.AddWidget(DialogueWD);
        isActive = true;
        currentInteractions = 0; // Reset interactions
        trappedPlayer = player;
        trappedPlayer.SetFreeToControl(false);
        flipBook = ImageUtility.makeFlipBook("/Objects/FootTrap/trapclosing"); // Closing state
        fpsPerImage = 4;
    }

    @Override
    public void interact()
    {
        if (isActive) {
            onEscapeAttempt(trappedPlayer);
        }
    }

    private void onEscapeAttempt(MainPlayer player)
    {
        if (!isActive) return; // If trap is not active, ignore

        currentInteractions++;
        if (currentInteractions >= requiredInteractions) {
            releasePlayer(trappedPlayer);
        } else {
            DialogueWD.SetMessages("Press E to escape (" + currentInteractions + "/" + requiredInteractions + ")");
        }
    }

    private void releasePlayer(MainPlayer player)
    {
        flipBook = null;
        isOpen = false;
        SetupAnimation();
        isActive = false;
        trappedPlayer.SetFreeToControl(true);
        HUD.RemoveWidget(DialogueWD);
        DialogueWD = null;
    }

}
