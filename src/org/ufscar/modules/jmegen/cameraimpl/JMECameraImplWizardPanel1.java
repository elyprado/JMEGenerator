/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.cameraimpl;

import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.ufscar.modules.jmegen.data.Camera;
import org.ufscar.modules.jmegen.trackimpl.JMETrackImplVisualPanel1;

public class JMECameraImplWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {
 
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private JMECameraImplVisualPanel1 component;
    private Project project; 
    public JMECameraImplWizardPanel1(Project project) {
        this.project = project;
    }
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public JMECameraImplVisualPanel1 getComponent() {
        if (component == null) {
            component = new JMECameraImplVisualPanel1(project);
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty(JMECameraImplVisualPanel1.CONTENT,  getContentFromVisualPanel());
        wiz.putProperty(JMECameraImplVisualPanel1.FILENAME,  getFileNameFromVisualPanel());
    }
    
    private Camera getContentFromVisualPanel() {
        return ((JMECameraImplVisualPanel1) component).getContent();
    }
    
     private String getFileNameFromVisualPanel() {
        return ((JMECameraImplVisualPanel1) component).getFileName();
    }
}
