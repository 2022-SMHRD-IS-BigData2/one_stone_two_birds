$('#allCheck').on('click', function() {
	var check = $('#allCheck').is(':checked')

	$('.agree').prop('checked', check)
	allcheck()
})

$('.primary').eq(0).on('click', function() {
	allcheck()
})

$('.primary').eq(1).on('click', function() {
	allcheck()
})

$('.option').eq(0).on('click', function() {
	allcheck()
})

$('.option').eq(1).on('click', function() {
	allcheck()
})
function allcheck() {

	const check01 = $('.agree').eq(0).is(':checked')
	const check02 = $('.agree').eq(1).is(':checked')
	const check03 = $('.agree').eq(2).is(':checked')
	const check04 = $('.agree').eq(3).is(':checked')


	if (check01 && check02 && check03 && check04) {
		$('#allCheck').prop('checked', true)
		$('#allCheck').css('accentColor', 'auto')
	} else if (check01 && check02 || (check03 || check04)) {
		$('#allCheck').prop('checked', check01 && check02)
		$('#allCheck').css('accentColor', 'gray')
	} else {
		$('#allCheck').prop('checked', false)
	}
}

// 비밀번호 체크
$('input[name=pwdResult]').on('keyup', function() {
    var pwd = $('input[name=pwd]').val()
    var pwdResult = $(this).val()
    if (pwdResult == '') {
        $(this).parent().find('p').hide() // 변경
		$(this).parent().find('p').css('color','black')
    }else if(pwd == pwdResult){
        console.log(true)
        $(this).parent().find('p').show() // 변경
        $(this).parent().find('p').text('비밀번호 일치') // 변경
		$(this).parent().find('p').css('color','green')
 	}else {
        console.log(false)
        $(this).parent().find('p').show() // 변경
        $(this).parent().find('p').text('비밀번호 불일치') // 변경
		$(this).parent().find('p').css('color','red')
    }
})

const open = (number) => {
	document.querySelectorAll(".modal")[number].classList.remove("hidden");
}

const close = (number) => {
	document.querySelectorAll(".modal")[number].classList.add("hidden");
}


document.querySelectorAll(".openBtn")[0].addEventListener("click", function() {
	open(0)
});
document.querySelectorAll(".openBtn")[1].addEventListener("click", function() {
	open(1)
});
document.querySelectorAll(".openBtn")[2].addEventListener("click", function() {
	open(2)
});
document.querySelectorAll(".openBtn")[3].addEventListener("click", function() {
	open(3)
});
document.querySelectorAll(".closeBtn")[0].addEventListener("click", function() {
	close(0)
});
document.querySelectorAll(".closeBtn")[1].addEventListener("click", function() {
	close(1)
});
document.querySelectorAll(".closeBtn")[2].addEventListener("click", function() {
	close(2)
});
document.querySelectorAll(".closeBtn")[3].addEventListener("click", function() {
	close(3)
});
document.querySelectorAll(".bg")[0].addEventListener("click", function() {
	close(0)
});
document.querySelectorAll(".bg")[1].addEventListener("click", function() {
	close(1)
});
document.querySelectorAll(".bg")[2].addEventListener("click", function() {
	close(2)
});
document.querySelectorAll(".bg")[3].addEventListener("click", function() {
	close(3)
});


// --- 회원가입 시도시 전송되는 곳
$('#sub-button').on('click', function() {
	const check01 = $('.agree').eq(0).is(':checked')
	const check02 = $('.agree').eq(1).is(':checked')
	const idVal = $('input[name=id]').val()
	const pwdVal = $('input[name=pwd]').val()
	const pwdResultVal = $('input[name=pwdResult]').val()
	const nickVal = $('input[name=nickname]').val()
	const birthVal = $('input[name=birthday]').val()
	const genderVal = $('select[name=gender]').val()
	const livingAreaVal = $('select[name=livingArea]').val()

	// 필수동의 항목 체크
	if (check01 && check02) {
		// 아이디 빈칸 체크
		if (idVal != '') {
			// 아이디 중복 체크
			if(idCheck(idVal) == "false"){
				// 비밀번호 빈칸 체크
				if(pwdVal != ''){
					// 비밀번호 확인 빈칸 체크
					if(pwdResultVal != ''){
						// 비밀번호 길이체크
						if(pwdVal.length > 8){
							// 닉네임 빈칸 체크
							if(nickVal != ''){
								// 생년월일 빈칸 체크
								if(birthVal != ''){
									// 성별 빈칸 체크
									if(genderVal != '성별'){
										//거주지 빈칸 체크
										if(livingAreaVal != '거주지'){
											swal("회원가입 성공", "회원가입이 완료되었습니다.","success")
											let param = {
												id: idVal,
												pwd: pwdVal,
												nickname: nickVal,
												birthday: birthVal,
												gender: genderVal,
												livingArea: livingAreaVal
											}
											post_to_url("/join",param,"post")
										//거주지 빈칸 체크
										}else{
											swal("주의", "사는 지역을 입력해주세요", "warning")
										}
									// 성별 빈칸 체크
									}else{
										swal("주의", "성별을 입력해주세요.", "warning")
									}
								// 생년월일 빈칸 체크
								}else{
									swal("주의", "생년월일을 입력해주세요.", "warning")
								}
							// 닉네임 빈칸 체크
							}else{
								swal("주의", "닉네임을 입력해주십시오.", "warning")
							}
						// 비밀번호 길이체크
						}else{
							swal("주의", "비밀번호가 너무 짧습니다.", "warning")
						}
					// 비밀번호 확인 빈칸 체크
					}else{
						swal("주의", "비밀번호 확인을 입력해 주십시오", "warning")
					}
				// 비밀번호 빈칸 체크
				}else{
					swal("주의", "비밀번호를 입력해 주십시오", "warning")
				}
			// 아이디 중복 체크
			}else{
				swal("주의", "존재하는 아이디 입니다.", "warning")
			}

		// 아이디 빈칸 체크
		} else {
			swal("주의", "아이디 칸은 비워둘 수 없습니다.", "warning")
		}
		
	// 필수동의 항목 체크
	} else {
		swal("주의", "필수 동의사항에 체크해 주십시오.", "warning")
	}

})


// 아이디 중복체크 함수
function idCheck(id){
	var result = ""
	$.ajax({
		url:"/ajax/search",
		data:{
			id:id
		},
		type:"get",
		async: false,
		success:function(res){
			console.log(res)
			result = res
		},
		error:function(e){
			
		}
	})
	return result;
}