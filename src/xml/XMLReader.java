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

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import turtle.Agent;
import turtle.Turtle;

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
		Map<String, Double> vars =  new HashMap<>();
		NodeList variableList = doc.getElementsByTagName("variables").item(0).getChildNodes();
		for (int i = 0; i<variableList.getLength(); i++) {
			Element variable = (Element) variableList.item(i);
			vars.put(variable.getElementsByTagName("name").item(0).getTextContent(),
					Double.parseDouble(variable.getElementsByTagName("value").item(0).getTextContent()));
		}
 		return vars;
	}
	
	private String getTagContents(Element base, String tag) {
		return base.getElementsByTagName(tag).item(0).getTextContent();
	}
	
	public List<List<Agent>> getTurtles() {
		List<List<Agent>> turtles = new ArrayList<>();
		
		Element allTurtles = (Element) doc.getElementsByTagName("turtles").item(0);
		
		NodeList turtleList = allTurtles.getElementsByTagName("turtle");
		
		
		for (int i = 0; i<turtleList.getLength(); i++) {
			Node node = turtleList.item(i);
			
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			List<Agent> pastStates = new ArrayList<>();

			NodeList pastInputStates = node.getChildNodes();

			for (int j = 0; j < pastInputStates.getLength(); j++) {

				if (pastInputStates.item(j).getNodeType() != Node.ELEMENT_NODE)
					continue;

				Element inputState = (Element) pastInputStates.item(j);

				pastStates.add(new Turtle(Double.parseDouble(getTagContents(inputState, "orientation")),
						new Point2D(Double.parseDouble(getTagContents(inputState, "x")),
								Double.parseDouble(getTagContents(inputState, "y"))),
						Boolean.parseBoolean(getTagContents(inputState, "pendown")),
						Boolean.parseBoolean(getTagContents(inputState, "visible")),
						Color.web(getTagContents(inputState, "pencolor")),
						Double.parseDouble(getTagContents(inputState, "linewidth")),
						Integer.parseInt(getTagContents(inputState, "time"))));

			}

			turtles.add(pastStates);
			
		}
		
		return turtles;
	}
	
	
	public static void main(String[] args) {
		try {
			XMLReader x = new XMLReader("/Users/bobby_mac/Documents/workspace/slogo_team17/test.txt");
			System.out.println(x.getTurtles().get(0).size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
