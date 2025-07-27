package seu.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.capstone1.Api.ApiRespnse;
import seu.capstone1.Model.CategoryModel;
import seu.capstone1.Service.CategoryService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }


    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryModel category , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiRespnse("Category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory( @PathVariable String id,@Valid @RequestBody CategoryModel category ,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = categoryService.updateCategory(id,category);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiRespnse("Category updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiRespnse("Category not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id){
        boolean isDeleted = categoryService.deleteCategory(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespnse("Category deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiRespnse("Category not found"));
    }
}
