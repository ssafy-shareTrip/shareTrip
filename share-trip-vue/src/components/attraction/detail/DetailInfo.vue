<script setup>
import { UseAttractionStore } from '@/stores/Attraction';
const store = UseAttractionStore();

</script>

<template>
    <div>
        {{ store.detail.overview }}
    </div>
</template>

<style scoped>

</style>