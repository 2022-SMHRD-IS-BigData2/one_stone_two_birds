$('#allCheck').on('click',function(){
	var check = $('#allCheck').is(':checked')
	
	$('.agree').prop('checked',check)
	allcheck()
})

$('.primary').eq(0).on('click',function(){
	allcheck()
})

$('.primary').eq(1).on('click',function(){
	allcheck()
})

$('.option').eq(0).on('click',function(){
	allcheck()
})

$('.option').eq(1).on('click',function(){
	allcheck()
})
function allcheck(){
	
	var check01 = $('.agree').eq(0).is(':checked')
	var check02 = $('.agree').eq(1).is(':checked')
	var check03 = $('.agree').eq(2).is(':checked')
	var check04 = $('.agree').eq(3).is(':checked')
	

	if(check01 && check02 && check03 && check04){
		$('#allCheck').prop('checked',true)
		$('#allCheck').css('accentColor','auto')
	}else if(check01 && check02 || (check03 || check04)){
		$('#allCheck').prop('checked',check01 && check02)
		$('#allCheck').css('accentColor','gray')
	}else{
		$('#allCheck').prop('checked',false)
	}
}

$('input[name=pwdResult]').on('keyup',function(){
	var pwd = $('input[name=pwd]').val()
	var pwdResult = $('input[name=pwdResult]').val()
	if(pwd === pwdResult){
		console.log(true)
	}else{
		console.log(false)
	}
})

const open = () => {
  document.querySelector(".modal").classList.remove("hidden");
}

const close = () => {
  document.querySelector(".modal").classList.add("hidden");
}

document.querySelectorAll(".openBtn")[0].addEventListener("click", open);
document.querySelectorAll(".openBtn")[1].addEventListener("click", open);
document.querySelectorAll(".openBtn")[2].addEventListener("click", open);
document.querySelectorAll(".openBtn")[3].addEventListener("click", open);
document.querySelector(".closeBtn").addEventListener("click", close);
document.querySelector(".bg").addEventListener("click", close);
            
