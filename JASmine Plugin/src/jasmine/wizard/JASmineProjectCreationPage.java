package jasmine.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class JASmineProjectCreationPage extends WizardNewProjectCreationPage {

	  private Text modelName;
	  private Text packageName;
	
	  public JASmineProjectCreationPage(String pageName) {
	    super(pageName);
	    setTitle("New Simulation Project");
	    setDescription("Create new JAS-mine simulation project.");
	  }
	
	  @Override
	  public void createControl(Composite parent) {
		  super.createControl(parent);
		  Composite control = (Composite) getControl();
//		  projectName = ((WizardNewProjectCreationPage)this).getProjectName();
	
	    Label label1 = new Label(control, SWT.NONE);
	    label1.setText("Model name");
	
	    modelName = new Text(control, SWT.BORDER | SWT.SINGLE);
	    modelName.setText("");
//	    modelName.addKeyListener(new KeyListener() {
//	
//	      @Override
//	      public void keyPressed(KeyEvent e) {
//	      }
//	
//	      @Override
//	      public void keyReleased(KeyEvent e) {
////	    	  String projectName = JASmineProjectCreationPage.this.getProjectName();
////	    	  boolean validProjectName = (projectName != "")||(projectName == null);
////	        if (validProjectName && !modelName.getText().isEmpty() && !packageName.getText().isEmpty()) {
////	          setPageComplete(true);
////	
////	        }
//	      }
//	
//	    });
	    
	    Label label2 = new Label(control, SWT.NONE);
	    label2.setText("Package name");
	
	    packageName = new Text(control, SWT.BORDER | SWT.SINGLE);
	    packageName.setText("");
//	    packageName.addKeyListener(new KeyListener() {
//	
//	      @Override
//	      public void keyPressed(KeyEvent e) {
//	      }
//	
//	      @Override
//	      public void keyReleased(KeyEvent e) {
////	    	  String projectName = JASmineProjectCreationPage.this.getProjectName();
////	    	   boolean validProjectName = (projectName != "")||(projectName == null);
////	        if (validProjectName && !modelName.getText().isEmpty() && !packageName.getText().isEmpty()) {
////	          setPageComplete(true);
////	        }
//	      }
//	    });
	
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    modelName.setLayoutData(gd);
	    packageName.setLayoutData(gd);
	
	    // required to avoid an error in the system
	    setControl(control);
	    setPageComplete(false);
	
	  }
	
	  public String getModelName() {
	    return modelName.getText();
	  }
	  
	  public String getPackageName() {
		    return packageName.getText();
		  }
	}