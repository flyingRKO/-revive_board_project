function checkId(){
    const id = $('#memberId').val(); //id값이 "memberId"인 입력란의 값을 저장
    const exp = /^[a-zA-Z0-9]{6,10}$/;
    $.ajax({
        url:'/member/idCheck', //Controller에서 요청 받을 주소
        type:'post', //POST 방식으로 전달
        data:{id:id},
        success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다
            if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디
                if (!id.match(exp)) {
                    $('.id_ok').css("display","none");
                    $('.id_invalid').css("display","inline-block");
                    $('.id_already').css("display", "none");
                } else {
                    $('.id_ok').css("display","inline-block");
                    $('.id_invalid').css("display","none");
                    $('.id_already').css("display", "none");
                }
            } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                $('.id_already').css("display","inline-block");
                $('.id_ok').css("display", "none");
                $('.id_invalid').css("display","none");
                $('#id').val('');
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
};