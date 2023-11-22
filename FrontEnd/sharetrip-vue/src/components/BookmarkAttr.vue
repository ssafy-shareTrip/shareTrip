<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
defineProps({
	userInfo: Object,
});
</script>

<template>
	<v-container fluid>
		<v-row align="center">
			<v-col v-for="i in 7" :key="i" cols="12" md="4">
				<v-img
					:src="`https://picsum.photos/500/300?image=${i * n * 5 + 10}`"
					:lazy-src="`https://picsum.photos/10/6?image=${i * n * 5 + 10}`"
					aspect-ratio="1"
				></v-img>
			</v-col>
		</v-row>
	</v-container>
</template>

<style scoped></style>
