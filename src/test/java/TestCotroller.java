import com.gxa.ehome.controller.UserCotroller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-bean.xml")
public class TestCotroller {
    @Autowired
    @Qualifier("userController")
    UserCotroller userCotroller=null;
    @Test
    public void test(){
        //UserCotroller userCotroller=new UserCotroller();
        userCotroller.showUsersByPage();
    }
}
