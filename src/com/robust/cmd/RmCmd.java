package com.robust.cmd;

import java.io.File;

import com.robust.file.IFileFilter;
import com.robust.manager.ModifyManager;

public class RmCmd extends BaseCmd {

	private ModifyManager mModifyManager = new ModifyManager();
	
	@Override
	public void onRun(String[] args) {
		String type = args[0];
		IFileFilter filter = getFileFilterByType(type);
		
		File resourceDir = new File(args[1]);
		File unusedFile = new File(args[2]);
		mModifyManager.removeUnused(resourceDir, unusedFile, filter);
	}

	
	@Override
	protected IFileFilter getFileFilterByType(String type) {
		return super.getFileFilterByType(type);
	}
	
	@Override
	public String getUsageInfo() {
		return "rm [androidDir | flatDir | singleFile] resourceDir unusedFile";
	}
}
