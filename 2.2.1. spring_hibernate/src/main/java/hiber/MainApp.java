package hiber;

import hiber.config.AppConfig;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("toyota", 1);
      Car car2 = new Car("honda", 2);
      Car car3 = new Car("tesla", 3);

      User user1 = new User("User1", "Lastname1", "user1@gmail.com");
      User user2 = new User("User2", "Lastname2", "user2@gmail.com");
      User user3 = new User("User3", "Lastname3", "user3@gmail.com");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("toyota", 1));
      System.out.println(userService.getUserByCar("honda", 2));
      System.out.println(userService.getUserByCar("tesla", 3));

      context.close();
   }
}
