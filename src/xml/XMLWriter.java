package xml;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import control.Controller;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.paint.Paint;
import turtle.Agent;
import turtle.State;
import turtle.Turtle;

public class XMLWriter {
	
	private DocumentBuilder docBuilder;
	private Document doc;
	private Element root;
	private File output;
	private Controller source;
	
	public XMLWriter(String location, Controller source) throws ParserConfigurationException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		output = new File(location);
		this.source = source;
	}
	
	private void writeVariables() {
		Element variables = doc.createElement("variables");
		root.appendChild(variables);
		for (String key : source.getVariables().keySet()) {
			Element var = doc.createElement("var");
			
			Element name = doc.createElement("name");
			writeNode(name, key);
			var.appendChild(name);
			
			Element value = doc.createElement("value");
			writeNode(value, source.getVariables().get(key));
			var.appendChild(value);
			
		}
	}
	

	private void writeTurtles() {
		Element turtles = doc.createElement("turtles");
		root.appendChild(turtles);
		System.out.println(source.getAllTurtles().size());
		for (Agent t: source.getAllTurtles()) {
			Element turtle = doc.createElement("turtle");
			Iterator<State> iter = t.getStates().iterator();
			while (iter.hasNext()) {
				Element state = doc.createElement("state");
				writeState(iter.next(), state);
				turtle.appendChild(state);
			}
			turtles.appendChild(turtle);
		}
	}
	
	private void writeState(State t, Element state) {
		
		Element orientation = doc.createElement("orientation");
		writeNode(orientation, t.getOrientation());
		state.appendChild(orientation);
		
		Element xLocation = doc.createElement("x");
		writeNode(xLocation, t.getLocation().getX());
		state.appendChild(xLocation);
		
		Element yLocation = doc.createElement("y");
		writeNode(yLocation, t.getLocation().getY());
		state.appendChild(yLocation);
		
		Element penDown = doc.createElement("pendown");
		writeNode(penDown, t.isDown());
		state.appendChild(penDown);
		
		Element visible = doc.createElement("visible");
		writeNode(visible, t.isVisible());
		state.appendChild(visible);
		
		Element penColor = doc.createElement("pencolor");
		writeNode(penColor, t.getPenColor());
		state.appendChild(penColor);
		
		Element lineWidth = doc.createElement("linewidth");
		writeNode(lineWidth, t.getLineWidth());
		state.appendChild(lineWidth);
		
		Element time = doc.createElement("time");
		writeNode(time, t.getTime());
		state.appendChild(time);
		
		Element shape = doc.createElement("shape");
		writeNode(shape, t.getShape());
		state.appendChild(shape);
		
		
		
		//starting state is just the turtle BEFORE it
//		
//		private double myOrientation;
//		private Point2D myLocation;
//		private boolean penIsDown;
//		private boolean isVisible;
//		private Paint myPenColor;
//		private double myLineWidth;
//		private int myTime;
//		private Turtle startingState;
//		private int myShape;
	}
	
	private void writeNode(Element attribute, Object value) {
		attribute.appendChild(doc.createTextNode(String.valueOf(value)));
	}
	
	
	private void writeGlobal() {
		Element global = doc.createElement("global");
		root.appendChild(global);
		
		Element backgroundColor = doc.createElement("background");
		writeNode(backgroundColor, source.getBackgroundColor());
		global.appendChild(backgroundColor);
		
		Element language = doc.createElement("language");
		writeNode(language, source.getResources().getBaseBundleName());
		global.appendChild(language);
		
		Element picture = doc.createElement("picture");
		
		String loc = source.getImageLocation();
		if (loc.length() > 5 && loc.substring(0, 5).equals("file:")) {
			loc = loc.substring(5);
		}
		
		writeNode(picture, loc);
		global.appendChild(picture);
		
		Element palette = doc.createElement("palette");
		
		ListView<String> myColors = source.getPalette();
		for (int i = 0; i<myColors.getItems().size(); i++) {
			Element color = doc.createElement("color");
			writeNode(color, myColors.getItems().get(i));
			palette.appendChild(color);
		}
		
		global.appendChild(palette);
		
	}
	
	
	
	
	
	public void write() {
		try {
			doc = docBuilder.newDocument();
			root = doc.createElement("root");
			doc.appendChild(root);
			writeVariables();
			writeTurtles();
			writeGlobal();
			writeOutput();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	
	
	private void writeOutput() throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);
		transformer.transform(source, result);
	}
	

}
