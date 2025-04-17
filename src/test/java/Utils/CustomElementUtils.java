package Utils;
import org.openqa.selenium.WebElement;

public class CustomElementUtils {
    public static String getCustomText(WebElement element) {
        String text = element.getText().trim();
        // Custom logic here if needed
        return text;
    }
}
