package LoadBalanceAlgorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class RoundRobin {
    /**
          * 轮询
          *
          * @author t50013699
          * @since 2020-08-04
          */

    //加权的话要重新建一个list

    public static Integer pos = 0;

    public static String getServer() {
        //去的IPlist

        Set<String> keySet = IpMap.serverWeightMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        String server = null;

        synchronized (pos) {
            //加锁 防止被多线程修改
            if (pos>=keyList.size()) {
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
        }
        return server;
    }

    public static String getServer_Weight() {
        //去的IPlist

        Set<String> keySet = IpMap.serverWeightMap.keySet();

        Iterator<String> iterator = keySet.iterator();
        ArrayList<String> serverList = new ArrayList<>();


        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = IpMap.serverWeightMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        String server = null;
        synchronized (pos) {
            //加锁 防止被多线程修改
            if (pos>=serverList.size()) {
                pos = 0;
            }
            server = serverList.get(pos);
            pos++;
        }
        return server;
    }
}
