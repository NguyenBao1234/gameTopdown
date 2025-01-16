//Copyright POWGameStd
package GameContent.Object.MasterObject;

import HelpDevGameTool.ImageUtility;

public class InteractObject extends ObjectPendOnPlayer implements InteractInterface
{
    protected String ImgInteractionPath, ImgDefaultPath;

    public InteractObject(){};

    public InteractObject(String ImgDefaultPath, String ImgInteractPath)
    {
        super(ImgDefaultPath);
        this.ImgDefaultPath = ImgDefaultPath;
        ImgInteractionPath = ImgInteractPath;

    }

    @Override
    public void interact()
    {
        if(ImgInteractionPath != null) Sprite = ImageUtility.LoadImage(ImgInteractionPath);
    }
}
