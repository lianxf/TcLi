package oss.boot.qiniu;

import lombok.Data;

@Data
public class EnableQiNiuService {
    private  String accessKey = "wx1KFyBF3owmhIaLZtunT6fngojephlEDEsxsMUP";
    private  String secretKey = "GravJZMq0Pe91RDh36XRCxwRY-zhK1C_4nfNuIx5";
    private  String bucket = "jhcz";
    private  Integer region = 2;
    private  String host = "https://oss.likepeng.cn";
}
