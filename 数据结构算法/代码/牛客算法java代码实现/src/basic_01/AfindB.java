package basic_01;

import javax.sound.sampled.SourceDataLine;

public class AfindB {
    public static void main(String[] args) {
        //题目：有序数组A，无序数组B，打印B中所有不在A的数，
        // A数组长度为N，B数组长度为M。
        // 1.B中每一个元素都在A中去遍历 复杂度为O(m*n)
//         2.B中每个元素在A中二分遍历，复杂度为O(m*logn)
//         3.先排序B 在同时遍历AB，若A小于B，A下一位，若
//           A等于B，B下一位，若A大于B，则打印B，B下一位
        //复杂度为O(m*logm)+O(m+n)
        System.out.println("你好");
    }
}
