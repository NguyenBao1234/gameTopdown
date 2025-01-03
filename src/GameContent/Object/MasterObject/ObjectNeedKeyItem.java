package GameContent.Object.MasterObject;

public class ObjectNeedKeyItem extends InteractObject
{

    protected boolean bUnlock = false;

    public ObjectNeedKeyItem(){}

    public ObjectNeedKeyItem(String ImgDefaultPath, String ImgInteractPath)
    {
        super(ImgDefaultPath, ImgInteractPath);
    }

    public void SetUnlock(boolean unlock) {bUnlock = unlock;}

    @Override
    public void interact()
    {
        super.interact();
        if(!bUnlock) return ;
        System.out.println("ObjectNeedKeyItem has interacted at unlock state");
    }
}
