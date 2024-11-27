package CoreGame.KeyHandlerComponent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    private static KeyHandler instance;
    private boolean bPressedButtons[] = new boolean[255];
    private int expectedKey;

    KeyHandler(){}
    public static KeyHandler getInstKeyHdl()
    {
        if (instance == null) instance = new KeyHandler();
        return instance;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()<bPressedButtons.length) bPressedButtons[e.getKeyCode()] = true;
        //System.out.println(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()<bPressedButtons.length) bPressedButtons[e.getKeyCode()] = false;
        //System.out.println("RELEASE");
    }

    public static boolean isKeyPressed(int KeyCode) {
        return getInstKeyHdl().bPressedButtons[KeyCode];
    }
}
