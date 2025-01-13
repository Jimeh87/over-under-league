
package com.overunderleague.integration.nbaclient2.api;

import jakarta.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Payload {

    private League league;
    private Season season;
    private List<StandingGroup> standingGroups = null;
    private String grouping;

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<StandingGroup> getStandingGroups() {
        return standingGroups;
    }

    public void setStandingGroups(List<StandingGroup> standingGroups) {
        this.standingGroups = standingGroups;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

}
