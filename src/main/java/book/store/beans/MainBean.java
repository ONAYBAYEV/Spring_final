package book.store.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class MainBean {
    @Bean(name = "resourceLoaderBean")
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }
}
