package blog.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_blogtype")
public class TBlogtype implements Serializable {
    /**
     * 博客id
     */
    @Id
    private Integer id;

    /**
     * 博客类别
     */
    @Column(name = "typeName")
    private String typename;

    /**
     * 博客排序
     */
    @Column(name = "orderNum")
    private Integer ordernum;

    private static final long serialVersionUID = 1L;

    /**
     * 获取博客id
     *
     * @return id - 博客id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置博客id
     *
     * @param id 博客id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取博客类别
     *
     * @return typeName - 博客类别
     */
    public String getTypename() {
        return typename;
    }

    /**
     * 设置博客类别
     *
     * @param typename 博客类别
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    /**
     * 获取博客排序
     *
     * @return orderNum - 博客排序
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * 设置博客排序
     *
     * @param ordernum 博客排序
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typename=").append(typename);
        sb.append(", ordernum=").append(ordernum);
        sb.append("]");
        return sb.toString();
    }
}