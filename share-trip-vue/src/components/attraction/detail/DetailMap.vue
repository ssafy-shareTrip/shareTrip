<script setup>
import { onMounted } from 'vue';

const latitude = 33.450701;
const longitude = 126.570667;
let map = null;
const initMap = () =>{
    const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    const options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(`${latitude}`,`${longitude}`), //지도의 중심좌표. (33.450701, 126.570667)
        level: 3 //지도의 레벨(확대, 축소 정도)
    }; //지도 객체를 등록합니다.
    map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
        getLocation();
    } else {
        const script = document.createElement('script'); 
        //autoload=false 스크립트를 동적으로 로드하기 위해서 사용
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
            import.meta.env.VITE_KAKAO_API_SERVICE_KEY
        }`;
        script.addEventListener('load', () => {
            kakao.maps.load(initMap);
        });
        document.head.appendChild(script); //헤드태그에 추가
    }
});

const getLocation = () => {
    console.log(param.value);
}
</script>

<template>
    <div>
        <div id="map"></div>
    </div>
</template>

<style scoped>
    #map {
        width:500px;
        height:400px;
    }
</style>