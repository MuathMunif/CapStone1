package seu.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    @NotNull(message = "The ID must not be empty ")
    public String id;

    @NotEmpty(message =  "The name must not be empty")
    @Size(min = 6 , message = "The name must be more than 5 length long")
    private String username;

    @NotEmpty(message = "The password must be not empty ")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least one letter and one digit, and be at least 6 characters long")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String email;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    private String role;

    @NotNull(message = "Balance must not be null")
    @Positive(message = "Balance must be a positive number")
    private Double balance;

}
