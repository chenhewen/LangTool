package com.robust;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Comment;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.util.IteratorIterable;

import com.robust.log.Log;

public class XmlParser {
	
	private XMLOutputter mXmlOutputter;
	
	private SAXBuilder mSaxBuilder;
	
	public XmlParser(XMLOutputter xmlOutput, SAXBuilder saxBuilder) {
		mXmlOutputter = xmlOutput;
		mSaxBuilder = saxBuilder;
	}

	public Map<String, Element> getStringMapByFile(File file) {
		Map<String, Element> map = new LinkedHashMap<String, Element>();
		if (file == null) {
			return map;
		}
		
		Document document = null;
		
		try {
			document = mSaxBuilder.build(file);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (document != null) {
			List<Element> elementList = document.getRootElement().getChildren();
			for (Element e : elementList) {
				map.put(e.getAttributeValue(Const.ATTR_NAME), e.clone());
			}
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
	public void setStringMapToFile(Map<String, Element> map, File file) {
		Document document = createEmptyXml();
		document.getRootElement().setContent(map.values());
		
		try {
			Global.prettyFormatXmlOutputter(mXmlOutputter);
			mXmlOutputter.output(document, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	/**
	 * remove the unusedMap from the srcFile. actually, what really matters is the keys of the map.
	 * @param srcFile
	 * @param unusedMap
	 * @throws JDOMException
	 * @throws IOException
	 */
	public void removeStringMapFromFile(File srcFile, Collection<String> unusedCollection) {
		Document document = null;
		try {
			document = mSaxBuilder.build(srcFile);
		} catch (JDOMException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (document != null) {
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
						Log.i("[" + srcFile.getName() + "]" + " element: " + e.getAttributeValue(Const.ATTR_NAME) + " remove success");
					} else {
						Log.i("[" + srcFile.getName() + "]" + " element: " + e.getAttributeValue(Const.ATTR_NAME) + " remove failed");
					}
				}
			}
		}
		
		
		// TODO: finally close the FileOutputStream
		try {
			Global.rawFormatXmlOutputter(mXmlOutputter);
			mXmlOutputter.output(document, new FileOutputStream(srcFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void  updateStringMapInFile(File srcFile, Map<String, Element> map) {
		
		Set<String> mapKeySet = map.keySet();
		
		Document document = buildFileNotThrow(srcFile);
		if (document == null) {
			Log.e(srcFile.getAbsolutePath() + "build failed! action stops!");
			return;
		}
		
		List<Element> elementList = document.getRootElement().getChildren();
		for (int i = 0; i < elementList.size(); i++) {
			Element e = elementList.get(i);
			String attributeValue = e.getAttributeValue(Const.ATTR_NAME);
			
			if (mapKeySet.contains(attributeValue)) {
				
				String translation = map.get(attributeValue).getText();
				if (StringUtils.isBlank(translation)) {
					List<Content> children = map.get(attributeValue).removeContent();
					e.setContent(children);
				} else {
					e.setText(translation);
				}
			}
		}
		
		// TODO: finally close the FileOutputStream
		try {
			Global.rawFormatXmlOutputter(mXmlOutputter);
			mXmlOutputter.output(document, new FileOutputStream(srcFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Document buildFileNotThrow(File file) {
		Document doc = null;
		try {
			 doc =  mSaxBuilder.build(file);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return doc;
	}
	
	public void formatStringMapInFile(File srcFile, File modelFile) {
		
			Document modelDocument = buildFileNotThrow(modelFile);
			Document srcDocument = buildFileNotThrow(srcFile);
			
			if (modelDocument == null) {
				Log.e(modelFile.getAbsolutePath() + " builds faild! action stops!");
				return;
			}
			
			if (srcDocument == null) {
				Log.e(srcFile.getAbsolutePath() + " builds faild! action stops");
				return;
			}
			
			IteratorIterable<Content> modelIterator = modelDocument.getRootElement().getDescendants();
			Element srcRootElement = srcDocument.getRootElement();
			
			Map<String, Element> srcFileStringMap = getStringMapByFile(srcFile);
			
			srcRootElement.removeContent();

			while (modelIterator.hasNext()) {
				Content next = modelIterator.next();
				if (next instanceof Comment) {
					srcRootElement.addContent(next.clone());
				} else if (next instanceof Element) {
					Element element = (Element) next;
					String attributeValue = element.getAttributeValue(Const.ATTR_NAME);
					Element srcElement = srcFileStringMap.get(attributeValue);
					if (srcElement != null) {
						srcRootElement.addContent(srcElement);
					}
				}
			}
			
			// TODO: finally close the FileOutputStream
			try {
				Global.prettyFormatXmlOutputter(mXmlOutputter);
				mXmlOutputter.output(srcDocument, new FileOutputStream(srcFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
