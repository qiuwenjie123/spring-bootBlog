package blog.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_blog")
public class TBlog implements Serializable {

    /**
     * 博客id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 博客题目
     */
    private String title;

    /**
     * 博客摘要
     */
    private String summary;

    /**
     * 发布日期
     */
    @Column(name = "releaseDate")
    private Date releasedate;

    /**
     * 显示的短日期
     */
    @Transient
    private String date;

    /**
     * 点击次数
     */
    @Column(name = "clickHit")
    private Integer clickhit;

    /**
     * 评论次数
     */
    @Column(name = "replyHit")
    private Integer replyhit;

    /**
     * 关键字
     */
    @Column(name = "keyWord")
    private String keyword;

    /**
     * 外键关联博客类别
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 博客类别名称
     */
    @Transient
    private String typename;


    /**
     * 博客内容(含html)
     */
    private String content;

    /**
     * 博客内容(不含html)
     */
    @Transient
    private String text;

    private static final long serialVersionUID = 1L;



    /**
     * 获取博客类型
     *
     * @return id - 博客类型
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置博客类型
     *
     * @param id 博客类型
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取博客题目
     *
     * @return title - 博客题目
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置博客题目
     *
     * @param title 博客题目
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取博客摘要
     *
     * @return summary - 博客摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置博客摘要
     *
     * @param summary 博客摘要
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * 获取发布日期
     *
     * @return releaseDate - 发布日期
     */
    public Date getReleasedate() {
        return releasedate;
    }

    /**
     * 设置发布日期
     *
     * @param releasedate 发布日期
     */
    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    /**
     * 获取评论次数
     *
     * @return clickHit - 评论次数
     */
    public Integer getClickhit() {
        return clickhit;
    }

    /**
     * 设置评论次数
     *
     * @param clickhit 评论次数
     */
    public void setClickhit(Integer clickhit) {
        this.clickhit = clickhit;
    }

    /**
     * 获取回复次数
     *
     * @return replyHit - 回复次数
     */
    public Integer getReplyhit() {
        return replyhit;
    }

    /**
     * 设置回复次数
     *
     * @param replyhit 回复次数
     */
    public void setReplyhit(Integer replyhit) {
        this.replyhit = replyhit;
    }

    /**
     * 获取关键字
     *
     * @return keyWord - 关键字
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置关键字
     *
     * @param keyword 关键字
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 获取外键关联博客类别
     *
     * @return type_id - 外键关联博客类别
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置外键关联博客类别
     *
     * @param typeId 外键关联博客类别
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取博客内容
     *
     * @return content - 博客内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置博客内容
     *
     * @param content 博客内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
    public String getTypename() {
        return typename;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", summary=").append(summary);
        sb.append(", releasedate=").append(releasedate);
        sb.append(", clickhit=").append(clickhit);
        sb.append(", replyhit=").append(replyhit);
        sb.append(", keyword=").append(keyword);
        sb.append(", typeId=").append(typeId);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}