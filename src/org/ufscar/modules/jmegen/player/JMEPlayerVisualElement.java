 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.player;

import java.io.StringReader;
import java.io.StringWriter;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel; 
import javax.swing.JToolBar;
import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.text.DataEditorSupport;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.ufscar.modules.jmegen.data.Camera;
import org.ufscar.modules.jmegen.data.Player;

@MultiViewElement.Registration(
    displayName = "#LBL_JMEPlayer_VISUAL",
iconBase = "org/ufscar/modules/jmegen/iconxml.png",
mimeType = "text/playerjmegen+xml",
persistenceType = TopComponent.PERSISTENCE_NEVER,
preferredID = "JMEPlayerVisual",
position = 2000)
@Messages("LBL_JMEPlayer_VISUAL=Visual")
public final class JMEPlayerVisualElement extends JPanel implements MultiViewElement {

    private JMEPlayerDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private transient MultiViewElementCallback callback;
    private final Document document;

    public JMEPlayerVisualElement(Lookup lkp) {
        obj = lkp.lookup(JMEPlayerDataObject.class);
        assert obj != null;
        initComponents();
        
                DataEditorSupport cookie = obj.getLookup().lookup(DataEditorSupport.class);
        document = cookie.getOpenedPanes()[0].getDocument();
        readFile();

    }

    @Override
    public String getName() {
        return "JMEPlayerVisualElement";
    }


    public void showFields() {
        lblCarStiffness.setVisible(false);
        lblCarCompValue.setVisible(false);
        lblCarDampValue.setVisible(false);
        lblCarMass.setVisible(false);
        lblCarFrontFrictionSlip.setVisible(false);
        lblCarBackFrictionSlip.setVisible(false);
        lblCarMaxAcceleration.setVisible(false);
        lblCarMaxSpeedKmHour.setVisible(false);
        lblCarSteeringIncrease.setVisible(false);
        lblCharRotateIncrease.setVisible(false);
        lblCharWalkForward.setVisible(false);
        lblCharWalkBackward.setVisible(false);
        txtCarStiffness.setVisible(false);
        txtCarCompValue.setVisible(false);
        txtCarDampValue.setVisible(false);
        txtCarMass.setVisible(false);
        txtCarFrontFrictionSlip.setVisible(false);
        txtCarBackFrictionSlip.setVisible(false);
        txtCarMaxAcceleration.setVisible(false);
        txtCarMaxSpeedKmHour.setVisible(false);
        txtCarSteeringIncrease.setVisible(false);
        txtCharRotateIncrease.setVisible(false);
        txtCharWalkForward.setVisible(false);
        txtCharWalkBackward.setVisible(false);
        
        if (radCharacter.isSelected()) {
            lblCharRotateIncrease.setVisible(true);
            lblCharWalkForward.setVisible(true);
            lblCharWalkBackward.setVisible(true);
            txtCharRotateIncrease.setVisible(true);
            txtCharWalkForward.setVisible(true);
            txtCharWalkBackward.setVisible(true);
        } else if (radCar.isSelected()) {
            lblCarStiffness.setVisible(true);
            lblCarCompValue.setVisible(true);
            lblCarDampValue.setVisible(true);
            lblCarMass.setVisible(true);
            lblCarFrontFrictionSlip.setVisible(true);
            lblCarBackFrictionSlip.setVisible(true);
            lblCarMaxAcceleration.setVisible(true);
            lblCarMaxSpeedKmHour.setVisible(true);
            lblCarSteeringIncrease.setVisible(true);
            txtCarStiffness.setVisible(true);
            txtCarCompValue.setVisible(true);
            txtCarDampValue.setVisible(true);
            txtCarMass.setVisible(true);
            txtCarFrontFrictionSlip.setVisible(true);
            txtCarBackFrictionSlip.setVisible(true);
            txtCarMaxAcceleration.setVisible(true);
            txtCarMaxSpeedKmHour.setVisible(true);
            txtCarSteeringIncrease.setVisible(true);
        }
        
    }
    
     public void writeFile() {
        String typePlayer = "";
        if (radCharacter.isSelected()) {
            typePlayer = "character";
        } else if (radCar.isSelected()) {
            typePlayer = "car";
        } else {
            typePlayer = "none";
        }


        String file = "";
        Player player = new Player();
        player.setTypePlayer(typePlayer);
        player.setCarStiffness(Float.parseFloat(txtCarStiffness.getText()));
        player.setCarCompValue(Float.parseFloat(txtCarCompValue.getText()));
        player.setCarDampValue(Float.parseFloat(txtCarDampValue.getText()));
        player.setCarMass(Float.parseFloat(txtCarMass.getText()));
        player.setCarFrontFrictionSlip(Float.parseFloat(txtCarFrontFrictionSlip.getText()));
        player.setCarBackFrictionSlip(Float.parseFloat(txtCarBackFrictionSlip.getText()));
        player.setCarMaxAcceleration(Float.parseFloat(txtCarMaxAcceleration.getText()));
        player.setCarMaxSpeedKmHour(Float.parseFloat(txtCarMaxSpeedKmHour.getText()));
        player.setCarSteeringIncrease(Float.parseFloat(txtCarSteeringIncrease.getText()));
        player.setCharRotateIncrease(Float.parseFloat(txtCharRotateIncrease.getText()));
        player.setCharWalkForward(Float.parseFloat(txtCharWalkForward.getText()));
        player.setCharWalkBackward(Float.parseFloat(txtCharWalkBackward.getText()));

        try {

            //JAXBContext context = JAXBContext.newInstance(Class.forName(player.getClass().getName()));
            JAXBContext context = JAXBContext.newInstance(Player.class);
            Marshaller marshaller = context.createMarshaller();
            //JAXBElement<Camera> element = new ObjectFactory().createCarro(carro);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(player, stringWriter);
            file = stringWriter.toString();


            document.remove(0, document.getLength());
            document.insertString(0, file, null);
            //  obj.getPrimaryFile().getOutputStream().write(file.getBytes());
            //  obj.getPrimaryFile().getOutputStream().flush();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    
    public void readFile() {
        Player player = new Player();


        try {
            JAXBContext context = JAXBContext.newInstance(Player.class);
            Unmarshaller uMarshaller = context.createUnmarshaller();
 
            String file = document.getText(0, document.getLength());
            StringReader reader = new StringReader(file);
            player = (Player) uMarshaller.unmarshal(reader);
        } catch (Exception ex) {
            //Exceptions.printStackTrace(ex);
        }
        
        if (player==null) {
            return;
        }
        
        try {
            if (player.getTypePlayer().equals("character")) { 
                radCharacter.setSelected(true);
            } else if (player.getTypePlayer().equals("car")) { 
                radCar.setSelected(true);
            }
            showFields();
                    
            txtCarStiffness.setText(String.valueOf(player.getCarStiffness()));
            txtCarCompValue.setText(String.valueOf(player.getCarCompValue()));
            txtCarDampValue.setText(String.valueOf(player.getCarDampValue()));
            txtCarMass.setText(String.valueOf(player.getCarMass()));
            txtCarFrontFrictionSlip.setText(String.valueOf(player.getCarFrontFrictionSlip()));
            txtCarBackFrictionSlip.setText(String.valueOf(player.getCarBackFrictionSlip()));
            txtCarMaxAcceleration.setText(String.valueOf(player.getCarMaxAcceleration()));
            txtCarMaxSpeedKmHour.setText(String.valueOf(player.getCarMaxSpeedKmHour()));
            txtCarSteeringIncrease.setText(String.valueOf(player.getCarSteeringIncrease()));
            txtCharRotateIncrease.setText(String.valueOf(player.getCharRotateIncrease()));
            txtCharWalkForward.setText(String.valueOf(player.getCharWalkForward()));
            txtCharWalkBackward.setText(String.valueOf(player.getCharWalkBackward()));
                    
        } catch (Exception e) {
        }
        

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        radCar = new javax.swing.JRadioButton();
        radCharacter = new javax.swing.JRadioButton();
        lblCharRotateIncrease = new javax.swing.JLabel();
        txtCharRotateIncrease = new javax.swing.JFormattedTextField();
        txtCharWalkForward = new javax.swing.JFormattedTextField();
        lblCharWalkForward = new javax.swing.JLabel();
        txtCharWalkBackward = new javax.swing.JFormattedTextField();
        lblCharWalkBackward = new javax.swing.JLabel();
        txtCarStiffness = new javax.swing.JFormattedTextField();
        lblCarStiffness = new javax.swing.JLabel();
        txtCarCompValue = new javax.swing.JFormattedTextField();
        lblCarCompValue = new javax.swing.JLabel();
        txtCarDampValue = new javax.swing.JFormattedTextField();
        lblCarDampValue = new javax.swing.JLabel();
        txtCarMass = new javax.swing.JFormattedTextField();
        lblCarMass = new javax.swing.JLabel();
        txtCarFrontFrictionSlip = new javax.swing.JFormattedTextField();
        lblCarFrontFrictionSlip = new javax.swing.JLabel();
        txtCarBackFrictionSlip = new javax.swing.JFormattedTextField();
        lblCarBackFrictionSlip = new javax.swing.JLabel();
        txtCarMaxAcceleration = new javax.swing.JFormattedTextField();
        lblCarMaxAcceleration = new javax.swing.JLabel();
        txtCarMaxSpeedKmHour = new javax.swing.JFormattedTextField();
        lblCarMaxSpeedKmHour = new javax.swing.JLabel();
        txtCarSteeringIncrease = new javax.swing.JFormattedTextField();
        lblCarSteeringIncrease = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, "Type of Player:");

        buttonGroup1.add(radCar);
        radCar.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(radCar, "Car");
        radCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCarActionPerformed(evt);
            }
        });

        buttonGroup1.add(radCharacter);
        org.openide.awt.Mnemonics.setLocalizedText(radCharacter, "Character");
        radCharacter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCharacterActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCharRotateIncrease, "Rotate Increase:");

        txtCharRotateIncrease.setText("jFormattedTextField1");
        txtCharRotateIncrease.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCharRotateIncreaseFocusLost(evt);
            }
        });

        txtCharWalkForward.setText("jFormattedTextField1");
        txtCharWalkForward.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCharWalkForwardFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCharWalkForward, "Walk Forward: ");

        txtCharWalkBackward.setText("jFormattedTextField1");
        txtCharWalkBackward.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCharWalkBackwardFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCharWalkBackward, "Walk Backward:");

        txtCarStiffness.setText("jFormattedTextField1");
        txtCarStiffness.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarStiffnessFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarStiffness, "Stiffness:");

        txtCarCompValue.setText("jFormattedTextField1");
        txtCarCompValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarCompValueFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarCompValue, "Comp Value:");

        txtCarDampValue.setText("jFormattedTextField1");
        txtCarDampValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarDampValueFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarDampValue, "Damp Value:");

        txtCarMass.setText("jFormattedTextField1");
        txtCarMass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarMassFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarMass, "Mass:");

        txtCarFrontFrictionSlip.setText("jFormattedTextField1");
        txtCarFrontFrictionSlip.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarFrontFrictionSlipFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarFrontFrictionSlip, "Front Friction Slip:");

        txtCarBackFrictionSlip.setText("jFormattedTextField1");
        txtCarBackFrictionSlip.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarBackFrictionSlipFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarBackFrictionSlip, "Back Friction Slip:");

        txtCarMaxAcceleration.setText("jFormattedTextField1");
        txtCarMaxAcceleration.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarMaxAccelerationFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarMaxAcceleration, "Max Acceleration:");

        txtCarMaxSpeedKmHour.setText("jFormattedTextField1");
        txtCarMaxSpeedKmHour.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarMaxSpeedKmHourFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarMaxSpeedKmHour, "Max Speed Km/Hour:");

        txtCarSteeringIncrease.setText("jFormattedTextField1");
        txtCarSteeringIncrease.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarSteeringIncreaseFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblCarSteeringIncrease, "Steering Increase:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(radCar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lblCharRotateIncrease)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCharRotateIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCharWalkForward, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCharWalkForward, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblCharWalkBackward, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCharWalkBackward, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(radCharacter)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarMass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCarMass, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarStiffness, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCarStiffness, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarFrontFrictionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCarFrontFrictionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarMaxAcceleration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCarMaxAcceleration, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarSteeringIncrease, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCarSteeringIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarCompValue, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCarCompValue, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(lblCarDampValue, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCarDampValue, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarBackFrictionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCarBackFrictionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCarMaxSpeedKmHour)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCarMaxSpeedKmHour, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(radCharacter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCharRotateIncrease)
                    .addComponent(txtCharRotateIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCharWalkForward)
                    .addComponent(txtCharWalkForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCharWalkBackward)
                    .addComponent(txtCharWalkBackward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(radCar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarStiffness)
                    .addComponent(txtCarStiffness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarCompValue)
                    .addComponent(txtCarCompValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarDampValue)
                    .addComponent(txtCarDampValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarMass)
                    .addComponent(txtCarMass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarFrontFrictionSlip)
                    .addComponent(txtCarFrontFrictionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarBackFrictionSlip)
                    .addComponent(txtCarBackFrictionSlip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarMaxAcceleration)
                    .addComponent(txtCarMaxAcceleration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCarMaxSpeedKmHour)
                    .addComponent(txtCarMaxSpeedKmHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarSteeringIncrease)
                    .addComponent(txtCarSteeringIncrease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void radCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCarActionPerformed
        showFields();
        writeFile();
    }//GEN-LAST:event_radCarActionPerformed

    private void radCharacterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCharacterActionPerformed
        showFields();
        writeFile();
    }//GEN-LAST:event_radCharacterActionPerformed

    private void txtCharRotateIncreaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCharRotateIncreaseFocusLost
        writeFile();
    }//GEN-LAST:event_txtCharRotateIncreaseFocusLost

    private void txtCharWalkForwardFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCharWalkForwardFocusLost
        writeFile();
    }//GEN-LAST:event_txtCharWalkForwardFocusLost

    private void txtCharWalkBackwardFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCharWalkBackwardFocusLost
        writeFile();
    }//GEN-LAST:event_txtCharWalkBackwardFocusLost

    private void txtCarStiffnessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarStiffnessFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarStiffnessFocusLost

    private void txtCarCompValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarCompValueFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarCompValueFocusLost

    private void txtCarDampValueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarDampValueFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarDampValueFocusLost

    private void txtCarMassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarMassFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarMassFocusLost

    private void txtCarFrontFrictionSlipFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarFrontFrictionSlipFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarFrontFrictionSlipFocusLost

    private void txtCarBackFrictionSlipFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarBackFrictionSlipFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarBackFrictionSlipFocusLost

    private void txtCarMaxAccelerationFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarMaxAccelerationFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarMaxAccelerationFocusLost

    private void txtCarMaxSpeedKmHourFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarMaxSpeedKmHourFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarMaxSpeedKmHourFocusLost

    private void txtCarSteeringIncreaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarSteeringIncreaseFocusLost
        writeFile();
    }//GEN-LAST:event_txtCarSteeringIncreaseFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCarBackFrictionSlip;
    private javax.swing.JLabel lblCarCompValue;
    private javax.swing.JLabel lblCarDampValue;
    private javax.swing.JLabel lblCarFrontFrictionSlip;
    private javax.swing.JLabel lblCarMass;
    private javax.swing.JLabel lblCarMaxAcceleration;
    private javax.swing.JLabel lblCarMaxSpeedKmHour;
    private javax.swing.JLabel lblCarSteeringIncrease;
    private javax.swing.JLabel lblCarStiffness;
    private javax.swing.JLabel lblCharRotateIncrease;
    private javax.swing.JLabel lblCharWalkBackward;
    private javax.swing.JLabel lblCharWalkForward;
    private javax.swing.JRadioButton radCar;
    private javax.swing.JRadioButton radCharacter;
    private javax.swing.JFormattedTextField txtCarBackFrictionSlip;
    private javax.swing.JFormattedTextField txtCarCompValue;
    private javax.swing.JFormattedTextField txtCarDampValue;
    private javax.swing.JFormattedTextField txtCarFrontFrictionSlip;
    private javax.swing.JFormattedTextField txtCarMass;
    private javax.swing.JFormattedTextField txtCarMaxAcceleration;
    private javax.swing.JFormattedTextField txtCarMaxSpeedKmHour;
    private javax.swing.JFormattedTextField txtCarSteeringIncrease;
    private javax.swing.JFormattedTextField txtCarStiffness;
    private javax.swing.JFormattedTextField txtCharRotateIncrease;
    private javax.swing.JFormattedTextField txtCharWalkBackward;
    private javax.swing.JFormattedTextField txtCharWalkForward;
    // End of variables declaration//GEN-END:variables
    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    @Override
    public JComponent getToolbarRepresentation() {
        return toolbar;
    }

    @Override
    public Action[] getActions() {
        return new Action[0];
    }

    @Override
    public Lookup getLookup() {
        return obj.getLookup();
    }

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    @Override
    public void componentShowing() {
        readFile();
    }

    @Override
    public void componentHidden() {
    }

    @Override
    public void componentActivated() {
    }

    @Override
    public void componentDeactivated() {
    }

    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
    }

    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }
}
