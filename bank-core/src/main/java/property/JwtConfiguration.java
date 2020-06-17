package property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfiguration {

    private String loginUrl;

    @NestedConfigurationProperty
    private Header header = new Header();

    private int expiration;
    private String privateKey;
    private String type;

    public static class Header {
        private String name;
        private String prefix;
    }
}
