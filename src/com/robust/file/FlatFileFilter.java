package com.robust.file;

import java.io.File;
import java.util.regex.Pattern;

import com.robust.Const;

public class FlatFileFilter extends BaseFileFilter {

	private static Pattern sPattern = Pattern.compile(Const.REGEX_XML_FILE);

	@Override
	public boolean accept(File file) {
		return sPattern.matcher(file.getName()).matches();
	}

	@Override
	public String getOutputFileName(File file) {
		return file.getName();
	}

}
