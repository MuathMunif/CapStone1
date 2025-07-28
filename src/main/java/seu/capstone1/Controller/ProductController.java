package seu.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.capstone1.Api.ApiRespnse;
import seu.capstone1.Model.ProductModel;
import seu.capstone1.Service.MerchantStockService;
import seu.capstone1.Service.ProductService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductModel product , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        if(productService.addProduct(product)){
            return ResponseEntity.status(200).body(new ApiRespnse("Product added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("Product not added The Category ID Not Correct"));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id , @Valid @RequestBody ProductModel product , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse (Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = productService.updateProduct(id, product);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiRespnse("Product updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("Product not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        boolean isDeleted = productService.deleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespnse("Product deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("Product not found"));
    }

    //Extra to check the sales for product
    @GetMapping("/sales/{id}")
    public ResponseEntity<?> getProductCount(@PathVariable String id){
        return ResponseEntity.status(200).body(productService.getProductCount(id));
    }


    //Extra to get all product income
    @GetMapping("/get-product-income/{id}")
    public ResponseEntity<?> getProductIncome(@PathVariable String id){
        return ResponseEntity.status(200).body(productService.getProductIncome(id));
    }


    //Search for product in all Stocks
    @GetMapping("/merchants-stock/{productId}")
    public ResponseEntity<?> getMerchantsSellingProduct(@PathVariable String productId) {

        if (merchantStockService.getMerchantsByProduct(productId).isEmpty()) {
            return ResponseEntity.status(404).body("No merchants found selling this product.");
        }

        return ResponseEntity.status(200).body(merchantStockService.getMerchantsByProduct(productId));
    }



    @GetMapping("/filter-products/{keyword}/{minPrice}/{maxPrice}/{categoryId}/{merchantId}")
    public ResponseEntity<?> filterProducts(@PathVariable String keyword, @PathVariable Double minPrice, @PathVariable Double maxPrice, @PathVariable String categoryId, @PathVariable String merchantId
    ) {
        if (merchantStockService.filterAvailableProducts(keyword, minPrice, maxPrice, categoryId, merchantId).isEmpty()) {
            return ResponseEntity.status(400).body("No matching products found.");
        }

       return ResponseEntity.status(200).body(merchantStockService.filterAvailableProducts(keyword, minPrice, maxPrice, categoryId, merchantId));
    }

}
