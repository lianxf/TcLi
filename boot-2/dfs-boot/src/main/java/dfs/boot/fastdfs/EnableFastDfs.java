package dfs.boot.fastdfs;

import lombok.Data;

/**
 *
 * @author TcLi
 * 邮箱 254870497@qq.com
 * @date 2019/9/7 18:15
 */
@Data
public class EnableFastDfs {
    private String trackerServers = "39.104.99.211:22122";
    private String connectTimeout = "10";
    private String networkTimeout = "60";
    private String trackerHttpPort = "80";
    private String host = "https://f.likepeng.cn";
}
