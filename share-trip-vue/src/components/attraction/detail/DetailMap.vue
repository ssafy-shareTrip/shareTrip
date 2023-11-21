<script setup>
import { onMounted, watch } from 'vue';
import { UseAttractionStore } from '@/stores/Attraction'; 

const store = UseAttractionStore();
const idx = 125266;
const props = defineProps({
    mapLat : Number,
    mapLng : Number,
})
const mapLat = store.detail.latitude;
const mapLng = store.detail.longitude;
var map;
const initMap = () =>{
    console.log("init")
    console.log(props.mapLat)
    console.log(props.mapLng)
    
    const options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표. (33.450701, 126.570667)
        draggable: false, // 지도 이동 막기 -> build up: 탭 이동에 따라 실시간 위치 다시 받아오기 -> 이동 가능으로 변경
        level: 3 //지도의 레벨(확대, 축소 정도)
    }; //지도 객체를 등록합니다.
    const container = document.getElementById('map');
    map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
    setTimeout(f1, 3500);
}

onMounted(() => {
    if (window.kakao && window.kakao.maps) {
        initMap();
    } else {
        const script = document.createElement('script'); 
        //autoload=false 스크립트를 동적으로 로드하기 위해서 사용
        script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
            import.meta.env.VITE_KAKAO_API_SERVICE_KEY
        }`; 
        /* global kakao */ 
        script.onload = () => kakao.maps.load(() => initMap());
        document.head.appendChild(script); //헤드태그에 추가
    }
});

function f1(){
    map.relayout();
}

</script>

<template>
    <div>
        <div id="map" ></div><button @click="f1">f1</button>
    </div>
</template>

<style scope>
    #map {
        width:500px;
        height:400px;
    }
</style>