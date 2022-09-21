package jpa.practice.image;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "file.upload")
@Data
public class FileUploadProperties {
    private String location;
}
