 let kakao_account = null;
    window.Kakao.init('cdcd53cff82aeec615bc251258c0690d');
   
function kakaoLogin() {
    window.Kakao.Auth.login({
        scope: 'account_email, birthday, gender, age_range, profile_nickname', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
        success: function(response) {
            window.Kakao.API.request({ // 사용자 정보 가져오기 
                url: '/v2/user/me',
                success: (res) => { // 카카오 서버에서 사용자 정보 받아오기
                    kakao_account = res.kakao_account;
                    
                    var params = {
                    	'username' : kakao_account.email,
                    	'password' : res.id,
                    	"nickname":''+kakao_account.profile.nickname
                    }
                    
                    $.ajax({ // 받아온 데이터로 비동기 통신
                    	url:"/ajax/search",
                    	data:{"id":''+kakao_account.email},
                    	type: "get",
                    	success:function(resp){
                    		
                    		 if(resp != "true"){ // 회원이 없는 경우
                    			 
                    			 post_to_url('/kakao/login',params,'post')
                    		 }else{
                
                    			 post_to_url('/loginProc',params,'post') // 로그인처리
                    		 }
                    	},
                    	error:function(e){
                    		
                    	}
                    	
                    })
                    
                    
                    
                    
                }
            });
            // window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
        },
        fail: function(error) {
            console.log(error);
        }
    });
}