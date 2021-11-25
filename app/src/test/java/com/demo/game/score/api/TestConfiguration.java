package com.demo.game.score.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    public ObjectMapper objectMapperBean(Jackson2ObjectMapperBuilder mapperBuilder) {
        mapperBuilder.modules(new Jdk8Module())
                     .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                                        DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
                     .serializationInclusion(JsonInclude.Include.NON_NULL);

        return mapperBuilder.build();
    }
}
