package com.robust.file;

import java.io.File;
import java.util.regex.Pattern;

import com.robust.Const;

/**
 * for android dir which is likely  to be rootDir/values-ar/strings.xml
 * @author Administrator
 *
 */
public class AndroidFileFilter extends BaseFileFilter {

	private static Pattern sPattern = Pattern.compile(Const.REGEX_ANDROID_XML_NAME);

	@Override
	public boolean accept(File file) {
		return sPattern.matcher(file.getName()).matches();
	}

	@Override
	public String getOutputFileName(File file) {
		return file.getParentFile().getName() + Const.EXTENSION_ANDROID_XML;
	}

}
