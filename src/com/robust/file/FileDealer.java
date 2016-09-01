package com.robust.file;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * file manager
 * @author chenhewen
 *
 */
public class FileDealer {
	
	/**
	 * return files in an array which matches regex
	 * @param f
	 * @return
	 */
	public File[] getFiles(File f, String regex) {
		List<File> fileList = new ArrayList<File>();
		getFile(f, fileList, regex);
		return fileList.toArray(new File[0]);
	}
	
	public File[] getFiles(File f, IFileFilter filter) {
		List<File> fileList = new ArrayList<File>();
		getFile(f, fileList, filter);
		return fileList.toArray(new File[0]);
	}
	
	/**
	 * return all files in an array
	 * @param f
	 * @return
	 */
	public File[] getFiles(File f) {
		return getFiles(f, ".*");
	}
	
	/**
	 * recursively find files match regex
	 * @param rootDir rootDir 
	 * @param list store the files which matches
	 */
	private void getFile(File rootDir, List<File> list, String regex) {
		if (rootDir.isDirectory()) {
			File[] files = rootDir.listFiles();
			for (File file : files) {
				getFile(file, list, regex);
			}
		} else {
			
			//TODO¡¡performance improvement
			Pattern pattern = Pattern.compile(regex);
			if (pattern.matcher(rootDir.getName()).matches()) {
				list.add(rootDir);
			}
		}
	}
	
	private void getFile(File rootDir, List<File> list, IFileFilter filter) {
		if (rootDir.isDirectory()) {
			File[] files = rootDir.listFiles();
			for (File file : files) {
				getFile(file, list, filter);
			}
		} else {
			if (filter.accept(rootDir)) {
				list.add(rootDir);
			}
		}
	}
	
	
}
