/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_LOGIC;

import IPL_BEANS.MatchCollection;
import IPL_BEANS.ScoreBoardBean;
import IPL_BEANS.UserBean;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Abhinav Kumar
 */
public class ScoreBoardLogic {

    ScoreBoardBean scoreBoardBean;

    public ScoreBoardLogic() {
    }

    public ScoreBoardLogic(ScoreBoardBean scoreBoardBean) {
        this.scoreBoardBean = scoreBoardBean;
    }

    public ScoreBoardBean getScoreBoardUsers() {
        MatchCollection collection = new MatchCollection();
        collection.setUserID(scoreBoardBean.getUserBean().getUserID().trim());
        MatchCollectionLogic matchCollectionLogic = new MatchCollectionLogic(collection);
        matchCollectionLogic.openConnection();
        ArrayList<UserBean> scoreBoardList = matchCollectionLogic.getScoreBoardUsers();
        Iterator itr = scoreBoardList.iterator();
        int amount = 0;
        int aheadUser = 0;
        while (itr.hasNext()) {
            UserBean userBean = (UserBean) itr.next();
            if (!(amount == Integer.parseInt(userBean.getUserPoints()))) {
                aheadUser++;
            }
            if (userBean.getUserID().trim().equalsIgnoreCase(scoreBoardBean.getUserBean().getUserID().trim())) {
                userBean.setUserEarnedPoints(matchCollectionLogic.getUserEarnedPoints());
                userBean.setUserLosedPoints(matchCollectionLogic.getUserLosedPoints());

                scoreBoardBean.setUserBean(userBean);
            }
            userBean.setUserPosition(Integer.toString(aheadUser));
            amount = Integer.parseInt(userBean.getUserPoints());
        }
        scoreBoardBean.setScoreBoardList(scoreBoardList);
        matchCollectionLogic.closeConnection();
        return scoreBoardBean;
    }
}
