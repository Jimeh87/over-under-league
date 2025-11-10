package com.overunderleague.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfiguration {

	@Bean
	public CacheManager nbaStandingsCacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("nbaStandings");
		cacheManager.setCaffeine(Caffeine.newBuilder()
				.expireAfterWrite(60, TimeUnit.MINUTES));
		return cacheManager;
	}

}
