/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.playerimpl;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle.Messages;
import org.ufscar.modules.jmegen.cameraimpl.JMECameraImplVisualPanel1;
import org.ufscar.modules.jmegen.data.Camera;
import org.ufscar.modules.jmegen.data.Player;

// TODO define position attribute
//@TemplateRegistration(folder = "JMEGenerator", displayName = "#JMEPlayerImplWizardIterator_displayName", iconBase = "org/ufscar/modules/jmegen/icon.png", description = "jMEPlayerImpl.html")
//@Messages("JMEPlayerImplWizardIterator_displayName=Player Implementation")
public final class JMEPlayerImplWizardIterator implements WizardDescriptor.InstantiatingIterator<WizardDescriptor> {

    private int index;
    private WizardDescriptor wizard;
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;

    private List<WizardDescriptor.Panel<WizardDescriptor>> getPanels() {
Project project = Templates.getProject(wizard);
Sources sources = (Sources) ProjectUtils.getSources(project);
SourceGroup[] groups = sources.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);

        if (panels == null) {
            panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
            panels.add(new JMEPlayerImplWizardPanel1(project));
            String[] steps = createSteps();
            for (int i = 0; i < panels.size(); i++) {
                Component c = panels.get(i).getComponent();
                if (steps[i] == null) {
                    // Default step name to component name of panel. Mainly
                    // useful for getting the name of the target chooser to
                    // appear in the list of steps.
                    steps[i] = c.getName();
                }
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                    jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
                }
            }
        }
        return panels;
    }

    @Override
    public Set<?> instantiate() throws IOException {
        //Prepare the arguments for passing to the FreeMarker template:
        //String extension = (String) wizard.getProperty(TrackVisualPanel1.EXTENSION);
        //String implementation = (String) wizard.getProperty(TrackVisualPanel1.IMPLEMENTATION);
        Player player = (Player) wizard.getProperty(JMEPlayerImplVisualPanel1.CONTENT);
        HashMap args = new HashMap();
        //args.put("extends", "extends " + extension);
        //args.put("implements", "implements " + implementation);
        String typePlayer = "car";
        if (player.getTypePlayer()!=null) {
            typePlayer = player.getTypePlayer();
        }
        args.put("typePlayer", typePlayer); 
        args.put("carStiffness", String.valueOf(player.getCarStiffness()));
        args.put("carCompValue", String.valueOf(player.getCarCompValue()));
        args.put("carDampValue", String.valueOf(player.getCarDampValue()));
        args.put("carMass", String.valueOf(player.getCarMass()));
        args.put("carFrontFrictionSlip", String.valueOf(player.getCarFrontFrictionSlip()));
        args.put("carBackFrictionSlip", String.valueOf(player.getCarBackFrictionSlip()));
        args.put("carMaxAcceleration", String.valueOf(player.getCarMaxAcceleration()));
        args.put("carMaxSpeedKmHour", String.valueOf(player.getCarMaxSpeedKmHour()));
        args.put("carSteeringIncrease", String.valueOf(player.getCarSteeringIncrease()));
        args.put("charRotateIncrease", String.valueOf(player.getCharRotateIncrease()));
        args.put("charWalkForward", String.valueOf(player.getCharWalkForward()));
        args.put("charWalkBackward", String.valueOf(player.getCharWalkBackward()));
 
        //Get the template and convert it:
        FileObject template = Templates.getTemplate(wizard);
        System.out.println("WIZARD " + wizard);
        DataObject dTemplate = DataObject.find(template);

        //Get the package:
        FileObject dir = Templates.getTargetFolder(wizard);
        DataFolder df = DataFolder.findFolder(dir);

        //Get the class:
        String targetName = Templates.getTargetName(wizard);
        //targetName = "Track.java";
        targetName = (String) wizard.getProperty(JMEPlayerImplVisualPanel1.FILENAME);

 
        String pathFile = dir.getPath() + "/" + targetName + ".java";
        File fileOld = new File(pathFile);
        if (fileOld.exists()) {
            if (JOptionPane.showConfirmDialog(null, "Arquivo [ " + targetName + " ] já existe! \n Deseja substituir?","JMEGenerator",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                fileOld.delete();
            } else {
                return null;
            }
        }
        

        //Define the template from the above,
        //passing the package, the file name, and the map of strings to the template:
        DataObject dobj = dTemplate.createFromTemplate(df, targetName, args);

        //Obtain a FileObject:
        FileObject createdFile = dobj.getPrimaryFile();

        //Create the new file:
        return Collections.singleton(createdFile); 
    }

    @Override
    public void initialize(WizardDescriptor wizard) {
        this.wizard = wizard;
    }

    @Override
    public void uninitialize(WizardDescriptor wizard) {
        panels = null;
    }

    @Override
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return getPanels().get(index);
    }

    @Override
    public String name() {
        return index + 1 + ". from " + getPanels().size();
    }

    @Override
    public boolean hasNext() {
        return index < getPanels().size() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
    // If something changes dynamically (besides moving between panels), e.g.
    // the number of panels changes in response to user input, then use
    // ChangeSupport to implement add/removeChangeListener and call fireChange
    // when needed

    // You could safely ignore this method. Is is here to keep steps which were
    // there before this wizard was instantiated. It should be better handled
    // by NetBeans Wizard API itself rather than needed to be implemented by a
    // client code.
    private String[] createSteps() {
        String[] beforeSteps = (String[]) wizard.getProperty("WizardPanel_contentData");
        assert beforeSteps != null : "This wizard may only be used embedded in the template wizard";
        String[] res = new String[(beforeSteps.length - 1) + panels.size()];
        for (int i = 0; i < res.length; i++) {
            if (i < (beforeSteps.length - 1)) {
                res[i] = beforeSteps[i];
            } else {
                res[i] = panels.get(i - beforeSteps.length + 1).getComponent().getName();
            }
        }
        return res;
    }
}
