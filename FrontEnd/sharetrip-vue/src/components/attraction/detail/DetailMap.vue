<script setup>
import { onMounted, watch } from "vue";
const props = defineProps({
	mapLat: Number,
	mapLng: Number,
});

var map;

onMounted(() => {
	if (window.kakao && window.kakao.maps) {
		initMap();
	} else {
		const script = document.createElement("script");
		script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
			//autoload= false 스크립트를 동적으로 로드하기 위해 필요
			import.meta.env.VITE_KAKAO_API_SERVICE_KEY
		}`;
		/* global kakao */
		script.addEventListener("load", () => {
			kakao.maps.load(initMap);
		}); //헤드태그에 추가
		document.head.appendChild(script);
	}
});

const initMap = () => {
	const container = document.getElementById("detailmap");
	const options = {
		//지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(props.mapLat, props.mapLng), //지도의 중심좌표. (33.450701, 126.570667)
		// draggable: false, // 지도 이동 막기 -> build up: 탭 이동에 따라 실시간 위치 다시 받아오기 -> 이동 가능으로 변경
		level: 7, //지도의 레벨(확대, 축소 정도)
	}; //지도 객체를 등록합니다.
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	// //현재 관광지 위치 기준으로 마커띄우기
	let myCenter = new kakao.maps.LatLng(props.mapLat, props.mapLng);
	new kakao.maps.Marker({
		map,
		position: myCenter,
	});
	map.setCenter(myCenter);

	map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
};

function setMapType(maptype) {
	if (maptype === "roadmap") {
		map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
	} else if (maptype === "skyview") {
		map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
	} else if (maptype === "traffic") {
		map.setMapTypeId(kakao.maps.MapTypeId.TRAFFIC);
	}
}

const refresh = () => {
	map.relayout();
};
</script>

<template>
	<v-row>
		<v-col>
			<div id="detailmap" style="margin: 0 auto"></div>
		</v-col>
	</v-row>
	<v-row>
		<v-col align="center">
			<v-btn @click="refresh">지도 새로고침</v-btn>
			<v-btn @click="setMapType('roadmap')">지도</v-btn>
			<v-btn @click="setMapType('skyview')">스카이뷰</v-btn></v-col
		>
	</v-row>
</template>

<style scope>
#detailmap {
	width: 500px;
	height: 400px;
}
</style>
