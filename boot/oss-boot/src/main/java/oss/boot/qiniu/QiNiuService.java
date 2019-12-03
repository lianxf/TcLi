package oss.boot.qiniu;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import oss.boot.model.OssResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnBean(EnableQiNiuService.class)
public class QiNiuService {
    @Autowired
    EnableQiNiuService enableQiNiuService;

    private Region[] regions = { Region.region0(),Region.region1(),Region.region2() };

    public OssResponse uploadFile(byte[] bytes,String savePath){
        Configuration cfg = new Configuration(regions[enableQiNiuService.getRegion()]);
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(enableQiNiuService.getAccessKey(), enableQiNiuService.getSecretKey());
        String upToken = auth.uploadToken(enableQiNiuService.getBucket());

        OssResponse ossResponse = new OssResponse();
        try {
            Response response = uploadManager.put(bytes, savePath, upToken);
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            String fileName = putRet.key;
            ossResponse.setUrl(enableQiNiuService.getHost()+"/"+fileName);
        } catch (QiniuException e) {
            ossResponse.setIsSuccess(false);
            ossResponse.setMsg("文件上传出错");
        }
        return ossResponse;
    }

    public List<OssResponse> fileList(String path){
        Configuration cfg = new Configuration(regions[enableQiNiuService.getRegion()]);
        Auth auth = Auth.create(enableQiNiuService.getAccessKey(), enableQiNiuService.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String bucket = enableQiNiuService.getBucket();
        String host = enableQiNiuService.getHost();

        int limit = 1000;
        String delimiter = "";

        ArrayList<OssResponse> ossResponses = new ArrayList<>();
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, path, limit, delimiter);
        while (fileListIterator.hasNext()) {
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                OssResponse ossResponse = new OssResponse();
                ossResponse.setUrl(host+"/"+item.key);
                ossResponses.add(ossResponse);
            }
        }
        return ossResponses;
    }

    public OssResponse delFile(String url){
        Configuration cfg = new Configuration(regions[enableQiNiuService.getRegion()]);
        Auth auth = Auth.create(enableQiNiuService.getAccessKey(), enableQiNiuService.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String bucket = enableQiNiuService.getBucket();
        String host = enableQiNiuService.getHost();

        String key = url.replace(host+"/", "");

        OssResponse ossResponse = new OssResponse();
        ossResponse.setUrl(url);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
           ossResponse.setIsSuccess(false);
           ossResponse.setMsg("删除失败");
           ossResponse.setUrl(url);
        }
        return ossResponse;
    }
}
