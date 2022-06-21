public class DateCalculator {

    /**
     * this function checks if the date it gets is in a leap year or not
     * @param date the date we want to check if it's in a leap year or not
     * @return true if the year is a leap year, otherwise return false
     */
    private static boolean isLeapYear(Date date) {
        int year = date.getYear();
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * this function calculates the days in the month of the given date and returns it
     * @param date the date we want to check for its month's days
     * @return the number of days in the dates' month
     */
    private static int daysInThisMonth(Date date) {
        int monthNumber = date.getMonth();
        if (monthNumber == 1 || monthNumber == 3 || monthNumber == 5 || monthNumber == 7 || monthNumber == 8 ||
                monthNumber == 10 || monthNumber == 12) return 31;
        if (monthNumber == 4 || monthNumber == 6 || monthNumber == 9 || monthNumber == 11) return 30;
        if (isLeapYear(date)) return 29;
        return 28;
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
                if (date.getMonth() != 12) {
                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num);
                    //for the other months, we stay in the same year
                } else return addToDate(new Date(1, 1, date.getYear() + 1), num);
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
                if (date.getMonth() != 1) {
                    //check what is the last day of the previous month
                    int daysInMonthBefore = daysInThisMonth(new Date(1, date.getMonth()- 1, date.getYear()));
                    return addToDate(new Date(daysInMonthBefore, date.getMonth() - 1, date.getYear()), num);
                } else{
                    // we are in January, the previous month is December
                    int daysInMonthBefore = 31;
                    return addToDate(new Date(daysInMonthBefore, 12, date.getYear() - 1), num);
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
                    return new Date(31, 12,date.getYear()-1);
                }
                //we are in the right month (and year), return the date that was before |num| days
                return new Date(date.getDay() + num, date.getMonth(), date.getYear());
            }
        }
    }
}
