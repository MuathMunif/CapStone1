package seu.capstone1.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.capstone1.Model.ProductModel;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;

    ArrayList<ProductModel> products = new ArrayList<>();


    public ArrayList<ProductModel> getProducts() {
        return products;
    }

    public boolean addProduct(ProductModel product) {//todo check
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (product.getCategoryID().equals(categoryService.categories.get(i).getId())){
                products.add(product);
                return true;
            }

        }
        return false;
    }


    public boolean updateProduct(String id , ProductModel product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


}
