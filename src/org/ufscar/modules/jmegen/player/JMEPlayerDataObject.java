/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufscar.modules.jmegen.player;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_JMEPlayer_LOADER=Files of JMEPlayer"
})
@MIMEResolver.NamespaceRegistration(
    displayName = "#LBL_JMEPlayer_LOADER",
mimeType = "text/playerjmegen+xml",
elementNS = {"playerjmegen"})
@DataObject.Registration(
    mimeType = "text/playerjmegen+xml",
iconBase = "org/ufscar/modules/jmegen/iconxml.png",
displayName = "#LBL_JMEPlayer_LOADER",
position = 300)
@ActionReferences({
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
    position = 100,
    separatorAfter = 200),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
    position = 300),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
    position = 400,
    separatorAfter = 500),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
    position = 600),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
    position = 700,
    separatorAfter = 800),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
    position = 900,
    separatorAfter = 1000),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
    position = 1100,
    separatorAfter = 1200),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
    position = 1300),
    @ActionReference(
        path = "Loaders/text/playerjmegen+xml/Actions",
    id =
    @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
    position = 1400)
})
public class JMEPlayerDataObject extends MultiDataObject {

    public JMEPlayerDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/playerjmegen+xml", true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
        displayName = "#LBL_JMEPlayer_EDITOR",
    iconBase = "org/ufscar/modules/jmegen/iconxml.png",
    mimeType = "text/playerjmegen+xml",
    persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
    preferredID = "JMEPlayer",
    position = 1000)
    @Messages("LBL_JMEPlayer_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }
}
