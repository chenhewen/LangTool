package com.robust.cmd;

import java.io.File;

import com.robust.file.AndroidFileFilter;
import com.robust.file.FlatFileFilter;
import com.robust.file.IFileFilter;
import com.robust.manager.DiffManager;

public class RetranCmd extends BaseCmd {

	DiffManager mDiffManager = new DiffManager();
	
	@Override
	public void onRun(String[] args) {

		String type = args[0];
		IFileFilter filter = getFileFilterByType(type);
		
		File resourceDir = new File(args[1]);
		File outputDir = new File(args[2]);
		File modelFile = new File(args[3]);
		File ignoreFile = new File(args[4]);
		
		mDiffManager.createTranslationForCustomDir(resourceDir, outputDir, modelFile, ignoreFile, true, filter);
	}
	
	@Override
	public void getUsageInfo() {
		super.getUsageInfo();
	}

}
