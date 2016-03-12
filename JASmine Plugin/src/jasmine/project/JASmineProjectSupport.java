package jasmine.project;
 
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class JASmineProjectSupport {
    /**
     * 
     * - create the default JAS-mine project
     * - add the JAS-mine project nature
     * - create the folder structure and files
     *
     * @param projectName
     */
    public static IProject createProject(String projectName, String modelName, String packageName, URI location) {
        
    	Assert.isNotNull(projectName);
        Assert.isTrue(projectName.trim().length() > 0);
        Assert.isNotNull(modelName);
        Assert.isTrue(modelName.trim().length() > 0);
        Assert.isNotNull(packageName);
        Assert.isTrue(packageName.trim().length() > 0);  	
        if(packageName.equals("package")) {
	    	JOptionPane.showMessageDialog(null, "\'" + packageName + "\' cannot be used for the name of the packages.  \nPlease enter a name for the packages that is not equal to the string \'package\'", "Java Model Exception: Invalid package name specified", JOptionPane.ERROR_MESSAGE);
        	Assert.isTrue(!packageName.equals("package"));
        }
        

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject project = root.getProject(projectName);
        try {
        	//Create and open the project
			project.create(null);
	        project.open(null);
	        
	        //Add the Java nature to the project
	        IProjectDescription description = project.getDescription();
//	        description.setNatureIds(new String[] { JavaCore.NATURE_ID });	//For Java project

	        //For Maven project (see http://stackoverflow.com/questions/24503245/how-to-create-maven-project-with-pom-file-programmatically-while-creating-eclips)
	        description.setNatureIds(new String[] {
//	                     "org.eclipse.jem.workbench.JavaEMFNature",		//Is this really required?  If so, need to install Eclipse EMF Plugin (see https://www.eclipse.org/forums/index.php/t/198753/)
	                     "org.eclipse.wst.common.modulecore.ModuleCoreNature",
	                     "org.eclipse.jdt.core.javanature",			//equivalent to JavaCore.NATURE_ID
	                     "org.eclipse.m2e.core.maven2Nature",
	                     "org.eclipse.wst.common.project.facet.core.nature"});

	        project.setDescription(description, null);
	        
	        //Create our Java project
	        IJavaProject javaProject = JavaCore.create(project);
	        
	        //Set the Java builder's output folder	        
	        IFolder binFolder = project.getFolder("bin"); //$NON-NLS-1$
	        binFolder.create(false, true, null);
	        javaProject.setOutputLocation(binFolder.getFullPath(), null);

	        //Define class path entries, which define the roots of package fragments - No need as this defines the JRE System Library that will be uploaded anyway via Maven.
//	        List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
//	        IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
//	        LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
//	        for (LibraryLocation element : locations) {
//	        	entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
//	        	System.out.println(element.toString() + ", " + element.getSystemLibraryPath().toString());
//	        }
//	        //Add libs to project class path  	 //TODO: Get libraries to be within the normal JRE System Library folder, just like in other Java projects
//	        javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
	        
	        //Add folders, packages and classes to the project
	        IFolder sourceFolder = addFolderStructureToProject(project, javaProject);
    		addEmptyPackages(sourceFolder, javaProject, packageName);
    		addExperimentPackageAndClasses(sourceFolder, javaProject, packageName, modelName);
    		addModelPackageAndClasses(sourceFolder, javaProject, packageName, modelName);
    		addPomFile(project);
    		addPersistenceFile(project);
    		addLog4jPropertiesFile(project);
	        	        
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        return project;
    }

	/**
     * Method to create folder - it can handle cases where folder has subfolders.
     * 
     * @param folder
     * @throws CoreException
     */
    private static void createFolder(IFolder folder) throws CoreException {
        IContainer parent = folder.getParent();
        if (parent instanceof IFolder) {
            createFolder((IFolder) parent);
        }
        if (!folder.exists()) {
            folder.create(false, true, null);
        }
    }

    
    /**
     * Create the folder structure of the project.
     *
     * @param project
     * @return sourceFolder
     * @throws CoreException
     */
    private static IFolder addFolderStructureToProject(IProject project, IJavaProject javaProject) throws CoreException {
    	
    	String[] folders = {
    			"src/main/resources/META-INF",
    			"input",
//    			"libs",
//    			"output",
//    			"target/classes/META-INF"
    			};
    	
    	//Source folder
    	IFolder sourceFolder = project.getFolder("src/main/java");
    	createFolder(sourceFolder);
    	
    	for(String folderName : folders) {
    		IFolder folder = project.getFolder(folderName);
    		createFolder(folder);
    	}
    	
    	//Add created folders to the class entries of the project (otherwise compilation will fail)    	
        IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
//        IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
//        IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
//        System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
//        newEntries[oldEntries.length] = JavaCore.newSourceEntry(root.getPath());
        IClasspathEntry[] newEntries = new IClasspathEntry[]{JavaCore.newSourceEntry(root.getPath())};
        javaProject.setRawClasspath(newEntries, null);
        
        return sourceFolder;

    }

    
	private static void addEmptyPackages(IFolder sourceFolder, IJavaProject javaProject, String packageName) {
		
		String[] packageExtensions = {
			"algorithms", //$NON-NLS-1$
			"data", //$NON-NLS-1$
			"model.enums", //$NON-NLS-1$
		};
		
		try {
			for (String packExt : packageExtensions) {
				String packageFullName = packageName + "." + packExt; //$NON-NLS-1$
				
	    		//Create package
				javaProject.getPackageFragmentRoot(sourceFolder).createPackageFragment(packageFullName, true, null);
			}
		}
		catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	private static void addExperimentPackageAndClasses(IFolder sourceFolder, IJavaProject javaProject, String packageName, String modelName) {
		String packageFullName = packageName + ".experiment"; //$NON-NLS-1$
		
		String[] classExtensions = {"Collector", "Observer", "Start", "MultiRun"};
	
		try {
			//Add experiment package
			IPackageFragment pack = javaProject.getPackageFragmentRoot(sourceFolder).createPackageFragment(packageFullName, false, null);
			
			//Add classes to package
			for(String classExt : classExtensions) {
				String className = modelName + classExt;
				String classSourceHeader;
				String classSourceSignature;
				String classSourceBody;
				if(classExt.equals("Collector")) {
					classSourceHeader = ClassSourceCode.collectorHeader(packageName, modelName);
					classSourceSignature = ClassSourceCode.collectorSignature(modelName);
					classSourceBody = ClassSourceCode.collectorBody(modelName);
				} 
				else if(classExt.equals("Observer")) {
					classSourceHeader = ClassSourceCode.observerHeader();
					classSourceSignature = ClassSourceCode.observerSignature(modelName);
					classSourceBody = ClassSourceCode.observerBody(modelName);
				} 
				else if(classExt.equals("Start")) {
					classSourceHeader = ClassSourceCode.startHeader(packageName, modelName);
					classSourceSignature = ClassSourceCode.startSignature(modelName);
					classSourceBody = ClassSourceCode.startBody(modelName);
				} 
				else if(classExt.equals("MultiRun")) {
					classSourceHeader = ClassSourceCode.multiRunHeader(packageName, modelName);
					classSourceSignature = ClassSourceCode.multiRunSignature(modelName);
					classSourceBody = ClassSourceCode.multiRunBody(modelName);
				}
				else throw new IllegalArgumentException("Not a valid extension");
				
				//Generate the Java source code
				StringBuffer classBuffer = new StringBuffer();
				classBuffer.append("package " + pack.getElementName() + ";\n"); //$NON-NLS-1$ //$NON-NLS-2$
				classBuffer.append("\n"); //$NON-NLS-1$
				classBuffer.append(classSourceHeader); 
				classBuffer.append("\n"); //$NON-NLS-1$
				classBuffer.append(classSourceSignature);
				classBuffer.append("\n"); //$NON-NLS-1$
				classBuffer.append(classSourceBody);
				
				//Create the Java classes
				className = className + ".java"; //$NON-NLS-1$
				pack.createCompilationUnit(className, classBuffer.toString(), false, null);				
			}
			
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private static void addModelPackageAndClasses(IFolder sourceFolder, IJavaProject javaProject, String packageName, String modelName) {
		
		String packageFullName = packageName + ".model";
		
		String[] classExtensions = {"Model", "Agent"};
		
		try {
			//Add experiment package
			IPackageFragment pack = javaProject.getPackageFragmentRoot(sourceFolder).createPackageFragment(packageFullName, false, null);
			
			//Add classes to package
			for(String classExt : classExtensions) {
				String className;
				String classSourceHeader;
				String classSourceSignature;
				String classSourceBody;
				if(classExt.equals("Model")) {
					className = modelName + classExt;
					classSourceHeader = ClassSourceCode.modelHeader();
					classSourceSignature = ClassSourceCode.modelSignature(className);
					classSourceBody = ClassSourceCode.modelBody(className);
				} 
				else if(classExt.equals("Agent")) {
					className = classExt;
					classSourceHeader = ClassSourceCode.agentHeader();
					classSourceSignature = ClassSourceCode.agentSignature(className);
					classSourceBody = ClassSourceCode.agentBody();
				}
				else throw new IllegalArgumentException("Only Model or Agent are allowed");
				
				//Generate the Java source code
				StringBuffer classBuffer = new StringBuffer();
				classBuffer.append("package " + pack.getElementName() + ";\n");
				classBuffer.append("\n");
				classBuffer.append(classSourceHeader);
				classBuffer.append("\n");
				classBuffer.append(classSourceSignature);
				classBuffer.append("\n");
				classBuffer.append(classSourceBody);
				
				//Create the Java classes
				className = className + ".java"; //$NON-NLS-1$
				pack.createCompilationUnit(className, classBuffer.toString(), false, null);				
			}
	
		
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 
    private static void addPomFile(IProject project) {

		//Create POM file in project directory
		IFile pomFile = project.getFile("pom.xml");
		InputStream inputStream = new ByteArrayInputStream(PomCode.pomStringData().getBytes(StandardCharsets.UTF_8));
		try {
			pomFile.create(inputStream, false, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
    
//    public static Document loadXMLFromString(String xml) throws Exception
//	{
//	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	    DocumentBuilder builder = factory.newDocumentBuilder();
//	    InputSource is = new InputSource(new StringReader(xml));
//	    return builder.parse(is);
//	}
    
    private static void addPersistenceFile(IProject project) {
    	
		//Create XML file in project directory
		IFile persFile = project.getFile("src/main/resources/META-INF/persistence.xml");
		
		InputStream inputStream = new ByteArrayInputStream(PersistenceCode.persistenceStringData().getBytes(StandardCharsets.UTF_8));
		try {
			persFile.create(inputStream, false, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	
    }
    
	private static void addLog4jPropertiesFile(IProject project) {

		IFile log4jFile = project.getFile("src/log4j.properties");
		
		InputStream inputStream = new ByteArrayInputStream(Log4jPropertiesCode.log4jStringData().getBytes(StandardCharsets.UTF_8));
		try {
			log4jFile.create(inputStream, false, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	 
 
}
 
