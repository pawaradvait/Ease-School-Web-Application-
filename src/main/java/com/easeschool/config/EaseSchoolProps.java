package com.easeschool.config;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "easeschool")
@Validated
public class EaseSchoolProps {

    @Min(value = 5, message = "pagesize must be  min of 5 value ")
    private int pagesize;
    private Map<String,String> contact;
}
