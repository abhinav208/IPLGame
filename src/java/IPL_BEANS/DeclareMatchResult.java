/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_BEANS;

/**
 *
 * @author ANUJGUPTA
 */
public class DeclareMatchResult {
    
    String team_name;
    String user_name;
    String team1_collection;
    String team2_collection;
    String team1_id;
    String team2_id;
    
    String total_gain;
    

    
    String ques_ans_arr[];
    


    public DeclareMatchResult() {
        this.ques_ans_arr = new String[4];
    }

    
    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTeam1_collection() {
        return team1_collection;
    }

    public void setTeam1_collection(String team1_collection) {
        this.team1_collection = team1_collection;
    }

    public String getTeam2_collection() {
        return team2_collection;
    }

    public void setTeam2_collection(String team2_collection) {
        this.team2_collection = team2_collection;
    }

    public String getTotal_gain() {
        return total_gain;
    }

    public void setTotal_gain(String total_gain) {
        this.total_gain = total_gain;
    }

    public String[] getQues_ans_arr() {
        return ques_ans_arr;
    }

    public void setQues_ans_arr(String[] ques_ans_arr) {
        this.ques_ans_arr = ques_ans_arr;
    }

    public String getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(String team1_id) {
        this.team1_id = team1_id;
    }

    public String getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(String team2_id) {
        this.team2_id = team2_id;
    }
}
