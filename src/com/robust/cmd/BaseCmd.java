package com.robust.cmd;

import java.io.File;

import com.robust.file.AndroidFileFilter;
import com.robust.file.FlatFileFilter;
import com.robust.file.IFileFilter;

public abstract class BaseCmd {

	protected String[] mArgs;

	public void run(String[] args) {
		mArgs = args;
		onRun(args);
	}

	public abstract void onRun(String[] args);

	public void getUsageInfo() {
	}
	
	protected IFileFilter getFileFilterByType(String type) {
		IFileFilter filter = new FlatFileFilter();
		if (type.equals("androidDir")) {
			filter = new AndroidFileFilter();
		} else if (type.equals("FlatDir")) {
			filter = new FlatFileFilter();
		} else if (type.equals("singleFile")) {
			filter = new FlatFileFilter();
		}
		
		return filter;
	}
	

}
