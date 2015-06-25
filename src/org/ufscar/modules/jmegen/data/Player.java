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
@XmlRootElement(namespace="playerjmegen", name="root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Player {
    private String typePlayer;
    private float carStiffness;
    private float carCompValue;
    private float carDampValue;
    private float carMass;
    private float carFrontFrictionSlip;
    private float carBackFrictionSlip;
    private float carMaxAcceleration;
    private float carMaxSpeedKmHour;
    private float carSteeringIncrease;
    private float charRotateIncrease;
    private float charWalkForward;
    private float charWalkBackward;
    
    public Player() {
         
    }

    public String getTypePlayer() {
        return typePlayer;
    }

    public void setTypePlayer(String typePlayer) {
        this.typePlayer = typePlayer;
    }

    public float getCarStiffness() {
        return carStiffness;
    }

    public void setCarStiffness(float carStiffness) {
        this.carStiffness = carStiffness;
    }

    public float getCarCompValue() {
        return carCompValue;
    }

    public void setCarCompValue(float carCompValue) {
        this.carCompValue = carCompValue;
    }

    public float getCarDampValue() {
        return carDampValue;
    }

    public void setCarDampValue(float carDampValue) {
        this.carDampValue = carDampValue;
    }

    public float getCarMass() {
        return carMass;
    }

    public void setCarMass(float carMass) {
        this.carMass = carMass;
    }

    public float getCarFrontFrictionSlip() {
        return carFrontFrictionSlip;
    }

    public void setCarFrontFrictionSlip(float carFrontFrictionSlip) {
        this.carFrontFrictionSlip = carFrontFrictionSlip;
    }

    public float getCarBackFrictionSlip() {
        return carBackFrictionSlip;
    }

    public void setCarBackFrictionSlip(float carBackFrictionSlip) {
        this.carBackFrictionSlip = carBackFrictionSlip;
    }

    public float getCarMaxAcceleration() {
        return carMaxAcceleration;
    }

    public void setCarMaxAcceleration(float carMaxAcceleration) {
        this.carMaxAcceleration = carMaxAcceleration;
    }

    public float getCarMaxSpeedKmHour() {
        return carMaxSpeedKmHour;
    }

    public void setCarMaxSpeedKmHour(float carMaxSpeedKmHour) {
        this.carMaxSpeedKmHour = carMaxSpeedKmHour;
    }

    public float getCarSteeringIncrease() {
        return carSteeringIncrease;
    }

    public void setCarSteeringIncrease(float carSteeringIncrease) {
        this.carSteeringIncrease = carSteeringIncrease;
    }

    public float getCharRotateIncrease() {
        return charRotateIncrease;
    }

    public void setCharRotateIncrease(float charRotateIncrease) {
        this.charRotateIncrease = charRotateIncrease;
    }

    public float getCharWalkForward() {
        return charWalkForward;
    }

    public void setCharWalkForward(float charWalkForward) {
        this.charWalkForward = charWalkForward;
    }

    public float getCharWalkBackward() {
        return charWalkBackward;
    }

    public void setCharWalkBackward(float charWalkBackward) {
        this.charWalkBackward = charWalkBackward;
    }

        
    
}
