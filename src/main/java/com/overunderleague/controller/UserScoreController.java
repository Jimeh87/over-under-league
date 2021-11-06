package com.overunderleague.controller;

import com.overunderleague.controller.api.UserStandingDto;
import com.overunderleague.core.userscore.UserStandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/user-scores")
@RestController
public class UserScoreController {

	@Autowired
	private UserStandingsService userStandingsService;

	@GetMapping
	public List<UserStandingDto> list() {
		return userStandingsService.list();
	}
}
