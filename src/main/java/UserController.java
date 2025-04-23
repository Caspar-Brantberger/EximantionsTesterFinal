import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //@PostMapping
    //public  User postUser(@RequestBody User user) {
      //  return userService.createUser(user);
    //}
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
       try {
           userService.createUser(user);
           return ResponseEntity.status(HttpStatus.CREATED).body("User created");
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
       }
    }

    @DeleteMapping
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
