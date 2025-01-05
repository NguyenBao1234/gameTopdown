package GameContent.NPC.NonThreatening;

import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

import java.awt.event.KeyEvent;

public class Loop_NPC extends ObjectPendOnPlayer implements InteractInterface
{
    private final NarrativeMessageWD DialogueWD = new NarrativeMessageWD(false,
            "Ta đã thấy...","...rất nhiều người như ngươi đã đến đây",
            "...nhưng không ai có thể sống sót",
            "Rừng này đầy rẫy những cạm bẫy và quái vật...",
            "Nếu ngươi muốn tiếp tục đi...","hãy cẩn thận",
            "Ta có thể cho ngươi một lời khuyên",
            "Đừng bao giờ tin vào những gì ngươi nhìn thấy...",
            "Ta đã từng như ngươi...","đầy tham vọng và tò mò",
            "Nhưng rồi ta nhận ra...","lối thoát...?"," Nó chưa từng tồn tại đối với ta",
            "Ngươi có thấy những bia mộ kia không?", "Đó là lịch sử của rừng này...",
            "là câu chuyện về những người đã từng đến đây...","...và thất bại",
            "Cái giá phải trả rất lớn...", "Liệu ngươi có sẵn sàng tiếp tục không..?",
            "Hỡi người bạn nhỏ của ta...");

    public Loop_NPC()
    {
        flipBook = ImageUtility.makeFlipBook("/Morph/Idle");
        fpsPerImage = 6;
        SpriteRenderSizeX = 17 * GamePanel.scale;
        SpriteRenderSizeY = 28 * GamePanel.scale;
        KeyHandler.getInstance().BindAction(KeyEvent.VK_Q,true, this::QuitDialog);
        DialogueWD.SetSubtitle("Q - Leave \n E - Next");
    }

    @Override
    public void interact()
    {
        if(DialogueWD.IsOnScreen()) DialogueWD.NextContent();
        else
        {
            HUD.AddWidget(DialogueWD);
            GamePanel.GetInst().player.SetFreeToControl(false);
        }
    }

    private void QuitDialog()
    {
        DialogueWD.RemoveFromHUD();
        GamePanel.GetInst().player.SetFreeToControl(true);
        KeyHandler.getInstance().UnbindAction(KeyEvent.VK_Q,this::QuitDialog);
    }
}
