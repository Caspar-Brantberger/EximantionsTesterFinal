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

    //Denna kod testar att användaren inte redan finns, men utan att att använda en riktig databas eller spring.
    @Test
    void registerUserShouldCallRepositorySave() {

        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mockedUserRepository);
        User user = new User(1L,"John","Doe");

        when(mockedUserRepository.existsById(1L)).thenReturn(false);

        userService.createUser(user);

        verify(mockedUserRepository).save(user);
    }
    @Test
    void testGetUsernameWithId() {
        // Arrange
        User expectedUser = new User(1L, "John", "@Doe");
        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        when(mockedUserRepository.findById(1L)).thenReturn(Optional.of(expectedUser));

        UserService userService = new UserService(mockedUserRepository);

        // Act
        Optional<User> actualUser = userService.findById(1L);


        // Assert
        assertEquals(expectedUser.getName(), actualUser.get().getName());
        assertEquals(expectedUser.getEmail(), actualUser.get().getEmail());
        assertEquals(expectedUser.getId(), actualUser.get().getId());

    }

}