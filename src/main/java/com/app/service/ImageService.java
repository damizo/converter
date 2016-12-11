package com.app.service;

import com.app.model.entity.Image;
import com.app.repository.ImageRepository;
import com.app.utils.CryptoUtils;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Damian on 2016-11-19.
 */
@Service
public class ImageService extends FileService<Image, ImageRepository>{

    @Override
    protected byte[] generateNewFile(Image image, File file, String newName) throws IOException {
        ImageIcon ii = new ImageIcon(file.getCanonicalPath());
        BufferedImage bi = new BufferedImage(image.getNewWidth().intValue(), image.getNewHeight().intValue(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(ii.getImage(), 0, 0, image.getNewWidth().intValue(), image.getNewHeight().intValue(), null);

        File newFile = new File(com.app.utils.StringUtils.PATH + newName);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }

        if(image.getName().contains(".bmp") || image.getName().contains(".gif")) {
            bi = ImageIO.read(file);
            int[][] array2D = new int[bi.getWidth()][bi.getHeight()];
            for (int xPixel = 0; xPixel < bi.getWidth(); xPixel++) {
                for (int yPixel = 0; yPixel < bi.getHeight(); yPixel++) {
                    int color = bi.getRGB(xPixel, yPixel);
                    if (color == Color.BLACK.getRGB()) {
                        array2D[xPixel][yPixel] = 1;
                    } else {
                        array2D[xPixel][yPixel] = 0; // ?
                    }
                }
            }
        }

        ImageIO.write(bi, image.getNewExtension().toLowerCase(), newFile);

        byte[] bytes = FileUtils.readFileToByteArray(newFile);

        writeByteArrayToFile(newFile, bytes);
        return bytes;
    }

    @Override
    public HttpEntity<byte[]> findByTokenAndGetConvertedFile(String token) throws IOException {
        Image image = findByToken(token);

        if (image != null) {

            String newName = getNewName(image);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", image.getNewExtension()));
            header.set("Content-Disposition", "inline; filename=" + newName);
            header.setContentLength(image.getNewFile().length);


            return new HttpEntity<byte[]>(image.getNewFile(), header);
        }
        return null;
    }


}
