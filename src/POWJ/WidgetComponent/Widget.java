//Copyright POWGameStd
package POWJ.WidgetComponent;

import java.awt.*;

import static POWJ.WidgetComponent.HUD.IsWidgetOnScreen;
import static POWJ.WidgetComponent.HUD.RemoveWidget;
import static POWJ.WidgetComponent.HUD.AddWidget;

public abstract class Widget
{
    abstract public void Draw(Graphics2D g2);

    public boolean IsOnScreen()
    {
        return IsWidgetOnScreen(this);
    }

    public void RemoveFromHUD()
    {
        RemoveWidget(this);
    }
    public void AddToViewport()
    {
        AddWidget(this);
    }
}
