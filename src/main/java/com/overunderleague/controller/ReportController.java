package com.overunderleague.controller;

import com.overunderleague.controller.api.UserStandingDto;
import com.overunderleague.controller.api.UserTeamScoreDto;
import com.overunderleague.core.userscore.UserStandingsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@RequestMapping("/api/reports")
@Controller
public class ReportController {

	private static final String NEW_LINE = "\n";
	private static final String TAB = "\t";

	@Autowired
	private UserStandingsService userStandingsService;

	@GetMapping("player-selections")
	@ResponseBody
	public String selections(HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();
		List<UserStandingDto> users = userStandingsService.list()
				.stream()
				.sorted(comparing(UserStandingDto::getUserNickname))
				.toList();

		for (UserStandingDto user : users) {
			sb.append(user.getUserNickname()).append(":").append(NEW_LINE);
			for (UserTeamScoreDto team : user.getTeamScores().stream().sorted(comparing(UserTeamScoreDto::getTeamNickname)).collect(toList())) {
				sb.append(TAB)
						.append(team.getTeamNickname())
						.append("(").append(team.getWinOverUnder()).append(")")
						.append(" - ")
						.append(team.getWager())
						.append(NEW_LINE);
			}
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		return sb.toString();
	}
}
