package dfs.boot.fastdfs;

import cn.likepeng.commons.core.exception.BusinessException;
import cn.likepeng.commons.core.response.Response;
import cn.likepeng.commons.core.utils.RespUtil;
import cn.likepeng.commons.core.utils.StringUtil;
import org.csource.fastdfs.StorageClient;
import java.util.HashMap;

/**
 *
 * @author TcLi
 * 邮箱 254870497@qq.com
 * @date 2019/9/7 17:01
 */
public class FastDfsService {

    private StorageClient storageClient;

    private EnableFastDfs enableFastDfs;

    public Response uploadFile(byte[] fileByte, String suffix)throws Exception{

        String[] response = storageClient.upload_file(fileByte, suffix, null);

        StringBuffer stringBuffer = new StringBuffer();
        int responseLength = 2;
        stringBuffer.append(enableFastDfs.getHost());
        stringBuffer.append("/");
        if (responseLength == response.length){
            stringBuffer.append(response[0]);
            stringBuffer.append("/");
            stringBuffer.append(response[1]);
        }

        HashMap<String, String> result = new HashMap<>(16);
        result.put("url",stringBuffer.toString());
        return RespUtil.success(result);
    }

    public Response delFile(String file)throws Exception{
        String noHostName = StringUtil.removePrefix(file, enableFastDfs.getHost() + "/");
        int i = StringUtil.indexOf(noHostName, '/');

        String group = noHostName.substring(0, i);
        String fileName = noHostName.substring(i + 1);

        int flag = storageClient.delete_file(group, fileName);
        if (flag != 0){
            throw new BusinessException(705,"文件删除失败");
        }
        return RespUtil.success();
    }

    public FastDfsService(StorageClient storageClient, EnableFastDfs enableFastDfs) {
        this.storageClient = storageClient;
        this.enableFastDfs = enableFastDfs;
    }
}
