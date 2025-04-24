

import com.example.EximantionsTester.User;
import com.example.EximantionsTester.UserController;
import com.example.EximantionsTester.UserRepository;
import com.example.EximantionsTester.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



class UserControllerTest {

    private UserRepository mockRepo;
    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockRepo = mock(UserRepository.class);
        userService = new UserService(mockRepo);
        userController = new UserController(userService);
    }

    @AfterEach
    void tearDown() {
        mockRepo = null;
        userService = null;
        userController = null;
    }

    //KomponentTest

    @Test
     public void testgetUser() {

        User user = new User(1L, "John", "@Doe");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> userOptional = userController.getUserById(1L);

        assertEquals("John", userOptional.get().getName());
        assertEquals("@Doe", userOptional.get().getEmail());

    }


    }




