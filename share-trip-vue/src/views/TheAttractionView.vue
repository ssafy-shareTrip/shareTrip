<script setup>
import KakaoMap from "../components/kakao/KakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";
import { ref, onMounted, computed } from "vue";
import axios from "axios";
import AttractionDetail from "../components/attraction/AttractionDetail.vue";
import { useRoute, useRouter } from 'vue-router'
import { UseAttractionStore } from '@/stores/Attraction';
var selectedSido = ref("");
var selectedGugun = ref();
var searchTitle = ref("");

const store = UseAttractionStore();
const router = useRouter();
const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const contentTypeId = ref([]);

var attractionList = ref([]);

const selectAttractionElement= ref([]);

onMounted(() => {
    listSido();
});

const listSido = () => {
    //시 정보를 가져오는 리스트
    axios

        // .get("http://192.168.31.55:80/sharetrip/map/sido")
        .get("http://localhost:80/sharetrip/map/sido")
        .then(function (data) {
            data = data.data.data;
            console.log(data);

            let options = [];
            options.push({ text: "지역", value: "" });
            options.push({ text: "전체", value: "0" });
            data.forEach((sido) => {
                options.push({ text: sido.sidoName, value: sido.sidoCode });
            });
            sidoList.value = options;
        })
        .catch(function (error) {
            console.log("시 가져오기 실패");
        });
};

const listGugun = (param) => {
    //군 정보를 가져오는 리스트
    axios
        //.get("http://192.168.31.55:80/sharetrip/map/gugun", { params: param })
        .get("http://localhost:80/sharetrip/map/gugun", { params: param })
        .then(function (data) {
            data = data.data.data;
            console.log(data);

            let options = [];
            options.push({ text: "구군선택", value: "" });
            data.forEach((gugun) => {
                options.push({ text: gugun.gugunName, value: gugun.gugunCode });
            });
            gugunList.value = options;
        })
        .catch(function (err) {
            console.log("군 가져오기 실패");
        });
};

const onChangeSido = (key) => {
    //시가 바뀌었을 때 호출됨
    selectedSido.value = key;
    listGugun({ sido: key });
};

const onChangeGugun = (key) => {
    //시가 바뀌었을 때 호출됨
    selectedGugun.value = key;
};

const search = () => {
    //let url = new URL("http://192.168.31.55:80/sharetrip/map/attr");
    let url = new URL("http://localhost:80/sharetrip/map/attr");

    const params = new URLSearchParams();

    if (selectedSido.value !== "" && selectedSido.value != 0) {
        params.append("sido", selectedSido.value);

        if (selectedGugun.value !== "") {
            params.append("gugun", selectedGugun.value);
        }
    }

    if (contentTypeId.value.length > 0) {
        contentTypeId.value.forEach((id) => {
            params.append("contentTypeId", id);
        });
    }

    if (searchTitle.value !== "") {
        params.append("keyword", searchTitle.value);
    }

    url.search = params.toString();


    
    /* 사용자 별 좋아요, 북마크는 userId 보내줘야 함.
        if문 // 로그인 아이디가 있다면 아이디 담기
        */
    const userId = 'jeon'
    url = "http://localhost:80/sharetrip/map/attr?userId="+userId;
    axios
        .get(url.toString())
        .then(function (data) {
            data = data.data.data;
            console.log(data);
            //검색 성공했으면 다시 마커를 띄워줘야 함
            attractionList.value = data;
        })
        .catch(function (error) {
            console.log("검색 실패");
        });

};


const clickSelectAttraction= (selectAttraction)=>{
    console.log("select 완료")
    console.log(selectAttraction)
    selectAttractionElement.value= selectAttraction;
}

const mvDet = (contentId, isLike, isBookmark) => {
    router.push({
        name: 'attrDet',
        params: { 
            idx: contentId,
            isLike:isLike,
            isBookmark:isBookmark
        }
    })
}

const favReg = (category, contentId, status, index) => {
    console.log(contentId, category, status, index);

    const url = "http://localhost:80/sharetrip/fav/attr/jeon";
    if (status == 0){ // 미등록 -> 등록
        axios
            .post("http://localhost:80/sharetrip/fav/attr/jeon",
            {
                "contentId":contentId,
                "category":category
            })
            .then(({data})=>{
                console.log("favReg : 성공");
                if (category == 0){ // isLike
                    attractionList.value[index].isLike = 1
                } else { //isBookmark
                    attractionList.value[index].isBookmark = 1
                }
            })
            .catch(()=>{
                console.log("favReg 등록 : 실패");
            })
    } else { //등록 -> 미등록
        axios
            .delete(url,
                {params : {
                    "contentId":contentId,
                    "category":category
                }
            })
            .then(({data})=>{
                console.log("favDel : 성공");
                if (category == 0){ // isLike
                    attractionList.value[index].isLike = 0
                } else { //isBookmark
                    attractionList.value[index].isBookmark = 0
                }
            })
            .catch(()=>{
                console.log("favDel : 실패");
            })
    }
}

</script>

<template>
    <div>
        관광지 페이지
        <h2>관광지 조회</h2>

        <div>
            <VSelect :selectOption="sidoList" @onKeySelect="onChangeSido" />

            <div>
                <VSelect :selectOption="gugunList" @onKeySelect="onChangeGugun" />
            </div>

            <input v-model="searchTitle" />
            <button @click="search()">검색</button>
        </div>
        <input type="checkbox" id="contentId12" value="12" v-model="contentTypeId" />
        <label for="contentId12">관광지</label>
        <input type="checkbox" id="contentId14" value="14" v-model="contentTypeId" />
        <label for="contentId14">문화시설</label>
        <input type="checkbox" id="contentId15" value="15" v-model="contentTypeId" />
        <label for="contentId15">축제공연행사</label>
        <input type="checkbox" id="contentId25" value="25" v-model="contentTypeId" />
        <label for="contentId25">여행코스</label>
        <input type="checkbox" id="contentId28" value="28" v-model="contentTypeId" />
        <label for="contentId28">레포츠</label>
        <input type="checkbox" id="contentId32" value="32" v-model="contentTypeId" />
        <label for="contentId32">숙박</label>
        <input type="checkbox" id="contentId38" value="38" v-model="contentTypeId" />
        <label for="contentId38">쇼핑</label>
        <input type="checkbox" id="contentId39" value="39" v-model="contentTypeId" />
        <label for="contentId39">음식점</label>

        <KakaoMap :attractionList="attractionList" :selectAttractionElement="selectAttractionElement"></KakaoMap>

        <!-- 검색한 관광지 리스트 보여주기 -->
        <div v-for="(element,index) in attractionList" 
        :key="element.title"
        @click="clickSelectAttraction(element)">

            <span @click="mvDet(element.contentId, element.isLike, element.isBookmark)">
                <img :src="element.firstImage" style="width: 200px; height: 200px" />
                <h3>{{ element.title }}</h3>
                <p>{{ element.addr1 }}</p>
                <p>{{ element.addr2 }}</p>
            </span>
            <img :src="`/public/icon/like_${element.isLike}.png`" width="25" @click="favReg(0, element.contentId, element.isLike, index)">
            <img :src="`/public/icon/star_${element.isBookmark}.png`" width="25" @click="favReg(1, element.contentId, element.isBookmark, index)">
        </div>
    </div>
</template>

<style scoped></style>
