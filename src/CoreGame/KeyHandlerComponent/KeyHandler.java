package CoreGame.KeyHandlerComponent;

import CoreGame.BinderComponent.MultiBinder;
import CoreGame.BinderComponent.MultiBinderWithParam;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

public class KeyHandler implements KeyListener
{
    private static KeyHandler Inst;
    private boolean[] bPressedButtons = new boolean[255];

    private final MultiBinder ActionBinder[] = new MultiBinder[255];
    private final boolean bPressedActions[] = new boolean[255];
    private final int FrameCount[] = new int[255];

    private final MultiBinderWithParam AxisBinder[] = new MultiBinderWithParam[255];
    private final float AxisValues[] = new float[255];

    public void BindAction(int KeyCode, boolean bPress, Runnable function)
    {
        if(ActionBinder[KeyCode] == null) ActionBinder[KeyCode]= new MultiBinder();
        ActionBinder[KeyCode].bind(function);
        bPressedActions[KeyCode] = bPress;
        System.out.println("Bind action Success");
    }
    //Bind
    public <T> void BindAxis(int KeyCode, float valueScale, Consumer<T> function)
    {
        if(AxisBinder[KeyCode] == null) AxisBinder[KeyCode] = new MultiBinderWithParam();
        AxisBinder[KeyCode].bind(function);
        AxisValues[KeyCode] = valueScale;
        System.out.println("Bind axis Success");
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        bPressedButtons[keyCode] = true;

        if(AxisBinder[keyCode] != null) AxisBinder[keyCode].executeAll(AxisValues[keyCode]);

        if(FrameCount[keyCode] != 0 || ActionBinder[keyCode] == null || !bPressedActions[keyCode]) return;
        ActionBinder[keyCode].executeAll();
        FrameCount [keyCode] ++;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        bPressedButtons[keyCode] = false;

        if(AxisBinder[keyCode]!=null)  AxisBinder[keyCode].executeAll(0.f);

        FrameCount[keyCode] = 0;
        if(bPressedActions[keyCode]) return;
        if(ActionBinder[keyCode] != null) ActionBinder[keyCode].executeAll();
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    public static KeyHandler getInstance()
    {
        if(Inst==null) Inst = new KeyHandler();
        return Inst;
    }

    public static boolean isKeyPressed(int KeyCode)
    {
        return getInstance().bPressedButtons[KeyCode];
    }
}
