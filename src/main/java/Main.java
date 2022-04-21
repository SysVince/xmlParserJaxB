import generated.Shiporder;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.awt.*;
import java.io.File;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        createJavaObject();

    }


    public static void createJavaObject(){
        JAXBContext jaxbContext = null;
        Unmarshaller unmarshaller = null;
        File file = new File("src\\main\\resources\\shiporder.xml");

        try {
            //Validation
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("src\\main\\resources\\shiporder.xsd"));

            //Create JAXB context
            jaxbContext = JAXBContext.newInstance(Shiporder.class);

            //Initializing unmarshaller
            unmarshaller = jaxbContext.createUnmarshaller();

            //Validate hjere against XDD
            unmarshaller.setSchema(schema);


            Shiporder shiporder = (Shiporder) unmarshaller.unmarshal(file);

            System.out.println("Shiporder id: "+ shiporder.getOrderid());
            System.out.println("Order person: "+shiporder.getOrderperson());
            System.out.println("Ship to: "+shiporder.getShipto().getName());
            System.out.println(shiporder.getItem().size());
            System.out.println(shiporder.getItem().get(0).getTitle());






            //Creates Java Objects from XML file
            /*JAXBElement<?> muzix = (JAXBElement<?>) unmarshaller.unmarshal(file);
            MusicType musicType = (MusicType) muzix.getValue();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
