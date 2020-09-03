/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.TeamBean;
import IPL_DAO.TeamDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abhinav Kumar
 */
public class TeamLogic {

    TeamBean teamBean;
    TeamDao teamDao;

    public TeamLogic() {
        teamDao = new TeamDao();
    }

    public TeamLogic(TeamBean teamBean) {
        this.teamBean = teamBean;
        teamDao = new TeamDao(this.teamBean);
    }

    public ArrayList<TeamBean> getAllTeams() {
        ResultSet result = teamDao.getTeamList();
        ArrayList<TeamBean> teamList = new ArrayList<TeamBean>();
        try {
            while (result.next()) {
                teamBean = new TeamBean();
                teamBean.setTeamID(result.getString("TEAM_ID"));
                teamBean.setTeamName(result.getString("TEAM_NAME"));
                teamList.add(teamBean);
            }
            teamDao.stmt.close();
            teamDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("TeamLogic:getAllTeams: Exception occured "
                    + "while preparing team list is : \n " + sqlException);
        }
        return teamList;
    }

    public String saveTeamDetails() {
        String returnMessage;
        boolean checkFlag = teamDao.insertTeamDetails();
        if (checkFlag) {
            returnMessage = "Team Details Saved.";
        } else {
            returnMessage = "Due to some technical issue, team details caanot be saved.!";
        }
        try {
            teamDao.stmt.close();
            teamDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("TeamLogic:saveTeamDetails: Exception occured "
                    + "while closing connection is : \n " + sqlException);
        }
        return returnMessage;
    }

    public String returnTeamIdByName() {
        ResultSet result = teamDao.getTeamIDByTeamName();
        try {
            while (result.next()) {
                teamBean.setTeamID(result.getString(1));
            }
            teamDao.stmt.close();
            teamDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("TeamLogic:returnTeamIdByName: Exception occured "
                    + "while preparing team id by team Name is : \n " + sqlException);
        }
        return teamBean.getTeamID();
    }

    public ArrayList<TeamBean> getPlayerNames() {
        ResultSet result = teamDao.getTeamPlayers();
        ArrayList<TeamBean> teamList = new ArrayList<TeamBean>();
        try {
            while (result.next()) {
                teamBean = new TeamBean();
                teamBean.setPlayerName(result.getString("PLAYER_NAME"));
                teamList.add(teamBean);
            }
            teamDao.stmt.close();
            teamDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("TeamLogic:getAllTeams: Exception occured "
                    + "while preparing team list is : \n " + sqlException);
        }
        return teamList;
    }

    public String returnTeamNameById() {
        ResultSet result = teamDao.getTeamNameByTeamID();
        try {
            while (result.next()) {
                teamBean.setTeamName(result.getString(1));
            }
            teamDao.stmt.close();
            teamDao.con.close();
        } catch (SQLException sqlException) {
            System.out.println("TeamLogic:returnTeamIdByName: Exception occured "
                    + "while preparing team id by team Name is : \n " + sqlException);
        }
        return teamBean.getTeamName();
    }
}
