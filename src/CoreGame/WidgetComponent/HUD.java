package CoreGame.WidgetComponent;

import java.awt.*;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class HUD
{
    private static HUD Inst;
    Font arial_40 = new Font("Arial", Font.PLAIN, 40);
    Font arial_80B = new Font("Arial", Font.PLAIN, 80);
    private static Graphics2D g2;
    public int commandNum = 0;

    private final static ArrayList<BaseWidget> ChildWidgets = new ArrayList<>();

    public static HUD getInst()
    {
        if(Inst == null) Inst = new HUD();
        return Inst;
    }

    public static void Draw(Graphics2D graphics2D)
    {
        if(ChildWidgets.isEmpty()) return;
        for(BaseWidget widget : ChildWidgets)
        {
            if(widget == null) continue;
            widget.Draw(graphics2D);
        }
    }

    public static void AddWidget(BaseWidget Widget)
    {
        if(getInst().ChildWidgets.contains(Widget)) return;
        getInst().ChildWidgets.add(Widget);
    }

    public static void RemoveWidget(BaseWidget Widget)
    {
        getInst().ChildWidgets.remove(Widget);
    }

    public static BaseWidget[] GetAllChildWidgets()
    {
        return getInst().ChildWidgets.toArray(new BaseWidget[0]);
    }

    public static boolean IsWidgetOnScreen(BaseWidget Widget)
    {
        return ChildWidgets.contains(Widget);
    }

}
