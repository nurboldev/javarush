package kz.noorball.wsexample.ws;

// таже аннотация, что и при описании интерфейса,
import javax.jws.WebService;

// но здесь используется с параметром endpointInterface,
// указывающим полное имя класса интерфейса нашего веб-сервиса
@WebService(endpointInterface = "kz.noorball.wsexample.ws.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {
    @Override
    public String getHelloString(String name) {
        // просто возвращаем приветствие
        return "Hello, " + name + "!";
    }
}
