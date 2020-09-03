/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IPL_UTILITY;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author abhinav
 */
public class MyServerDateTime {

    public static String getServerDate() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        Date sqlDate;
        sqlDate = new Date(utilDate.getTime());
        return sqlDate.toString();
    }

    public static String getServerTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String systemTime = sdf.format(cal.getTime());
        return systemTime;
    }
}
