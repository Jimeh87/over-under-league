package com.overunderleague.configuration;

import com.overunderleague.integration.nbaclient2.NbaClient2;
import com.overunderleague.core.standing.NbaStandingsProvider;
import com.overunderleague.integration.clientbridge.nbaclient2.NbaClient2NbaStandingsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NbaClientConfiguration {

	@Bean
	public NbaStandingsProvider nbaStandingsProvider(NbaClient2 nbaClient) {
		return new NbaClient2NbaStandingsProvider(nbaClient);
	}

}
