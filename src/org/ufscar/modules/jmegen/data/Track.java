package org.ufscar.modules.jmegen.data;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="trackjmegen", name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {

    private ArrayList<TrackPart> parts = new ArrayList<TrackPart>();

    public Track() {
        //parts = new TrackPart[50][50];
    }

    public void addPart(TrackPart part, int x, int y) {
        //limpa os proximos campos p/ nao colidir
        for (int i = parts.size()-1; i >= 0; i--) {
            TrackPart p = parts.get(i);
            if ((p.getX() + p.getWidth() > x) && (p.getX() < x + part.getWidth()) && (p.getY() + p.getHeight() > y) && (p.getY() < y + part.getHeight())) {
                parts.remove(p);
            }
        }

        if (!part.getName().equals("erase")) {
            parts.add(part);
        }
    }

    public ArrayList<TrackPart> getParts() {
        return parts;
    }

    public void setParts(ArrayList<TrackPart> parts) {
        this.parts = parts;
    }
    
}
