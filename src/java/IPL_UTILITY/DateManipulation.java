package IPL_UTILITY;

/**
 *
 * @author Abhinav Kumar
 */
public class DateManipulation {
    
    private static String monthArray[] = {"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE",
        "JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
    
    public static String  convertInSQLFormat(String inputDate)
    {
        String outputDate="";
        String inputDateArray[] = inputDate.split("-");
        outputDate = inputDateArray[0]+"-"+returnMonthNumber(inputDateArray[1])+"-"+inputDateArray[2];
        return outputDate;
    }
    
    public static String convertInDisplayFormat(String inputDate)
    {
        String outputDate="";
        String inputDateArray[] = inputDate.split("-");
        outputDate = inputDateArray[0]+"-"+returnMonthName(Integer.parseInt(inputDateArray[1]))+"-"+inputDateArray[2];
        return outputDate;
    }
    
    public static int returnMonthNumber(String monthName)
    {
        int monthNumber=0;
        for(int i = 0 ; i < monthArray.length; i++)
        {
            if(monthName.equalsIgnoreCase(monthArray[i]))
            {
                monthNumber = i + 1;
            }
        }
        return monthNumber;
    }
    
    public static String returnMonthName(int monthNumber)
    {
        return monthArray[monthNumber-1].substring(0, 3);
    }
    
}
