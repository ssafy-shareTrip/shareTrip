import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_ATTRACTION_API = `http://localhost:80/sharetrip/`

export const UseAttractionStore = defineStore('attraction', () => {
    const detail = ref({
        memos:[]
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

    return {getDetail, detail}
})