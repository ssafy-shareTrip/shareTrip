<script setup>
import axios from 'axios';
import {ref} from 'vue'

const base = `http://localhost:80/sharetrip/board`;
const boardList = ref([]);

// 글 목록 읽어오기
axios
.get(
    base,
)
.then(({data}) => {
    boardList.value = data.data;
    console.log(data.data)
})
.catch(()=>{})
</script>

<template>
    <div v-for="(item, idx) in boardList" :key="item.boardNo">
        <h3>{{ item.subject }}</h3>
        <span>조회수 : {{ item.hit }}</span>
        <p>경로</p>
        <p>{{ item.content }}</p>
        <span>
            <img :src="`public/icon/like_0.png`" width="30">
            개수
        </span>
        <span>
            <img :src="`public/icon/star_0.png`" width="30">
            개수
        </span>
        <span>
            <img :src="`public/icon/comment.png`" width="30">
            개수
        </span>
        <span>
            이미지?
        </span>
        <hr/>
    </div>
</template>

<style scoped>

</style>