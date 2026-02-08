package Window;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WindowType;
import Constant.Constant;

public class WindowManager {

    private static Map<Site, String> windows = new HashMap<>();

    public static void save(Site site) {
    	windows.put(site, Constant.WEBDRIVER.getWindowHandle());
    }

    public static void switchTo(Site site) {

        String handle = windows.get(site);

        if (handle == null) {
        	throw new RuntimeException("No window saved for: " + site);
        }

        Constant.WEBDRIVER.switchTo().window(handle);
    }

    public static void openNew(Site site) {

        Constant.WEBDRIVER.switchTo().newWindow(WindowType.TAB);

        save(site);
    }
}
