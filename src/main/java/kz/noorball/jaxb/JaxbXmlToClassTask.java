package kz.noorball.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JaxbXmlToClassTask {
    public static void main(String[] args) {
        String xmlData =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<shop>\n" +
                        "    <goods>\n" +
                        "        <names>S1</names>\n" +
                        "        <names>S2</names>\n" +
                        "    </goods>\n" +
                        "    <count>12</count>\n" +
                        "    <profit>123.4</profit>\n" +
                        "    <secretData>String1</secretData>\n" +
                        "    <secretData>String2</secretData>\n" +
                        "    <secretData>String3</secretData>\n" +
                        "    <secretData>String4</secretData>\n" +
                        "    <secretData>String5</secretData>\n" +
                        "</shop>";
        StringReader reader = new StringReader(xmlData);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Shop.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object o = unmarshaller.unmarshal(reader);
            System.out.println(o.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlType(name = "shop")
    @XmlRootElement
    public static class Shop {
        Goods good;
        int count;
        double profit;
        String[] secretData;

        public static class Goods {
            @XmlElement()
            public List<String> names = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Shop{" +
                    "good=" + good +
                    ", count=" + count +
                    ", profit=" + profit +
                    ", secretData=" + Arrays.toString(secretData) +
                    '}';
        }
    }
}
