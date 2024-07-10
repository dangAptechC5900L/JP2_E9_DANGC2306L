package Entity;

public enum EStatus {
    PE("PENDING"),PA("PAID"),CA("CANCEL"),CO("COMPLETE");
    private String status;
    EStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
