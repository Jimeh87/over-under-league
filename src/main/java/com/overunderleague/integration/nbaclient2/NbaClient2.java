package com.overunderleague.integration.nbaclient2;

import com.overunderleague.integration.nbaclient2.api.ConferenceStandings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NbaClient2 {

	@Autowired
	private RestTemplate restTemplate;
	@Value("${app.nbaclient2.standingsUrl}")
	private String standingsUrl;

	@Cacheable("nbaStandings")
	public ConferenceStandings findCurrentStandings() {
		return restTemplate.getForObject(standingsUrl, ConferenceStandings.class);
	}

}
