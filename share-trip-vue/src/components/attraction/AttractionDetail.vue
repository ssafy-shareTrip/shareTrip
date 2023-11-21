<script setup>
import { RouterView, useRoute, useRouter } from 'vue-router'
import { onMounted, provide } from 'vue';
import { ref } from 'vue'
import { UseAttractionStore } from '@/stores/Attraction';
import DetailInfo from '@/components/attraction/detail/DetailInfo.vue'
import DetailMap from '@/components/attraction/detail/DetailMap.vue'
import DetailWeather from '@/components/attraction/detail/DetailWeather.vue'
import DetailMemo from '@/components/attraction/detail/DetailMemo.vue';
import axios from 'axios';

const route = useRoute()
const router = useRouter()
const store = UseAttractionStore();

const idx = route.params.idx; //125266
const userId = "jeon"
const info = ref(true);
const map = ref(false);
const weather = ref(false);

onMounted(() => {
    console.log(idx);
    store.getDetail(idx);
    store.getFav(userId);
});


const infoShow = () => {
    info.value = true;
    map.value = false;
    weather.value = false;
}
const mapShow = () => {
    info.value = false;
    map.value = true;
    weather.value = false;
}
const weatherShow = () => {
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
                    {{ store.detail.title }}
                </span>
                <span><img src="/public/icon/like_none.png" width="30"></span>
                <span><img src="/public/icon/like_push.png" width="30"></span>
                <span><img src="/public/icon/star_none.png" width="30"></span>
                <span><img src="/public/icon/star_push.png" width="30"></span>
            </div>
            <div>
                <span>
                    <img :src="`${store.detail.firstImage}`">
                </span>
                <span>
                    {{ store.detail.addr1 }}
                    {{ store.detail.addr2 }}
                </span>
            </div>
                <!-- <div>
                    
                    <button @click="overView">설명</button>
                    <RouterLink :to="{name:'detInfo'}">설명</RouterLink>
                    <RouterLink :to="{name:'detMap'}" :map-lat="store.detail.latitude" :map-long="store.detail.longitude">지도</RouterLink>
                    <RouterLink :to="{name:'detWeather'}" :map-lat="store.detail.latitude" :map-long="store.detail.longitude">날씨</RouterLink>
                    <hr>
                    <RouterView></RouterView>
                </div> -->
                <div>
                    <span @click="infoShow">설명</span>
                    <span @click="mapShow">지도</span>
                    <span @click="weatherShow">날씨</span>
                </div>
                <hr>
                <div>
                    <DetailInfo v-show="info" :over-view="store.detail.overview"></DetailInfo>
                    <DetailMap v-show="map" :map-lat="store.detail.latitude" :map-lng="store.detail.longitude"></DetailMap>
                    <DetailWeather v-show="weather" :map-lat="store.detail.latitude" :map-lng="store.detail.longitude"></DetailWeather>
                </div>
            </div>
            <div>
                <DetailMemo></DetailMemo>
            </div>
        </main>
</template>

<style scoped>

</style>