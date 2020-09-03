/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

/**
 *
 * @author anujgupt
 */
public class ResultBean {
    
    String matchID;
    String teamID;
    String winningRemarks;
    String team1Collection;
    String team2Collection;

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getWinningRemarks() {
        return winningRemarks;
    }

    public void setWinningRemarks(String winningRemarks) {
        this.winningRemarks = winningRemarks;
    }

    public String getTeam1Collection() {
        return team1Collection;
    }

    public void setTeam1Collection(String team1Collection) {
        this.team1Collection = team1Collection;
    }

    public String getTeam2Collection() {
        return team2Collection;
    }

    public void setTeam2Collection(String team2Collection) {
        this.team2Collection = team2Collection;
    }
    
}
