package jasmine.project;

public class ClassSourceCode {

//	static final String IMPORT_PREFIX = "it.zero11."; 	//May want to change this to remove the association with Zero11
	static final String IMPORT_PREFIX = "";
	
	//Collector Source Code Template
	static String collectorHeader(String packageName, String modelName) {
		return "import " + IMPORT_PREFIX + "microsim.annotation.GUIparameter;\n"
				+ "import " + IMPORT_PREFIX + "microsim.data.DataExport;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.db.DatabaseUtils;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.AbstractSimulationCollectorManager;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationEngine;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationManager;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.exception.SimulationException;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.exception.SimulationRuntimeException;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.annotation.GUIparameter;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.MultiKeyCoefficientMap;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.excel.ExcelAssistant;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventGroup;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventListener;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.Order;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.event.SingleTargetEvent;\n"
				+ "\n"
//				+ "import java.util.List;\n"
				+ "import org.apache.log4j.Logger;\n"
				+ "\n"
				+ "import " + packageName + ".model." + modelName + "Model;\n";
	}
	
	static String collectorSignature(String modelName) {
		return "public class " + modelName + "Collector extends AbstractSimulationCollectorManager implements EventListener {\n";
	}
	
	static String collectorBody(String modelName) {
		return "\tprivate final static Logger log = Logger.getLogger(" + modelName + "Collector.class);\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Toggle to export snapshot to .csv files\")\n"
				+ "\tboolean exportToCSV = true;				//If true, data will be recorded to .csv files in the output directory\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Toggle to export snapshot to output database\")\n"
				+ "\tboolean exportToDatabase = true;		//If true, data will be recorded in the output database in the output directory\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Set the time at which to start exporting snaphots to the database and/or .csv files\")\n"
				+ "\tDouble timeOfFirstSnapshot = 0.;\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Set the time between snapshots to be exported to the database and/or .csv files\")\n"
				+ "\tDouble timestepsBetweenSnapshots = 1.;\n"
				+ "\n"
				+ "\tpublic " + modelName + "Collector(SimulationManager manager) {\n"
				+ "\t\tsuper(manager);\n"
				+ "\t}\n"
				+ "\n"
				+ "\t//DataExport objects to handle exporting data to database and/or .csv files\n"
				+ "//\tprivate DataExport exportAgentsFromInputDatabase;\n"
//				+ "\n"
				+ "\tprivate DataExport exportAgentsCreated;\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Manager methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic void buildObjects() {\n"
//				+ "\t\tif(exportToCSV || exportToDatabase) {\n"
				+ "\n"
				+ "//\t\texportAgentsFromInputDatabase = new DataExport(((" + modelName + "Model) getManager()).getAgentsLoadedFromDatabase(), exportToDatabase, exportToCSV);\n"
				+ "\t\texportAgentsCreated = new DataExport(((" + modelName + "Model) getManager()).getAgentsCreated(), exportToDatabase, exportToCSV);\n"
				+ "\n"
				+ "\t\tlog.debug(\"Collector objects created\");"
//				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void buildSchedule() {\n"
//				+ "\t\tif(dumpToDatabase) {\n"
				+ "\n"
				+ "\t\t\tEventGroup collectorEvents = new EventGroup();\n"
				+ "\n"
				+ "\t\t\tcollectorEvents.addEvent(this, Processes.DumpInfo);\n"
				+ "\n"
				+ "\t\t\tgetEngine().getEventQueue().scheduleRepeat(collectorEvents, timeOfFirstSnapshot, Order.AFTER_ALL.getOrdering()-1, timestepsBetweenSnapshots);\n"
				+ "\n"
				+ "\t\tlog.debug(\"Collector schedule created\");"
				+ "\n"
//				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// EventListener\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic enum Processes {\n"
				+ "\t\tDumpInfo;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void onEvent(Enum<?> type) {\n"
				+ "\t\tswitch ((Processes) type) {\n"
				+ "\n"
				+ "\t\tcase DumpInfo:\n"
//				+ "\t\t\ttry {\n"
				+ "\n"
				+ "\t\t\t//Export to database and/or .csv files\n"
				+ "\t\t\tif(exportToDatabase || exportToCSV) {\n"
				+ "//\t\t\t\texportAgentsFromInputDatabase.export();\n"
				+ "\t\t\t\texportAgentsCreated.export();\n"
				+ "\t\t\t}\n"
				+ "\n"
//				+ "\t\t\t\tDatabaseUtils.snap(DatabaseUtils.getOutEntityManger(),\n"
//				+ "\t\t\t\t\t\t(long) SimulationEngine.getInstance().getCurrentRunNumber(),\n"
//				+ "\t\t\t\t\t\tgetEngine().getTime(),\n"
//				+ "\t\t\t\t\t\t((" + modelName + "Model) getManager()).agentsLoadedFromDatabase);\n"
//				+ "\n"
//				+ "\t\t\t\tDatabaseUtils.snap(DatabaseUtils.getOutEntityManger(),\n"
//				+ "\t\t\t\t\t\t(long) SimulationEngine.getInstance().getCurrentRunNumber(),\n"
//				+ "\t\t\t\t\t\tgetEngine().getTime(),\n"
//				+ "\t\t\t\t\t\t((" + modelName + "Model) getManager()).agentsCreated);\n"
//				+ "\n"
//				+ "\t\t\t} catch (Exception e) {\n"
//				+ "\t\t\t\tlog.error(e.getMessage());\n"
//				+ "\t\t\t}\n"
				+ "\t\t\tbreak;\n"
				+ "\n"
				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Own methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Access methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic boolean isExportToCSV() {\n"
				+ "\t	return exportToCSV;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setExportToCSV(boolean exportToCSV) {\n"
				+ "\t	this.exportToCSV = exportToCSV;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic boolean isExportToDatabase() {\n"
				+ "\t	return exportToDatabase;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setExportToDatabase(boolean exportToDatabase) {\n"
				+ "\t	this.exportToDatabase = exportToDatabase;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic Double getTimeOfFirstSnapshot() {\n"
				+ "\t\treturn timeOfFirstSnapshot;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setTimeOfFirstSnapshot(Double timeOfFirstSnapshot) {\n"
				+ "\t\tthis.timeOfFirstSnapshot = timeOfFirstSnapshot;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic Double getTimestepsBetweenSnapshots() {\n"
				+ "\t\treturn timestepsBetweenSnapshots;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setTimestepsBetweenSnapshots(Double timestepsBetweenSnapshots) {\n"
				+ "\t\tthis.timestepsBetweenSnapshots = timestepsBetweenSnapshots;\n"
				+ "\t}\n"
				+ "\n"				
				+ "}";
		
	}
	
	//Observer Source Code Template
	static String observerHeader(String packageName, String modelName) {
		return "import " + IMPORT_PREFIX + "microsim.annotation.GUIparameter;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.AbstractSimulationObserverManager;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationCollectorManager;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationEngine;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationManager;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.exception.SimulationException;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.exception.SimulationRuntimeException;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.annotation.GUIparameter;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.MultiKeyCoefficientMap;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.db.DatabaseUtils;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.excel.ExcelAssistant;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.CommonEventType;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventGroup;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventListener;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.Order;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.SingleTargetEvent;\n"
				+ "import " + IMPORT_PREFIX + "microsim.gui.GuiUtils;\n"
				+ "import " + IMPORT_PREFIX + "microsim.gui.plot.TimeSeriesSimulationPlotter;\n"
				+ "import " + IMPORT_PREFIX + "microsim.statistics.CrossSection;\n"
				+ "import " + IMPORT_PREFIX + "microsim.statistics.IDoubleSource;\n"
				+ "import " + IMPORT_PREFIX + "microsim.statistics.functions.MeanArrayFunction;\n"
				+ "import " + IMPORT_PREFIX + "microsim.statistics.functions.MultiTraceFunction;\n"
				+ "import " + packageName + ".model." + "Agent;\n"
				+ "import " + packageName + ".model." + modelName + "Model;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.event.SingleTargetEvent;\n"
				+ "\n"
//				+ "import java.util.List;\n"
				+ "import org.apache.log4j.Logger;\n";

	}

	static String observerSignature(String className) {
			
		return "public class " + className + "Observer extends AbstractSimulationObserverManager implements EventListener {\n"; 	
	
	}
	
	
	static String observerBody(String modelName) {
		
		return "\tprivate final static Logger log = Logger.getLogger(" + modelName + "Observer.class);\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Toggle to display charts in the GUI\")\n"
				+ "\tprivate boolean showGraphs = true;\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Set a regular time for any charts to update\")\n"
				+ "\tprivate Double chartUpdatePeriod = 1.;\n"	
				+ "\n"
				+ "\tprivate TimeSeriesSimulationPlotter csWealthPlotter, averagePlotter;\n"
				+ "\n"
				+ "\tpublic " + modelName + "Observer(SimulationManager manager, SimulationCollectorManager collectorManager) {\n"
				+ "\t\tsuper(manager, collectorManager);\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// EventListener\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic enum Processes {\n"
				+ "//\t\tUpdate;\n"
				+ "\t}"
				+ "\n"
				+ "\tpublic void onEvent(Enum<?> type) {\n"
				+ "\t\tswitch ((Processes) type) {\n"
				+ "\n"
				+ "//\t\tcase Update:\n"
				+ "//\n"
				+ "//\t\t\tbreak;\n"
				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Manager methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic void buildObjects() {\n"
				+ "\n"
				+ "\t\tif(showGraphs) {\n"
				+ "\n"
				+ "\t\t\tfinal " + modelName + "Model model = (" + modelName + "Model) getManager();\n"
				+ "\n"	
				+ "\t\t\tcsWealthPlotter = new TimeSeriesSimulationPlotter(\"Agents\' wealth\", \"wealth\");\n"
				+ "\t\t\tfor(Agent agent : model.getAgentsCreated()){\n"
				+ "\t\t\t\tcsWealthPlotter.addSeries(\"Agent \" + agent.getKey().getId(), (IDoubleSource) new MultiTraceFunction.Double(agent, Agent.Variables.Wealth));\n"
				+ "\t\t\t}\n"
				+ "\t\t\tGuiUtils.addWindow(csWealthPlotter, 50, 120, 700, 450);\n"
				+ "\n"
				+ "//\t\t\tCrossSection.Double wealthCS = new CrossSection.Double(model.getAgentsCreated(), Agent.Variables.Wealth);				//Obtain wealth values by IDoubleSource interface...\n"
				+ "\t\t\tCrossSection.Double wealthCS = new CrossSection.Double(model.getAgentsCreated(), Agent.class, \"getWealth\", true);		//... or obtain wealth values by Java reflection\n"
				+ "\t\t\taveragePlotter = new TimeSeriesSimulationPlotter(\"Average wealth\", \"wealth\");\n"
				+ "\t\t\taveragePlotter.addSeries(\"Average\", new MeanArrayFunction(wealthCS));\n"
				+ "\t\t\tGuiUtils.addWindow(averagePlotter, 750, 120, 700, 450);\n"
				+ "\t\t}\n"
				+ "\n"
				+ "\t\t\tlog.debug(\"Observer objects created\");\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void buildSchedule() {\n"
				+ "\n"
				+ "\t\tif(showGraphs) {\n"
				+ "\t\t\tEventGroup chartingEvents = new EventGroup();\n"
				+ "\n"
				+ "\t\t\tchartingEvents.addEvent(new SingleTargetEvent(csWealthPlotter, CommonEventType.Update));\n"
				+ "\t\t\tchartingEvents.addEvent(new SingleTargetEvent(averagePlotter, CommonEventType.Update));\n"
				+ "\t\t\tgetEngine().getEventQueue().scheduleRepeat(chartingEvents, 0., Order.AFTER_ALL.getOrdering()-1, chartUpdatePeriod);\n"
				+ "\t\t}\n"
				+ "\n"
				+ "\t\tlog.debug(\"Observer schedule created\");\n"
				+ "\n"
				+ "\t}\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Own methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Access methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic boolean isShowGraphs() {\n"
				+ "\t\treturn showGraphs;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setShowGraphs(boolean showGraphs) {\n"
				+ "\t\tthis.showGraphs = showGraphs;\n"
				+ "\t}\n"
				+ "\n"				
				+ "\tpublic Double getChartUpdatePeriod() {\n"
				+ "\t\treturn chartUpdatePeriod;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setChartUpdatePeriod(Double chartUpdatePeriod) {\n"
				+ "\t\tthis.chartUpdatePeriod = chartUpdatePeriod;\n"
				+ "\t}\n"
				+ "\n"				
				+ "}\n";
		
	}
	
	//Start Source Code Template
	static String startHeader(String packageName, String modelName) {
		return "import " + IMPORT_PREFIX + "microsim.engine.ExperimentBuilder;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationEngine;\n"
				+ "import " + IMPORT_PREFIX + "microsim.gui.shell.MicrosimShell;\n"
				+ "import " + packageName + ".model." + modelName + "Model;\n"
				+ "import " + packageName + ".experiment." + modelName + "Collector;\n"
				+ "import " + packageName + ".experiment." + modelName + "Observer;\n";

	}
		
	static String startSignature(String modelName) {
		return "public class " + modelName + "Start implements ExperimentBuilder {\n";
	}
		
	static String startBody(String modelName) {
		return "\tpublic static void main(String[] args) {\n"
				+ "\t\tboolean showGui = true;\n"
				+ "\n"
				+ "\t\tSimulationEngine engine = SimulationEngine.getInstance();\n"
				+ "\n"
				+ "\t\t/* If turnOffDatabaseConnection is set to true, the simulation run will not be connected to an\n"
				+ "\t\t * input or output database. This may speed up the building and execution of the simulation,\n"
				+ "\t\t* however the relational database management features provided by JAS-mine cannot then be used for\n"
				+ "\t\t* the duration of the simulation and data will not be persisted to the output database.  Any data\n" 
				+ "\t\t* should be exported to CSV files instead.  If an attempt is made to import any data from an input\n"
				+ "\t\t* database during the simulation, an exception will be thrown.  */\n"
				+ "\t\tengine.setTurnOffDatabaseConnection(false);\n"
				+ "\n"
				+ "\t\tMicrosimShell gui = null;\n"
				+ "\t\tif (showGui) {\n"
				+ "\t\t\tgui = new MicrosimShell(engine);\n"
				+ "\t\t\tgui.setVisible(true);\n"
				+ "\t\t}\n"
				+ "\n"
//				+ "\t\tengine.setBuilderClass(" + modelName + "Start.class);\n"
				+ "\t\t" + modelName + "Start experimentBuilder = new " + modelName + "Start();\n"
				+ "\t\tengine.setExperimentBuilder(experimentBuilder);\n"
				+ "\n"
				+ "\t\tengine.setup();\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void buildExperiment(SimulationEngine engine) {\n"
				+ "\t\t" + modelName + "Model model = new " + modelName + "Model();\n"
				+ "\t\t" + modelName + "Collector collector = new " + modelName + "Collector(model);\n"
				+ "\t\t" + modelName + "Observer observer = new " + modelName + "Observer(model, collector);\n"
				+ "\n"
				+ "\t\tengine.addSimulationManager(model);\n"
				+ "\t\tengine.addSimulationManager(collector);\n"
				+ "\t\tengine.addSimulationManager(observer);	\n"
				+ "\t}\n"
				+ "}\n";
	}
	
	
	//MultiRun Source Code Template
	static String multiRunHeader(String packageName, String modelName) {
		return "import " + IMPORT_PREFIX + "microsim.engine.SimulationEngine;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.MultiRun;\n"
				+ "import " + IMPORT_PREFIX + "microsim.gui.shell.MultiRunFrame;\n"
				+ "\n"
				+ "import " + packageName + ".model." + modelName + "Model;\n";

	}
	
	static String multiRunSignature(String modelName) {
		return "public class " + modelName + "MultiRun extends MultiRun {\n";
	}
	
	static String multiRunBody(String modelName) {
		return "\t// Experimental design usually involves one of the following:\n"
				+ "\t// (a) Running the simulation a given number of times, without changing the values of the parameters (but changing the random number seed)\n"
				+ "\t// (b) Spanning over the values of the parameters, keeping the random number seed fixed\n"
				+ "\t// In the example, the simulation is repeated a number of times equal to numberOfRepeatedRuns for each population size of agents, "
				+ "\t// specified by the parameter maxNumberOfAgents.\n"
				+ "\n"
				+ "\tpublic static boolean executeWithGui = true;\n"
				+ "\n"
				+ "\t// Define the parameters that specify the experiment, and assign an initial value (used in the first simulation)\n"
				+ "\tprivate Long counter = 1L;\n"
				+ "\tprivate Integer numberOfAgents = 10;\n"
				+ "\n"
				+ "\t// Define maximum values for the experiment (used in the last simulation)\n"
				+ "\tprivate static Integer numberOfRepeatedRuns = 5;		//Set default number of repeated runs\n"
				+ "\tprivate static Integer maxNumberOfAgents = 100000;		//Set default maximum number of agents\n"
				+ "\n"
				+ "\t//Set the absolute maximum number of runs when using the MultiRun GUI.  The series of simulations will stop when this\n"
				+ "\t//value is reached when using the MultiRun GUI.  Ensure that this is large enough to cover all necessary simulation runs\n"
				+ "\t//to prevent premature termination of experiment.\n"
				+ "\tprivate static Integer maxNumberOfRuns = (int) (numberOfRepeatedRuns * Math.log10(maxNumberOfAgents));\n"
				+ "\n"
				+ "\n"
				+ "\tpublic static void main(String[] args) {\n"
				+ "\n"
				+ "\t\tbatchModeArgumentParsing(args);		//Used to pass arguments to the main class via the command line if the user wants to run in 'batch mode' outside of Eclipse IDE\n"
//				+ "\t\t//MultiRun is designed for batch mode, so can overwrite initial \n\t\t//default maximum number of runs by passing it as \n\t\t//a command line argument with the following string:\n\t\t// -n 10\n\t\t//if, for example, the model is to be run 10 times.\n"
//				+ "\t\tfor (int i = 0; i < args.length; i++) {\n"
//				+ "\n"
//				+ "\t\t\tif (args[i].equals(\"-n\")){\t\t\t//Set the number of repeated runs in the experiment as a command line argument\n"
//				+ "\n"
//				+ "\t\t\t\ttry {\n"
//				+ "\t\t\t\t\tmaxNumberOfRuns = Integer.parseInt(args[i + 1]);\n"
//				+ "\t\t\t\t} catch (NumberFormatException e) {\n"
//				+ "\t\t\t\t\tSystem.err.println(\"Argument \" + args[i + 1] + \" must be an integer reflecting the maximum number of runs.\");\n"
//				+ "\t\t\t\t\tSystem.exit(1);\n"
//				+ "\t\t\t\t}\n"
//				+ "\n"
//				+ "\t\t\t\ti++;\n"
//				+ "\t\t\t}\n"
//				+ "\t\t\telse if (args[i].equals(\"-a\")){\t\t\t//Set the maximum number of agents in the experiment as a command line argument\n"
//				+ "\n"
//				+ "\t\t\t\ttry {\n"
//				+ "\t\t\t\tmaxNumberOfAgents = Integer.parseInt(args[i + 1]);\n"
//				+ "\t\t\t\t} catch (NumberFormatException e) {\n"
//				+ "\t\t\t\t\tSystem.err.println(\"Argument \" + args[i + 1] + \" must be an integer reflecting the maximum number of agents.\");\n"
//				+ "\t\t\t\t\tSystem.exit(1);\n"
//				+ "\t\t\t\t}\n"
//				+ "\n"
//				+ "\t\t\t\ti++;\n"
//				+ "\t\t\t}\n"
//				+ "\t\t\telse if (args[i].equals(\"-g\")){\t\t\t//Toggle the MultiRun Gui on / off by passing the string '-g true' (on) or '-g false' (off) as a command line argument\n"
//				+ "\t\t\t\texecuteWithGui = Boolean.parseBoolean(args[i + 1]);\n"
//				+ "\t\t\t\ti++;\n"
//				+ "\t\t\t}\n"
//				+ "\n"
//				+ "\t\t}\n"
				+ "\n"
				+ "\t\tSimulationEngine engine = SimulationEngine.getInstance();\n"
				+ "\n"
				+ "\t\t" + modelName + "MultiRun experimentBuilder = new " + modelName + "MultiRun();\n"
				+ "\t\tengine.setExperimentBuilder(experimentBuilder);\n"
				+ "\t\tengine.setup();\n"
				+ "\n"
				+ "\t\tif (executeWithGui)\n"
				+ "\t\t\tnew MultiRunFrame(experimentBuilder, \"" + modelName + " MultiRun\", maxNumberOfRuns);\n"
				+ "\t\telse\n"
				+ "\t\t\texperimentBuilder.start();\n"
				+ "\t}\n"
				+ "\n"
				+ "\t@Override\n"
				+ "\tpublic void buildExperiment(SimulationEngine engine) {\n"
				+ "\n"
				+ "\t\t" + modelName + "Model model = new " + modelName + "Model();\n"
				+ "\t\tengine.addSimulationManager(model);\n"
				+ "\n"
				+ "\t\t" + modelName + "Collector collector = new " + modelName + "Collector(model);\n"
				+ "\t\tengine.addSimulationManager(collector);\n"
				+ "\n"
				+ "\t\t//No need to add observer if running in batch mode\n"
				+ "\n"
				+ "\t\t// Overwrite the default values of the parameters of the simulation\n"
				+ "\t\tmodel.setNumberOfAgents(numberOfAgents);\n"
				+ "\t}\n"
				+ "\n"
				+ "\t@Override\n"
				+ "\tpublic boolean nextModel() {\n"
				+ "\t\t// Update the values of the parameters for the next experiment\n"
				+ "\t\tcounter++;\n"
				+ "\t\tif(counter > numberOfRepeatedRuns) {\n"
				+ "\t\t\tnumberOfAgents *= 10;			//Increase the number of agents by a factor of 10 for the next experiment\n"
				+ "\t\t\tcounter = 1L;					//Reset counter\n"
				+ "\t\t}\n"
				+ "\n"
				+ "\t\t// Define the continuation condition\n"
				+ "\t\tif(numberOfAgents < maxNumberOfAgents) {		//Stop when the numberOfAgents goes above maxNumberOfAgents\n"
				+ "\t\t\treturn true;\n"
				+ "\t\t}\n"
				+ "\t\telse return false;\n"
				+ "\t}\n"
				+ "\n"
				+ "\t@Override\n"
				+ "\tpublic String setupRunLabel() {\n"
				+ "\t\treturn numberOfAgents.toString() + \" agents, count: \" + counter.toString();\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t//MultiRun is designed for batch mode, so can overwrite default values by passing the values\n"  
				+ "\t//to the main class as command line arguments\n"
				+ "\t//E.g. To specify the number of repeated runs to equal 10, add the following string in\n"
				+ "\t//the command line when executing the java program:\n"
				+ "\t// -n 10\n"
				+ "\t//if, for example, the model is to be run 10 times.\n"
				+ "\tpublic static void batchModeArgumentParsing(String[] args) {\n"
				+ "\n"	
				+ "\t	for (int i = 0; i < args.length; i++) {\n"
				+ "\n"
				+ "\t		if (args[i].equals(\"-n\")){			//Set the number of repeated runs in the experiment as a command line argument\n"
				+ "\n"
				+ "\t			try {\n"
				+ "\t				numberOfRepeatedRuns = Integer.parseInt(args[i + 1]);\n"
				+ "\t			} catch (NumberFormatException e) {\n"
				+ "\t				System.err.println(\"Argument \" + args[i + 1] + \" must be an integer reflecting the number of repeated simulations to run.\");\n"
				+ "\t				System.exit(1);\n"
				+ "\t			}\n"
				+ "\t			\n"
				+ "\t			i++;\n"
				+ "\t		}\n"
				+ "\t		else if (args[i].equals(\"-a\")){			//Set the maximum number of agents in the experiment as a command line argument\n"
				+ "\t			\n"
				+ "\t			try {\n"
				+ "\t			maxNumberOfAgents = Integer.parseInt(args[i + 1]);\n"
				+ "\t			} catch (NumberFormatException e) {\n"
				+ "\t				System.err.println(\"Argument \" + args[i + 1] + \" must be an integer reflecting the maximum number of agents.\");\n"
				+ "\t				System.exit(1);\n"
				+ "\t			}\n"
				+ "\t			\n"
				+ "\t			i++;\n"
				+ "\t		}\n"
				+ "\t		else if (args[i].equals(\"-g\")){			//Toggle the MultiRun Gui on / off by passing the string '-g true' (on) or '-g false' (off) as a command line argument\n"
				+ "\t			executeWithGui = Boolean.parseBoolean(args[i + 1]);\n"
				+ "\t			i++;\n"
				+ "\t		}\n"
				+ "\t	}\n"
				+ "\t}\n"
				+ "\n"
				+ "}\n";

	}
	
	
	//Agent Source Code Template
	static String agentHeader() {
		return "import " + IMPORT_PREFIX + "microsim.data.db.PanelEntityKey;\n"
				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationEngine;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventListener;\n"
				+ "import " + IMPORT_PREFIX + "microsim.statistics.IDoubleSource;\n"
				+ "import " + IMPORT_PREFIX + "microsim.statistics.regression.RegressionUtils;\n"
				+ "\n"
//				+ "import javax.persistence.Column;\n"
				+ "import javax.persistence.Entity;\n"
//				+ "import javax.persistence.EnumType;\n"
//				+ "import javax.persistence.Enumerated;\n"
				+ "import javax.persistence.Id;\n"
				+ "import javax.persistence.Transient;\n";

	}
	
	static String agentSignature(String className) {
		return "@Entity\n"
				+ "public class " + className + " implements EventListener, IDoubleSource {\n";
	}
	
	static String agentBody() {
		return "\t@Id\n"
				+ "\tprivate PanelEntityKey key = new PanelEntityKey(idCounter++);\n"
				+ "\n"
				+ "\t@Transient\n"
				+ "\tprivate static long idCounter = 1000000;\n"
				+ "\n"
				+ "\tprivate double wealth = 100 * SimulationEngine.getRnd().nextDouble();		//Each agent has a random endowment of wealth\n"
				+ "\n"
//				+ "\t//Default Constructor\n"
//				+ "\tpublic Agent() {\n"
//				+ "\n"
//				+ "\t}\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// EventListener\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic enum Processes {\n"
				+ "\t\tAgentProcess;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void onEvent(Enum<?> type) {\n"
				+ "\t\tswitch ((Processes) type) {\n"
				+ "\t\tcase AgentProcess:\n"
				+ "\t\t\t//TODO: code to manage process\n"
				+ "\t\t\tif(RegressionUtils.event(0.1)) {	//Lose 90% of wealth with a 10% probability\n"
				+ "\t\t\t\twealth /= 10.;\n"
				+ "\t\t\t}\n"
				+ "\t\t\telse {\n"
				+ "\t\t\t\twealth *= 1.1;			//Else wealth grows steadily by 10% per time-step\n"
				+ "\t\t\t}\n"
				+ "\n"
				+ "\t\t\tbreak;\n"
				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// IDoubleSource\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic enum Variables{\n"
				+ "\t\tWealth;\n"
				+ "\t}\n"
				+ "\n"
				+ "\t@Override\n"
				+ "\tpublic double getDoubleValue(Enum<?> variable) {\n"
				+ "\t\tswitch((Variables) variable){\n"
				+ "\t\t\tcase Wealth:\n"
				+ "\t\t\t\treturn wealth;\n"
				+ "\n"
				+ "\t\t\tdefault: \n"
				+ "\t\t\t\tthrow new IllegalArgumentException(\"Unsupported variable\");\n" 		
				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Own methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Access methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic PanelEntityKey getKey() {\n"
				+ "\t\treturn key;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic double getWealth() {\n"
				+ "\t\treturn wealth;\n"
				+ "\t}\n"
				+ "\n"				
				+ "}";
	}
	
	
	//Model Source Code Template
	static String modelHeader() {
		return "import " + IMPORT_PREFIX + "microsim.engine.AbstractSimulationManager;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.engine.SimulationEngine;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.exception.SimulationException;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.exception.SimulationRuntimeException;\n"
				+ "import " + IMPORT_PREFIX + "microsim.annotation.GUIparameter;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.MultiKeyCoefficientMap;\n"
				+ "import " + IMPORT_PREFIX + "microsim.data.db.DatabaseUtils;\n"
//				+ "import " + IMPORT_PREFIX + "microsim.data.excel.ExcelAssistant;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventGroup;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.EventListener;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.Order;\n"
				+ "import " + IMPORT_PREFIX + "microsim.event.SingleTargetEvent;\n"
				+ "\n"
				+ "import java.util.ArrayList;\n"
				+ "import java.util.List;\n"
				+ "import org.apache.log4j.Logger;\n";
	}
	
	static String modelSignature(String className) {
		return "public class " + className + " extends AbstractSimulationManager implements EventListener {\n";
									
	}
	
	static String modelBody(String className) {
		return "\tprivate final static Logger log = Logger.getLogger(" + className	+ ".class);\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Set the number of agents to create\")\n"
				+ "\tInteger numberOfAgents = 10;\n"
				+ "\n"
				+ "\t@GUIparameter(description = \"Set the time at which the simulation will terminate\")\n"
				+ "\tDouble endTime = 20.;\n"
				+ "\n"
				+ "\tprivate List<Agent> agentsCreated;\n"
				+ "\n"
				+ "//\tprivate List<Agent> agentsLoadedFromInputDatabase;\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Manager methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic void buildObjects() {\n"
				+ "\n"
				+ "\t\t//Load agents from an input/input.h2.db database containing the Agent table (if there is one).\n"
				+ "//\t\tagentsLoadedFromInputDatabase = (List<Agent>) DatabaseUtils.loadTable(Agent.class);\n"
				+ "\n"
				+ "\t\t//Alternatively, create a collection of agents here\n"
				+ "\t\tagentsCreated = new ArrayList<Agent>();\n"
				+ "\t\tfor(int i=0; i < numberOfAgents; i++) {\n"
				+ "\t\t	agentsCreated.add(new Agent());\n"
				+ "\t\t}\n"
				+ "\n"
				+ "\t\tlog.debug(\"Model objects created\");\n"
				+ "\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void buildSchedule() {\n"
				+ "\n"
				+ "\t\tEventGroup modelEvents = new EventGroup();\n"
				+ "\n"
				+ "//\t\tmodelEvents.addCollectionEvent(agentsLoadedFromInputDatabase, Agent.Processes.AgentProcess);\n"
				+ "\t\tmodelEvents.addCollectionEvent(agentsCreated, Agent.Processes.AgentProcess);\n"
				+ "\n"
				+ "\t\tgetEngine().getEventQueue().scheduleRepeat(modelEvents, 0., 0, 1.);\n"
				+ "\n"
				+ "\t\tgetEngine().getEventQueue().scheduleOnce(new SingleTargetEvent(this, Processes.End), endTime, Order.AFTER_ALL.getOrdering());\n"
				+ "\n"
				+ "\t\tlog.debug(\"Model schedule created\");\n"
				+ "\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// EventListener\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic enum Processes {\n"
				+ "\t\tEnd;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void onEvent(Enum<?> type) {\n"
				+ "\t\tswitch ((Processes) type) {\n"
				+ "\n"
				+ "\t\tcase End:\n"
				+ "\t\t\tgetEngine().end();\n"
				+ "\t\t\tbreak;\n"
				+ "\n"
				+ "\t\t}\n"
				+ "\t}\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Own methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\t// Access methods\n"
				+ "\t// ---------------------------------------------------------------------\n"
				+ "\n"
				+ "\tpublic Double getEndTime() {\n"
				+ "\t	return endTime;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setEndTime(Double endTime) {\n"
				+ "\t	this.endTime = endTime;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic Integer getNumberOfAgents() {\n"
				+ "\t	return numberOfAgents;\n"
				+ "\t}\n"
				+ "\n"
				+ "\tpublic void setNumberOfAgents(Integer numberOfAgents) {\n"
				+ "\t	this.numberOfAgents = numberOfAgents;\n"
				+ "\t}\n"
				+ "\n"			
				+ "\tpublic List<Agent> getAgentsCreated() {\n"
				+ "\t\treturn agentsCreated;\n"
				+ "\t}\n"
				+ "\n"
//				+ "\tpublic void setAgentsCreated(List<Agent> agentsCreated) {\n"
//				+ "\t\tthis.agentsCreated = agentsCreated;\n"
//				+ "\t}\n"
//				+ "\n"
				+ "//\tpublic List<Agent> getAgentsLoadedFromInputDatabase() {\n"
				+ "//\t\treturn agentsLoadedFromInputDatabase;\n"
				+ "//\t}\n"
//				+ "\n"
//				+ "\tpublic void setAgentsLoadedFromDatabase(List<Agent> agentsLoadedFromDatabase) {\n"
//				+ "\t\tthis.agentsLoadedFromDatabase = agentsLoadedFromDatabase;\n"
//				+ "\t}\n"
				+ "\n"
				+ "}";
	}
	
}
