package CoreGame.AnimationClass;

import CoreGame.AnimNotifyComponent.AnimNotify;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimMontage
{
    private BufferedImage[] FlipBook;
    private final ArrayList<AnimNotify> Notifies = new ArrayList<>();

    public void AddNotify(AnimNotify Notify)
    {
        Notifies.add(Notify);
    }

    public ArrayList<AnimNotify> getNotifies() {return Notifies;}

    public BufferedImage[] getFlipBook() {return FlipBook;}

    public void setFlipBook(BufferedImage[] flipBook) {FlipBook = flipBook;}
}
