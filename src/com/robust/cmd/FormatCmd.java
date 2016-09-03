package com.robust.cmd;

import java.io.File;

import com.robust.Global;
import com.robust.XmlParser;

public class FormatCmd extends BaseCmd {

	private Global mGloble = new Global();
	private XmlParser mXmlParer;
	
	public FormatCmd() {
		mXmlParer = mGloble.getXmlParer();
	}
	
	@Override
	public void onRun(String[] args) {
		File srcFile = new File(args[0]);
		File modelFile = new File(args[0]);
		mXmlParer.formatStringMapInFile(srcFile, modelFile);
	}
	
	@Override
	public void getUsageInfo() {
		super.getUsageInfo();
	}

}
