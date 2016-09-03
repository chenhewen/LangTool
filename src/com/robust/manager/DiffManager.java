package com.robust.manager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.BeforeClass;

import com.robust.Const;
import com.robust.Global;
import com.robust.XmlParser;
import com.robust.file.AndroidFileFilter;
import com.robust.file.FileDealer;
import com.robust.file.IFileFilter;
import com.robust.log.Log;

public class DiffManager {

	private Global mGlobal;

	private XmlParser mXmlParser;

	private FileDealer mFileDealer;

	public DiffManager() {
		mGlobal = new Global();
		mXmlParser = mGlobal.getXmlParer();
		mFileDealer = mGlobal.getFileDealer();
	}

	/**
	 * create files for translation recursively of all files which can be accepted by {@link IFileFilter.accept(File)}}
	 * @param resourceDir
	 * @param outputDir
	 * @param modelFile
	 * @param ignoreFile
	 * @param onlyCopyModelFile
	 * @param filter
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void createTranslationForCustomDir(File resourceDir, File outputDir, File modelFile, File ignoreFile,
			boolean onlyCopyModelFile, IFileFilter filter) {

		checkArgs(resourceDir, outputDir, modelFile);
		ensureDir(outputDir);

		File[] srcFiles = mFileDealer.getFiles(resourceDir, filter);
		for (int i = 0; i < srcFiles.length; i++) {
			File srcFile = srcFiles[i];
			File destFile = new File(outputDir, filter.getOutputFileName(srcFile));
			createTranslation(srcFile, destFile, modelFile, ignoreFile, onlyCopyModelFile);
		}
	}

	/**
	 * compare srcFile and modelFile, output the missing text into destFile
	 * 
	 * @param srcFile
	 * @param destFile
	 * @param modelFile
	 * @param ignoreFile
	 * @throws IOException
	 * @throws JDOMException
	 */
	public void createTranslation(File srcFile, File destFile, File modelFile, File ignoreFile,
			boolean onlyCopyModelFile) {

		checkArgs(srcFile, destFile, modelFile);
		ensureFile(destFile);

		Map<String, Element> srcFileStringMap = null;
		if (onlyCopyModelFile) {
			srcFileStringMap = new LinkedHashMap<String, Element>();
		} else {
			srcFileStringMap = mXmlParser.getStringMapByFile(srcFile);
		}
		Map<String, Element> modelFileStringMap = mXmlParser.getStringMapByFile(modelFile);
		Map<String, Element> ignoreFileStringMap = mXmlParser.getStringMapByFile(ignoreFile);

		Iterator<Entry<String, Element>> modelFileIterator = modelFileStringMap.entrySet().iterator();
		while (modelFileIterator.hasNext()) {
			Entry<String, Element> next = modelFileIterator.next();
			if (srcFileStringMap.containsKey(next.getKey()) || ignoreFileStringMap.containsKey(next.getKey())) {
				modelFileIterator.remove();
			}
		}

		Map<String, Element> diffMap = modelFileStringMap;
		mXmlParser.setStringMapToFile(diffMap, destFile);
	}

	/**
	 * check arguments
	 * 
	 * @param srcFile
	 * @param destFile
	 * @param modelFile
	 */
	private void checkArgs(File srcFile, File destFile, File modelFile) {

		if (modelFile == null) {
			throw new IllegalArgumentException("modelFile can not be null");
		}
	}

	/**
	 * ensure the file existent, if it exists, the method does nothing, or
	 * create a new file
	 * 
	 * @param destFile
	 */
	private void ensureFile(File destFile) {
		if (destFile.exists()) {
			Log.i("[" + destFile.getName() + "]"
					+ " has already exsited, previous content cleared");
		}

		try {
			destFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ensure the directory
	 * 
	 * @param outputDir
	 */
	private void ensureDir(File outputDir) {
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
	}
}
