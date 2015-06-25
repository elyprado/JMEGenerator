/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.data;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ely
 */
@XmlRootElement(namespace="camjmegen", name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Camera {
    private String typecam;
    private float backDistance;
    private float overDistance;
    private float chaseDefDist;
    private float chaseMaxDist;
    private float chaseMinDist;
    private float chaseSensitivity;
    private float steerDistance;
    private float steerCurve;

    public Camera() {
        
    }

    public String getTypecam() {
        return typecam;
    }

    public void setTypecam(String typecam) {
        this.typecam = typecam;
    }

    public float getBackDistance() {
        return backDistance;
    }

    public void setBackDistance(float backDistance) {
        this.backDistance = backDistance;
    }

    public float getOverDistance() {
        return overDistance;
    }

    public void setOverDistance(float overDistance) {
        this.overDistance = overDistance;
    }

    public float getChaseDefDist() {
        return chaseDefDist;
    }

    public void setChaseDefDist(float chaseDefDist) {
        this.chaseDefDist = chaseDefDist;
    }

    public float getChaseMaxDist() {
        return chaseMaxDist;
    }

    public void setChaseMaxDist(float chaseMaxDist) {
        this.chaseMaxDist = chaseMaxDist;
    }

    public float getChaseMinDist() {
        return chaseMinDist;
    }

    public void setChaseMinDist(float chaseMinDist) {
        this.chaseMinDist = chaseMinDist;
    }

    public float getChaseSensitivity() {
        return chaseSensitivity;
    }

    public void setChaseSensitivity(float chaseSensitivity) {
        this.chaseSensitivity = chaseSensitivity;
    }

    public float getSteerDistance() {
        return steerDistance;
    }

    public void setSteerDistance(float steerDistance) {
        this.steerDistance = steerDistance;
    }

    public float getSteerCurve() {
        return steerCurve;
    }

    public void setSteerCurve(float steerCurve) {
        this.steerCurve = steerCurve;
    }
    
    
}
