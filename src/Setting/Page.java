package Setting;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Page {

    private int width;
    private int height;


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    //получаем настройки для Page
    public Page getValuePage (String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Page page = new Page();

        try {
            builder = factory.newDocumentBuilder();
            Document document = null;
            document = builder.parse(new File(fileName));
            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("page");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                {
                    Element eElement = (Element) nNode;
                    page.setWidth(Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent()));
                    page.setHeight(Integer.parseInt(String.valueOf(eElement.getElementsByTagName("height").item(0).getTextContent())));

                }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return page;
    }
}
