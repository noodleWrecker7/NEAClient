package dev.adamhodgkinson;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TextureSheetManager {
    // Constructor loads the files and creates all the ImageViews
    private Image IMAGE;
    private HashMap<String, ImageView> textures = new HashMap(); // stores all textures

    public TextureSheetManager(File file) throws ParserConfigurationException, IOException, SAXException {
        // Loads and parses xml file
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("sprite"); // list of all sprites
        String imgPath = doc.getDocumentElement().getAttribute("imagePath"); // location of texture sheet
        IMAGE = new Image(imgPath); // loads texture sheet

        for (int i = 0; i < nodeList.getLength(); i++) { // for each sprite
            Node n = nodeList.item(i);
            NamedNodeMap attr = n.getAttributes();

            ImageView spriteView = new ImageView(); // creates new image view for the sprite
            spriteView.setImage(IMAGE); // sets image to texture sheet

            // sets up image view
            double x = Double.parseDouble(attr.getNamedItem("x").getNodeValue());
            double y = Double.parseDouble(attr.getNamedItem("y").getNodeValue());
            spriteView.setX(x);
            spriteView.setY(y);
            double w = Double.parseDouble(attr.getNamedItem("w").getNodeValue());
            double h = Double.parseDouble(attr.getNamedItem("h").getNodeValue());
            spriteView.setViewport(new Rectangle2D(x, y, w, h));
            // adds texture to textures hash map
            textures.put(n.getAttributes().getNamedItem("n").getNodeValue(), spriteView);
        }
        System.out.println("Textures loaded");
    }

    public ImageView getTexture(String name) {
        return textures.get(name);
    }
}
