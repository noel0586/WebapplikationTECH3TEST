package Model;

import DAO.DatabaseRead;
//import DAO.Read;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by leonemborg on 06/04/2017.
 */
public class UserList {

    private List<User> list;

    public UserList() {
        DatabaseRead dbRead = new DatabaseRead();
        try {
            list = dbRead.getUser();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public String validateUser(String un, String pw){
        if(list != null) {
            for (User u : list) {
                if (u.getUser().equals(un) && u.getPassword().equals(pw)) {
                    if (u.getRole().equals("admin")) {
                        return "isAdmin";
                    } else {
                        return "isUser";
                    }

                }

            }
        }
        return "noUser";


    }

}
