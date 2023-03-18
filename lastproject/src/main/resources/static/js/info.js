// 좋아요, 싫어요 버튼

 $(document).ready(function () {
 $(".like").click(function () {
     $.ajax({
		url:"/ajax/like",
		data:{
			id: userName,
			drugCode: itemSeq
		},
		type:"get",
		success:function(res){
			if(res == "fail"){
				swal({
					content: "text",
					title:"로그인 해주세요",
					icon:"error",
					buttons:{
						cancel: false,
						confirm: true
					}
				})
			}else if(res == "up"){
								swal({
					content: "text",
					title:"선호 등록 되었습니다.",
					icon:"success",
					buttons:{
						cancel: false,
						confirm: true
					}
				})
			}else if(res == "down"){
				swal({
					content: "text",
					title:"선호 취소 되었습니다.",
					icon:"success",
					buttons:{
						cancel: false,
						confirm: true
					}
				})
			}
		},
		error:function(e){
			
		}			
	})
});

 $(".dislike").click(function () {
                // 싫어요 버튼 클릭시 실행될 동작
     $.ajax({
		url:"/ajax/dislike",
		data:{
			id: userName,
			drugCode: drugData.itemSeq
		},
		type:"get",
		success:function(res){
			if(res == "fail"){
				swal({
					content: "text",
					title:"로그인 해주세요",
					icon:"error",
					buttons:{
						cancel: false,
						confirm: true
					}
				})
			}else if(res == "up"){
								swal({
					content: "text",
					title:"비선호 등록 되었습니다.",
					icon:"success",
					buttons:{
						cancel: false,
						confirm: true
					}
				})
			}else if(res == "down"){
				swal({
					content: "text",
					title:"비선호 취소 되었습니다.",
					icon:"success",
					buttons:{
						cancel: false,
						confirm: true
					}
				})
			}			
		},
		error:function(e){
			
		}			
	})
 });
 });