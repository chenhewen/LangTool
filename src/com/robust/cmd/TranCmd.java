package com.robust.cmd;

import static com.robust.test.TestDirs.FAKE_MODEL_FILE;
import static com.robust.test.TestDirs.OUTPUT_OUTTER_DIR_ONE_FILE;

import java.io.File;
import java.util.Map;

import org.jdom2.Element;

import com.robust.Global;
import com.robust.XmlParser;

public class TranCmd extends BaseCmd {

	private Global mGloble = new Global();
	private XmlParser mXmlParer;
	
	public TranCmd() {
		mXmlParer = mGloble.getXmlParer();
	}
	
	@Override
	public void onRun(String[] args) {
		File srcFile = new File (args[0]);
		File modelFile = new File(args[1]);
		
		Map<String, Element> stringMapByFile = mXmlParer.getStringMapByFile(modelFile);
		mXmlParer.updateStringMapInFile(srcFile, stringMapByFile);
	}
	
	@Override
	public void getUsageInfo() {
		super.getUsageInfo();
	}
}
