package commons.boot.request;

import lombok.Data;

@Data
public class EnableSaveRequestInfo {
    private String expression;
    private SaveRequestInfo saveRequestInfo = new DefaultSaveRequestInfo();

    public EnableSaveRequestInfo() {
    }

    public EnableSaveRequestInfo(String expression) {
        this.expression = expression;
    }

    public EnableSaveRequestInfo(String expression, SaveRequestInfo saveRequestInfo) {
        this.expression = expression;
        this.saveRequestInfo = saveRequestInfo;
    }
}
