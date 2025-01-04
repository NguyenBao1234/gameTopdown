package GameContent.WidgetInstances;

import CoreGame.SoundComponent.SoundUtility;
import CoreGame.WidgetComponent.Widget;
import CoreGame.WidgetComponent.HUD;

public abstract class OptionalWidget extends Widget
{
    protected int SelectingRowOption = 0;
    private int MaxRowOption;
    protected int SelectingColOption = 0;
    private int MaxColOption;
    protected String[] options;

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
        SoundUtility.playSound(1,false,"/Sound/SFX/UI_Btn/btn_hover_3_405158__rayolf.wav");
    }

    protected void Down ()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingRowOption ++;
        if (SelectingRowOption > MaxRowOption) SelectingRowOption = 0;
        SoundUtility.playSound(1,false,"/Sound/SFX/UI_Btn/btn_hover_3_405158__rayolf.wav");
    }

    protected void Left ()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingColOption--;
        if (SelectingColOption < 0) SelectingColOption = MaxColOption;
        SoundUtility.playSound(1,false,"/Sound/SFX/UI_Btn/btn_hover_3_405158__rayolf.wav");
    }

    protected void Right()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SelectingColOption++;
        if (SelectingColOption > MaxColOption) SelectingColOption = 0;
        SoundUtility.playSound(1,false,"/Sound/SFX/UI_Btn/btn_hover_3_405158__rayolf.wav");
    }

    protected void SelectOption()
    {
        if(!HUD.IsWidgetOnScreen(this)) return;
        SoundUtility.playSound(1,false,"/Sound/SFX/UI_Btn/button-click-03_707040__vilkas_sound__vs-.wav");
    }
}
