<script setup>
import { RouterView, useRoute, useRouter } from 'vue-router'
import { onMounted, provide } from 'vue';
import { ref } from 'vue'
import { UseAttractionStore } from '@/stores/Attraction';
import DetailInfo from '@/components/attraction/detail/DetailInfo.vue'
import DetailMap from '@/components/attraction/detail/DetailMap.vue'
import DetailWeather from '@/components/attraction/detail/DetailWeather.vue'
import DetailMemo from '@/components/attraction/detail/DetailMemo.vue';

const route = useRoute()
const router = useRouter()
const store = UseAttractionStore();

const idx = route.params.idx; //125266
const outline = ref(true);
const info = ref(false);
const map = ref(false);
const weather = ref(false);

onMounted(() => {
    console.log(idx);
    store.getDetail(idx);
    // async function getInfo() {
    //     const detail = await store.detail;
    //     await console.log(store.detail)
    // }
});


const overView = (e) => {
    router.push({ 
        name:'detInfo', 
        params:{
            idx:'idx'}
    })
}

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
                    {{ store.detail.title }}
                </span>
                <span>하트</span>
                <span>별</span>
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
                    <DetailMap v-show="map" :map-lat="store.detail.latitude" :map-long="store.detail.longitude"></DetailMap>
                    <DetailWeather v-show="weather" :map-lat="store.detail.latitude" :map-long="store.detail.longitude"></DetailWeather>
                </div>
            </div>
            <div>
                <DetailMemo :det-idx="idx"></DetailMemo>
            </div>
        </main>
</template>

<style scoped>

</style>