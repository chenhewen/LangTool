package com.robust.test;

import  static com.robust.test.TestDirs.FAKE_MODEL_FILE;
import  static com.robust.test.TestDirs.MODEL_FILE;
import  static com.robust.test.TestDirs.OUTPUT_OUTTER_DIR;
import  static com.robust.test.TestDirs.OUTER_RESOURCE_DIR;
import  static com.robust.test.TestDirs.OUTPUT_INNER_DIR;
import  static com.robust.test.TestDirs.RESOURCE_DIR;

import java.io.IOException;

import org.apache.commons.io.filefilter.AndFileFilter;
import org.jdom2.JDOMException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.Global;
import com.robust.file.AndroidFileFilter;
import com.robust.file.FlatFileFilter;
import com.robust.manager.DiffManager;

public class TestDiffManager {

	private static Global sGlobal;
	
	private static DiffManager sDiffManager;

	@BeforeClass
	public static void start() {
		sGlobal = new Global();
		sDiffManager = new DiffManager();
	}
	
	public void testCreateTranslationForAndroidDir() throws IOException, JDOMException {
		sDiffManager.createTranslationForCustomDir(RESOURCE_DIR, OUTPUT_INNER_DIR, MODEL_FILE, null, false, new AndroidFileFilter());
	}
	
	@Test
	public void testCreateTranslationForXMLDir() throws IOException, JDOMException {
		sDiffManager.createTranslationForCustomDir(OUTER_RESOURCE_DIR, OUTPUT_OUTTER_DIR, MODEL_FILE, null, false, new FlatFileFilter());
	}
}
