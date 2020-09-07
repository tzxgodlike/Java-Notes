package LoadBalanceAlgorithm;

import java.util.ArrayList;
import java.util.Set;

public class HashIp {
    /**
          * 源地址hash法
          * 根据客户端的IP地址hash得到服务器编号
          * 
          * @author t50013699
          * @since 2020-08-04
          */
    public static String getServer() {
        Set<String> keySet = IpMap.serverWeightMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        String remoteIp = "127.0.0.1";
        int hashcode = remoteIp.hashCode();

        int serverListSize = keyList.size();
        int serverPos = hashcode%serverListSize;

        return keyList.get (serverPos);

    }

}
