package commons.boot.lock;

import lombok.Data;

@Data
public class EnableDistributedLock {
    private String  host = "127.0.0.1";
    private Integer port = 6379;
    private String  password ;
    private Integer dataBase = 1;
}
