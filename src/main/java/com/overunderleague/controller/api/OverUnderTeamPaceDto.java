package com.overunderleague.controller.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class OverUnderTeamPaceDto {
	private String teamId;
	private String teamNickname;
	private BigDecimal winOverUnder;
	private Integer wins;
	private Integer loses;
	private BigDecimal winPercentage;
	private Integer pace;
}
