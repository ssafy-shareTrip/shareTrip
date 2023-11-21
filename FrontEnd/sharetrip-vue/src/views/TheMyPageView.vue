<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
const router = useRouter();
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
				next-icon="mdi-arrow-right-bold-box-outline"
				prev-icon="mdi-arrow-left-bold-box-outline"
				show-arrows
			>
				<v-tab :value="1">내 정보</v-tab>
				<v-tab :value="2">즐겨찾기한 관광지</v-tab>
				<v-tab :value="3">즐겨찾기한 경로</v-tab>

				<v-tab :value="4">작성 글</v-tab>
				<v-tab :value="5">작성 댓글</v-tab>
			</v-tabs>
		</v-row>
		<v-row>
			<v-window v-model="tab" style="width: 100%">
				<v-window-item :value="1">
					<v-container>
						<v-row align="center">
							<v-col cols="4"
								><v-img
									class="bg-white"
									width="100%"
									:aspect-ratio="1"
									src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
									style="border-radius: 100%"
									cover
								></v-img
							></v-col>
							<v-col>
								<v-list lines="two" class="rounded-lg" elevation="13">
									<v-list-item>이름 : {{ userInfo.name }}</v-list-item>
									<v-divider :thickness="5" color="info"></v-divider>
									<v-list-item
										>생년월일 :
										<template v-if="userInfo.birth == null">미공개</template>
										<template v-else>{{ userInfo.brith }}</template>
									</v-list-item>
									<v-divider :thickness="5" color="info"></v-divider>
									<v-list-item>
										<v-row>
											<v-col> 팔로우 : {{ userInfo.follower }} </v-col
											><v-col>팔로워 : {{ userInfo.following }}</v-col>
										</v-row>
									</v-list-item>
								</v-list>
							</v-col>
						</v-row>
					</v-container>
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

<!-- <v-row style="margin-bottom: 30px" align="center">
	<v-col cols="4" color="orange-lighten-4"
		><v-img
			class="bg-white"
			width="100%"
			:aspect-ratio="1"
			src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
			style="border-radius: 100%"
			cover
		></v-img
	></v-col>
	<v-col>
		<v-list lines="two" class="rounded-lg" elevation="13">
			<v-list-item>이름 : {{ userInfo.name }}</v-list-item>
			<v-divider :thickness="5" color="info"></v-divider>
			<v-list-item
				>생년월일 :
				<template v-if="userInfo.birth == null">미공개</template>
				<template v-else>{{ userInfo.brith }}</template>
			</v-list-item>
			<v-divider :thickness="5" color="info"></v-divider>
			<v-list-item>
				<v-row>
					<v-col> 팔로우 : {{ userInfo.follower }} </v-col
					><v-col>팔로워 : {{ userInfo.following }}</v-col>
				</v-row>
			</v-list-item>
		</v-list>
	</v-col>
</v-row> -->
