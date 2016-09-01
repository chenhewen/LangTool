package com.robust.file;

import java.io.File;

/**
 * a file filter which can help choose which file we really care about
 * @author Administrator
 *
 */
public interface IFileFilter {

	public boolean accept(File file);
	
	public String getOutputFileName(File file);
}
