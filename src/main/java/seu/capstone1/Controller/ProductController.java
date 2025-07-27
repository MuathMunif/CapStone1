package seu.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.capstone1.Api.ApiRespnse;
import seu.capstone1.Model.ProductModel;
import seu.capstone1.Service.ProductService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


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



}
