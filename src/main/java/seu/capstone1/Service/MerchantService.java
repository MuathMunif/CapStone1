package seu.capstone1.Service;

import org.springframework.stereotype.Service;
import seu.capstone1.Model.MerchantModel;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<MerchantModel> merchants = new ArrayList<>();

    public ArrayList<MerchantModel> getMerchants() {
        return merchants;
    }

    public void addMerchant(MerchantModel merchant) {
        merchants.add(merchant);
    }


    public boolean updateMerchant(String id, MerchantModel merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.set(i, merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }
}
