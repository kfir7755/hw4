public class DateCalculator {

    private static boolean isLeapYear(Date date) {
        int year = date.getYear();
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private static int daysInThisMonth(Date date) {
        int monthNumber = date.getMonth();
        if (monthNumber == 1 || monthNumber == 3 || monthNumber == 5 || monthNumber == 7 || monthNumber == 8 ||
                monthNumber == 10 || monthNumber == 12) return 31;
        if (monthNumber == 4 || monthNumber == 6 || monthNumber == 9 || monthNumber == 11) return 30;
        if (isLeapYear(date)) return 29;
        return 28;
    }

    private static int daysLeftInMonth(Date date) {
        int dayNumber = date.getDay();
        return daysInThisMonth(date) - dayNumber;
    }

    public static Date addToDate(Date date, int num) {
        if (num == 0) return date;
        if (num > 0) {
            int daysLeftInThisMonth = daysLeftInMonth(date);
            if (num > daysLeftInThisMonth) {
                num -= (daysLeftInThisMonth + 1);
                if (date.getMonth() != 12) {
                    return addToDate(new Date(1, date.getMonth() + 1, date.getYear()), num);
                } else return addToDate(new Date(1, 1, date.getYear() + 1), num);
            } else {
                return new Date(date.getDay() + num, date.getMonth(), date.getYear());
            }
        } else {
            if (-num > date.getDay()) {
                num += date.getDay();
                if (date.getMonth() != 1) {
                    int daysInMonthBefore = daysInThisMonth(new Date(1, date.getMonth()- 1, date.getYear()));
                    return addToDate(new Date(daysInMonthBefore, date.getMonth() - 1, date.getYear()), num);
                } else{
                    int daysInMonthBefore = 31;
                    return addToDate(new Date(daysInMonthBefore, 12, date.getYear() - 1), num);
                }
            } else {
                if (num + date.getDay() == 0){
                    int month = date.getMonth() -1;
                    if (month !=0) {
                        Date dateToCheck = new Date(date.getDay(), month, date.getYear());
                        int day = daysInThisMonth(dateToCheck);
                        return new Date (day, month, date.getYear());
                    }
                    return new Date(31, 12,date.getYear()-1);
                }
                return new Date(date.getDay() + num, date.getMonth(), date.getYear());
            }
        }
    }
}
