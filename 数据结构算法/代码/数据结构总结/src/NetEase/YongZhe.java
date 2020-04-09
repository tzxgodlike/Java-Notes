package NetEase;

import java.util.Arrays;
import java.util.Scanner;

public class YongZhe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int D = sc.nextInt();

        int[] attack = new int[n];
        int[] damage = new int[n];

        for (int i = 0;i<n;i++) {
            attack[i] = sc.nextInt();
        }
        for (int i = 0;i<n;i++) {
            damage[i] = sc.nextInt();
        }

        System.out.println(damage(D,attack,damage));
    }

    public static int damage(int Defense,int[] attack,int[] damage){
        Arrays.sort(attack);
        int n = attack.length;
        int allDamage = 0;
        for (int i = 0;i<n;i++){
            if(Defense<=attack[n-i-1]){

                allDamage+=damage[i];
            }
            Defense++;
        }
        return allDamage;
    }
}
