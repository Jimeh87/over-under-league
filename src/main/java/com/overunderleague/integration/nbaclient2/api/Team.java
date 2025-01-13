
package com.overunderleague.integration.nbaclient2.api;

import jakarta.annotation.Generated;


@Generated("jsonschema2pojo")
public class Team {

    private Profile profile;
    private Standings standings;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Standings getStandings() {
        return standings;
    }

    public void setStandings(Standings standings) {
        this.standings = standings;
    }

}
