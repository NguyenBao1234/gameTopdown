package HelpDevGameTool;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ImageGridSplitter {

    public static String getBaseName(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        int slashIndex = Math.max(filePath.lastIndexOf('/'), filePath.lastIndexOf('\\'));
        if (dotIndex > slashIndex) {
            return filePath.substring(slashIndex + 1, dotIndex);
        }
        return null; // Invalid path or no base name found
    }


    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1).toLowerCase();
        }
        return null; // No extension found or invalid format
    }


    public static void SplitImage(int sizeGridX, int sizeGridY ,String ImagePath, String OutputDir)
    {

        try
        {
            String baseName = getBaseName(ImagePath);
            String fileExtension = getFileExtension(ImagePath);
            if (fileExtension == null || baseName == null) {
                System.out.println("Unsupported file type. Please use a valid image format.");
                return;
            }


            File outputDirectory = new File(OutputDir);

            // Check if output directory exists, if not, create it
            if (!outputDirectory.exists()) {
                if (outputDirectory.mkdirs()) {
                    System.out.println("Created output directory: " + OutputDir);
                } else {
                    System.out.println("Failed to create output directory: " + OutputDir);
                    return;
                }
            }
            // Load the image
            System.out.println("Loading");
            BufferedImage OriginalImageBf = ImageIO.read(new File(ImagePath));
            System.out.println("Loaded");
            int gridSizeWidth = sizeGridX;
            int tileHeight = sizeGridY;

            int rows = OriginalImageBf.getHeight() / tileHeight;
            int cols = OriginalImageBf.getWidth() / gridSizeWidth;

            // Loop through each grid tile
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    // Crop the tile
                    BufferedImage extractedImage = OriginalImageBf.getSubimage(x * gridSizeWidth,y * tileHeight,gridSizeWidth,tileHeight);

                    // Optionally save each tile as a separate image file
                    File outputfile = new File(OutputDir,baseName + x + "_" + y + "." + fileExtension);
                    ImageIO.write(extractedImage, fileExtension, outputfile);
                }
            }
            System.out.println("image extracted successfully.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SplitImage(64,64,"AssetSource/Player/ImageSet/Side animations/spr_player_right_hit.png","src/Resource/Player/right/hit");

    }
}