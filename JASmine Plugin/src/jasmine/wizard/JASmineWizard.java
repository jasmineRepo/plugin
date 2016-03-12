package jasmine.wizard;

import java.net.URI;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import jasmine.project.JASmineProjectSupport;

public class JASmineWizard extends Wizard implements INewWizard {

	private JASmineProjectCreationPage jasmineProjPage;	  

	  public JASmineWizard() {
	    super();
	    setWindowTitle("New JAS-mine Project Wizard");
//	    setNeedsProgressMonitor(true);
	  }

//	  @Override
//	  public String getWindowTitle() {
//	    return "New JAS-mine Project Wizard";
//	  }

	  @Override
	  public void addPages() {
	    super.addPages();
	    
	    jasmineProjPage = new JASmineProjectCreationPage("Create new JAS-mine project");	    
	    addPage(jasmineProjPage);
	    
	  }

		@Override
		public boolean performFinish() {
		    String projectName = jasmineProjPage.getProjectName();
		    String modelName = jasmineProjPage.getModelName();
		    String packageName = jasmineProjPage.getPackageName();
		    URI location = null;
		    if (!jasmineProjPage.useDefaults()) {
		        location = jasmineProjPage.getLocationURI();
		    } // else location == null
		 
		    JASmineProjectSupport.createProject(projectName, modelName, packageName, location);
		    
		    return true;
	
		}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

	 
	
//	private static final String PAGE_NAME = "Custom Plug-in Project Wizard"; //$NON-NLS-1$
//	private static final String WIZARD_NAME = "New Custom Plug-in Project"; //$NON-NLS-1$
//	private WizardNewProjectCreationPage _pageOne;
//
//	
//	public JASmineWizard() {
//		setWindowTitle(WIZARD_NAME);
//	}
//
//	@Override
//	public void init(IWorkbench workbench, IStructuredSelection selection) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean performFinish() {
//	    String name = _pageOne.getProjectName();
//	    URI location = null;
//	    if (!_pageOne.useDefaults()) {
//	        location = _pageOne.getLocationURI();
//	    } // else location == null
//	 
//	    JASmineProjectSupport.createProject(name, location);
//	    
//	    return true;
//
//	}
//	
//	@Override
//	public void addPages() {
//	    super.addPages();
//	 
//	    _pageOne = new WizardNewProjectCreationPage(PAGE_NAME);
//	    _pageOne.setTitle(NewWizardMessages.JASmineProjectNewWizard_Project);
//	    _pageOne.setDescription(NewWizardMessages.JASmineProjectNewWizard_Create);
//	 
//	    addPage(_pageOne);
//	}


}
