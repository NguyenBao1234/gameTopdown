package Entity.Object.Master;

public class ObjectNeedKeyItem extends BaseObject implements InteractInterface
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
