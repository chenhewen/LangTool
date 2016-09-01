package com.robust.test;

import java.io.File;

public class TestDirs {

	public static final File OUTPUT = new File("G:/output");
	public static final File FAKE_MODEL_FILE = new File(OUTPUT, "model.xml");
	
	public static final File RESOURCE_DIR = new File("F:/AndroidStudioProjects/ZBoostAs/zBoost/src/main/res");
	public static final File OUTPUT_INNER_DIR = new File(OUTPUT, "inner");
	public static final File MODEL_FILE = new File("F:/AndroidStudioProjects/ZBoostAs/zBoost/src/main/res/values/strings.xml");;
	
	// outer language test url
	public static final File OUTER_RESOURCE_DIR = new File("G:/æ√∞ÓSVN/ZBoost/Release/languages/v2.0.2");
	public static final File OUTPUT_OUTTER_DIR = new File(OUTPUT, "outter");
	public static final File OUTPUT_OUTTER_DIR_ONE_FILE = new File(OUTPUT_OUTTER_DIR, "strings_cs.xml");
	
}
