// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(35.16022, 126.85164), // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

var geocoder = new kakao.maps.services.Geocoder();

var coords = null;

// 주소로 좌표를 검색합니다
function kakaoMapSearch() {
	geocoder.addressSearch($('#kakaomap').val(), function(result, status) {

		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: '<div style="width:150px;text-align:center;padding:6px 0;">현재위치</div>'
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);
		}

	});

}

kakao.maps.event.addListener(map, 'center_changed', function() {
	kakaoPM9Search()
});

// 지도 확대 레벨 변화 이벤트를 등록한다
kakao.maps.event.addListener(map, 'zoom_changed', function() {
	kakaoPM9Search()
});

// 지도 드래깅 이벤트를 등록한다 (드래그 시작 : dragstart, 드래그 종료 : dragend)
kakao.maps.event.addListener(map, 'drag', function() {
	kakaoPM9Search()
});
kakao.maps.event.addListener(map, 'mousewheel', function(event) {
	event.preventDefault();
});


function kakaoPM9Search() {
	var placeName = [];
	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places(map);

	// 카테고리로 약국을 검색합니다
	ps.categorySearch('PM9', placesSearchCB, { useMapBounds: true });

	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {
		if (status === kakao.maps.services.Status.OK) {
			for (var i = 0; i < data.length; i++) {
				displayMarker(data[i]);
			}
		}
	}

	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {
		// 마커를 생성하고 지도에 표시합니다
		var marker = new kakao.maps.Marker({
			map: map,
			position: new kakao.maps.LatLng(place.y, place.x)
		});

		// 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'mouseover', function() {
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
			infowindow.open(map, marker);
		});
		kakao.maps.event.addListener(marker, 'click', function() {
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div><p>상호명: ' + place.place_name + '</p> <p>전화번호: ' + place.phone + '<br> 주소: ' + place.road_address_name + '</p><a href="' + place.place_url + '"><img src="/img/url.png" height="20"></a></div>');
			infowindow.open(map, marker);
		});
	}
}