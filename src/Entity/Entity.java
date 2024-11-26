package Entity;

import CoreGame.Enums.Collision;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX,worldY;
    protected int speed = 4;

    public Collision collisionMode = Collision.NoCollision;
    public boolean bOverlapping = false;
    public Rectangle collisionArea;

    protected float passDelta = 0;
    protected int currentFrame = -1;
    protected int fpsPerImage;

    /**flipbook contain buffer images to make frame by frame animation. FlipBook=Animation*/
    protected BufferedImage[] flipBook;
    protected BufferedImage sprite;

    public int getSpeed(){return speed;}


    /**Refresh sprite frequently to run flipBook (or play animation)*/
    protected void runFlipBook(float dt)
    {
        if(flipBook == null||flipBook.length < 1) return;
        passDelta += dt;
        if(passDelta < dt * fpsPerImage) return;
        //refresh new sprite by new frame of flipBook
        passDelta -= dt* fpsPerImage;
        currentFrame ++;
        if(currentFrame >= flipBook.length) currentFrame = 0;
        sprite = flipBook[currentFrame];
        //System.out.println("sprite has refresh
    }

}
