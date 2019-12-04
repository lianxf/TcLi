package cn.likepeng.commons.core.enumeration;

import lombok.Getter;

@Getter
public enum RsaLength {
    RSA1024(1024),RSA2048(2048),RSA3072(3072),RSA4096(4096),RSA5120(5120),RSA10240(10240);
    private final Integer length;
    RsaLength(Integer length) {
        this.length=length;
    }
}
