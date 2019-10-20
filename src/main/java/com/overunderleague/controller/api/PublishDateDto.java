package com.overunderleague.controller.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PublishDateDto {
	private String lastUpdated;
}
