import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapWithVirtualNode implements LoadBalancer {

    private static Logger logger = LoggerFactory.getLogger(SortedMapWithVirtualNode.class);

    private TreeMap<Integer, String> treeMapHash;

    @Override
    public void addServerNode(String serverNodeName) {
        int hash = new GetHashCode().getHashCode(serverNodeName);
        treeMapHash.put(hash, serverNodeName);
        // logger.info("服务器节点：{} 上线", serverNodeName);
    }

    @Override
    public void delServerNode(String serverNodeName) {
        int hash = new GetHashCode().getHashCode(serverNodeName);
        treeMapHash.remove(hash);
        System.out.println("服务器节点：" + serverNodeName + " 下线");
        logger.debug("服务器节点：{} 下线", serverNodeName);
    }

    @Override
    public String selectServerNode(String requestURL) {
        int hash = new GetHashCode().getHashCode(requestURL);
        // 向右寻找第一个 key
        Map.Entry<Integer, String> subEntry = treeMapHash.ceilingEntry(hash);
        // 设置成一个环，如果超过尾部，则取第一个点
        subEntry = subEntry == null ? treeMapHash.firstEntry() : subEntry;
        String VNNode = subEntry.getValue();
        return VNNode.substring(0, VNNode.indexOf("&&"));
    }

    // 构建 Hash 环
    public SortedMap<Integer, String> buildHash(TreeMap<Integer, String> treeMap) {
        this.treeMapHash = treeMap;
        return treeMapHash;
    }
}