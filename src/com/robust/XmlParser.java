package com.robust;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import com.robust.log.Log;

public class XmlParser {
	
	private XMLOutputter mXmlOutputter;
	
	private SAXBuilder mSaxBuilder;
	
	public XmlParser(XMLOutputter xmlOutput, SAXBuilder saxBuilder) {
		mXmlOutputter = xmlOutput;
		mSaxBuilder = saxBuilder;
	}

	//==================DiffManager====================
	public Map<String, Element> getStringMapByFile(File file) throws JDOMException, IOException {
		Map<String, Element> map = new LinkedHashMap<String, Element>();
		if (file == null) {
			return map;
		}
		
		Document document = mSaxBuilder.build(file);
		List<Element> elementList = document.getRootElement().getChildren();
		
		for (Element e : elementList) {
			map.put(e.getAttributeValue(Const.ATTR_NAME), e.clone());
		}
		return map;
	}
	
	/**
	 * create an empty xml 
	 * @param outputStream
	 * @throws IOException
	 */
	public Document createEmptyXml() {
		Element rootElement = new Element(Const.TAG_ROOT);
		Document doc = new Document(rootElement);
		return doc;
	}
	
	/**
	 * set string map to file, this action will first clear previous content
	 * @param map
	 * @param file
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void setStringMapToFile(Map<String, Element> map, File file) throws JDOMException, IOException {
		Document document = createEmptyXml();
		document.getRootElement().setContent(map.values());
		
		mXmlOutputter.output(document, new FileOutputStream(file));
	}
	
	public Element createStringElement(String key, String value) {
		Element element = new Element(Const.TAG_STRING);
		element.setAttribute(Const.ATTR_NAME, key);
		element.setText(value);
		return element;
	}
	
	public Element getRootElement(InputStream inputStream) throws JDOMException, IOException {
		Document document = mSaxBuilder.build(inputStream);
		return document.getRootElement();
	}
	
	public Document addStringElement(Document doc, Content content) {
		doc.getRootElement().addContent(content);
		return doc;
	}
	
	//=================RmManager======================
	/**
	 * remove the unusedMap from the srcFile. actually, what really matters is the keys of the map.
	 * @param srcFile
	 * @param unusedMap
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void removeStringMapFromFile(File srcFile, Collection<String> unusedCollection) throws JDOMException, IOException {
		Document document = mSaxBuilder.build(srcFile);
		List<Element> elementList = document.getRootElement().getChildren();
		List<Element> copyList = new ArrayList<Element>();
		for (Element e : elementList){
			copyList.add(e);
		}
		
		Iterator<Element> iterator = copyList.iterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			if (unusedCollection.contains(e.getAttributeValue(Const.ATTR_NAME))) {
				boolean success = document.getRootElement().removeContent(e);
				if (success) {
					Log.i("remove element: " + e.getAttributeValue(Const.ATTR_NAME) + "success");
				} else {
					Log.i("remove element: " + e.getAttributeValue(Const.ATTR_NAME) + "failed");
				}
			}
		}
		
		// TODO: finally close the FileOutputStream
		mXmlOutputter.output(document, new FileOutputStream(srcFile));
	}
	
	public void  updateStringMapInFile(File srcFile, Map<String, Element> map) throws JDOMException, IOException {
		
		Set<String> mapKeySet = map.keySet();
		
		Document document = mSaxBuilder.build(srcFile);
		List<Element> elementList = document.getRootElement().getChildren();
		for (int i = 0; i < elementList.size(); i++) {
			Element e = elementList.get(i);
			String attributeValue = e.getAttributeValue(Const.ATTR_NAME);
			if (mapKeySet.contains(attributeValue)) {
				e.setText(map.get(attributeValue).getText());
			}
		}
		
		// TODO: finally close the FileOutputStream
		mXmlOutputter.output(document, new FileOutputStream(srcFile));
	}
}
