package seu.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.capstone1.Model.MerchantModel;
import seu.capstone1.Model.MerchantStockModel;
import seu.capstone1.Model.ProductModel;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantService merchantService;
    private final ProductService productService;


    ArrayList<MerchantStockModel> merchantStockModels = new ArrayList<>();

    public ArrayList<MerchantStockModel> getMerchantStockModels() {
        return merchantStockModels;
    }

    public boolean addMerchantStockModel(MerchantStockModel merchantStockModel) {
        boolean merchantIdExists = false;
        boolean productIdExists = false;
        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantStockModel.getMerchantId().equals(merchantService.merchants.get(i).getId())) {
                merchantIdExists = true;
            }
        }
        for (int i = 0; i < productService.products.size(); i++) {
            if (merchantStockModel.getProductId().equals(productService.products.get(i).getId())) {
                productIdExists = true;
            }
        }
        if (merchantIdExists && productIdExists) {
            merchantStockModels.add(merchantStockModel);
            return true;
        }

        return false;

    }

    public boolean updateMerchantStockModel(String id, MerchantStockModel merchantStockModel) {
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


    public String addStock(String merchantId, String productId, int quantity) {

        boolean merchantExists = false;
        boolean productExists = false;

        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId().equals(merchantId)) {
                merchantExists = true;
                break;
            }
        }

        if (!merchantExists) {
            return "The merchant does not exist";
        }

        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId().equals(productId)) {
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            return "The product does not exist";
        }

        for (int i = 0; i < merchantStockModels.size(); i++) {
            if (merchantStockModels.get(i).getMerchantId().equals(merchantId) && merchantStockModels.get(i).getProductId().equals(productId)) {
                int newStock = merchantStockModels.get(i).getStock() + quantity;
                merchantStockModels.get(i).setStock(newStock);
                return "Stock added successfully";
            }
        }

        return "The Stock entry does not exist";
    }


    public ArrayList<String> getMerchantsByProduct(String productId) {
        ArrayList<String> result = new ArrayList<>();

        for (MerchantStockModel stock : merchantStockModels) {
            if (stock.getProductId().equals(productId)) {
                String merchantName = " ";

                for (MerchantModel merchant : merchantService.merchants) {
                    if (merchant.getId().equals(stock.getMerchantId())) {
                        merchantName = merchant.getName();
                        break;
                    }
                }

                String found = "Merchant: " + merchantName + ", Stock: " + stock.getStock();
                result.add(found);
            }
        }

        return result;
    }


    public ArrayList<ProductModel> filterAvailableProducts(String keyword, Double minPrice, Double maxPrice, String categoryId, String merchantId) {
        ArrayList<ProductModel> result = new ArrayList<>();

        for (MerchantStockModel stock : merchantStockModels) {
            if (stock.getStock() > 0) {
                if (stock.getMerchantId().equals(merchantId)) {

                    ProductModel product = null;
                    for (ProductModel p : productService.products) {
                        if (p.getId().equals(stock.getProductId())) {
                            product = p;
                            break;
                        }
                    }

                    if (product != null) {
                        if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                                if (product.getCategoryID().equals(categoryId)) {
                                    if (!result.contains(product)) {
                                        result.add(product);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

}
