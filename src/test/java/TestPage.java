import com.gxa.ehome.pojo.User;
import com.gxa.ehome.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:spring-bean.xml")
    public class TestPage {
        @Autowired
        @Qualifier("userService")
        private UserService userService = null;

        @Test
        public void showUsersByPage() {
            int page=2;
            int limit=5;
            try {
                List<User> userList = userService.SelectUserByPage(page, limit);
                System.out.println(userList);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //userService.test();
        }
    }