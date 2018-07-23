


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


@Controller
public class Hellojava {

    @RequestMapping("/hello")
    public String sayhello(Model model){

        HashMap<Integer,String> hashMap=new HashMap<>() ;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_blog","root","root");
            PreparedStatement preparedStatement=connection.prepareStatement("select *from t_blogger");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                Integer integer=resultSet.getInt("id");
                String username=resultSet.getString("username");
                hashMap.put(integer,username);
            }
            resultSet.close();
            preparedStatement.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        model.addAttribute("name",hashMap.get(1));
        return "hello";
    }

}
