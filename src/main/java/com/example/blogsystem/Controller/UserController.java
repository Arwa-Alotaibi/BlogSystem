package com.example.blogsystem.Controller;
import com.example.blogsystem.Dto.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/all")
    public ResponseEntity GetAll(){
        return ResponseEntity.status(200).body(userService.GetAllUsers());
    }
    @PostMapping("/register")
    public ResponseEntity AddUser(@Valid @RequestBody User newuser){
        userService.AddUser(newuser);
        return ResponseEntity.status(200).body(new ApiResponse("user created"));
    }

    @PutMapping("/update")
    public ResponseEntity UpdateUser(@Valid @RequestBody User user ,@AuthenticationPrincipal User user_id){
        userService.UpdateUser(user_id.getId(),user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity DeleteUser(@PathVariable Integer id){
        userService.DeleteUser(id);
        return ResponseEntity.status(200).body("user deleted");

    }
}
