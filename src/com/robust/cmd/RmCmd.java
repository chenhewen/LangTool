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
		
		File rootDir = new File(args[1]);
		File unusedFile = new File(args[2]);
		mModifyManager.removeUnused(rootDir, unusedFile, filter);
	}

	
	@Override
	protected IFileFilter getFileFilterByType(String type) {
		return super.getFileFilterByType(type);
	}
}
