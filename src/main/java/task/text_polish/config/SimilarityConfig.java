package task.text_polish.config;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimilarityConfig {

    @Bean
    public JaroWinklerSimilarity jaroWinklerSimilarity() {
        return new JaroWinklerSimilarity();
    }
}
