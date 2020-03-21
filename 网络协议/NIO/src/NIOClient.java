import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        //for (int i = 0;i<10000;i++){
            new Thread(()->{
                try {
                    Socket socket = new Socket("127.0.0.1", 8888);
                    OutputStream out = null;
                    out = socket.getOutputStream();
                    String s = "hello world";
                    out.write(s.getBytes());
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            },"t1").start();
        //}



    }
}
