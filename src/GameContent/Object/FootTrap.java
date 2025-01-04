package GameContent.Object;

import CoreGame.Data.Enums.Collision;
import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import CoreGame.PlayerComponent.Player;
import GameContent.MainPlayer;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

public class FootTrap extends ObjectPendOnPlayer implements InteractInterface {
    private boolean isActive = false; // Indicates if the trap is holding the player
    private boolean isOpen = true;
    private final int requiredInteractions = 10; // Number of presses needed to escape
    private int currentInteractions = 0; // Current number of presses
    private MainPlayer trappedPlayer;
    private NarrativeMessageWD DialogueWD = new NarrativeMessageWD("Press E to escape");

    public FootTrap()
    {
        SetupAnimation();
        SpriteRenderSizeX = 16 * GamePanel.scale;
        SpriteRenderSizeY = 16 * GamePanel.scale;
        setCollisionMode(Collision.Overlap); // Allow overlapping with the player
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
        }
    }

    private void immobilizePlayer(MainPlayer player)
    {
        System.out.println("Can't move");
        DialogueWD = new NarrativeMessageWD("Press E to escape");
        isActive = true;
        currentInteractions = 0; // Reset interactions
        trappedPlayer = player;
        trappedPlayer.setLocked(true);
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
            // Update the message to show progress
            DialogueWD.SetMessages("Press E to escape (" + currentInteractions + "/" + requiredInteractions + ")");
        }
    }

    private void releasePlayer(MainPlayer player)
    {
        flipBook = null;
        isOpen = false;
        SetupAnimation();
        isActive = false;
        trappedPlayer.setLocked(false);
    }

}
