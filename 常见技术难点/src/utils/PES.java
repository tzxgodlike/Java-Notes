package utils;

import java.text.NumberFormat;

public class PES {

    public static void main(String[] args) {

        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        for (int i = 1; i < 55; i++) {
            double res_shike = 1 - Math.pow(0.9875, 10 * i);
            double res_chuanqi = 1 - Math.pow(0.995, 10 * i);
            String n1 = numberFormat.format(res_shike);
            String n2 = numberFormat.format(res_chuanqi);
            System.out.println("抽" + 10 * i + "发出时刻的概率为 = " + n1 + "  " + "抽" + 10 * i + "发出黄传的概率为 = " + n2);
        }

    }
}
