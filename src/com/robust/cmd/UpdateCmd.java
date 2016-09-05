package com.robust.cmd;

import java.io.File;
import java.util.Map;

import org.jdom2.Element;

import com.robust.Global;
import com.robust.XmlParser;

public class UpdateCmd extends BaseCmd {

	private Global mGloble = new Global();
	private XmlParser mXmlParer;
	
	public UpdateCmd() {
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
	public String getUsageInfo() {
		return "update srcFile modelFile";
	}
}
