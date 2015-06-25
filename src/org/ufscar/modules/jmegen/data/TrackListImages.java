/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author ely
 */
public class TrackListImages {
    private ArrayList<TrackImage> images = new ArrayList<TrackImage>();
    
    public BufferedImage getImage(String name) {
        for (TrackImage img : images) {
            if (img.getName().equals(name)) {
                return img.getImage();
            }
        }
        
        return null;
    }
    
    public void addImage(String name, Icon icon) {
        TrackImage img = new TrackImage(name, icon);
        images.add(img);
    }
    
}
