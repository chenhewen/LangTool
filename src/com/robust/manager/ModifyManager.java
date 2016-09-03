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
import com.robust.file.IFileFilter;

public class ModifyManager {

	private Global mGlobal;

	private XmlParser mXmlParser;

	private FileDealer mFileDealer;

	public ModifyManager() {
		mGlobal = new Global();
		mXmlParser = mGlobal.getXmlParer();
		mFileDealer = mGlobal.getFileDealer();
	}

	public void removeUnused(File rootDir, File unusedFile, IFileFilter filter) {
		
		File[] modifyFiles = mFileDealer.getFiles(rootDir, filter);
		
		Map<String, Element> stringMapByFile = mXmlParser.getStringMapByFile(unusedFile);
		Set<String> keySet = stringMapByFile.keySet();
		
		for (int i = 0; i < modifyFiles.length; i++) {
			File srcFile = modifyFiles[i];
			mXmlParser.removeStringMapFromFile(srcFile, keySet);
		}
	}

}
