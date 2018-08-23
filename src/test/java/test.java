import blog.utils.MD5Util;

public class test {

    public static void main(String[] args) {
        String password = MD5Util.md5("q19982182008", "xp");//后面那个是“盐”参数
        System.out.println(password);
        //fccf0a886e69eb0d3b89e35159e73b00
    }
}
