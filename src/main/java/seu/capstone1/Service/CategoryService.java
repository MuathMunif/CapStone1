package seu.capstone1.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import seu.capstone1.Model.CategoryModel;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CategoryService {

    ArrayList<CategoryModel> categories = new ArrayList<>();

    public ArrayList<CategoryModel> getCategories() {
        return categories;
    }


    public void addCategory(CategoryModel category) {
        categories.add(category);
    }


    public boolean updateCategory(String id , CategoryModel category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }


    public boolean deleteCategory(String id) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equals(id)) {
                categories.remove(i);
                return true;
            }
        }
        return false;
    }



}
