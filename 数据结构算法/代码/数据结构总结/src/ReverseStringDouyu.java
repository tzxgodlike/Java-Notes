public class ReverseStringDouyu {
    public static String reverse (String str) {
        // write code here
        if (str==null||str.length()==0) return null;
        char[] c = str.toCharArray();
        for (int i = 0;i<c.length/2;i++) {
            char temp;
            temp = c[i];
            c[i] = c[c.length-1-i];
            c[c.length-i-1] = temp;
        }

        return String.valueOf(c);
    }

    public static void main(String[] args) {
        String s = "Douyu";
        String res = reverse(s);
        System.out.println(res);
    }
}
