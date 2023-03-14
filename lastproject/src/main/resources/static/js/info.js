console.log(drugData)

if(drugData.itemName==null){
	$('#tab-제품명').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(0).hide()
}else{
	$('#tab-제품명').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(0).show()
	$("h1[name='제품명']").after("<p>"+drugData.itemName+"</p>")
}

if(drugData.entpName==null){
	$('#tab-업체명').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(1).hide()
}else{
	$('#tab-업체명').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(1).show()
	$("h1[name='업체명']").after("<p>"+drugData.entpName+"</p>")
}

if(drugData.efcyQesitm==null){
	$('#tab-효능').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(2).hide()
}else{
	$('#tab-효능').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(2).show()
	$("h1[name='효능']").after(drugData.efcyQesitm)
}

if(drugData.useMethodQesitm==null){
	$('#tab-사용법').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(3).hide()
}else{
	$('#tab-사용법').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(3).show()
	$("h1[name='사용법']").after(drugData.useMethodQesitm)
}

if(drugData.atpnWarnQesitm == null){
	$('#tab-복용전주의').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(4).hide()
}else{
	$('#tab-복용전주의').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(4).show()
	$("h1[name='복용전주의']").after(drugData.atpnWarnQesitm)
}

if(drugData.atpnQesitm==null){
	$('#tab-주의사항').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(5).hide()
}else{
	$('#tab-주의사항').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(5).show()
	$("h1[name='주의사항']").after(drugData.atpnQesitm)
}

if(drugData.intrcQesitm==null){
	$('#tab-상호작용').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(6).hide()
}else{
	$('#tab-상호작용').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(6).show()
	$("h1[name='상호작용']").after(drugData.intrcQesitm)
}

if(drugData.seQesitm==null){
	$('#tab-부작용').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(7).hide()
}else{
	$('#tab-부작용').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(7).show()
	$("h1[name='부작용']").after(drugData.seQesitm)
}

if(drugData.depositMethodQesitm==null){
	$('#tab-보관법').attr("hidden",true)
	$('a[class="et-hero-tab"]').eq(8).hide()
}else{
	$('#tab-보관법').attr("hidden",false)
	$('a[class="et-hero-tab"]').eq(8).show()
	$("h1[name='보관법']").after(drugData.depositMethodQesitm)
}

if(drugData.itemImage != null){
	$('img[name="pillImage"]').attr('src',drugData.itemImage)	
}


// 좋아요, 싫어요 버튼

 $(document).ready(function () {
 $(".like").click(function () {
     $.ajax({
		url:"/ajax/like",
		data:{
			id: "",
			drugCode: ""
		},
		type:"get",
		success:function(res){
			
		},
		error:function(e){
			
		}			
	})
});

 $(".dislike").click(function () {
                // 싫어요 버튼 클릭시 실행될 동작
 });
 });