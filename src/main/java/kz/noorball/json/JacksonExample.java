package kz.noorball.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JacksonExample {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Mur";
        cat.age = 3;
        cat.weight = 15;

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, cat);

        System.out.println(writer.toString());

        String json = "{\"name\":\"Mur\",\"age\":3,\"weight\":15}";

        StringReader reader = new StringReader(json);
        Cat cat2 = mapper.readValue(reader, Cat.class);
        System.out.println(cat2.name + " " + cat2.age + " " + cat2.weight);
    }

    @Data
    @NoArgsConstructor
    static class Cat {
        String name;
        Integer age;;
        Integer weight;
    }
}
