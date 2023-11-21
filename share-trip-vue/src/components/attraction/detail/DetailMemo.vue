<script setup>
import { UseAttractionStore } from '@/stores/Attraction';
import axios from 'axios';
import { ref,watch } from "vue"

const REST_ATTRACTION_API = `http://localhost:80/sharetrip/map/attr/`
const REST_MEMO_API = `http://localhost:80/sharetrip/memo/attr/`
const store = UseAttractionStore();
// 댓글 읽어오기
const memos = ref([])

axios({
    url: REST_ATTRACTION_API+"125266",
})
.then((response)=>{
    memos.value = response.data.data.memos
    console.log(memos.value)
})
.catch(()=>{})

const memo = ref({
    detailId: store.detail.contentId,
    id: 0,
    userId: "ryu",
    content: "",
});

// 댓글 작성 기능
const registMemo = function() {
    const contentId = store.detail.contentId;
    console.log("댓글 작성", contentId);
    axios.post(
        REST_MEMO_API+contentId,
        memo.value)
    .then((response) => {
        console.log("성공", response)
        memos.value.unshift(response.data.data)
    })
    .catch(()=>{})
}

// 댓글 삭제 기능
const delMemo = (id) => {
    const contentId = store.detail.contentId;
    console.log("댓글 삭제", id);
    axios.delete(
        REST_MEMO_API+id
    )
    .then((response) => {
        console.log("성공",response)
        // initRead();
    })
    .catch(()=>{})
}

</script>

<template>
    <hr>
    {{ memos }}
    <h4>댓글 ({{ memos.length }})</h4>
        <input type="text" v-model="memo.content">
        <button @click="registMemo">작성</button>
    <table>
        <div v-for="(memo) in memos" :key="memo.id">
            <tr>
                <td>{{ memo.userId }}</td>
                <td>{{ memo.registTime }}</td>
                <td>{{ memo.content }}</td>
                <td><button @click="delMemo(memo.id)" v-if="memo.userId === 'ryu'">x</button></td>
            </tr>
        </div>
    </table>
</template>

<style scoped>

</style>