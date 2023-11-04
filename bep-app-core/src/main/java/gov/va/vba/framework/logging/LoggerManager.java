package gov.va.vba.framework.logging;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;


public class LoggerManager {
	
	//private static Logger  logger = Logger.getLogger("gov.va.vba.framework.logging");

	/*
	public void log(String msg){
		logger.trace("trace msg .... " + msg);
		logger.debug("debug msg .... " + msg);
		logger.info("info msg .... " + msg);
		logger.warn("warn msg .... "+ msg);
		logger.error("error msg .... "+ msg);
		logger.fatal("fatal msg .... " + msg);
		
	}
	*/
	
	public void setLogLevel(String loggerName, int level){
		
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	    final Configuration config = ctx.getConfiguration();

	    LoggerConfig loggerConfig; 
		
		if (loggerName.equalsIgnoreCase("root")){
			loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
		} else {
			loggerConfig = config.getLoggerConfig(loggerName);
		}
		
		switch (level) {
		case 0:
			loggerConfig.setLevel(Level.TRACE);
			break;
		case 1:
			loggerConfig.setLevel(Level.DEBUG);
			break;
		case 2:
			loggerConfig.setLevel(Level.INFO);
			break;
		case 3:
			loggerConfig.setLevel(Level.WARN);
			break;
		case 4:
			loggerConfig.setLevel(Level.ERROR);
			break;
		case 5:
			loggerConfig.setLevel(Level.FATAL);
			break;			
		default:
			break;
		}
	}
	
	public List<LoggerDto> getLoggers(boolean filterOn, String packageName){

		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	    final Configuration config = ctx.getConfiguration();

		List<LoggerDto> loggers = new ArrayList<LoggerDto>();
		
		LoggerDto dto = new LoggerDto();
		dto.setName("root logger");
		dto.setLevel(config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).getLevel().toString());
		loggers.add(dto);
		
		boolean hidePackage = packageName != null && packageName.length() > 0;
		
		for (String loggerKey : config.getLoggers().keySet()) {
		    
			LoggerConfig loggerConfig = config.getLoggers().get(loggerKey);
		    
		    if (hidePackage && loggerConfig.getName().startsWith(packageName)){
		    	continue;
		    }
		    if (filterOn && loggerConfig.getLevel() == null){
			    continue;
		    }
		    
			dto = new LoggerDto();
			dto.setName(loggerConfig.getName());
		    if (loggerConfig.getLevel() != null){
		    	dto.setLevel(loggerConfig.getLevel().toString());
		    }			
			
			
		    StringBuffer sbNames = new StringBuffer();
		    StringBuffer sbFiles = new StringBuffer();
		    
		    for (String appenderKey : loggerConfig.getAppenders().keySet()) {
		    	
		    	Appender app = (Appender) loggerConfig.getAppenders().get(appenderKey);
		    	
		    	sbNames.append(app.getName() + ":");
		    	
		    	if (app instanceof FileAppender){
		    		FileAppender fa = (FileAppender) app;
		    		sbFiles.append(fa.getFileName() + ":");
		    	}
		    }
		    if (sbNames.length() > 0){
		    	//System.out.println("sbNames - " + sbNames);
		    	dto.setAppenderNames(sbNames.toString().split(":"));
		    }
		    
		    if (sbFiles.length() > 0){
		    	//System.out.println("sbFiles - " + sbFiles);
		    	dto.setAppenderFiles(sbFiles.toString().split(":"));
		    }
			
		    loggers.add(dto);
		}
		
		//System.out.println("length - " + dto.getAppenderFiles().length);
		return loggers;

	}
	
	public List<AppenderDto> getAppenders(){

		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
	    final Configuration config = ctx.getConfiguration();
	    
		List<AppenderDto> appenders = new ArrayList<AppenderDto>();
		
		for (String appedenrKey : config.getAppenders().keySet()) {
			Appender app = config.getAppenders().get(appedenrKey);
			PatternLayout pl = (PatternLayout) app.getLayout();
			AppenderDto appender = new AppenderDto();
			appender.setName(app.getName());
			appender.setLayout(pl.getConversionPattern());
			appenders.add(appender);
		}
		
		return appenders;

	}
}
