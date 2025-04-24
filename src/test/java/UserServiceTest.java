import com.example.EximantionsTester.User;
import com.example.EximantionsTester.UserRepository;
import com.example.EximantionsTester.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    //Enhetstest
    //Detta test testar att createUser i userService funkar korrekt genom att spara en användare ,men bara om de inte redan finns.
    @Test
    void registerUserShouldCallRepositorySave() {

        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mockedUserRepository);
        User user = new User(1L,"John","Doe");

        when(mockedUserRepository.existsById(1L)).thenReturn(false);

        userService.createUser(user);

        verify(mockedUserRepository).save(user);
    }

    //Enhetstest
    //Kollar om UserService.findbyId returnerar rätt användare med id
    @Test
    void testGetUsernameWithId() {

        User expectedUser = new User(1L, "John", "@Doe");
        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        when(mockedUserRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        UserService userService = new UserService(mockedUserRepository);


        Optional<User> actualUser = userService.findById(1L);

        assertEquals(expectedUser.getName(), actualUser.get().getName());
        assertEquals(expectedUser.getEmail(), actualUser.get().getEmail());
        assertEquals(expectedUser.getId(), actualUser.get().getId());

    }

}