package CoreGame;

import java.awt.*;

public class EventHandler
{
    EventRect eventRect[][][];
    int previousEventX,  previousEventY;
    boolean canTouchEvent = true;

    public EventHandler()
    {
        eventRect = new EventRect[GamePanel.maxMap][50][50];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < GamePanel.maxMap && col < 50 && row < 50){
            eventRect[map][col][row]= new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 4;
            eventRect[map][col][row].height = 4;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if(col== 50){
                col = 0;
                row++;
                if(row == 50){
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent(){
        int xDistance = Math.abs(GamePanel.getInstGamePanel().player.worldX - previousEventX);
        int yDistance = Math.abs(GamePanel.getInstGamePanel().player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);

        if (distance > GamePanel.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true) {
            if (hit(0,10,2,"any") == true){teleport(1,0,5);} // mọi thứ dều trừ 1 tính theo map
            else if (hit(1,0,5,"any") == true){teleport(0,10,2);}
        }
    }
    public boolean hit(int map, int col, int row, String reqDirection){
        boolean hit = false;
        if(map == GamePanel.getInstGamePanel().currentMap){
            GamePanel.getInstGamePanel().player.getCollisionArea().x = GamePanel.getInstGamePanel().player.worldX + GamePanel.getInstGamePanel().player.getCollisionArea().x;
            GamePanel.getInstGamePanel().player.getCollisionArea().y = GamePanel.getInstGamePanel().player.worldY + GamePanel.getInstGamePanel().player.getCollisionArea().y;
            eventRect[map][col][row].x = col* GamePanel.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row* GamePanel.tileSize + eventRect[map][col][row].y;

            if(GamePanel.getInstGamePanel().player.getCollisionArea().intersects(eventRect[map][col][row])  && eventRect[map][col][row].eventDone == false){
                if(GamePanel.getInstGamePanel().player.getCurrentDirection().equals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;

                    previousEventX = GamePanel.getInstGamePanel().player.worldX;
                    previousEventY = GamePanel.getInstGamePanel().player.worldY;
                    //eventRect[map][col][row].eventDone = true;
                }
            }
            GamePanel.getInstGamePanel().player.getCollisionArea().x = 2* GamePanel.scale; //gia tri ben class player
            GamePanel.getInstGamePanel().player.getCollisionArea().y = 17* GamePanel.scale;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void teleport(int map, int col, int row){
        GamePanel.getInstGamePanel().currentMap = map;
        GamePanel.getInstGamePanel().player.worldX = col* GamePanel.tileSize;
        GamePanel.getInstGamePanel().player.worldY = row* GamePanel.tileSize;
        previousEventX = GamePanel.getInstGamePanel().player.worldX;  //bỏ 2 dòng này thì xảy ra liên tiếp
        previousEventY = GamePanel.getInstGamePanel().player.worldY;
        canTouchEvent = false;
    }
}
