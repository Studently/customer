package cn.edu.stu.customer.domain;

public class Customer {

    /**
     * cid		CHAR(32) PRIMARY KEY,
     * 	cname		VARCHAR(40) NOT NULL,
     * 	gender		VARCHAR(6) NOT NULL,
     * 	birthday	CHAR(10),
     * 	cellphone	VARCHAR(15) NOT NULL,
     * 	email		VARCHAR(40),
     * 	description	VARCHAR(500)
     */

    private int cid;//id
    private String cname;//名字
    private String gender;//性别
    private String birthday;//生日
    private String cellphone;//电话
    private String email;//邮箱
    private String description;//描述

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
