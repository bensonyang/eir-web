package com.morningsidevc.po.gen;

public class WeixinUserInfo {
    private Integer id;

    private Integer userid;

    private String openid;

    private String avatarurl;

    private String weixinusername;

    private Byte gender;

    private String province;

    private String city;

    private String country;

    private String privilege;

    private String unionid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getWeixinusername() {
        return weixinusername;
    }

    public void setWeixinusername(String weixinusername) {
        this.weixinusername = weixinusername;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WeixinUserInfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", openid='" + openid + '\'' +
                ", avatarurl='" + avatarurl + '\'' +
                ", weixinusername='" + weixinusername + '\'' +
                ", gender=" + gender +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", privilege='" + privilege + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}