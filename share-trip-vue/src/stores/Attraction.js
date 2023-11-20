import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_ATTRACTION_API = `http://localhost:80/sharetrip/map/attr/`

export const UseAttractionStore = defineStore('attraction', () => {
    const detail = ref({});

    const getDetail = (idx) => {
        axios({
            url: REST_ATTRACTION_API+idx,
        })
        .then((response)=>{
            console.log(response)
            detail.value = response.data.data
            console.log(response.data)
        })
        .catch(()=>{})
    }

    return {getDetail, detail}
})