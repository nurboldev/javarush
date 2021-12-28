package kz.noorball.wsexample;

// нужно, чтобы получить wsdl описание и через него
// дотянуться до самого веб-сервиса

import kz.noorball.wsexample.ws.HelloWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

// такой эксепшн возникнет при работе с объектом URL
// классы, чтобы пропарсить xml-ку c wsdl описанием
// и дотянуться до тега service в нем

// интерфейс нашего веб-сервиса (нам больше и нужно)


public class HelloWebServiceClient {
    public static void main(String[] args) throws MalformedURLException {
        // создаем ссылку на wsdl описание
        URL url = new URL("http://localhost:1986/wss/hello?wsdl");

        // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
        // 1-ый аргумент смотрим в атрибуте targetNamespace
        // 2-ой аргумент смотрим в атрибуте name
        QName qname = new QName("http://ws.wsexample.noorball.kz/", "HelloWebServiceImplService");

        // Теперь мы можем дотянуться до тега service в wsdl описании,
        Service service = Service.create(url, qname);
        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса
        HelloWebService hello = service.getPort(HelloWebService.class);

        // Ура! Теперь можно вызывать удаленный метод
        System.out.println(hello.getHelloString("JavaRush"));
    }
}