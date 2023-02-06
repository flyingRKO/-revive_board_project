function registerCheck(){
    var memID=$("#memID").val();
    $.ajax({
        url : "${contextPath}/memRegisterCheck.do",
        type : "get",
        data : { "memID" : memID},
        success : function(result){
            // 중복유무 출력(result=1 : 사용할수있는 아이디, 0 : 사용할수없는 아이디)
            if(result==1){
                $("#checkMessage").html("사용할 수 있는 아이디입니다.");
                $("#checkType").attr("class","modal-content panel-success");
            }else{
                $("#checkMessage").html("사용할 수 없는 아이디입니다.");
                $("#checkType").attr("class","modal-content panel-warning");
            }
            $("#myModal").modal("show");
        },
        error : function(){ alert("error"); }
    });
}