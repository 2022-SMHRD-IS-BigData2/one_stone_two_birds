$('#edit').on('click', function() {
	const idVal = $('input[name=id]').val()
	const pwdVal = $('input[name=pwd]').val()
	const pwdResultVal = $('input[name=pwdResult]').val()
	const nickVal = $('input[name=nickname]').val()
	const birthVal = $('input[name=birthday]').val()
	console.log(birthVal)
	const genderVal = $('select[name=gender]').val()
	console.log(genderVal)
	const livingAreaVal = $('select[name=livingArea]').val()
	console.log(livingAreaVal)
})

$('#remove').on('click', function() {
	swal({
		content: "input",
		title:"비밀번호를 입력해주세요",
		text:"비밀번호 입력시 회원 탈퇴 됩니다.",
		icon:"warning",
		dangerMode: true
	})
	.then((value) => {
		console.log(value)
	})
	
})