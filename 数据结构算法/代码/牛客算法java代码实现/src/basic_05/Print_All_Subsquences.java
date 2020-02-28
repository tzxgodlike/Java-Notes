package basic_05;

public class Print_All_Subsquences {
    private static void printAllSub(char[] arr,int index,String res){
        if(index==arr.length){
            System.out.println(res);
            //return ; //这里是否需要加return要看情况
            //要看该函数是否需要中断 若下面删掉else
            //当index==arr.len之后不return的话会继续执行printAllSub导致溢出
        }else{
            printAllSub(arr,index+1,res);
            printAllSub(arr,index+1,res+String.valueOf(arr[index]));
        }

    }

    public static void main(String[] args) {
        String test = "abc";
        char[] str = test.toCharArray();
        String res = "";
        printAllSub(str,0,res);
    }
}
