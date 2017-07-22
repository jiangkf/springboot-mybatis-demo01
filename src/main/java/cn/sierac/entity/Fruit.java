package cn.sierac.entity;

/**
 * Created by Jack on 2017/7/21.
 */
public class Fruit extends BaseEntity {

    private String price; //价格
    private String type; //水果类型
    private Integer sid ; //商店id

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
