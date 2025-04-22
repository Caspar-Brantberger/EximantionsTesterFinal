import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    // Detta test testar om användaren redan finns så kommer skriva en runtime exepction
    @Test
    void registerUserShouldThrowExceptionWhenUserAlreadyExists() {
        UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mockedUserRepository);
        User user = new User(1L,"John","Doe");

        when(mockedUserRepository.existsById(1L)).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.createUser(user));

      verify(mockedUserRepository, never()).save(any(User.class));
    }

}