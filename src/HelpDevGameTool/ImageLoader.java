package HelpDevGameTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class ImageLoader
{
    private static boolean isImageFile(File file)
    {
        String[] imageExtensions = { "jpg", "jpeg", "png", "bmp", "gif" };
        String fileName = file.getName().toLowerCase();
        for (String ext : imageExtensions)
        {
            if (fileName.endsWith("." + ext)) return true;
        }
        return false;
    }
    /** make BufferedImage Array from images in specify folder in resources */
    public static BufferedImage[] makeFlipBook( String folderPath)
    {
        URL urlResFolder =  ImageLoader.class.getResource(folderPath);
        File folder = null;
        try {
            assert urlResFolder != null;
            folder = new File(urlResFolder.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if (!folder.isDirectory()) throw new IllegalArgumentException("Đường dẫn không phải là một thư mục! ");

        // Lấy danh sách tất cả các file trong thư mục
        File[] files = folder.listFiles();
        if (files == null) throw new IllegalArgumentException("Không thể đọc thư mục hoặc thư mục trống!");

        ArrayList<BufferedImage> imageList = new ArrayList<>();

        for (File file : files) {
            if (!file.isFile() || !isImageFile(file)) continue;
            try {
                BufferedImage image = ImageIO.read(file);
                if (image != null) imageList.add(image);
            } catch (IOException e) {
                System.err.println("Không thể đọc ảnh: " + file.getName());
            }
        }

        return imageList.toArray(new BufferedImage[0]);
    }


    public static BufferedImage flipImage(boolean flipX , BufferedImage originalImage)
    {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage Image = new BufferedImage(width, height, originalImage.getType());

        Graphics2D g2d = Image.createGraphics();
        if(flipX) g2d.drawImage(originalImage, width, 0, -width, height, null);
        else g2d.drawImage(originalImage, 0, height, width, -height, null);

        g2d.dispose();

        return Image;
    }
    public BufferedImage[] flipFrame(boolean flipX, BufferedImage[] originalFlipBook)
    {
        BufferedImage[] flippedFrames = new BufferedImage[originalFlipBook.length];
        for (int i = 0; i < originalFlipBook.length; i++)
        {
            flippedFrames[i] = flipImage(flipX, originalFlipBook[i]);
        }
        return flippedFrames;
    }
}
