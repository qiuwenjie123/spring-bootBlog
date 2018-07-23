package blog.pojo;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_blogger")
public class TBlogger implements Serializable {
    /**
     * 博主id
     */
    @Id
    private Integer id;

    /**
     * 博主姓名
     */
    private String username;

    /**
     * 博主密码
     */
    private String password;

    /**
     * 博主昵称
     */
    private String nickname;

    /**
     * 博主签名
     */
    private String sign;

    /**
     * 博主头像路径
     */
    private String imagename;

    /**
     * 博主信息
     */
    private String profile;

    private static final long serialVersionUID = 1L;

    /**
     * 获取博主id
     *
     * @return id - 博主id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置博主id
     *
     * @param id 博主id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取博主姓名
     *
     * @return username - 博主姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置博主姓名
     *
     * @param username 博主姓名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取博主密码
     *
     * @return password - 博主密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置博主密码
     *
     * @param password 博主密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取博主昵称
     *
     * @return nickname - 博主昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置博主昵称
     *
     * @param nickname 博主昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取博主签名
     *
     * @return sign - 博主签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置博主签名
     *
     * @param sign 博主签名
     */
    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    /**
     * 获取博主头像路径
     *
     * @return imagename - 博主头像路径
     */
    public String getImagename() {
        return imagename;
    }

    /**
     * 设置博主头像路径
     *
     * @param imagename 博主头像路径
     */
    public void setImagename(String imagename) {
        this.imagename = imagename == null ? null : imagename.trim();
    }

    /**
     * 获取博主信息
     *
     * @return profile - 博主信息
     */
    public String getProfile() {
        return profile;
    }

    /**
     * 设置博主信息
     *
     * @param profile 博主信息
     */
    public void setProfile(String profile) {
        this.profile = profile == null ? null : profile.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", sign=").append(sign);
        sb.append(", imagename=").append(imagename);
        sb.append(", profile=").append(profile);
        sb.append("]");
        return sb.toString();
    }
}