package kz.noorball.jaxb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class JaxbExample {
    public static void main(String[] args) throws JAXBException {
        Cat cat = new Cat();
        cat.name = "Mur";
        cat.age = 3;
        cat.weight = 15;

        Zoo zoo = new Zoo();
        zoo.animals.add(cat);
        zoo.animals.add(cat);

        StringWriter writer = new StringWriter();

//        JAXBContext context1 = JAXBContext.newInstance(Cat.class);
//        Marshaller marshaller1 = context1.createMarshaller();
//        marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller1.marshal(cat, writer);

        JAXBContext context2 = JAXBContext.newInstance(Cat.class, Zoo.class);
        Marshaller marshaller2 = context2.createMarshaller();
        marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller2.marshal(zoo, writer);


        System.out.println(writer.toString());
    }

    @Data
    @XmlType(name = "zoo")
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    static
    class Zoo {
        @XmlElementWrapper(name="wild-animals", nillable = true)
        @XmlElement(name = "tiger")
        public List animals = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor @AllArgsConstructor
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    static
    class Cat {
        @XmlElement(name = "catname")
        String name;
        @XmlAttribute(name = "age")
        Integer age;
        @XmlAttribute(name = "weight")
        Integer weight;
    }
}
