package seu.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.capstone1.Model.MerchantModel;
import seu.capstone1.Model.MerchantStockModel;
import seu.capstone1.Model.ProductModel;
import seu.capstone1.Model.UserModel;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    ArrayList<UserModel> users = new ArrayList<>();

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public void addUser(UserModel user) {
        users.add(user);
    }

    public boolean updateUser(String id,UserModel user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }


    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

public String buyProduct(String id , String productId, String merchantId) {

        boolean isProductExist = false;
        boolean isMerchantExist = false;
        boolean isUserExist = false;
        boolean isMerchantStockExist = false;

        UserModel user = null;
        ProductModel productF = null;
        MerchantModel merchantF = null;
        MerchantStockModel merchantStockF = null;


        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId().equals(productId)) {
                productF = productService.products.get(i);
                isProductExist = true;
            }
        }
        if (!isProductExist) {
            return "No product found for id: " + productId;
        }

        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId().equals(merchantId)) {
                merchantF = merchantService.merchants.get(i);
                isMerchantExist = true;
            }
        }
        if (!isMerchantExist) {
            return "No merchant found for id: " + merchantId;
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                user = users.get(i);
                isUserExist = true;
            }
        }
        if (!isUserExist) {
            return "No User found for id: " + id;
        }

        if (!user.isActive()){
            return "User is not active";
    }

       for (int i = 0; i < merchantStockService.merchantStockModels.size(); i++) {
           if (merchantStockService.merchantStockModels.get(i).getMerchantId().equals(merchantId) && merchantStockService.merchantStockModels.get(i).getProductId().equals(productId)) {
               merchantStockF = merchantStockService.merchantStockModels.get(i);
               isMerchantStockExist = true;
           }
       }
       if (!isMerchantStockExist) {
           return "No merchant stock found for id: " + merchantId;
       }

       if (merchantStockF.getStock() <= 0) {
           return "Product is out of stock";
       }

       if (user.getBalance() < productF.getPrice()) {
           return "You don't have enough money";
       }

       merchantStockF.setStock(merchantStockF.getStock() - 1);
       user.setBalance(user.getBalance() - productF.getPrice());
       productF.setSoldCount(productF.getSoldCount() + 1);


        return "Purchase successful";
}


//Admin Can De Active user
public String adminDeActiveUser(String adminId , String userId) {
        boolean isAdmin = false;
        boolean isUserExist = false;
        UserModel user = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(adminId) && users.get(i).getRole().equals("Admin")) {
                isAdmin = true;
            }
        }
        if (!isAdmin) {
            return "No admin found for id: " + adminId;
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(userId)) {
                user = users.get(i);
                isUserExist = true;
            }
        }
        if (!isUserExist) {
            return "No User found for id: " + userId;
        }
        user.setActive(false);
        return "Deactivated successful";
}


//Admin can get Active users and not Active users
public ArrayList<UserModel> getUsersByStatus(String adminId , String search) {
    ArrayList<UserModel> usersFound = new ArrayList<>();

    boolean isAdmin = false;
    for (UserModel user : users) {
        if (user.getId().equals(adminId) && user.getRole().equals("Admin")) {
            isAdmin = true;
            break;
        }
    }

    if (!isAdmin) {
        return null;
    }

    if (!search.equalsIgnoreCase("active") && !search.equalsIgnoreCase("notActive")) {
        return null;
    }


    for (UserModel user : users) {
        if (search.equalsIgnoreCase("active") && user.isActive()) {
            usersFound.add(user);
        } else if (search.equalsIgnoreCase("notActive") && !user.isActive()) {
            usersFound.add(user);
        }
    }

    return usersFound;
}


}
