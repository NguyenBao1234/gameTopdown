package Environment;

import java.awt.*;

public class PostProcessing
{
    Lightning lightning;
    public PostProcessing()
    {
    }

    public void setup()
    {
        lightning = new Lightning(130);
    }

    public void Render(Graphics2D g2){
        lightning.draw(g2);
    }
}
