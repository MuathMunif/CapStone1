package seu.capstone1.Model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantStockModel {

    @NotNull(message = "The ID must not be empty ")
    private String id;

    @NotNull(message = "(The product must not be empty")
    private String productId;

    @NotNull(message = "The merchantId must not be empty ")
    private String merchantId;// todo check

    @NotNull(message = "The stock must not be empty ")
    private int stock;
}
