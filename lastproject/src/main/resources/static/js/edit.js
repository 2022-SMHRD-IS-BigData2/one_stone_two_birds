$('#edit').on('click', function() {
	let idVal = $('input[name=id]').val()
	let pwdVal = $('input[name=pwd]').val()
	let nickVal = $('input[name=nickname]').val()
	let birthVal = $('input[name=birthday]').val()
	let genderVal = $('select[name=gender]').val()
	let livingAreaVal = $('select[name=livingArea]').val()
	console.log(pwdVal)
	$.ajax({
		url: "/ajax/update",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify({
			id: idVal,
			pwd: pwdVal,
			nickname: nickVal,
			birthday: birthVal,
			gender: genderVal,
			livingArea: livingAreaVal,
		}),
		success:function(res){
			if(res == "true"){
				location.href = "/myPage/mypage"
			}
		},
		error:function(e){
			
		}
	});
})

// 비밀번호 체크
$('input[name=pwdResult]').on('keyup', function() {
 	pwdRePwd()
})
// 비밀번호 체크
$('input[name=pwd]').on('keyup', function() {
 	pwdRePwd()
})

function pwdRePwd(){
	var pwd = $('input[name=pwd]').val()
    var pwdResult = $('input[name=pwdResult]').val()
    if (pwdResult == '') {
        $('input[name=pwdResult]').parent().find('p').hide() // 변경
		$('input[name=pwdResult]').parent().find('p').css('color','black')
    }else if(pwd == pwdResult){
        $('input[name=pwdResult]').parent().find('p').show() // 변경
        $('input[name=pwdResult]').parent().find('p').text('비밀번호 일치') // 변경
		$('input[name=pwdResult]').parent().find('p').css('color','green')
 	}else {
        $('input[name=pwdResult]').parent().find('p').show() // 변경
        $('input[name=pwdResult]').parent().find('p').text('비밀번호 불일치') // 변경
		$('input[name=pwdResult]').parent().find('p').css('color','red')
    }
}

$('#remove').on('click', function() {
	let idVal = $('input[name=id]').val()
	swal({
		content: "input",
		title:"비밀번호를 입력해주세요",
		text:"비밀번호 입력시 회원 탈퇴 됩니다.",
		icon:"warning",
		dangerMode: true
	})
	.then((value) => {
		console.log(value)
		console.log(idVal)
		
		$.ajax({
			url:"/ajax/remove",
			data:{
				id : idVal,
				pwd : value
			},
			type:"post",
			success:function(res){
				if(res == "true"){
					location.href="/logout"
				}
			},
			error:function(e){}
		})
		
	})
	
})