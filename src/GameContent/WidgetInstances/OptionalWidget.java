package GameContent.WidgetInstances;

import CoreGame.KeyHandlerComponent.KeyHandler;
import CoreGame.WidgetComponent.BaseWidget;
import CoreGame.WidgetComponent.HUD;

import java.awt.event.KeyEvent;

public abstract class OptionalWidget extends BaseWidget
{
    protected int SelectingRowOption = 0;
    private int MaxRowOption;
    protected int SelectingColOption = 0;
    private int MaxColOption;


    public void SetMaxOption (int maxRowOption, int maxColOption)
    {
        MaxRowOption = maxRowOption - 1;
        MaxColOption = maxColOption - 1;
    }

    protected void Up ()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingRowOption --;
        if (SelectingRowOption < 0) SelectingRowOption = MaxRowOption;
    }

    protected void Down ()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingRowOption ++;
        if (SelectingRowOption > MaxRowOption) SelectingRowOption = 0;
    }

    protected void Left ()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingColOption--;
        if (SelectingColOption < 0) SelectingColOption = MaxColOption;
    }

    protected void Right()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingColOption++;
        if (SelectingColOption > MaxColOption) SelectingColOption = 0;
    }

    protected abstract void SelectOption();
}
