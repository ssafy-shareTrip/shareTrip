<script setup>
import {useRoute, useRouter} from 'vue-router';
const router = useRouter();

const back = () => {
    router.go(-1)
}
</script>

<template>
    <div>
        <img @click="back" src="public/icon/back_arrow.png" width="30">
        <h3>경로 게시글 작성</h3>
        
    </div>
</template>

<style scoped>

</style>