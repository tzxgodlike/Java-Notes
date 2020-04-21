package meituan;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class shijianhuisu {
    /*
    给定时间  求N分钟之前是几
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //System.out.println(Integer.parseInt("02")+1);
        int week = sc.nextInt();
        sc.nextLine();
        String[] time = sc.nextLine().split(":");
        int hour = Integer.parseInt(time[0]);
        int min = Integer.parseInt(time[1]);
        int minuteBefore = sc.nextInt();
        Cal(week,hour,min,minuteBefore);
        //Cal(1,0,30,60);
        //System.out.println((1+7-3)%7);
    }

    private static void Cal(int week, int hour, int min, int minuteBefore) {
        int[] weekDate = {7,1,2,3,4,5,6};
        int CurMin = hour*60+min;  //130
        if (CurMin<minuteBefore) {
            int lastMin = minuteBefore - CurMin;  //200-130
            int day = lastMin / (24 * 60)+1;     //0
            int Oldweek =weekDate[((week + 7 - day) % 7)] ; //1
            int thatmin = lastMin % (24 * 60);  //
            int Oldhour = thatmin / 60;
            int thismin = thatmin % 60;
            int resH = 24-Oldhour-1;
            int resM = 60-thismin;
            DecimalFormat df = new DecimalFormat("00");

            String sresH = df.format(resH);
            String sresM = df.format(resM);

            System.out.println(Oldweek);
            System.out.println(sresH+":"+sresM);
        }else {
            int HHH =  minuteBefore/60;
            int MMM = minuteBefore%60;
            int resHH = hour-HHH;
            int resMM = 60-MMM;
            DecimalFormat df = new DecimalFormat("00");
            String sresHH = df.format(resHH);
            String sresMM = df.format(resMM);
            System.out.println(week);
            System.out.println(sresHH+":"+sresMM);
        }
    }


}
