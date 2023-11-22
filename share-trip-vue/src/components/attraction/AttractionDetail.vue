<script setup>
import { RouterView, useRoute, useRouter } from 'vue-router'
import { onMounted,ref,computed  } from 'vue';
import { UseAttractionStore } from '@/stores/Attraction';
import DetailInfo from '@/components/attraction/detail/DetailInfo.vue'
import DetailMap from '@/components/attraction/detail/DetailMap.vue'
import DetailWeather from '@/components/attraction/detail/DetailWeather.vue'
import DetailMemo from '@/components/attraction/detail/DetailMemo.vue';
import KakaoMap from '../kakao/KakaoMap.vue';
import axios from 'axios';

const route = useRoute();
const router = useRouter();
const store = UseAttractionStore();

const idx = route.params.idx; //125266
const userId = "jeon"
const infoItem = ref(true);
const mapItem = ref(false);
const weatherItem = ref(false);
const isLike = ref(route.params.isLike);
const isBookmark = ref(route.params.isBookmark);

onMounted(() => {
    console.log(idx);
    store.getDetail(idx);
});

const infoShow = () => {
    infoItem.value = true;
    mapItem.value = false;
    weatherItem.value = false;
}
const mapShow = () => {
    infoItem.value = false;
    mapItem.value = true;
    weatherItem.value = false;
}
const weatherShow = () => {
    infoItem.value = false;
    mapItem.value = false;
    weatherItem.value = true;
}

const favReg = (category, status) => {
    console.log(category, status);

    const url = "http://localhost:80/sharetrip/fav/attr/jeon";
    if (status == '0'){ // 미등록 -> 등록
        axios
            .post("http://localhost:80/sharetrip/fav/attr/jeon",
            {
                "contentId":idx,
                "category":category
            })
            .then(({data})=>{
                console.log("favReg : 성공");
                if (category == 0){ // isLike
                    isLike.value = '1'
                } else { //isBookmark
                    isBookmark.value = '1'
                }
            })
            .catch(()=>{
                console.log("favReg 등록 : 실패");
            })
    } else { //등록 -> 미등록
        axios
            .delete(url,
                {params : {
                    "contentId":idx,
                    "category":category
                }
            })
            .then(({data})=>{
                console.log("favDel : 성공");
                if (category == 0){ // isLike
                    isLike.value = '0'
                } else { //isBookmark
                    isBookmark.value = '0'
                }
            })
            .catch(()=>{
                console.log("favDel : 실패");
            })
    }
}
</script>

<template>
    <main>
        <div class="attraction-info">
            <div>
                <span>
                    {{ store.detail.title }}
                </span>
                <span>
                    <img :src="`/public/icon/like_${isLike}.png`" width="25" @click="favReg(0, isLike)">
                    <img :src="`/public/icon/star_${isBookmark}.png`" width="25" @click="favReg(1, isBookmark)">
                </span>
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
                    <!-- <span @click="weatherShow">날씨</span> -->
                </div>
                <hr>
                <div>
                    <DetailInfo v-show="infoItem" :over-view="store.detail.overview"></DetailInfo>
                    <!-- <KakaoMap v-show="mapItem" :attractionList="attractionList"></KakaoMap> -->
                    <DetailMap v-if="mapItem" :map-lat="store.detail.latitude" :map-lng="store.detail.longitude"></DetailMap>
                    <!-- <DetailWeather v-show="weatherItem" :map-lat="store.detail.latitude" :map-lng="store.detail.longitude"></DetailWeather> -->
                </div>
            </div>
            <div>
                <DetailMemo :content-id="idx"></DetailMemo>
            </div>
        </main>
</template>

<style scoped>

</style>