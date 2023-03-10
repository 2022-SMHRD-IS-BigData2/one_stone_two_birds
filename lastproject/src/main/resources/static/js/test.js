$('#test').on('click',function(){
	var page_no = $('input[name="pageNo"]').val()
	if(page_no.length == 0){
		page_no = "1";
	}
	$.ajax({
		url:"http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList",
		data:{
			serviceKey:"nOpbKEOOTeYPonSnPwFJ/bGrXOM4uVVRmGPmE8/+jvwOY+ZOvY2P6ZMyelh0/4m8VX3granOhVp50XlIT3BdBA==",
			//pageNo:"",
			numOfRows:"1",
			entpName:"",
			itemName:"",
			itemSeq:page_no,
			efcyQesitm:"",
			useMethodQesitm:"",
			atpnWarnQesitm:"",
			atpnQesitm:"",
			intrcQesitm:"",
			seQesitm:"",
			depositMethodQesitm:"",
			openDe:"",
			updateDe:"",
			type:"json"
		},
		type:"get",
		success:function(res){
			console.log("res")
			console.log(res)
			console.log(res.body.items[0].itemImage)
			console.log(res.body.items[0].itemSeq)
			$('#itemName').text("약품이름: "+res.body.items[0].itemName)
			$('#itemSeq').text("약품코드: "+res.body.items[0].itemSeq)
				$.ajax({
					url:"/ajax/drugImage",
					data:{
						drugCode : res.body.items[0].itemSeq
					},
					type:"get",
					success:function(res){
						$('#imgSrc').attr('src',res.itemImage)
						$('#imgSrc').attr('alt',res.drugCode)
					},
					error:function(e){
						
					}
				})
		},
		error:function(e){
			console.log(e)
			
		}
	})
})

$('#drugImg').on('click',function(){
	var drugCode = $('input[name="drugCode"]').val()
	console.log(drugCode)

})