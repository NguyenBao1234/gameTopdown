package CoreGame.WidgetComponent;

import java.awt.*;

import static CoreGame.WidgetComponent.HUD.IsWidgetOnScreen;

public abstract class Widget
{
    abstract public void Draw(Graphics2D g2);

    public boolean IsOnScreen()
    {
        return IsWidgetOnScreen(this);
    }
}
