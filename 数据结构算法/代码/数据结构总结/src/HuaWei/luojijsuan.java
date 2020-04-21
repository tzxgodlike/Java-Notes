package HuaWei;
import java.util.*;

/*

输出逻辑运算后的最终结果：0或者1

示例1：
输入：! ( 1 & 0 ) | 0 & 1

输出：1

示例2：
输入：! ( 1 & 0 ) & 0 | 0

输出：0
 */
public class luojijsuan {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int k = 0;
        while (input.length() != 1) {
            input = input.replaceAll("!1", "0");
            input = input.replaceAll("!0", "1");

            input = input.replaceAll("1&0", "0");
            input = input.replaceAll("0&1", "0");
            input = input.replaceAll("0&0", "0");
            input = input.replaceAll("1&1", "1");


            input = input.replaceAll("1\\|0", "1");
            input = input.replaceAll("0\\|1", "1");
            input = input.replaceAll("0\\|0", "0");
            input = input.replaceAll("1\\|1", "1");

            input = input.replaceAll("\\(1\\)", "1");
            input = input.replaceAll("\\(0\\)", "0");

            k++;
            if (k > 20) break;
        }
        System.out.println(input);
        sc.close();
    }

}
