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

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EximantionsTesterApplication.class)
public class UserIntergrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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
