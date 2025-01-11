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
    private int FpsPerImage = 1;

    public AnimMontage(){}
    public AnimMontage(AnimNotify... notifies)
    {
        Collections.addAll(Notifies, notifies);
    }

    public AnimMontage(int fpsPerImage){ FpsPerImage = fpsPerImage;}
    public AnimMontage(int fpsPerImage, BufferedImage[] flipBook)
    {
        FlipBook = flipBook;
        FpsPerImage = fpsPerImage;
    }
    public AnimMontage(int fpsPerImage, AnimNotify... notifies)
    {
        Collections.addAll(Notifies, notifies);
        FpsPerImage = fpsPerImage;
    }

    public AnimMontage(int fpsPerImage, BufferedImage[] flipBook, AnimNotify... notifies)
    {
        FlipBook = flipBook;
        Collections.addAll(Notifies, notifies);
        FpsPerImage = fpsPerImage;
    }

    public void AddNotify(AnimNotify Notify)
    {
        Notifies.add(Notify);
    }

    public ArrayList<AnimNotify> getNotifies() {return Notifies;}

    public BufferedImage[] getFlipBook() {return FlipBook;}
    public void setFlipBook(BufferedImage[] flipBook) {FlipBook = flipBook;}

    public int getFpsPerImage(){return FpsPerImage;}
    public void setFpsPerImage(int fps) {FpsPerImage = fps;}
}
