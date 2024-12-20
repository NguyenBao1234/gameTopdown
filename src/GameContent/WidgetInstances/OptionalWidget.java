package GameContent.WidgetInstances;

import CoreGame.WidgetComponent.Widget;
import CoreGame.WidgetComponent.HUD;

public abstract class OptionalWidget extends Widget
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
