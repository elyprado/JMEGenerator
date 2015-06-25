package org.ufscar.modules.jmegen.data;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TrackPart {

    private String name;
    private int width;
    private int height;
    private int x;
    private int y;

    public TrackPart() {
    }

    public TrackPart(String name, int width, int height, int x, int y) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        /*try {            
         //new javax.swing.ImageIcon(getClass().getResource("Straight2.png"));
         image = ImageIO.read(ClassLoader.getSystemResource( "/org/ufscar/modules/jmegen/images/" + name + ".png" ));
         //image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
         //Graphics2D g2 = image.createGraphics();
         //g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         //g2.drawImage(image, 0, 0, w, h, null);
         //g2.dispose();
         } catch (IOException ex) {
         ex.printStackTrace();
         }
         */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
