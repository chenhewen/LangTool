package com.robust.test;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.Global;
import com.robust.file.AndroidFileFilter;
import com.robust.file.FlatFileFilter;
import com.robust.manager.DiffManager;
import com.robust.manager.ModifyManager;

public class TestModifyManager {

	private static Global sGlobal;

	private static ModifyManager sModifyManager;
	
	@BeforeClass
	public static void start() {
		sGlobal = new Global();
		sModifyManager = new ModifyManager();
	}
	
	@Test
	public void testRemoveFromAndroidDir() throws JDOMException, IOException {
		sModifyManager.removeUnused(TestDirs.OUTPUT_OUTTER_DIR, TestDirs.FAKE_MODEL_FILE, new FlatFileFilter());
	}
}
