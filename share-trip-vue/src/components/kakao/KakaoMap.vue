<script setup>

import { onMounted } from 'vue';

let map= null;
const initMap= function() {
    const container= document.getElementById('map');
    const options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
}; //지도 객체 등록
map= new kakao.maps.Map(container, options);
};



onMounted(() => {
    if (window.kakao && window.kakao.maps) {
      initMap();
    } else {
      const script = document.createElement("script");
      script.src =
        `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${ //autoload= false 스크립트를 동적으로 로드하기 위해 필요
            import.meta.env.VITE_KAKAO_API_KEY
        }`;
        /* global kakao */
        script.addEventListener('load', ()=>{
            kakao.maps.load(initMap);
        }); //헤드태그에 추가 
      document.head.appendChild(script);
    }
  });
  


</script>

<template>
    <div>
        <div id="map"></div>
    </div>
</template>

<style scoped>
#map{
    width:500px;
    height:400px;
}
</style>