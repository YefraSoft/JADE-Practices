package helperClasess;

public class datas {

    static double[] yearsExperience = {
        1, 2, 3, 4, 5, 6, 7, 8, 9
    };

    static double[] salary = {
        3, 6, 9, 12, 15, 18, 21, 24, 27
    };
    static double[] year = {
        2050, 2045, 2040, 2035, 2030, 2025, 2020,
        2019, 2018, 2017, 2016, 2015, 2010, 2005,
        2000, 1995, 1990, 1985, 1980, 1975, 1970,
        1965, 1960, 1955
    };

    static double[] mediaAge = {
        38.1, 36.6, 35.0, 33.3, 31.7, 30.0, 28.4,
        27.1, 27.1, 27.1, 27.1, 26.8, 25.1, 23.8,
        22.7, 21.8, 21.1, 20.6, 20.2, 19.7, 19.3,
        19.6, 20.2, 20.7
    };
    static double[] age = {18, 22, 23, 26, 28, 31, 33};
    static double[] premium = {10000, 15000, 18000, 21000, 24000, 26500, 27000};
    static double sales[] = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
    static double advertising[] = {23, 26, 30, 34, 43, 48, 52, 57, 58};

    public static double[] getSales() {
        return sales;
    }

    public static double[] getAdvertising() {
        return advertising;
    }

    public static double[] getYearsExperience() {
        return yearsExperience;
    }

    public static double[] getSalary() {
        return salary;
    }

    public static double[] getYear() {
        return year;
    }

    public static double[] getMediaAge() {
        return mediaAge;
    }

    public static double[] getAge() {
        return age;
    }

    public static double[] getPremium() {
        return premium;
    }

}
