package com.overunderleague.integration.nbaclient.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class NbaTeamStandingDto {
	private String teamId;
	private Integer win;
	private Integer loss;
	private BigDecimal winPct;
	private BigDecimal winPctV2;
	private BigDecimal lossPct;
	private BigDecimal lossPctV2;
	private BigDecimal gamesBehind;
	private BigDecimal divGamesBehind;
	private String clinchedPlayoffsCode;
	private String clinchedPlayoffsCodeV2;
	private String confRank;
	private Integer confWin;
	private Integer confLoss;
    private Integer divWin;
    private Integer divLoss;
    private Integer homeWin;
    private Integer homeLoss;
    private Integer awayWin;
    private Integer awayLoss;
    private Integer lastTenWin;
    private Integer lastTenLoss;
    private Integer streak;
    private String divRank;
    private Boolean isWinStreak;
    private NbaTeamDto teamSitesOnly;
    private String tieBreakerPts;
    private Map<String, Integer> sortKey = new HashMap<>();
}
