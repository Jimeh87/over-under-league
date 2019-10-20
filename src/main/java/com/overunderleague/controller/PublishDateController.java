package com.overunderleague.controller;

import com.overunderleague.client.NbaClient;
import com.overunderleague.controller.api.PublishDateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/publish-date")
@RestController
public class PublishDateController {

	@Autowired
	private NbaClient nbaClient;

	@GetMapping
	public PublishDateDto getPublishDate() {
		return new PublishDateDto().setLastUpdated(nbaClient.findCurrentStandings().get_internal().getPubDateTime());
	}
}
