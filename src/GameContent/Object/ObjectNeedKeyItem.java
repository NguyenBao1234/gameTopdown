package GameContent.Object;

public class ObjectNeedKeyItem extends ObjectPendOnPlayer implements InteractInterface
{
    protected boolean bUnlock = false;
    public void SetUnlock(boolean unlock) {bUnlock = unlock;}
    @Override
    public void interact()
    {
        if(!bUnlock) return ;
        System.out.println("ObjectNeedKeyItem has interacted at unlock state");
    }

}
