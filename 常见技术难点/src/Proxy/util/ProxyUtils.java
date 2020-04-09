package Proxy.util;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ProxyUtils {
    public static void generateClassFile (Class clazz,String proxyName) {
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName,new Class[]{clazz});

        String paths = clazz.getResource(".").getPath();
        try {
            paths = URLDecoder.decode(paths, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(paths);

        FileOutputStream out = null;

        try{
            out = new FileOutputStream(paths+proxyName+".class");
            out.write(classFile);
            out.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
