package LoadBalanceAlgorithm;

import java.util.HashMap;

public class IpMap {

    //待路由的ip列表 key代表ip Value代表权重

    public static HashMap<String,Integer> serverWeightMap = new HashMap<>();

    static {
        serverWeightMap.put("192.168.1.100",1);
        serverWeightMap.put("192.168.1.101",4);

        serverWeightMap.put("192.168.1.102",4);
        serverWeightMap.put("192.168.1.103",1);
        serverWeightMap.put("192.168.1.104",1);

        serverWeightMap.put("192.168.1.105",3);
        serverWeightMap.put("192.168.1.106",1);

        serverWeightMap.put("192.168.1.107",2);
        serverWeightMap.put("192.168.1.108",1);
        serverWeightMap.put("192.168.1.109",1);
        serverWeightMap.put("192.168.1.110",1);
    }
}
