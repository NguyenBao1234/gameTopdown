package Entity;

import CoreGame.Enums.Collision;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX,worldY;

    protected boolean bColliding = false;
    protected boolean bOverlapping = false;

    protected Collision collisionMode = Collision.NoCollision;
    protected Rectangle collisionArea;

    protected float passDelta = 0;
    protected int currentFrame = -1;
    protected int fpsPerImage;

    /**flipbook contain buffer images to make frame by frame animation. FlipBook=Animation*/
    protected BufferedImage[] flipBook;
    protected BufferedImage sprite;

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
        //System.out.println("sprite has refresh)
    }

    public final Rectangle getCollisionArea(){ return collisionArea;}
    public final Collision getCollisionMode(){return collisionMode;}
    public final void setCollisionMode(Collision collision){collisionMode = collision;}
    public final boolean isbOverlapping(){return bOverlapping;}
    public final void setOverlapping(boolean bOverlap){bOverlapping = bOverlap;}
    public final boolean IsColliding(){return bColliding;}
    public final void SetColliding(boolean bCollide){bColliding = bCollide; }
}
