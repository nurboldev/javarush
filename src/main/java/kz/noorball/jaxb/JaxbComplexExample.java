package kz.noorball.jaxb;

import lombok.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class JaxbComplexExample {
    public static void main(String[] args) {
        String xmldata = "<zoo><cat/><cat/><dog/><cat/></zoo>";
        StringReader reader = new StringReader(xmldata);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Zoo.class, Cat.class, Dog.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Zoo zoo = (Zoo)unmarshaller.unmarshal(reader);
            System.out.println(zoo);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlType(name = "zoo")
    @XmlRootElement
    public static class Zoo {

        @XmlAnyElement
        List animals = new ArrayList<>();

        @Override
        public String toString() {
            return "Zoo{" +
                    "animals=" + animals +
                    '}';
        }
    }

    @XmlType(name = "cat")
    @XmlRootElement
    public static class Cat {
        public String name;
        int age;
        int weight;

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
