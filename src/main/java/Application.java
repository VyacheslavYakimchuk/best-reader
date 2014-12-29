import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by TeSla on 25.12.2014.
 */
public class Application {
    public static void main(String[] args) {
//        UserDialog userDialog = new UserDialog();
//        userDialog.openFile(args[0]);
//        userDialog.start();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"app-context.xml"});
        UserDialog userDialog = (UserDialog)applicationContext.getBean("Dialog");
        userDialog.openFile(args[0]);
        userDialog.start();

    }

}
