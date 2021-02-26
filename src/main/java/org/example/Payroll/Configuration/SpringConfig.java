package org.example.Payroll.Configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.example.Payroll")
@PropertySource("classpath:dataConnection.properties")
public class SpringConfig {

}
