public class DateCalculator {
    private final static int JANUARY= 1;
    private final static int MARCH= 3;
    private final static int APRIL= 4;
    private final static int MAY= 5;
    private final static int JUNE= 6;
    private final static int JULY= 7;
    private final static int AUGUST= 8;
    private final static int SEPTEMBER= 9;
    private final static int OCTOBER= 10;
    private final static int NOVEMBER= 11;
    private final static int DECEMBER= 12;
    private final static int THIRTY_DAYS= 30;
    private final static int THIRTY_ONE_DAYS= 31;
    private final static int TWENTY_EIGHT_DAYS= 28;
    private final static int TWENTY_NINE_DAYS= 29;
    private final static int FOUR_HUNDRED_YEARS= 400;
    private final static int ONE_HUNDRED_YEARS= 100;
    private final static int FOUR_YEARS= 4;



    /**
     * this function checks if the date it gets is in a leap year or not
     * @param date the date we want to check if it's in a leap year or not
     * @return true if the year is a leap year, otherwise return false
     */
    private static boolean isLeapYear(Date date) {
        int year = date.getYear();
        return (year % FOUR_YEARS == 0 && year % ONE_HUNDRED_YEARS != 0) || year % FOUR_HUNDRED_YEARS == 0;
    }

    /**
     * this function calculates the days in the month of the given date and returns it
     * @param date the date we want to check for its month's days
     * @return the number of days in the dates' month
     */
    private static int daysInThisMonth(Date date) {
        int monthNumber = date.getMonth();
        if (monthNumber == JANUARY || monthNumber ==MARCH || monthNumber == MAY || monthNumber == JULY
                || monthNumber == AUGUST || monthNumber == OCTOBER || monthNumber == DECEMBER)
            return THIRTY_ONE_DAYS;
        if (monthNumber == APRIL || monthNumber == JUNE || monthNumber == SEPTEMBER || monthNumber == NOVEMBER)
            return THIRTY_DAYS;
        if (isLeapYear(date)) return TWENTY_NINE_DAYS;
        return TWENTY_EIGHT_DAYS;
    }

    /**
     * calculates how many days are left from the date given until next month
     * @param date the date it checks for
     * @return how many days are left from the date given until next month
     */
    private static int daysLeftInMonth(Date date) {
        int dayNumber = date.getDay();
        return daysInThisMonth(date) - dayNumber;
    }

    // function we were asked to execute
    public static Date addToDate(Date date, int num) {
        //if we have the date we wanted, return the date
        if (num == 0) return date;
        //for a positive num
        if (num > 0) {
            int daysLeftInThisMonth = daysLeftInMonth(date);
            // if we are not in the right month (and year) , move to the next month and check again for it
            if (num > daysLeftInThisMonth) {
                num -= (daysLeftInThisMonth + 1);
                // if we are in December, the next month will be in the next year
                if (date.getMonth() != DECEMBER) {
                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num);
                    //for the other months, we stay in the same year
                } else return addToDate(new Date(1, JANUARY, date.getYear() + 1), num);
            } else {
                // if we are in the right month (and year), calculate the date in num days from now and return it
                return new Date(date.getDay() + num, date.getMonth(), date.getYear());
            }
        //for a negative num
        } else {
            //we are not in the correct month (and year),  move to the previous month and check again for it
            if (-num > date.getDay()) {
                num += date.getDay();
                // if the month isn't January, we stay in the same year
                if (date.getMonth() != JANUARY) {
                    //check what is the last day of the previous month
                    int daysInMonthBefore = daysInThisMonth(new Date(1, date.getMonth()- 1, date.getYear()));
                    return addToDate(new Date(daysInMonthBefore, date.getMonth() - 1, date.getYear()), num);
                } else{
                    // we are in January, the previous month is December
                    int daysInMonthBefore = THIRTY_ONE_DAYS;
                    return addToDate(new Date(daysInMonthBefore, DECEMBER, date.getYear() - 1), num);
                }
            } else {
                //-num == day, it means we need to return the last day in the month of the previous month
                if (num + date.getDay() == 0){
                    int month = date.getMonth() -1;
                    // we are still in the same year
                    if (month !=0) {
                        Date dateToCheck = new Date(date.getDay(), month, date.getYear());
                        int day = daysInThisMonth(dateToCheck);
                        return new Date (day, month, date.getYear());
                    }
                    //we moved to the previous year
                    return new Date(THIRTY_ONE_DAYS, DECEMBER,date.getYear()-1);
                }
                //we are in the right month (and year), return the date that was before |num| days
                return new Date(date.getDay() + num, date.getMonth(), date.getYear());
            }
        }
    }
}
