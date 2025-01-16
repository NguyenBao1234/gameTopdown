//Copyright POWGameStd
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


    public static void SplitImage(int sizeGridX, int sizeGridY ,String ImagePath,
                                  String OutputDir, boolean bHorizontal, boolean bOneIndex, int numDigit, int StartIndex)
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
            int gridSizeHeight = sizeGridY;

            int cols =  OriginalImageBf.getWidth() / gridSizeWidth;
            int rows =  OriginalImageBf.getHeight() / gridSizeHeight;

            // Loop through each grid tile
            int i = StartIndex;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    // Crop the tile
                    BufferedImage extractedImage = OriginalImageBf.getSubimage(x * gridSizeWidth,
                            y * gridSizeHeight,gridSizeWidth,gridSizeHeight);
                    // Optionally save each tile as a separate image file
                    String FinalIndex;
                    String fileName;
                    if(bOneIndex)
                    {
                        FinalIndex = (numDigit > 1)?FormatDigit(i,numDigit):String.valueOf(i) ;
                        i++;
                    }
                    else FinalIndex = bHorizontal ? (y + "_" + x ): ( x + "_" + y );
                    fileName = baseName + "_" + FinalIndex + "." + fileExtension;
                    File outputfile = new File(OutputDir,fileName);
                    ImageIO.write(extractedImage, fileExtension, outputfile);
                }
            }
            System.out.println("image extracted successfully.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String FormatDigit(int value, int numDigits)
    {
        if (numDigits <= 0) throw new IllegalArgumentException("So chu so can lon hon 0");

        String formatString = "%0" + numDigits + "d";
        String formattedValue = String.format(formatString, value);

        if (formattedValue.length() > numDigits) return formattedValue.substring(formattedValue.length() - numDigits);

        return formattedValue;
    }

    public static void main(String[] args)
    {
        SplitImage(384,64,"AssetSource/Orc/Orc1/orc1_hurt_full.png","Resource/Character/Orc/Or1/hurt", true, true,1,0);
    }
}