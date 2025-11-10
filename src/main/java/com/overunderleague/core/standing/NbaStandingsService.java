package com.overunderleague.core.standing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NbaStandingsService {

	private final NbaStandingsProvider nbaStandingsProvider;

	@Cacheable("nbaStandings")
	public List<NbaTeamStanding> getStandings() {
		return nbaStandingsProvider.getStandings();
	}
}
