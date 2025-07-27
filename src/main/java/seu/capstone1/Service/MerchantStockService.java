package seu.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.capstone1.Model.MerchantStockModel;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    ArrayList<MerchantStockModel> merchantStockModels = new ArrayList<>();

    public ArrayList<MerchantStockModel> getMerchantStockModels() {
        return merchantStockModels;
    }

    public void addMerchantStockModel(MerchantStockModel merchantStockModel) {
        merchantStockModels.add(merchantStockModel);
    }

    public boolean updateMerchantStockModel(String id ,MerchantStockModel merchantStockModel) {
        for (int i = 0; i < merchantStockModels.size(); i++) {
            if (merchantStockModels.get(i).getId().equals(id)) {
                merchantStockModels.set(i, merchantStockModel);
                return true;
            }
        }
        return false;
    }


    public boolean deleteMerchantStockModel(String id) {
        for (int i = 0; i < merchantStockModels.size(); i++) {
            if (merchantStockModels.get(i).getId().equals(id)) {
                merchantStockModels.remove(i);
                return true;
            }
        }
        return false;
    }


}
