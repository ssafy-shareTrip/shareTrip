<script setup>
import {useRouter, useRoute} from 'vue-router'
import RouteBoardList from '@/components/board/RouteBoardList.vue'

const router = useRouter();

const mvWrite = () => {
    router.push({
        name: 'routeReg',
    })
}

</script>

<template>
    <div>
        <h3>경로 게시판</h3>
        <div>
            <input v-model="searchTitle" />
            <button @click="search()">검색</button>
        </div>
        <hr/>
            <RouteBoardList></RouteBoardList>
        <img @click="mvWrite" src="/public/icon/write.png" width="30">
    </div>
</template>

<style scoped>

</style>