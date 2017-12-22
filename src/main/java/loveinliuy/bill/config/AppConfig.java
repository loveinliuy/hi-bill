package loveinliuy.bill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "bill")
public class AppConfig {

    private String mode;

    public boolean isDebug() {
        return "debug".equals(mode);
    }
}
