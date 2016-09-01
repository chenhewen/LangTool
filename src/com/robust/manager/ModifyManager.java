package com.robust.manager;

import static com.robust.test.TestDirs.FAKE_MODEL_FILE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.robust.Const;
import com.robust.Global;
import com.robust.XmlParser;
import com.robust.file.FileDealer;

public class ModifyManager {

	private Global mGlobal;

	private XmlParser mXmlParser;

	private FileDealer mFileDealer;

	public ModifyManager() {
		mGlobal = new Global();
		mXmlParser = mGlobal.getXmlParer();
		mFileDealer = mGlobal.getFileDealer();
	}

	/*
	 * public void modifyUnusedForAndroidDir(File srcFile, File unusedFile,
	 * boolean delete) {
	 * 
	 * }
	 * 
	 * public void modifyUnusedForXMLDir(File srcFile, File unusedFile, boolean
	 * delete) {
	 * 
	 * }
	 */

	public void removeUnusedForAndroidDir(File resourceDir, File unusedFile)
			throws JDOMException, IOException {

		File[] srcFiles = mFileDealer.getFiles(resourceDir, Const.REGEX_ANDROID_XML_NAME);
		removeUnusedPrivate(srcFiles, unusedFile);
	}

	public void removeUnusedForXmlDir(File resourceDir, File unusedFile) throws JDOMException, IOException {
		
		File[] srcFiles = mFileDealer.getFiles(resourceDir, Const.REGEX_XML_FILE);
		removeUnusedPrivate(srcFiles, unusedFile);
		
	}
	
	private void removeUnusedPrivate(File[] modifyFiles, File unusedFile) throws JDOMException, IOException {
		Map<String, Element> stringMapByFile = mXmlParser.getStringMapByFile(unusedFile);
		Set<String> keySet = stringMapByFile.keySet();
		
		for (int i = 0; i < modifyFiles.length; i++) {
			File srcFile = modifyFiles[i];
			mXmlParser.removeStringMapFromFile(srcFile, keySet);
		}
	}

}
