package com.robust.cmd;

import java.io.File;

import com.robust.file.AndroidFileFilter;
import com.robust.file.FlatFileFilter;
import com.robust.file.IFileFilter;

public abstract class BaseCmd {

	public void run(String[] args) {
		onRun(args);
	}

	public abstract void onRun(String[] args);

	public abstract String getUsageInfo();
	
	public void printUsageInfo() {
		System.out.println(getUsageInfo());
	}
	
	protected IFileFilter getFileFilterByType(String type) {
		IFileFilter filter = new FlatFileFilter();
		if (type.equals("androidDir")) {
			filter = new AndroidFileFilter();
		} else if (type.equals("flatDir")) {
			filter = new FlatFileFilter();
		} else if (type.equals("singleFile")) {
			filter = new FlatFileFilter();
		}
		
		return filter;
	}
	

}
