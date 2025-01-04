package GameContent.NPC.NonThreatening;

import CoreGame.GamePanel;
import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.HUD;
import GameContent.Object.MasterObject.InteractInterface;
import GameContent.Object.MasterObject.ObjectPendOnPlayer;
import GameContent.WidgetInstances.NarrativeMessageWD;
import HelpDevGameTool.ImageUtility;

import java.awt.event.KeyEvent;

public class Loop_NPC extends ObjectPendOnPlayer implements InteractInterface {
    String[] sentences = {
            "Ta đã thấy...","...rất nhiều người như ngươi đã đến đây",
            "...nhưng không ai có thể sống sót",
            "Rừng này đầy rẫy những cạm bẫy và quái vật...",
            "Nếu ngươi muốn tiếp tục đi...","hãy cẩn thận",
            "Ta có thể cho ngươi một lời khuyên",
            "Đừng bao giờ tin vào những gì ngươi nhìn thấy...",
            "Ta đã từng như ngươi...","đầy tham vọng và tò mò",
            "Nhưng rồi ta nhận ra...","lối thoát...?"," Nó chưa từng tồn tại đối với ta",
            "Ngươi có thấy những bia mộ kia không? ", "Đó là lịch sử của rừng này...",
            "là câu chuyện về những người đã từng đến đây...","...và thất bại",
            "Cái giá phải trả rất lớn...", "Liệu ngươi có sẵn sàng tiếp tục không..?",
            "Hỡi người bạn nhỏ của ta..."
    };
    int speedDefaultPlayer;
    public int length = sentences.length;
    public NarrativeMessageWD DialogueWD = new NarrativeMessageWD(sentences);
    public Loop_NPC()
    {
        flipBook = ImageUtility.makeFlipBook("/Morph/Idle");
        fpsPerImage = 6;
        SpriteRenderSizeX = 17 * GamePanel.scale;
        SpriteRenderSizeY = 28 * GamePanel.scale;

    }


    @Override
    public void interact(){
        if(DialogueWD.IsOnScreen()){
            DialogueWD.SetMessages(true,sentences);
            if(KeyHandler.isKeyPressed(KeyEvent.VK_Q)){
                HUD.RemoveWidget(DialogueWD); // an dong thoi q vs e thi se tat thoai
            }
            if(!DialogueWD.IsOnScreen() ){
                GamePanel.GetInst().player.setSpeed(speedDefaultPlayer);
                DialogueWD.setCurrMessageIndex(0);
            }

        }
        else {
            HUD.AddWidget(DialogueWD);
            speedDefaultPlayer = GamePanel.GetInst().player.getSpeed();
            GamePanel.GetInst().player.setSpeed(0);

        }
    }
}
