
package com.overunderleague.integration.nbaclient2.api;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class StandingGroup {

    private List<Team> teams = null;
    private String conference;
    private String displayConference;
    private Object displayDivision;
    private Object division;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDisplayConference() {
        return displayConference;
    }

    public void setDisplayConference(String displayConference) {
        this.displayConference = displayConference;
    }

    public Object getDisplayDivision() {
        return displayDivision;
    }

    public void setDisplayDivision(Object displayDivision) {
        this.displayDivision = displayDivision;
    }

    public Object getDivision() {
        return division;
    }

    public void setDivision(Object division) {
        this.division = division;
    }

}
