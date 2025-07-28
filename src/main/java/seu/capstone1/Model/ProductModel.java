package seu.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @NotNull(message = "The ID must not be empty")
    private String id;

    @NotEmpty(message =  "The name must not be empty")
    @Size(min = 4 , message = "The name must be more than 3 length long")
    private String name;

    @NotNull(message =  "The price must not be empty")
    @Positive(message =  "The price must be positive number")
    private double price;

    @NotEmpty(message =  "The categoryID must not be empty")
    private String categoryID;

    private int soldCount = 0;

    private double totalIncome;

}
