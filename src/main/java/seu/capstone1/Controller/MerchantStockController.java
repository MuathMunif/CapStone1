package seu.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.capstone1.Api.ApiRespnse;
import seu.capstone1.Model.MerchantStockModel;
import seu.capstone1.Service.MerchantStockService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity<?> getMerchantStockModels(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStockModels());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStockModel(@Valid @RequestBody MerchantStockModel merchantStockModel , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        merchantStockService.addMerchantStockModel(merchantStockModel);
        return ResponseEntity.status(200).body(new ApiRespnse("Merchant Stock Added Successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStockModel(@PathVariable String id, @Valid @RequestBody MerchantStockModel merchantStockModel , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiRespnse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = merchantStockService.updateMerchantStockModel(id, merchantStockModel);
        if(isUpdated){
            return ResponseEntity.status(200).body(new ApiRespnse("Merchant Stock Updated"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("Merchant Stock Not Updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantStockModel(@PathVariable String id){
        boolean isDeleted = merchantStockService.deleteMerchantStockModel(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiRespnse("Merchant Stock Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiRespnse("Merchant Stock Not Deleted"));
    }
}
