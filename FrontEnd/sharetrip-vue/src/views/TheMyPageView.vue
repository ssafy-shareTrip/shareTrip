<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import MyInfo from "@/components/MyInfo.vue";
import BookmarkAttr from "@/components/BookmarkAttr.vue";

const store = useUserStore();
const axios = localAxios();
const tab = ref(1);
const userInfo = ref({});

onMounted(() => {
	axios
		.get(`/user/info/${store.userId}`)
		.then(({ data }) => {
			userInfo.value = data.data;
		})
		.catch((err) => {
			console.log(err);
		});
});
</script>

<template>
	<v-container style="width: 80%; margin-top: 100px; background-color: aliceblue">
		<v-row>
			<v-tabs
				v-model="tab"
				color="blue lighten-4"
				density="comfortable"
				style="margin: 0 auto"
				next-icon="mdi-chevron-right"
				prev-icon="mdi-chevron-left"
				show-arrows
			>
				<v-tab :value="1">내 정보</v-tab>
				<v-tab :value="2">즐겨찾기한 관광지</v-tab>
				<v-tab :value="3">즐겨찾기한 경로</v-tab>
				<v-tab :value="4">팔로잉 목록</v-tab>
				<v-tab :value="5">팔로워 목록</v-tab>
			</v-tabs>
		</v-row>
		<v-row>
			<v-window v-model="tab" style="width: 100%">
				<v-window-item :value="1">
					<my-info :user-info="userInfo"></my-info>
				</v-window-item>
				<v-window-item :value="2">
					<bookmark-attr :user-info="userInfo"></bookmark-attr>
				</v-window-item>
				<v-window-item :value="3">
					<my-info :user-info="userInfo"></my-info>
				</v-window-item>

				<v-window-item v-for="n in 7" :key="n" :value="n">
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
				</v-window-item>
			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped></style>
