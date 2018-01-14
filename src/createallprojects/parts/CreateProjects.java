package createallprojects.parts;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

public class CreateProjects {
    
    /** List of projects to create.
     * This will need to be read from a file, which 
     * makehandsons will generate the starting version of
     */
    List<String> projectNames = Arrays.asList(
    	    "donow31",
    	    "donow31solution",
    	    "ember",
    	    "embersolution",
    	    "ex21",
    	    "ex21solution",
    	    "ex31",
    	    "ex31solution",
    	    "ex41",
    	    "ex41solution",
    	    "ex42",
    	    "ex42solution",
    	    "ex51",
    	    "ex51solution",
    	    "ex52",
    	    "ex52solution",
    	    "ex53",
    	    "ex53client",
    	    "ex53clientsolution",
    	    "ex53solution",
    	    "ex61",
    	    "ex61solution",
    	    "ex71",
    	    "ex71solution",
    	    "ex81",
    	    "ex81solution",
    	    "minimal",
    	    "samples",
    	    "samples_ee8"
    	);

	public void doCreates() {
	    	System.out.println("CreateProjects.execute()");
	    	IWorkspace workspace = ResourcesPlugin.getWorkspace();
	    	IWorkspaceRoot root = workspace.getRoot();
	    	for (String name : projectNames) {
	    		IProject project = root.getProject(name);
	    		try {
	    			if (!project.exists()) {
	    				project.create(new NullProgressMonitor());
	    				project.open(null);	// verifies the project?
	    			} else {
	    				project.refreshLocal(-1, null);
	    			}
	    			project.close(null);		// Close unconditionally
	    		} catch (CoreException e) {
	    			System.out.println(e);
	    		}
	
	    	}
	    	System.out.println("CreateProjects.execute() done");
	}
}