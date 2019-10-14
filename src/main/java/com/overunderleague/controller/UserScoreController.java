package com.overunderleague.controller;

import com.overunderleague.controller.api.UserScoreDto;
import com.overunderleague.core.userscore.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/user-scores")
@RestController
public class UserScoreController {

	@Autowired
	private UserScoreService userScoreService;

	@GetMapping
	public List<UserScoreDto> list() {
		return userScoreService.list();
	}
}
