package seu.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.capstone1.Api.ApiRespnse;
import seu.capstone1.Model.UserModel;
import seu.capstone1.Service.UserService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserModel userModel, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        userService.addUser(userModel);
        return ResponseEntity.status(200).body(new ApiRespnse("User added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser( @PathVariable String id ,@Valid @RequestBody UserModel userModel, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        boolean isUpdated = userService.updateUser(id,userModel);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiRespnse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("User Not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespnse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("User Not found"));
    }


    @PutMapping("/buy-product/{userid}/{productid}/{merchantid}")
    public ResponseEntity<?> buyProduct(@PathVariable String userid,@PathVariable String productid,@PathVariable String merchantid){
        String response = userService.buyProduct(userid,productid,merchantid);
        if(response.equals("Purchase successful")){
            return ResponseEntity.status(200).body(new ApiRespnse("Purchase successful"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse(response));
    }


    //Extra for admin
    @PutMapping("/admin-de-active-user/{adminid}/{userid}")
    public ResponseEntity<?> adminDeActiveUser(@PathVariable String adminid,@PathVariable String userid){
        String response = userService.adminDeActiveUser(adminid,userid);
        if(response.equals("Deactivated successful")){
            return ResponseEntity.status(200).body(new ApiRespnse("Deactivated successful"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse(response));
    }


    //Extra for admin "Can Search for Active and Not Active users"
    @GetMapping("/admin/get-users-by-status/{adminId}/{status}")
    public ResponseEntity<?> getUsersByStatus(@PathVariable String adminId, @PathVariable String status) {

        if (userService.getUsersByStatus(adminId, status) == null) {
            return ResponseEntity.status(400).body(new ApiRespnse("You are not an Admin or the status value is invalid (use 'active' or 'notActive')"));
        }

        if (userService.getUsersByStatus(adminId, status).isEmpty()) {
            return ResponseEntity.status(404).body(new ApiRespnse("No users found with status: " + status));
        }

        return ResponseEntity.status(200).body(userService.getUsersByStatus(adminId, status));
    }

}
