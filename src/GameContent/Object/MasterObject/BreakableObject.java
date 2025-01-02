package GameContent.Object.MasterObject;

import CoreGame.EntityComponent.Entity;
import CoreGame.GamePanel;
import HelpDevGameTool.ImageUtility;

import static CoreGame.Data.Enums.Collision.Block;
import static CoreGame.Data.Enums.Collision.NoCollision;

public class BreakableObject extends ObjectPendOnPlayer
{
    protected String DefaultImagePath, BrokenImagePath;
    private float health;
    private int ChangedRotation;
    public BreakableObject(String DefaultImgPath, String BrokenImgPath, float Health)
    {
        super(DefaultImgPath);
        DefaultImagePath = DefaultImgPath;
        BrokenImagePath = BrokenImgPath;
        health = Health;
        CollisionMode = Block;
    }
    public BreakableObject(String DefaultImgPath, String BrokenImgPath, float Health, int degree)
    {
        DefaultImagePath = DefaultImgPath;
        BrokenImagePath = BrokenImgPath;
        health = Health;
        Sprite = ImageUtility.LoadImage(DefaultImgPath);
        if(Sprite == null) return;
        if(degree !=0)
        {
            ChangedRotation = degree;
            Sprite = ImageUtility.RotateImage(Sprite,degree);
        }
        SpriteRenderSizeX = Sprite.getWidth() * GamePanel.scale;
        SpriteRenderSizeY = Sprite.getHeight() * GamePanel.scale;
        CollisionMode = Block;
    }

    @Override
    protected void OnAnyDamage(Entity Causer, float Damage, int SourceWorldX, int SourceWorldY)
    {
        super.OnAnyDamage(Causer, Damage, SourceWorldX, SourceWorldY);
        if(health == 0) return;
        health -= Damage;
        if( health<=0 )
        {
            CollisionMode = NoCollision;
            health = 0;
            Sprite = ImageUtility.LoadImage(BrokenImagePath);
            if(Sprite == null) return;
            if(ChangedRotation != 0 ) Sprite = ImageUtility.RotateImage(Sprite,ChangedRotation);
        }
    }
}
