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
import java.util.ArrayList;

public class Columns {

    private String title;
    private int width;

    public Columns(String title, int width) {
        this.title = title;
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    //получаем настройки для Columns
    public static ArrayList<Columns> getValueColumns(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        ArrayList<Columns> columns = new ArrayList<>();

        try {
            builder = factory.newDocumentBuilder();
            Document document = null;
            document = builder.parse(new File(fileName));
            document.getDocumentElement().normalize();

            NodeList nList1 = document.getElementsByTagName("column");

            for (int temp = 0; temp < nList1.getLength(); temp++) {
                Node nNode = nList1.item(temp);
                {
                    Element eElement = (Element) nNode;
                    columns.add(new Columns(eElement.getElementsByTagName("title").item(0).getTextContent(), Integer.parseInt(String.valueOf(eElement.getElementsByTagName("width").item(0).getTextContent()))));

                }

            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return columns;
    }
}
