package com.overunderleague.core.standing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NbaStandingsService {

	@Autowired
	private NbaStandingsProvider nbaStandingsProvider;

	@Cacheable("nbaStandings")
	public List<NbaTeamStanding> getStandings() {
		return nbaStandingsProvider.getStandings();
	}
}
