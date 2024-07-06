package org.project4.back_end.cofig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Applicaltionconfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}