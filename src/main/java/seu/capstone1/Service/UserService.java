package seu.capstone1.Service;

import org.springframework.stereotype.Service;
import seu.capstone1.Model.UserModel;

import java.util.ArrayList;

@Service
public class UserService {

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


}
