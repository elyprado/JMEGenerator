/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.track;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.StringReader;
import java.io.StringWriter;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import org.ufscar.modules.jmegen.data.Track;
import org.ufscar.modules.jmegen.data.TrackPart;

@MultiViewElement.Registration(
    displayName = "#LBL_trackjmegen_VISUAL",
iconBase = "org/ufscar/modules/jmegen/iconxml.png",
mimeType = "text/trackjmegen+xml",
persistenceType = TopComponent.PERSISTENCE_NEVER,
preferredID = "trackjmegenVisual",
position = 2000)
@Messages("LBL_trackjmegen_VISUAL=Visual")
public final class trackjmegenVisualElement extends JPanel implements MultiViewElement {

    private trackjmegenDataObject obj;
    private JToolBar toolbar = new JToolBar();
    private transient MultiViewElementCallback callback;
    
    private final Document document;
    
    private Track track;
    private CanvasPanel canvasPanel;
    
    public trackjmegenVisualElement(Lookup lkp) {
        obj = lkp.lookup(trackjmegenDataObject.class);
        assert obj != null;
        initComponents();
        
        track = new Track();
        
        int width = 50 * 36;
        int height = 50 * 36;
        canvasPanel = new CanvasPanel(this, track, width, height);
        
        canvasPanel.getTrackListImages().addImage("erase", btnErase.getIcon());
        canvasPanel.getTrackListImages().addImage("start1", btnStart1.getIcon());
        canvasPanel.getTrackListImages().addImage("start2",btnStart2.getIcon());
        canvasPanel.getTrackListImages().addImage("start3",btnStart3.getIcon());
        canvasPanel.getTrackListImages().addImage("start4",btnStart4.getIcon());
        canvasPanel.getTrackListImages().addImage("straight1",btnStraight1.getIcon());
        canvasPanel.getTrackListImages().addImage("straight2",btnStraight2.getIcon());
        canvasPanel.getTrackListImages().addImage("curve1",btnCurve1.getIcon());
        canvasPanel.getTrackListImages().addImage("curve2",btnCurve2.getIcon());
        canvasPanel.getTrackListImages().addImage("curve3",btnCurve3.getIcon());
        canvasPanel.getTrackListImages().addImage("curve4",btnCurve4.getIcon());
        canvasPanel.getTrackListImages().addImage("ramp1",btnRamp1.getIcon());
        canvasPanel.getTrackListImages().addImage("ramp2",btnRamp2.getIcon());
        canvasPanel.getTrackListImages().addImage("ramp3",btnRamp3.getIcon());
        canvasPanel.getTrackListImages().addImage("ramp4",btnRamp4.getIcon());
        canvasPanel.getTrackListImages().addImage("bridge1",btnBridge1.getIcon());
        canvasPanel.getTrackListImages().addImage("bridge2",btnBridge2.getIcon());
        canvasPanel.getTrackListImages().addImage("bridge3",btnBridge3.getIcon());
        canvasPanel.getTrackListImages().addImage("bridge4",btnBridge4.getIcon());
        
        canvasPanel.getTrackListImages().addImage("house1",btnHouse1.getIcon());
        canvasPanel.getTrackListImages().addImage("house2",btnHouse2.getIcon());
        canvasPanel.getTrackListImages().addImage("house3",btnHouse3.getIcon());
        canvasPanel.getTrackListImages().addImage("house4",btnHouse4.getIcon());
        canvasPanel.getTrackListImages().addImage("tree1",btnTree1.getIcon());
        canvasPanel.getTrackListImages().addImage("tree2",btnTree2.getIcon());

        

        jScrollPane1.setViewportView(canvasPanel);
        jScrollPane1.setPreferredSize(new Dimension(width, height));
        jScrollPane1.setMaximumSize(new Dimension(width, height));
        canvasPanel.repaint();
        
        DataEditorSupport cookie = obj.getLookup().lookup(DataEditorSupport.class);
        document = cookie.getOpenedPanes()[0].getDocument();
        readFile();
    }

    @Override
    public String getName() {
        return "trackjmegenVisualElement";
    }

    public void readFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(Track.class);
            Unmarshaller uMarshaller = context.createUnmarshaller();
 
            String file = document.getText(0, document.getLength());
            StringReader reader = new StringReader(file);
            track = (Track) uMarshaller.unmarshal(reader);
        } catch (Exception ex) {
            //Exceptions.printStackTrace(ex);
        }
        
        if (track==null) {
            track = new Track();
        }
        
        canvasPanel.setTrack(track);
        canvasPanel.repaint();

    }
    
     public void writeFile() {
        String file = "";
        try {

            //JAXBContext context = JAXBContext.newInstance(Class.forName(cam.getClass().getName()));
            JAXBContext context = JAXBContext.newInstance(Track.class);
            Marshaller marshaller = context.createMarshaller();
            //JAXBElement<Camera> element = new ObjectFactory().createCarro(carro);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(track, stringWriter);
            file = stringWriter.toString();


            document.remove(0, document.getLength());
            document.insertString(0, file, null);
            //  obj.getPrimaryFile().getOutputStream().write(file.getBytes());
            //  obj.getPrimaryFile().getOutputStream().flush();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStraight1 = new javax.swing.JToggleButton();
        btnBridge1 = new javax.swing.JToggleButton();
        btnBridge4 = new javax.swing.JToggleButton();
        btnRamp1 = new javax.swing.JToggleButton();
        btnBridge2 = new javax.swing.JToggleButton();
        btnBridge3 = new javax.swing.JToggleButton();
        btnCurve2 = new javax.swing.JToggleButton();
        btnErase = new javax.swing.JToggleButton();
        btnStart4 = new javax.swing.JToggleButton();
        btnStart3 = new javax.swing.JToggleButton();
        btnCurve4 = new javax.swing.JToggleButton();
        btnStart2 = new javax.swing.JToggleButton();
        btnStart1 = new javax.swing.JToggleButton();
        btnRamp2 = new javax.swing.JToggleButton();
        btnCurve1 = new javax.swing.JToggleButton();
        btnRamp3 = new javax.swing.JToggleButton();
        btnRamp4 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        btnCurve3 = new javax.swing.JToggleButton();
        btnStraight2 = new javax.swing.JToggleButton();
        btnHouse1 = new javax.swing.JToggleButton();
        btnHouse2 = new javax.swing.JToggleButton();
        btnHouse3 = new javax.swing.JToggleButton();
        btnHouse4 = new javax.swing.JToggleButton();
        btnTree2 = new javax.swing.JToggleButton();
        btnTree1 = new javax.swing.JToggleButton();

        btnStraight1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/straight1.png"))); // NOI18N
        btnStraight1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStraight1ActionPerformed(evt);
            }
        });

        btnBridge1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/bridge1.png"))); // NOI18N
        btnBridge1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBridge1ActionPerformed(evt);
            }
        });

        btnBridge4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/bridge4.png"))); // NOI18N
        btnBridge4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBridge4ActionPerformed(evt);
            }
        });

        btnRamp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/ramp1.png"))); // NOI18N
        btnRamp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamp1ActionPerformed(evt);
            }
        });

        btnBridge2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/bridge2.png"))); // NOI18N
        btnBridge2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBridge2ActionPerformed(evt);
            }
        });

        btnBridge3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/bridge3.png"))); // NOI18N
        btnBridge3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBridge3ActionPerformed(evt);
            }
        });

        btnCurve2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/curve2.png"))); // NOI18N
        btnCurve2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurve2ActionPerformed(evt);
            }
        });

        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/erase.png"))); // NOI18N
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });

        btnStart4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/start4.png"))); // NOI18N
        btnStart4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStart4ActionPerformed(evt);
            }
        });

        btnStart3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/start3.png"))); // NOI18N
        btnStart3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStart3ActionPerformed(evt);
            }
        });

        btnCurve4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/curve4.png"))); // NOI18N
        btnCurve4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurve4ActionPerformed(evt);
            }
        });

        btnStart2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/start2.png"))); // NOI18N
        btnStart2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStart2ActionPerformed(evt);
            }
        });

        btnStart1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/start1.png"))); // NOI18N
        btnStart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStart1ActionPerformed(evt);
            }
        });

        btnRamp2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/ramp2.png"))); // NOI18N
        btnRamp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamp2ActionPerformed(evt);
            }
        });

        btnCurve1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/curve1.png"))); // NOI18N
        btnCurve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurve1ActionPerformed(evt);
            }
        });

        btnRamp3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/ramp3.png"))); // NOI18N
        btnRamp3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamp3ActionPerformed(evt);
            }
        });

        btnRamp4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/ramp4.png"))); // NOI18N
        btnRamp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRamp4ActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        btnCurve3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/curve3.png"))); // NOI18N
        btnCurve3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCurve3ActionPerformed(evt);
            }
        });

        btnStraight2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/straight2.png"))); // NOI18N
        btnStraight2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStraight2ActionPerformed(evt);
            }
        });

        btnHouse1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/house1.png"))); // NOI18N
        btnHouse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHouse1ActionPerformed(evt);
            }
        });

        btnHouse2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/house2.png"))); // NOI18N
        btnHouse2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHouse2ActionPerformed(evt);
            }
        });

        btnHouse3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/house3.png"))); // NOI18N
        btnHouse3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHouse3ActionPerformed(evt);
            }
        });

        btnHouse4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/house4.png"))); // NOI18N
        btnHouse4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHouse4ActionPerformed(evt);
            }
        });

        btnTree2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/tree2.png"))); // NOI18N
        btnTree2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTree2ActionPerformed(evt);
            }
        });

        btnTree1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ufscar/modules/jmegen/track/images/tree1.png"))); // NOI18N
        btnTree1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTree1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStraight1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStart1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCurve1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRamp1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBridge1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStraight2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStart2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCurve2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRamp2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBridge2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStart3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCurve3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRamp3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBridge3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnErase, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnStart4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCurve4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRamp4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBridge4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTree1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnTree2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHouse1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnHouse2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnHouse3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btnHouse4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnStraight1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnStart1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnCurve1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnRamp1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnBridge1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnStraight2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnStart2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnCurve2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnRamp2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnBridge2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnStart3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnCurve3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnRamp3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnBridge3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnErase, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnStart4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnCurve4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnRamp4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(btnBridge4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHouse1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHouse2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHouse3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHouse4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTree1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTree2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 49, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStraight1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStraight1ActionPerformed
        selectTool("straight1",1,1, btnStraight1.getIcon());
        btnStraight1.setSelected(true);
    }//GEN-LAST:event_btnStraight1ActionPerformed

    private void btnBridge1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBridge1ActionPerformed
        selectTool("bridge1",1,1, btnBridge1.getIcon());
        btnBridge1.setSelected(true);
    }//GEN-LAST:event_btnBridge1ActionPerformed

    private void btnBridge4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBridge4ActionPerformed
        selectTool("bridge4",1,1, btnBridge4.getIcon());
        btnBridge4.setSelected(true);
    }//GEN-LAST:event_btnBridge4ActionPerformed

    private void btnRamp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamp1ActionPerformed
        selectTool("ramp1",1,2, btnRamp1.getIcon());
        btnRamp1.setSelected(true);
    }//GEN-LAST:event_btnRamp1ActionPerformed

    private void btnBridge2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBridge2ActionPerformed
        selectTool("bridge2",1,1, btnBridge2.getIcon());
        btnBridge2.setSelected(true);
    }//GEN-LAST:event_btnBridge2ActionPerformed

    private void btnBridge3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBridge3ActionPerformed
        selectTool("bridge3",1,1, btnBridge3.getIcon());
        btnBridge3.setSelected(true);
    }//GEN-LAST:event_btnBridge3ActionPerformed

    private void btnCurve2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurve2ActionPerformed
        selectTool("curve2",2,2, btnCurve2.getIcon());
        btnCurve2.setSelected(true);
    }//GEN-LAST:event_btnCurve2ActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        selectTool("erase",1,1, btnErase.getIcon());
        btnErase.setSelected(true);
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnStart4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStart4ActionPerformed
        selectTool("start4",1,1,btnStart4.getIcon());
        btnStart4.setSelected(true);
    }//GEN-LAST:event_btnStart4ActionPerformed

    private void btnStart3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStart3ActionPerformed
        selectTool("start3",1,1, btnStart3.getIcon());
        btnStart3.setSelected(true);
    }//GEN-LAST:event_btnStart3ActionPerformed

    private void btnCurve4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurve4ActionPerformed
        selectTool("curve4",2,2, btnCurve4.getIcon());
        btnCurve4.setSelected(true);
    }//GEN-LAST:event_btnCurve4ActionPerformed

    private void btnStart2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStart2ActionPerformed
        selectTool("start2",1,1, btnStart2.getIcon());
        btnStart2.setSelected(true);
    }//GEN-LAST:event_btnStart2ActionPerformed

    private void btnStart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStart1ActionPerformed
        selectTool("start1",1,1, btnStart1.getIcon());
        btnStart1.setSelected(true);
    }//GEN-LAST:event_btnStart1ActionPerformed

    private void btnRamp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamp2ActionPerformed
        selectTool("ramp2",1,2, btnRamp2.getIcon());
        btnRamp2.setSelected(true);
    }//GEN-LAST:event_btnRamp2ActionPerformed

    private void btnCurve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurve1ActionPerformed
        selectTool("curve1",2,2, btnCurve1.getIcon());
        btnCurve1.setSelected(true);
    }//GEN-LAST:event_btnCurve1ActionPerformed

    private void btnRamp3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamp3ActionPerformed
        selectTool("ramp3",2,1, btnRamp3.getIcon());
        btnRamp3.setSelected(true);
    }//GEN-LAST:event_btnRamp3ActionPerformed

    private void btnRamp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRamp4ActionPerformed
        selectTool("ramp4",2,1, btnRamp4.getIcon());
        btnRamp4.setSelected(true);
    }//GEN-LAST:event_btnRamp4ActionPerformed

    private void btnCurve3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCurve3ActionPerformed
        selectTool("curve3",2,2, btnCurve3.getIcon());
        btnCurve3.setSelected(true);
    }//GEN-LAST:event_btnCurve3ActionPerformed

    private void btnStraight2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStraight2ActionPerformed
        selectTool("straight2",1,1, btnStraight2.getIcon());
        btnStraight2.setSelected(true);
    }//GEN-LAST:event_btnStraight2ActionPerformed

    private void btnHouse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHouse1ActionPerformed
        selectTool("house1",1,1, btnHouse1.getIcon());
        btnHouse1.setSelected(true);
    }//GEN-LAST:event_btnHouse1ActionPerformed

    private void btnHouse2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHouse2ActionPerformed
        selectTool("house2",1,1, btnHouse2.getIcon());
        btnHouse2.setSelected(true);
    }//GEN-LAST:event_btnHouse2ActionPerformed

    private void btnHouse3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHouse3ActionPerformed
        selectTool("house3",1,1, btnHouse3.getIcon());
        btnHouse3.setSelected(true);
    }//GEN-LAST:event_btnHouse3ActionPerformed

    private void btnHouse4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHouse4ActionPerformed
        selectTool("house4",1,1, btnHouse4.getIcon());
        btnHouse4.setSelected(true);
    }//GEN-LAST:event_btnHouse4ActionPerformed

    private void btnTree2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTree2ActionPerformed
        selectTool("tree2",1,1, btnTree2.getIcon());
        btnTree2.setSelected(true);
    }//GEN-LAST:event_btnTree2ActionPerformed

    private void btnTree1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTree1ActionPerformed
        selectTool("tree1",1,1, btnTree1.getIcon());
        btnTree1.setSelected(true);
    }//GEN-LAST:event_btnTree1ActionPerformed

    public void selectTool(String tool, int width, int height, Icon icon) {
        ImageIcon imgIcon = (ImageIcon)icon;

        BufferedImage img = new BufferedImage(
            imgIcon.getIconWidth(),
            imgIcon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
        //g.setColor(Color.white);
        //g.clearRect(0, 0, imgIcon.getIconWidth(), imgIcon.getIconHeight());
        // paint the Icon to the BufferedImage.
        imgIcon.paintIcon(null, g, 0,0);
        g.dispose();
        
        TrackPart part = new TrackPart(tool, width, height, 0, 0);
        canvasPanel.selectTool(part);
        
                
        btnErase.setSelected(false);
        btnStart1.setSelected(false);
        btnStart2.setSelected(false);
        btnStart3.setSelected(false);
        btnStart4.setSelected(false);
        btnStraight1.setSelected(false);
        btnStraight2.setSelected(false);
        btnCurve1.setSelected(false);
        btnCurve2.setSelected(false);
        btnCurve3.setSelected(false);
        btnCurve4.setSelected(false);
        btnRamp1.setSelected(false);
        btnRamp2.setSelected(false);
        btnRamp3.setSelected(false);
        btnRamp4.setSelected(false);
        btnBridge1.setSelected(false);
        btnBridge2.setSelected(false);
        btnBridge3.setSelected(false);
        btnBridge4.setSelected(false);
        btnHouse1.setSelected(false);
        btnHouse2.setSelected(false);
        btnHouse3.setSelected(false);
        btnHouse4.setSelected(false);
        btnTree1.setSelected(false);
        btnTree2.setSelected(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBridge1;
    private javax.swing.JToggleButton btnBridge2;
    private javax.swing.JToggleButton btnBridge3;
    private javax.swing.JToggleButton btnBridge4;
    private javax.swing.JToggleButton btnCurve1;
    private javax.swing.JToggleButton btnCurve2;
    private javax.swing.JToggleButton btnCurve3;
    private javax.swing.JToggleButton btnCurve4;
    private javax.swing.JToggleButton btnErase;
    private javax.swing.JToggleButton btnHouse1;
    private javax.swing.JToggleButton btnHouse2;
    private javax.swing.JToggleButton btnHouse3;
    private javax.swing.JToggleButton btnHouse4;
    private javax.swing.JToggleButton btnRamp1;
    private javax.swing.JToggleButton btnRamp2;
    private javax.swing.JToggleButton btnRamp3;
    private javax.swing.JToggleButton btnRamp4;
    private javax.swing.JToggleButton btnStart1;
    private javax.swing.JToggleButton btnStart2;
    private javax.swing.JToggleButton btnStart3;
    private javax.swing.JToggleButton btnStart4;
    private javax.swing.JToggleButton btnStraight1;
    private javax.swing.JToggleButton btnStraight2;
    private javax.swing.JToggleButton btnTree1;
    private javax.swing.JToggleButton btnTree2;
    private javax.swing.JScrollPane jScrollPane1;
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
