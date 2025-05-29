package kr.hhplus.be.server.config;

import java.net.URISyntaxException;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() throws URISyntaxException {
		CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
		return cachingProvider.getCacheManager(
			getClass().getResource("/ehcache.xml").toURI(),
			getClass().getClassLoader()
		);
	}
}

