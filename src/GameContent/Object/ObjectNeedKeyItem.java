package GameContent.Object;

public class ObjectNeedKeyItem extends BaseObjectPendOnPlayer implements InteractInterface
{
    protected boolean bUnlock = false;
    public void SetUnlock(boolean unlock) {bUnlock = unlock;}
    @Override
    public boolean interact()
    {
        if(!bUnlock) return false;
        System.out.println("ObjectNeedKeyItem has interacted at unlock state");
        return true;
    }

}
