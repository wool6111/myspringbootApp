package com.basic.myspringboot.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("myBootProperty")
@ConfigurationProperties("myboot")
@Getter
@Setter
public class MyBootProperties {
    private String name;
    private int age;
    private String fullName;
    private String username;
    private String password;
}
