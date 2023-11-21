<script setup>
import { onMounted, ref } from "vue";
const attractiontElement = defineProps({ attractionList: Object });

let map = null;
const initMap = function () {
    const container = document.getElementById("map");
    const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
    }; //지도 객체 등록
    map = new kakao.maps.Map(container, options);
    //지도에 여러개의 마커를 띄움
    displayMarkers();
};

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else {
        const script = document.createElement("script");
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
            //autoload= false 스크립트를 동적으로 로드하기 위해 필요
            import.meta.env.VITE_KAKAO_API_KEY
        }`;
        /* global kakao */
        script.addEventListener("load", () => {
            kakao.maps.load(initMap);
        }); //헤드태그에 추가
        document.head.appendChild(script);
    }
});

// //현재 내 위치 기준으로 마커띄우기
let myCenter = new kakao.maps.LatLng(33.450701, 126.570667);
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
        const lat = position.coords.latitude;
        const lon = position.coords.longitude;
        myCenter = new kakao.maps.LatLng(lat, lon);
        new kakao.maps.Marker({
            map,
            position: myCenter,
        });
        map.setCenter(myCenter);
    });
}

// positions 배열에 받아온 attractionList를 저장
var positions = attractiontElement.attractionList.map((attraction) => ({
    title: attraction.title,
    latlng: new kakao.maps.LatLng(attraction.latitude, attraction.longitude),
}));

// // 마커 이미지의 이미지 주소입니다
var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

const displayMarkers = function () {
    for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        let imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        let marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
        });
    }
};
</script>

<template>
    <div>
        <div id="map"></div>
    </div>
</template>

<style scoped>
#map {
    width: 500px;
    height: 400px;
}
</style>
