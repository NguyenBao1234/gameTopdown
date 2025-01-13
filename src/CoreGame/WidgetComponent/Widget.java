package CoreGame.WidgetComponent;

import java.awt.*;

import static CoreGame.WidgetComponent.HUD.IsWidgetOnScreen;
import static CoreGame.WidgetComponent.HUD.RemoveWidget;
import static CoreGame.WidgetComponent.HUD.AddWidget;

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
