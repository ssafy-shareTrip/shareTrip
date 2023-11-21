import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_ATTRACTION_API = `http://localhost:80/sharetrip/`

export const UseAttractionStore = defineStore('attraction', () => {
    const detail = ref({
        memos:[]
    });
    const favs = ref({

    });

    // 관광지 상세 정보 조회
    const getDetail = (idx) => {
        axios({
            url: REST_ATTRACTION_API+'map/attr/'+idx,
        })
        .then((response)=>{
            detail.value = response.data.data
        })
        .catch(()=>{})
    }

    // 사용자의 관광지 좋아요 또는 북마크 정보 조회
    const getFav = (userId) => {
        axios
        .get(REST_ATTRACTION_API+'/fav/attr/'+userId)
        .then((response) => {
            favs.value = response.data.data
            console.log("FAV :", favs.value)
        })
        .catch(() => {})
    }

    return {getDetail, detail, getFav, favs}
})