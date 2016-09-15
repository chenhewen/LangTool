package com.robust;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.Format.TextMode;
import org.jdom2.output.XMLOutputter;

import com.robust.file.FileDealer;

public class Global {

	private XMLOutputter mXmlOutputter;
	
	private XmlParser mXmlParser;
	
	private SAXBuilder mSaxBuilder;
	
	private FileDealer mFileDealer;
	
	public Global() {
		mXmlOutputter = new XMLOutputter();
		mXmlOutputter.setFormat(
				Format.getPrettyFormat().setExpandEmptyElements(true).setEncoding("UTF-8")
				.setIndent(Const.INDENT_FOUR_SPACE));
		
		mSaxBuilder = new SAXBuilder();
		mXmlParser = new XmlParser(mXmlOutputter, mSaxBuilder);
		mFileDealer = new FileDealer();
	}
	
	public XMLOutputter getXmlOutputter() {
		return mXmlOutputter;
	} 
	
	public XmlParser getXmlParer() {
		return mXmlParser;
	}
	
	public static void rawFormatXmlOutputter(XMLOutputter xmlOutputter) {
		xmlOutputter.setFormat(Format.getRawFormat().setExpandEmptyElements(true).setEncoding("UTF-8").setIndent(Const.INDENT_FOUR_SPACE));
	}

	public static void prettyFormatXmlOutputter(XMLOutputter xmlOutputter) {
		xmlOutputter.setFormat(Format.getPrettyFormat().setExpandEmptyElements(true).setEncoding("UTF-8").setIndent(Const.INDENT_FOUR_SPACE));
	}
	
	public SAXBuilder getSaxBuilder() {
		return mSaxBuilder;
	}

	public FileDealer getFileDealer() {
		return mFileDealer;
	}

	
	
}
