package com.robust.log;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Log {

	public static final String LOG_NAME = "mylog";
	private static Logger sLogger;
	
	//TODO LogRecorder performance improvement
	static {
		sLogger = Logger.getLogger(LOG_NAME);
	}
	
	public static void i(String msg) {
		sLogger.log(Level.INFO, msg);
	}
	
	public static void w(String msg) {
		sLogger.log(Level.WARNING, msg);
	}
	
	public static void e(String msg) {
		sLogger.log(Level.SEVERE, msg);
	}
}
