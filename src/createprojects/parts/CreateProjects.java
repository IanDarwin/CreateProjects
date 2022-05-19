package createprojects.parts;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class CreateProjects {
    
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
	IWorkspaceRoot root = workspace.getRoot();
	
	public void doCreates() {
			System.out.println("CreateProjects.execute()");

			for (ProjDesc pd : ProjectList.getProjects()) {
			String name = pd.name;
			String path = pd.path;
			IPath projectLocation = null;
			if (path != null) {
				if (!new File(path).isDirectory()) {
					System.err.println("WARNING: Project " + name + " Path " + path + " is not a directory");
				} else {
					projectLocation = new Path(path);
				}
			}
				IProject project = root.getProject(name);
				try {
					if (!project.exists()) {
						IProjectDescription description =
							ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
						description.setLocation(projectLocation);
						project.create(description, null);
					}
					project.open(null);	// verifies the project
					IPath rawLocation = project.getRawLocation();
					if (rawLocation == null) {
						System.err.println("Warning: Project %s exists but has no location");
					}
					project.refreshLocal(IResource.DEPTH_INFINITE, null); // Force refreshing even if proj existed.

				} catch (CoreException e) {
					System.err.println("Error creating/refreshing " + name + "; " + e);
				}
			}
			System.out.println("CreateProjects.execute() done");
	}

	public void doCloseAll() {
		for (ProjDesc pd : ProjectList.getProjects()) {
			String projectName = pd.name;
			IProject project = root.getProject(projectName);

			if (!project.exists()) {
				System.err.println("Project not open: " + projectName);
			}
			if (project.isOpen()) {
				try {
					project.close(null);
				} catch (CoreException e) {
					System.err.println("Error in closing " + projectName + "; " + e);
				}
			}
		}
	}
}
