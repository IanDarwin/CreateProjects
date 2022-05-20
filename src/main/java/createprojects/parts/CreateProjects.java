package createprojects.parts;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class CreateProjects {
    
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
	IWorkspaceRoot root = workspace.getRoot();
	ILog log = Platform.getLog(CreateProjects.class);
	
	/**
	 * This is why we are here!
	 */
	public void doCreates() {
		ILog log = Platform.getLog(CreateProjects.class);
		log.info("CreateProjects.execute()");

		for (ProjDesc pd : ProjectList.getProjects()) {
			log.info(pd.toString());
			String name = pd.name();
			String path = pd.path();
			IPath projectLocation = null;
			if (path != null) {
				if (!new File(path).isDirectory()) {
					log.warn("Project " + name + " Path " + path + " is not a directory");
				} else {
					projectLocation = new Path(path);
				}
			}
				IProject project = root.getProject(name);
				try {
					if (project.exists()) {
						log.info("Project already exists!");
					} else {
						IProjectDescription description =
								ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
						description.setLocation(projectLocation);
						project.create(description, null);
					}
					project.open(null);	// verifies the project
					IPath rawLocation = project.getRawLocation();
					if (rawLocation == null) {
						log.warn("Project " + project + " exists but has no location");
					}
					project.refreshLocal(IResource.DEPTH_INFINITE, null); // Force refreshing even if proj existed.

				} catch (CoreException e) {
					log.error("Error creating/refreshing " + name + "; " + e);
				}
			}
			log.info("CreateProjects.execute() done");
	}

	public void doCloseAll() {
		for (ProjDesc pd : ProjectList.getProjects()) {
			log.info("Closing " + pd);
			String projectName = pd.name();
			IProject project = root.getProject(projectName);

			if (!project.exists()) {
				log.warn("Project not open: " + projectName);
			}
			if (project.isOpen()) {
				try {
					project.close(null);
				} catch (CoreException e) {
					log.error("Error in closing " + projectName + "; " + e);
				}
			}
		}
	}
}
