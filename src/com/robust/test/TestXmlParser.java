package com.robust.test;

import  static com.robust.test.TestDirs.FAKE_MODEL_FILE;
import  static com.robust.test.TestDirs.MODEL_FILE;
import  static com.robust.test.TestDirs.OUTPUT_OUTTER_DIR_ONE_FILE;
import  static com.robust.test.TestDirs.OUTER_RESOURCE_DIR;
import  static com.robust.test.TestDirs.OUTPUT_INNER_DIR;
import  static com.robust.test.TestDirs.RESOURCE_DIR;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Parent;
import org.junit.BeforeClass;
import org.junit.Test;

import com.robust.Global;
import com.robust.XmlParser;

public class TestXmlParser {

	private static Global sGlobal;
	
	private static XmlParser sXmlParser; 
	
	@BeforeClass
	public static void start() {
		sGlobal = new Global();
		sXmlParser = sGlobal.getXmlParer();
	}
	
	//@Test
	public void testCreateEmptyXml() throws IOException {
		Document document = sXmlParser.createEmptyXml();
		sGlobal.getXmlOutputter().output(document, System.out);
	}
	
	//@Test
	public void testGetRootElement() throws IOException, JDOMException {
		sGlobal.getXmlOutputter().output(getRootElement(), System.out);
	}
	
	private Element getRootElement() throws IOException, JDOMException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = sXmlParser.createEmptyXml();
		sGlobal.getXmlOutputter().output(document, baos);
		byte[] byteArray = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		Element rootElement = sXmlParser.getRootElement(bais);
		return rootElement;
	}
	
	//@Test
	public void testAddStringElement()  throws IOException, JDOMException{
		Element newStringElement = sXmlParser.createStringElement("key", "value");
		Document document = sXmlParser.addStringElement(sXmlParser.createEmptyXml(), newStringElement);
		sGlobal.getXmlOutputter().output(document, System.out);
	}
	
	//@Test
	public void testRemoveStringMapFromFile() throws JDOMException, IOException {
		Map<String, Element> stringMapByFile = sXmlParser.getStringMapByFile(FAKE_MODEL_FILE);
		sXmlParser.removeStringMapFromFile(OUTPUT_OUTTER_DIR_ONE_FILE, stringMapByFile.keySet());
	}
	
	//@Test
	public void textUpdateStringMapInFile() throws JDOMException, IOException {
		Map<String, Element> stringMapByFile = sXmlParser.getStringMapByFile(FAKE_MODEL_FILE);
		sXmlParser.updateStringMapInFile(OUTPUT_OUTTER_DIR_ONE_FILE, stringMapByFile);
	}
	
	@Test
	public void testFormatStringMapInFile() throws JDOMException, IOException {
		sXmlParser.formatStringMapInFile(TestDirs.FORMAT_FILE_1, TestDirs.FORMAT_MODEL_FILE);
	}
}
