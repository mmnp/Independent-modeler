package cz.cvut.indepmod.classmodel.file;

import cz.cvut.indepmod.classmodel.persistence.xml.ClassModelXMLCoder;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.ClassModelWorkspace;
import java.io.File;
import java.io.IOException;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

/**
 * Date: 6.11.2010
 * Time: 9:24:57
 * @author Lucky
 */
public class ClassModelSaveCookie implements SaveCookie {

    private ClassModelWorkspace workspace;
    private ClassModelGraph graph;

    public ClassModelSaveCookie(ClassModelWorkspace workspace, ClassModelGraph graph) {
        this.workspace = workspace;
        this.graph = graph;
    }

    @Override
    public void save() throws IOException {
        DataObject dataObj = this.workspace.getLookup().lookup(DataObject.class);
        if (dataObj != null) {
            File f = FileUtil.toFile(dataObj.getPrimaryFile());
            ClassModelXMLCoder.getInstance().encode(this.graph.getGraphLayoutCache(), f.getPath());
        } else {
            
        }
    }

    private void save(File f) throws IOException {
        
    }

}
