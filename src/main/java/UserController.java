import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public  User postUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
