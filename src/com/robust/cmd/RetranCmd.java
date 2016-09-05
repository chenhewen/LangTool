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

		String type = getArg(args, 0);
		IFileFilter filter = getFileFilterByType(type);
		
		File resourceDir = new File(getArg(args, 1));
		File outputDir = new File(getArg(args, 2));
		File modelFile = new File(getArg(args, 3));
		File ignoreFile = getArg(args, 4) == null ? null : new File(getArg(args, 4));
		
		mDiffManager.createTranslationForCustomDir(resourceDir, outputDir, modelFile, ignoreFile, true, filter);
	}
	
	private String getArg(String[] args, int index) {
		if (index >= args.length ) {
			return null;
		}
		
		return args[index];
	}
	
	@Override
	public String getUsageInfo() {
		return "retran [androidDir | flatDir | singleFile] resourceDir outputDir modelFile [ignoreFile]";
	}

}
