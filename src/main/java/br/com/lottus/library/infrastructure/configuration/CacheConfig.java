package br.com.lottus.library.infrastructure.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.addMixIn(PageImpl.class, PageMixin.class);
        objectMapper.addMixIn(PageRequest.class, PageRequestMixin.class);
        objectMapper.addMixIn(Sort.class, SortMixin.class);
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)));

        return (builder) -> builder
                .withCacheConfiguration("alunos",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration("livros",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(5)))
                .withCacheConfiguration("categorias",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(20)))
                .withCacheConfiguration("turmas",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(15)))
                .withCacheConfiguration("emprestimos",
                        defaultCacheConfig.entryTtl(Duration.ofMinutes(1)));
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class PageMixin<T> {
        @JsonCreator
        public PageMixin(@JsonProperty("content") List<T> content,
                         @JsonProperty("pageable") Pageable pageable,
                         @JsonProperty("total") long total) {
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class PageRequestMixin {
        @JsonCreator
        public PageRequestMixin(@JsonProperty("pageNumber") int page,
                                @JsonProperty("pageSize") int size,
                                @JsonProperty("sort") Sort sort) {
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class SortMixin {
        @JsonCreator
        public SortMixin(@JsonProperty("orders") List<Sort.Order> orders) {
        }
    }
}
