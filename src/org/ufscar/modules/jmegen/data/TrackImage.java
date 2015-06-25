/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.data;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.openide.util.Exceptions;

/**
 *
 * @author ely
 */
public class TrackImage {
    private BufferedImage image;
    private String name;

    public TrackImage(String name, Icon icon) {
        this.name = name;
        try {
            //image = ImageIO.read(new File("/org/ufscar/modules/jmegen/track/images/bridge1.png"));
            ImageIcon imgIcon = (ImageIcon)icon;
            image = new BufferedImage(
                imgIcon.getIconWidth(),
                imgIcon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            imgIcon.paintIcon(null, g, 0,0);
            g.dispose();
            //image = ImageIO.read(this.getClass().getResource("/org/ufscar/modules/jmegen/track/images/"+name+".png"));
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
