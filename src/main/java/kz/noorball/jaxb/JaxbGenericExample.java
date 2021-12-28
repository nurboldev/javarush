package kz.noorball.jaxb;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;

public class JaxbGenericExample {
    public static void main(String[] args) throws IOException, JAXBException {
        String xmlData1 = "<cat><name>Murka</name><age>5</age><weight>4</weight></cat>";
        String xmlData2 = "<dog><name>Aqtos</name><age>5</age><owner>Nur</owner></dog>";
        Cat cat = convertFromXmlToNormal(xmlData1, Cat.class);
        Dog dog = convertFromXmlToNormal(xmlData2, Dog.class);
        System.out.println(cat);
        System.out.println(dog);
    }

    public static <T> T convertFromXmlToNormal(String xmlData, Class<T> clazz) throws JAXBException {
        StringReader reader = new StringReader(xmlData);
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        T t = (T) unmarshaller.unmarshal(reader);
        return t;
    }

    @XmlType(name = "cat")
    @XmlRootElement
    public static class Cat {
        public String name;
        public int age;
        public int weight;

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }

    @Data
    @XmlType(name = "dog")
    @XmlRootElement
    public static class Dog {
        String name;
        int age;
        String owner;
    }
}
