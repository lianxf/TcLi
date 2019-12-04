package dfs.boot.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author TcLi
 * 邮箱 254870497@qq.com
 * @date 2019/9/7 18:19
 */
@Configuration
@ConditionalOnBean(EnableFastDfs.class)
public class FastDfsConfig {
    @Autowired(required = false)
    EnableFastDfs enableFastDfs;

    @Bean
    public StorageClient storageClient(){

        Properties props = new Properties();
        props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS, enableFastDfs.getTrackerServers());
        props.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, enableFastDfs.getConnectTimeout());
        props.put(ClientGlobal.PROP_KEY_CHARSET, "UTF-8");
        props.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, enableFastDfs.getNetworkTimeout());
        props.put(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, "false");
        props.put(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, enableFastDfs.getTrackerHttpPort());

        try {
            ClientGlobal.initByProperties(props);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = null;
        try {
            trackerServer = tracker.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    @Bean
    public FastDfsService fastDfsService(){
        FastDfsService fastDfsService = new FastDfsService(storageClient(), enableFastDfs);
        return fastDfsService;
    }
}
