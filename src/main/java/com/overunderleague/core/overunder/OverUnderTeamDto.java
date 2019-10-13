package com.overunderleague.core.overunder;

import lombok.Value;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Accessors(chain = true)
@Value(staticConstructor = "of")
public class OverUnderTeamDto {
	private String teamId;
	private String teamNickname;
	private BigDecimal winOverUnder;
}
