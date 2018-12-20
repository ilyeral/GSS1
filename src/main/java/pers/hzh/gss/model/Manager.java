package pers.hzh.gss.model;

public class Manager {
    private String id;
    private String password;
    private String vericode;
    Manager(){
        id="";
        password="";
        vericode ="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVericode() {
        return vericode;
    }

    public void setVericode(String vericode) {
        this.vericode = vericode;
    }
}
