package com.robust.cmd;

import java.io.File;
import java.io.IOException;

import org.jdom2.JDOMException;

import com.robust.manager.DiffManager;

public class DiffCmd extends BaseCmd {
	
	DiffManager mDiffManager = new DiffManager();
	
	
	public void diffAndroidDir(File resourceDir, File outputDir, File modelFile, File ignoreFile) throws IOException, JDOMException {
		mDiffManager.createTranslationForAndroidDir(resourceDir, outputDir, modelFile, ignoreFile, false);
	}
	
	public void diffFlatDir(File resourceDir, File outputDir, File modelFile, File ignoreFile) throws IOException, JDOMException {
		mDiffManager.createTranslationForXMLDir(resourceDir, outputDir, modelFile, ignoreFile, false);
	}
	
	@Override
	public void run(String args[]) {
		super.run(args);
	}
	
	@Override
	public void onRun() {
		String op = nextArgRequired();
		if (op.equals("dir")) {

		} else if (op.equals("file")) {
			
		}
	}
	
	@Override
	public void printInfo() {
		
	}
}
