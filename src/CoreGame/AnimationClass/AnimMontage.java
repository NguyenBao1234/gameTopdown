package CoreGame.AnimationClass;

import CoreGame.AnimNotifyComponent.AnimNotify;

import java.awt.image.BufferedImage;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;

public class AnimMontage
{
    private BufferedImage[] FlipBook;
    private final ArrayList<AnimNotify> Notifies = new ArrayList<>();
    public AnimMontage(){}
    public AnimMontage(BufferedImage[] flipBook)
    {
        FlipBook = flipBook;
    }
    public AnimMontage(AnimNotify... notifies)
    {
        Collections.addAll(Notifies, notifies);
    }

    public AnimMontage(BufferedImage[] flipBook, AnimNotify... notifies)
    {
        FlipBook = flipBook;
        Collections.addAll(Notifies, notifies);
    }

    public void AddNotify(AnimNotify Notify)
    {
        Notifies.add(Notify);
    }

    public ArrayList<AnimNotify> getNotifies() {return Notifies;}

    public BufferedImage[] getFlipBook() {return FlipBook;}

    public void setFlipBook(BufferedImage[] flipBook) {FlipBook = flipBook;}
}
