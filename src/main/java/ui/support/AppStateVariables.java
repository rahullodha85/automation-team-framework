package ui.support;

/**
 * Created by vevinmoza on 3/31/16.
 */
public class AppStateVariables {
    private String bmUID;
    private String bmFormId;
    private String sid;
    private String folderId;
    private String productId;
    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    ;
    public String getBmFormId() {
        return bmFormId;
    }
    public String getBmUID(){
        return bmUID;
    }

    public void setBmFormId(String bmFormId) {
        this.bmFormId=bmFormId;
    }
    public void setBmUID(String bmUID){
        this.bmUID=bmUID;
    }

    public String getSID(){
        return sid;
    }
    public void setSID(String sid) {
        this.sid = sid;
    }
}
