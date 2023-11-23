<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import MyInfo from "@/components/user/MyInfo.vue";
import MyFollowing from "@/components/user/MyFollowing.vue";
import BookmarkAttr from "@/components/user/BookmarkAttr.vue";

const store = useUserStore();
const axios = localAxios();
const tab = ref(1);
const userInfo = ref({});

onMounted(() => {
	axios
		.get(`/user/info/${store.userId}`)
		// .get(`/user/info/ryu`)  //임시
		.then(({ data }) => {
			userInfo.value = data.data;
            store.setUser(data.data);
		})
		.catch((err) => {
			console.log(err);
		});
});
</script>

<template>
    <v-container style="width: 80%; margin-top: 100px; background-color: aliceblue">
		<v-row>
            <h1>ShareTrip</h1>
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
                <v-window-item :value="4">
					<my-following :user-info="userInfo"></my-following>
				</v-window-item>

			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped>
@font-face {
    font-family: 'PyeongChangPeace-Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2206-02@1.0/PyeongChangPeace-Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}
h1{
    font-family: 'PyeongChangPeace-Bold';
}
</style>

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
