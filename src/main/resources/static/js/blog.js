


$(function (){
    init();
})

//将文本转成html
function init() {
    var content=$("#contenttemp").val();
    $("#content").html(content);
}

//方法定义
function commitsubmit(){
    var nickname=$("#name").val();
    var comment=$("#comment").val();
    if(nickname==null||comment==null){
        alert("昵称和评论内容不能为空");
    }else{
        $.ajax({
            type:"post",
            url:"/comment/submit",
            data:{nickname:nickname,str:comment,blogId:$("#blogId").val()},
            dataType:"json",
            success:function (data){
                alert(data.msg);
                $("#name").val("");
                $("#comment").val("");
            }
        })
    }
}
