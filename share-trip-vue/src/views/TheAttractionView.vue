<script setup>
import KakaoMap from "../components/kakao/KakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";
import { ref, onMounted } from "vue";
import axios from "axios";

var selectedSido = ref("");
var selectedGugun = ref();
var searchTitle = ref("");

const sidoList = ref([]);
const gugunList = ref([{ text: "구군선택", value: "" }]);
const contentTypeId = ref([]);

var attractionList = ref([]);

onMounted(() => {
    listSido();
});

const listSido = () => {
    //시 정보를 가져오는 리스트
    axios
        .get("http://192.168.31.55:80/sharetrip/map/sido")
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
        .get("http://192.168.31.55:80/sharetrip/map/gugun", { params: param })
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
    let url = new URL("http://192.168.31.55:80/sharetrip/map/attr");
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
</script>

<template>
    <div>
        <AttractionDetail></AttractionDetail>
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

        <KakaoMap :attractionList="attractionList"></KakaoMap>

        <!-- 검색한 관광지 리스트 보여주기 -->
        <div v-for="element in attractionList" :key="element.title">
            <img :src="element.firstImage" style="width: 200px; height: 200px" />
            <h3>{{ element.title }}</h3>
            <p>{{ element.addr1 }}</p>
            <p>{{ element.addr2 }}</p>
        </div>
    </div>
</template>

<style scoped></style>
