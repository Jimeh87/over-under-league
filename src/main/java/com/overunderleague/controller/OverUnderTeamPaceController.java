package com.overunderleague.controller;

import com.overunderleague.controller.api.OverUnderTeamPaceDto;
import com.overunderleague.core.overunderpace.OverUnderTeamPaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/over-under-teams")
@RestController
public class OverUnderTeamPaceController {

	@Autowired
	private OverUnderTeamPaceService overUnderTeamPaceService;

	@GetMapping
	public List<OverUnderTeamPaceDto> list() {
		return overUnderTeamPaceService.list();
	}
}
