package com.example.udpm14sellcomputerpartsbackend.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Value("ducnd1306")
    private String cloudName;
    @Value("822517849944935")
    private String apiKey;
    @Value("8iMU6ACFx4S35bRMLy9k6A_mqF4")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cloudName,
                "api_key",apiKey,
                "api_secret",apiSecret,
                "secure",true
        ));
    }


}
