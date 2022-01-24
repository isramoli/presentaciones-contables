package edu.viu.presentacionescontables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Configuraciónes spring mvc
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
            "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addFormatter(dateFormatter());
        registry.addFormatter(localDateFormatter());
        registry.addFormatter(localTimeFormatter());
        registry.addFormatter(localDateTimeFormatter());
    }

    @Bean
    public DateFormatter dateFormatter() {
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setIso(DateTimeFormat.ISO.DATE);

        return dateFormatter;
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public String print(LocalDate object, Locale locale) {
                return object.toString();
            }

            @Override
            public LocalDate parse(String text, Locale locale) {
                return LocalDate.parse(text);
            }
        };
    }

    @Bean
    public Formatter<LocalTime> localTimeFormatter() {
        return new Formatter<LocalTime>() {
            @Override
            public String print(LocalTime object, Locale locale) {
                return object.toString();
            }

            @Override
            public LocalTime parse(String text, Locale locale) {
                return LocalTime.parse(text);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public String print(LocalDateTime object, Locale locale) {
                return object.toString();
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return LocalDateTime.parse(text);
            }
        };
    }


}
