package blog.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_link")
public class TLink implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 名字
     */
    @Column(name = "link_name")
    private String linkName;

    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 网址
     */
    @Column(name = "link_url")
    private String linkUrl;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return link_name - 名字
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * 设置名字
     *
     * @param linkName 名字
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    /**
     * @return order_num
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取网址
     *
     * @return link_url - 网址
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置网址
     *
     * @param linkUrl 网址
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", linkName=").append(linkName);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append("]");
        return sb.toString();
    }
}