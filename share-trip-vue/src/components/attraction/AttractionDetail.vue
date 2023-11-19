<script setup>
import { ref } from 'vue'
import DetailOutline from './detail/DetailOutline.vue'
import DetailInfo from './detail/DetailInfo.vue'
import DetailMap from './detail/DetailMap.vue'
import DetailWeather from './detail/DetailWeather.vue'

const outline = ref(true);
const info = ref(false);
const map = ref(false);
const weather = ref(false);

const outlineShow = () => {
    outline.value = true;
    info.value = false;
    map.value = false;
    weather.value = false;

}
const infoShow = () => {
    outline.value = false;
    info.value = true;
    map.value = false;
    weather.value = false;
}
const mapShow = () => {
    outline.value = false;
    info.value = false;
    map.value = true;
    weather.value = false;
}
const weatherShow = () => {
    outline.value = false;
    info.value = false;
    map.value = false;
    weather.value = true;
}

</script>

<template>
    <main>
        <div class="attraction-info">
            <div>
                <span>
                    관광지 이름
                </span>
                <span>하트</span>
                <span>별</span>
            </div>
            <div>
                <span>
                    <img>img
                </span>
                <span>
                    관광지 주소
                </span>
            </div>
        </div>
        <div class="menu-tab">
            <div>
                <span @click="outlineShow">개요</span>
                <span @click="infoShow">설명</span>
                <span @click="mapShow">지도</span>
                <span @click="weatherShow">날씨</span>
            </div>
            <hr>
            <div>
                <DetailOutline v-show="outline"></DetailOutline>
                <DetailInfo v-show="info"></DetailInfo>
                <DetailMap v-show="map"></DetailMap>
                <DetailWeather v-show="weather"></DetailWeather>
            </div>
        </div>
    </main>
</template>

<style scoped>

</style>