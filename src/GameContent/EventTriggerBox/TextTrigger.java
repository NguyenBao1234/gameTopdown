package GameContent.EventTriggerBox;

import CoreGame.Data.Enums.Collision;
import CoreGame.EntityComponent.Entity;
import GameContent.MainPlayer;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;

public class TextTrigger extends ObjectPendOnPlayer implements InteractInterface
{
    private final NarrativeMessageWD HintMessage;
    public TextTrigger(String... Messages)
    {
        super();
        CollisionMode = Collision.Overlap;
        HintMessage = new NarrativeMessageWD(Messages);
    }
    @Override
    public void OnBeginOverlapped(Entity otherEntity) {
        super.OnBeginOverlapped(otherEntity);
        if(otherEntity instanceof MainPlayer)HintMessage.AddToViewport();
    }

    @Override
    public void OnEndOverlapped(Entity otherEntity) {
        super.OnEndOverlapped(otherEntity);
        if(otherEntity instanceof MainPlayer && HintMessage.IsOnScreen())HintMessage.RemoveFromHUD();
    }

    @Override
    public void interact() {
        if(HintMessage.IsOnScreen()) HintMessage.NextContent();
    }
}
