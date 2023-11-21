<script setup>
import { UseAttractionStore } from '@/stores/Attraction';
import axios from 'axios';
import { ref,computed } from "vue"

const REST_ATTRACTION_API = `http://localhost:80/sharetrip/map/attr/`
const REST_MEMO_API = `http://localhost:80/sharetrip/memo/attr/`

// 댓글 읽어오기
const memos = ref([])
const props = defineProps({
    contentId:String,
})
const contentId = computed(() => props.contentId)

axios({
    url: REST_ATTRACTION_API+contentId.value,
})
.then((response)=>{
    memos.value = response.data.data.memos
    console.log("mem",response.data.data.memos)
})
.catch(()=>{})

const memo = ref({
    contentId: contentId.value,
    id: 0,
    userId: "ryu",
    content: "",
    registTime: "",
});

// 댓글 작성 기능
const registMemo = function() {
    console.log(contentId+"글의 댓글 작성", contentId.value);
    axios.post(
        REST_MEMO_API+contentId.value,
        memo.value)
    .then((response) => {
        console.log("성공", response)
        memos.value.unshift(response.data.data)
        memo.value.content = "";
    })
    .catch(()=>{})
}

// 댓글 삭제 기능
const delMemo = (id, index) => {
    console.log(contentId+"글의 댓글 삭제", id);
    axios.delete(
        REST_MEMO_API+id
    )
    .then((response) => {
        console.log("성공",response)
        memos.value.splice(index,1);
        
    })
    .catch(()=>{})
}

</script>

<template>
    <hr>
    <h4>댓글 ({{ memos.length }})</h4>
        <input type="text" v-model="memo.content" @keyup.enter="registMemo">
        <button @click="registMemo">작성</button>
    <table>
        <div v-for="(memo, index) in memos" :key="memo.id">
            <tr>
                <td>{{ memo.userId }}</td>
                <td>{{ memo.registTime }}</td>
                <td>{{ memo.content }}</td>
                <td><button @click="delMemo(memo.id, index)" v-if="memo.userId === 'ryu'">x</button></td>
            </tr>
        </div>
    </table>
</template>

<style scoped>

</style>