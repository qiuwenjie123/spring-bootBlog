package common;

public class Result {

    private int code;
    private int count;
    private Object data;
    private String msg;

    //正确的状态码：200
    //错误的状态码：400
    public Result(){
        msg="200";
    }

    public Result(String msg,int code){
        this.msg=msg;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
