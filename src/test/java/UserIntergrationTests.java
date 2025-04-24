import com.example.EximantionsTester.EximantionsTesterApplication;
import com.example.EximantionsTester.User;
import com.example.EximantionsTester.UserController;
import com.example.EximantionsTester.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")//Testar på en test fil med en databas som droppas efter varje gång det körts så ingen känslig information sparats.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EximantionsTesterApplication.class)
public class UserIntergrationTests {

    //För att ha en port
    @LocalServerPort
    private int port;

    //Testar riktiga endPoints utan MockMVC
    //Gör HTTP anrop i applikationen.(GET,POST,PUT,DELETE)
    @Autowired
    private TestRestTemplate restTemplate;

    //Skickar en HTTP till /user för att skapa en ny användare, sedan hämtaar via HTTP GET sedan verifieras att den sparats korrekt.
    @Test
    public void testCreateAndGetUserByHttp(){
       User user = new User();
       user.setName("John");
       user.setEmail("@Doe");
       user.setId(null);


         ResponseEntity<User> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/user", user, User.class);


         assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());

          Long userId = postResponse.getBody().getId();

         ResponseEntity<com.example.EximantionsTester.User> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/user/" +
              userId, User.class);


         assertEquals(HttpStatus.OK, getResponse.getStatusCode());

         assertEquals("John", getResponse.getBody().getName());
         assertEquals("@Doe", getResponse.getBody().getEmail());

    }
}
