package com.overunderleague.client;

import com.overunderleague.client.api.StandingsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NbaClient {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${app.nbaclient.standingsUrl}")
	private String standingsUrl;

	@Cacheable("nbaStandings")
	public StandingsDto findCurrentStandings() {
		return restTemplate.getForObject(standingsUrl, StandingsDto.class);
	}

}
