package LoadBalanceAlgorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Random {
    /**
     *      * 随机
     *      *
     *      * @author t50013699
     *      * @since 2020-08-04
     *      
     */

    public static String getServer() {
        Set<String> keySet = IpMap.serverWeightMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(keyList.size());  // 获得0~size()-1的随机数
        return keyList.get(randomPos);

    }

    public static String getServer_Weight() {
        Set<String> keySet = IpMap.serverWeightMap.keySet();
        Iterator<String> iterator = keySet.iterator();

        List<String> serverList = new ArrayList<>();

        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = IpMap.serverWeightMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }

        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());  // 获得0~size()-1的随机数
        return serverList.get(randomPos);
    }
}
