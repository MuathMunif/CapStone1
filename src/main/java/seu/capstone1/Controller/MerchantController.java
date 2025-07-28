package seu.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.capstone1.Api.ApiRespnse;
import seu.capstone1.Model.MerchantModel;
import seu.capstone1.Service.MerchantService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity<?> getMerchants(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchant(@Valid @RequestBody MerchantModel merchant , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiRespnse("Merchant added successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchant( @PathVariable String id ,@Valid @RequestBody MerchantModel merchant ,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = merchantService.updateMerchant(id,merchant);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiRespnse("Merchant updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiRespnse("Merchant not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchant(@PathVariable String id){
        boolean isDeleted = merchantService.deleteMerchant(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespnse("Merchant deleted successfully"));
        }
        return ResponseEntity.status(404).body(new ApiRespnse("Merchant not found"));
    }
}

