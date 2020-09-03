package IPL_BEANS;

public class MatchBean {
    
    String matchID;
    String team1ID;
    String team2ID;
    String matchDate;
    String roundType;
    String startTime;
    String matchStatus;
    String matchDay;
    ResultBean resultBean;  
    String team1Name;
    String team2Name;
    String team1Collection;
    String team2Collection;
    
    UserBean userBean;

    public String getMatchID() {
        return matchID;
    }

    public void setMatchID(String matchID) {
        this.matchID = matchID;
    }

    public String getTeam1ID() {
        return team1ID;
    }

    public void setTeam1ID(String team1ID) {
        this.team1ID = team1ID;
    }

    public String getTeam2ID() {
        return team2ID;
    }

    public void setTeam2ID(String team2ID) {
        this.team2ID = team2ID;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(String matchDay) {
        this.matchDay = matchDay;
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
