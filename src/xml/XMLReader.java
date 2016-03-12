package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	
	Document doc;
	
	public XMLReader(String location) throws Exception
	{
		
		File playerFile = new File(location);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(playerFile);
			doc.getDocumentElement().normalize();

			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public Map<String, Double> getVariables() {
		
		return null;
	}
	
	
	
	public Map<String, String> getBasicInfo() {
		Map<String, String> basicInfo = new HashMap<String, String>();
		
		Element element = (Element) doc.getElementsByTagName("basic").item(0);
		for (String s: new String[] {"name", "title", "author"}) {
			basicInfo.put(s, element.getElementsByTagName(s).item(0).getTextContent());
		}
		return basicInfo;
	}
	
	public Map<String, String> getGlobalInfo() {
		Map<String, String> basicInfo = new HashMap<String, String>();
		Element element = (Element) doc.getElementsByTagName("global").item(0);

		for (String s: new String[] {"gametype",
				"satisfactionratio",
				"flamepercent",
				"energy","fishreproduction", "sharkreproduction", "eatingbonus", "toroidal", 
				"diffusion", "evaporation", "maxants", "spawnrate", "initialfood",
				"campdrop", "sniffthreshold", "slimespots"}) {
			if (element.getElementsByTagName(s).getLength() > 0) {
				basicInfo.put(s, element.getElementsByTagName(s).item(0).getTextContent());
			}
		}
		return basicInfo;
	}
	
	public Map<String, String> getColorMap() {
		Map<String, String> allColors = new HashMap<String, String>();
		Element element = (Element) doc.getElementsByTagName("global").item(0);
		if (element.getElementsByTagName("colorlist").getLength() > 0) {
			NodeList colorlist = element.getElementsByTagName("colorlist").item(0).getChildNodes();
			for (int i = 0; i < colorlist.getLength(); i++) {
					Node colorNode = colorlist.item(i);
					
					if (colorNode.getNodeType() == Node.ELEMENT_NODE) {
						Element colorElement = (Element) colorNode;
						
						allColors.put(colorElement.getElementsByTagName("type").item(0).getTextContent(), 
								colorElement.getElementsByTagName("hex").item(0).getTextContent());
					}
					
			}
		}
		return allColors;
	}
	
	
	
	
	public int[] getSize() {
		int[] size = new int[2];
		Element element = (Element) doc.getElementsByTagName("state").item(0);
		Element dimensions = (Element) element.getElementsByTagName("dimension").item(0);
		size[0] = Integer.parseInt(dimensions.getElementsByTagName("width").item(0).getTextContent());
		size[1] = Integer.parseInt(dimensions.getElementsByTagName("height").item(0).getTextContent());
		return size;
	}
	
	
	public List<Map<String, Integer>> getCells() {
		List<Map<String, Integer>> allCells = new ArrayList<Map<String, Integer>>();
		Element element = (Element) doc.getElementsByTagName("state").item(0);
		NodeList cells = element.getElementsByTagName("cellList").item(0).getChildNodes();
		
		for (int i = 0; i < cells.getLength(); i++) {
				Node cellNode = cells.item(i);
				
				if (cellNode.getNodeType() == Node.ELEMENT_NODE) {
					Map<String, Integer> cellMap = new HashMap<String, Integer>();
					Element cellElement = (Element) cellNode;
					for (String s: new String[] {"x", "y", "s"}) {
						cellMap.put(s, Integer.parseInt(cellElement.getElementsByTagName(s).item(0).getTextContent()));
					}
					allCells.add(cellMap);
				}
				
		}
		return allCells;
	}
	
}
