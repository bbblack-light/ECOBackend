package ECOBackend.controllers;

import ECOBackend.controllers.utils.response.OperationResponse;
import ECOBackend.dto.UserDto;
import ECOBackend.model.user.User;
import ECOBackend.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"User"})
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public UserDto getUserInformation(@PathVariable(value = "name", required = false) String userIdParam) {
        return userService.getUserInformation(userIdParam);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/registration")
    public OperationResponse addNewUser(@RequestBody UserDto user) {
        User userAddSuccess = userService.addNewUser(user);
        if (userAddSuccess != null) {
            return new OperationResponse("ok");
        }
        return new OperationResponse("Произошла непредвиденная ошибка");
    }

    @PostMapping("/edit")
    public UserDto editUser(@RequestBody UserDto user) {
        return userService.edit(user);
    }

    @GetMapping("/info/{userId}")
    public UserDto getUserInfo(@PathVariable("userId") String userId) {
        return userService.getUserInformation(userId);
    }

    @DeleteMapping("/{userId}")
    public OperationResponse deleteUser(@PathVariable("userId") String userId) {
        return userService.delete(userId);
    }
}
