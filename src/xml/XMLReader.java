package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import control.Controller;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import turtle.Agent;
import turtle.Turtle;

public class XMLReader {
	
	private Document doc;
	private Controller controller;
	
	public XMLReader(String location, Controller controller) throws Exception
	{
		this.controller = controller;
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
			
			if (variableList.item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			
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
	
	private Element getGlobalElement() {
		return ((Element) doc.getElementsByTagName("global").item(0));
	}
	
	public Color getBackgroundColor() {
		return Color.web(getGlobalElement()
				.getElementsByTagName("background").item(0).getTextContent());
	}
	
	
	public ResourceBundle getLanguage() {
		return ResourceBundle.getBundle(getGlobalElement()
				.getElementsByTagName("language").item(0).getTextContent());
	}
	
	public String getImageLocation() {
		return getGlobalElement()
				.getElementsByTagName("picture").item(0).getTextContent();
	}
	
	public List<String> getPalette() {
		List<String> palette = new ArrayList<>();
		NodeList colorList = getGlobalElement()
				.getElementsByTagName("palette").item(0).getChildNodes();
		for (int i = 0; i<colorList.getLength(); i++) {
			if (colorList.item(i).getNodeType() != Node.ELEMENT_NODE)
				continue;
			
			palette.add(colorList.item(i).getTextContent());
				
		}
		return palette;
	}
	
	public void overWrite() {
		controller.setVariables(getVariables());
		
		List<Agent> allNewTurtles = new ArrayList<>();
		for (List<Agent> individual : getTurtles()) {
			Turtle t = new Turtle();
			t.setStates(individual);
			allNewTurtles.add(t);
		}
		controller.getEnvironment().setAllTurtles(allNewTurtles);
		controller.getEnvironment().setActiveTurtles(new ArrayList<Agent>());
		controller.getEnvironment().addActiveTurtle(allNewTurtles.get(0));
		
		controller.setBackGroundColor(getBackgroundColor());
		controller.changeLanguage(getLanguage());
		controller.setImageLocation(getImageLocation());
		controller.setOverallPalette(getPalette());
	}
	
}
