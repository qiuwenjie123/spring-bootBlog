package blog.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_comment")
public class TComment implements Serializable {
    /**
     * 评论id
     */
    @Id
    private Integer id;

    /**
     * 用户ip(外加一个昵称)
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * 评论时间
     */
    @Column(name = "comment_date")
    private Date commentDate;

    /**
     * 评论状态
     */
    private Integer state;

    /**
     * 所评论的博客id
     */
    @Column(name = "blog_id")
    private Integer blogId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人昵称
     */
    @Transient
    private String nickname;

    private static final long serialVersionUID = 1L;

    /**
     * 获取评论id
     *
     * @return id - 评论id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置评论id
     *
     * @param id 评论id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ip
     *
     * @return user_ip - 用户ip
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 设置用户ip
     *
     * @param userIp 用户ip
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    /**
     * 获取评论时间
     *
     * @return comment_date - 评论时间
     */
    public Date getCommentDate() {
        return commentDate;
    }

    /**
     * 设置评论时间
     *
     * @param commentDate 评论时间
     */
    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    /**
     * 获取评论状态
     *
     * @return state - 评论状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置评论状态
     *
     * @param state 评论状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取所评论的博客id
     *
     * @return blog_id - 所评论的博客id
     */
    public Integer getBlogId() {
        return blogId;
    }

    /**
     * 设置所评论的博客id
     *
     * @param blogId 所评论的博客id
     */
    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userIp=").append(userIp);
        sb.append(", commentDate=").append(commentDate);
        sb.append(", state=").append(state);
        sb.append(", blogId=").append(blogId);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}