<script setup>
import { ref, onMounted,computed } from 'vue';
import axios from 'axios';
import { UseAttractionStore } from '@/stores/Attraction';
const store = UseAttractionStore();

const props = defineProps({
    mapLat: Number,
    mapLng: Number,
})

const tmp = ref();
const sky = ref();
const pty = ref();
const pop = ref();
const lat = computed(() => props.mapLat)
const lng = computed(() => props.mapLng)

onMounted(() => {
    initWeather();
    
});

const initWeather = () => {
    const API_URL = import.meta.env.VITE_WEATHER_API_URL

    const today = new Date();
    let year = today.getFullYear();
    let month = today.getMonth() + 1;
    let day = today.getDate();
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day;
    const todayStr = `${year}${month}${day}`;
    console.log(todayStr);
    console.log(lat);
    //발표시간을 전부 넣어둬
    const times = ['0200', '0500']
    axios
        .get(API_URL, {
            params: {
                ServiceKey: import.meta.env.VITE_WEATHER_SERVICE_KEY,
                dataType: "JSON",
                base_date: todayStr, //20231105 형태
                base_time: "0200",   //이것은 총 8회 발표
                numOfRows: 15,
                nx: lat, //역삼위치
                ny: lng,
            },
        })
        .then((response) => {
            return response.data.response.body.items.item;
        })
        .then((response) => {
            //TMP : 1시간 기온 (섭씨)
            //UUU : 풍속(동서) m/s
            //VVV : 풍속(남북) m/s
            //VEC : 풍향 deg
            //WSD : 풍속 m/s
            //SKY : 하늘상태 --> 코드 : 맑음(1), 구름많음(3), 흐림(4)
            //PTY : 강수형태 --> 코드 : 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
            //POP : 강수확률 %
            //MAV : 파고 M
            //PCP : 1시간 강수량 범주(1mm)
            //REH : 습도 %
            //SNO : 1시간 적설량
            //TMN : 일 최저기온 
            //TMX : 일 최고기온
            response.forEach((item) => {
                if (item.category === "TMP"){
                    tmp.value = item.fcstValue;
                } else if (item.category === "SKY"){
                    switch(item.fcstValue){
                        case "1":
                            sky.value = "맑음";
                            break;
                        case "3":
                            sky.value = "구름많음";
                            break;
                        case "4":
                            sky.value = "흐림";
                            break;
                    }
                } else if (item.category == "PTY") {
                    pty.value = item.fcstValue;
                } else if (item.category == "POP") {
                    pop.value = item.fcstValue;
                }
            });
        });
}
</script>

<template>
    <div>
        <h4>날씨정보</h4>
        {{ lat }}, {{ lng }}
        <div>기온 : {{ tmp }}</div>
        <div>하늘상태 : {{ sky }}</div>
        <div>강수형태 : {{ pty }}</div>
        <div>강수확률 : {{ pop }}%</div>
    </div>
</template>

<style scoped>

</style>