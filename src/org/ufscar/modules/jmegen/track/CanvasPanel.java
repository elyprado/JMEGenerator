/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.track;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import org.ufscar.modules.jmegen.data.Track;
import org.ufscar.modules.jmegen.data.TrackListImages;
import org.ufscar.modules.jmegen.data.TrackPart;

/**
 *
 * @author ely
 */
public class CanvasPanel extends JPanel {
    private TrackPart partSelected = null;
    private int toolX = -1;
    private int toolY = -1;
    
    private int width;
    private int height;
    private Track track;
    
    private TrackListImages trackListImages = new TrackListImages();
    private trackjmegenVisualElement visualElement;
    
    public CanvasPanel(trackjmegenVisualElement visualElement, Track track, int width, int height) {
        this.track = track;
        this.width = width;
        this.height = height;
        this.visualElement = visualElement;
        setPreferredSize(new Dimension(width, height));
        
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                toolX = e.getX() / 36;
                toolY = e.getY() / 36;
                repaint();
            }
        });
        
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                addPart();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                toolX = e.getX() / 36;
                toolY = e.getY() / 36;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toolX = -1;
                toolY = -1;
                repaint();
            }
        });
        
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.black);
        g.clearRect(0, 0, width, height);

        //desenha linhas de grade
        for (int i = 0; i < width; i += 36) {
            g.fillRect(i, 0, 1, height);
            g.fillRect(0, i, width, 1);
            
            //g.drawString(i/36 + "", i, i);
        }
        
        //desenha a track
        for (int i = 0; i < track.getParts().size(); i++) {
            //imagesList.getImageItem("images/" + track.getParts()[x][y].getName() + ".png").draw(context, x*36, y*36);
            g.drawImage(trackListImages.getImage(track.getParts().get(i).getName()), track.getParts().get(i).getX()*36, track.getParts().get(i).getY()*36, this);
        }

        if ((toolX>=0) && (partSelected!=null)) {
            g.drawImage(trackListImages.getImage(partSelected.getName()), toolX*36, toolY*36, this);
            //g.drawString("XXX", toolX*36, toolY*36);
        }
        
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
    
    public void addPart() {
        TrackPart part = new TrackPart(partSelected.getName(), partSelected.getWidth(), partSelected.getHeight(), toolX, toolY);
        track.addPart(part, toolX, toolY);
        visualElement.writeFile();
    }

    public void selectTool(TrackPart partSelected) {
        this.partSelected = partSelected;
    }

    public TrackListImages getTrackListImages() {
        return trackListImages;
    }
     
    
}
