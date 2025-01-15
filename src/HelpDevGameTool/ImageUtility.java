/*
    /
 */
package HelpDevGameTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class ImageUtility {
    private static boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "bmp", "gif"};
        String fileName = file.getName().toLowerCase();
        for (String ext : imageExtensions) {
            if (fileName.endsWith("." + ext)) return true;
        }
        return false;
    }

    /**
     * make BufferedImage Array from images in specify folder in resources
     */
    public static BufferedImage[] makeFlipBook(String folderPath) {
        URL urlResFolder = ImageUtility.class.getResource(folderPath);
        File folder = null;
        try {
            if (urlResFolder == null) return new BufferedImage[0];
            folder = new File(urlResFolder.toURI());
        } catch (URISyntaxException e) {
            System.err.println("invalid for URL" + e.getMessage());
        }

        if (!folder.isDirectory()) {
            Logger.logError("Path is not Folder", null);
            return null;
        }

        // Lấy danh sách tất cả các file trong thư mục
        File[] files = folder.listFiles();
        if (files == null) {
            Logger.logError("Empty Folder", null);
            return new BufferedImage[0];
        }

        ArrayList<BufferedImage> imageList = new ArrayList<>();

        for (File file : files) {
            if (!file.isFile() || !isImageFile(file)) continue;
            try {
                BufferedImage image = ImageIO.read(file);
                if (image != null) imageList.add(image);
            } catch (IOException e) {
                System.err.println("Can't read file: " + file.getName());
            }
        }

        return imageList.toArray(new BufferedImage[0]);
    }

    public static BufferedImage flipImage(boolean flipX, BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage Image = new BufferedImage(width, height, originalImage.getType());

        Graphics2D g2d = Image.createGraphics();
        if (flipX) g2d.drawImage(originalImage, width, 0, -width, height, null);
        else g2d.drawImage(originalImage, 0, height, width, -height, null);

        g2d.dispose();

        return Image;
    }

    public BufferedImage[] flipFrame(boolean flipX, BufferedImage[] originalFlipBook) {
        BufferedImage[] flippedFrames = new BufferedImage[originalFlipBook.length];
        for (int i = 0; i < originalFlipBook.length; i++) {
            flippedFrames[i] = flipImage(flipX, originalFlipBook[i]);
        }
        return flippedFrames;
    }

    /**
     * load image as BufferedImage from specify image path in resources
     */
    public static BufferedImage LoadImage(String imagePath) {
        try {
            InputStream stream = ImageUtility.class.getResourceAsStream(imagePath);
            if (stream == null)
            {
                Logger.logError("Invalid Image Path: " + imagePath, null);
                return null;
            }
            BufferedImage BImage = ImageIO.read(stream);
            if (BImage == null)
            {
                Logger.logError("Can't read file as Image: " + imagePath, null);
                return null;
            }
            return BImage;

        } catch (IOException e) {
            Logger.logError("Failed to load" + imagePath, e);
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage[] MakeFlipBookFromSheet(String imagePath, int gridSizeWidth, int tileHeight)
    {
        try
        {
            InputStream inputStream = ImageUtility.class.getResourceAsStream(imagePath);
            if(inputStream == null)
            {
                Logger.logError("Invalid Image Sheet Path: " + imagePath, null);
                return null;
            }

            BufferedImage OriginalImageBf = ImageIO.read(inputStream);
            if(OriginalImageBf == null)
            {
                Logger.logError("Can't read file as Image: " + imagePath, null);
                return null;
            }
            int rows = OriginalImageBf.getHeight() / tileHeight;
            int cols = OriginalImageBf.getWidth() / gridSizeWidth;
            ArrayList<BufferedImage> frameList = new ArrayList<>();
            for (int y = 0; y < rows; y++)
            {
                for (int x = 0; x < cols; x++)
                {
                    // Crop the tile
                    BufferedImage extractedImage = OriginalImageBf.getSubimage(x * gridSizeWidth,y * tileHeight,gridSizeWidth,tileHeight);
                    frameList.add(extractedImage);
                }
            }
            return frameList.toArray(new BufferedImage[0]);
        }
        catch (IOException e){
            Logger.logError("Failed to load" + imagePath, e);
            throw new RuntimeException(e);
        }
    }
    public static BufferedImage RotateImage(BufferedImage image, int degree)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        //Calculate new size
        AffineTransform rotationTrans = AffineTransform.getRotateInstance(Math.toRadians(degree), width / 2.0, height / 2.0);
        Rectangle2D bounds = rotationTrans.createTransformedShape(new Rectangle2D.Double(0, 0, width, height)).getBounds2D(); //use rectangle2D for more precise with double type

        int newWidth = (int) Math.ceil(bounds.getWidth());
        int newHeight = (int) Math.ceil(bounds.getHeight());
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2d = rotatedImage.createGraphics();

        //create transform data
        AffineTransform transform = new AffineTransform();
        transform.translate(newWidth / 2.0, newHeight / 2.0);
        transform.rotate(Math.toRadians(degree));
        transform.translate(-width / 2.0, -height / 2.0); //refine center of content image

        g2d.drawImage(image, transform, null);
        g2d.dispose();

        return rotatedImage;
    }
}
