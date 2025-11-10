package com.overunderleague.core.userscore;

import com.overunderleague.controller.api.UserPickDto;
import com.overunderleague.controller.api.UserPicksDto;
import com.overunderleague.controller.api.WagerType;
import com.overunderleague.core.overunder.Team;
import com.overunderleague.util.Csv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPicksService {

	private static final String USER_PICKS_CSV_PATH = "user-picks-2025.csv";

	private List<UserPicksDto> userPicks;

	@PostConstruct
	void loadUserPicks() {
		List<List<String>> entries = Csv.parse(USER_PICKS_CSV_PATH);

		this.userPicks = entries.stream()
				.map(entry -> {
					var userNickName = entry.getFirst();
					var picks = new ArrayList<UserPickDto>();
					for (int i = 1; i < entry.size(); i = i + 2) {
						picks.add(UserPickDto.of(Team.valueOf(entry.get(i)), WagerType.valueOf(entry.get(i + 1))));
					}

					return UserPicksDto.of(userNickName, picks);
				})
				.collect(Collectors.toList());
	}

	public List<UserPicksDto> list() {
		return userPicks;
	}
}
