package seu.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantModel {

    @NotNull(message = "The ID must not be empty ")
    public String id;

    @NotEmpty(message =  "The name must not be empty")
    @Size(min = 4 , message = "The name must be more than 3 length long")
    private String name;
}
