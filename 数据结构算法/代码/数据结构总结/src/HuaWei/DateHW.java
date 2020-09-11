package HuaWei;

import java.net.CacheRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class DateHW {

    public static boolean isSat(String dateStr) {
        String[] s = dateStr.split("-");
        String year = s[0];
        String month = s[1];
        String day = s[2];


        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();

        Date date;
        try {
            date = f.parse(dateStr);
            cal.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        String weekDay = weekDays[w];
        if (!weekDay.equals("星期六")) return false;

        int cnt = cal.WEEK_OF_MONTH;  //从1开始
        System.out.println(cnt);

        if (cnt<4) return false;
        return true;
    }

    public static void main(String[] args) {
        //System.out.println(isSat("2020-06-01"));
        System.out.println(isSat("2019-3-23"));
    }
}
