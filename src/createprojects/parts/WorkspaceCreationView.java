package createprojects.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.*;

public class WorkspaceCreationView {
	private Label statusLabel;
	private Button creationButton;
	private Button closeAllButton;

	CreateProjects createProjectsHelper = new CreateProjects();

	@PostConstruct
	public void createPartControl(Composite parent) {
		System.out.println("WorkspaceCreationView.createPartControl()");

		statusLabel = new Label(parent, SWT.BORDER);
		statusLabel.setText("Status field. This tool is recommended for admin use only!");
		
		creationButton = new Button(parent, SWT.BORDER);
		creationButton.setText("Create/Refresh All Projects");
		creationButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				statusLabel.setText("Creating...");
				createProjectsHelper.doCreates();
				statusLabel.setText("All Done, but wait for 'Building workspace...'");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statusLabel.setText("UNEXPECTED: CreateButton");
			}
			
		});

		closeAllButton = new Button(parent, SWT.BORDER);
		closeAllButton.setText("Close All Projects");
		closeAllButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				statusLabel.setText("Closing...");
				createProjectsHelper.doCloseAll();
				statusLabel.setText("All Done.");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				statusLabel.setText("UNEXPECTED: closeAllButton");
			}
		});

	}

	@Focus
	public void setFocus() {
		statusLabel.setFocus();
	}

	/**
	 * This method manages the selection of your current object. In this example
	 * we listen to a single Object (even the ISelection already captured in E3
	 * mode). <br/>
	 * You should change the parameter type of your received Object to manage
	 * your specific selection
	 * 
	 * @param o
	 *            : the current object received
	 */
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) {

		// Remove the 2 following lines in pure E4 mode, keep them in mixed mode
		if (o instanceof ISelection) // Already captured
			return;

		// Test if label exists (inject methods are called before PostConstruct)
		if (statusLabel != null)
			statusLabel.setText("Current single selection class is : " + o.getClass());
	}

	/**
	 * This method manages the multiple selection of your current objects. <br/>
	 * You should change the parameter type of your array of Objects to manage
	 * your specific selection
	 * 
	 * @param o
	 *            : the current array of objects received in case of multiple selection
	 */
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selectedObjects) {

		// Test if label exists (inject methods are called before PostConstruct)
		if (statusLabel != null)
			statusLabel.setText("This is a multiple selection of " + selectedObjects.length + " objects");
	}
}
