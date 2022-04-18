package com.backend.finalProject.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Con esta anotación, cuando spring levante la app, lo primero que va a hacer es venir a levantar tod0 lo que este dentro
@Configuration
public class MapperConfig {

    //Le indicamos a spring que la instancia lo maneje el contenedor y no nosotros, solo le decimos el cómo. IoC
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
