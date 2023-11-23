<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import MyInfo from "@/components/MyInfo.vue";
import BookmarkAttr from "@/components/BookmarkAttr.vue";
import TripList from "@/components/TripList.vue";
import FollowList from "../components/FollowList.vue";

const store = useUserStore();
const axios = localAxios();
const tab = ref(1);
const userInfo = ref({});
const myTrip = ref();
onMounted(() => {
	axios
		.get(`/user/info/${store.userId}`)
		.then(({ data }) => {
			userInfo.value = data.data;
			store.setUser(data.data);
		})
		.catch((err) => {
			console.log(err);
		});

	axios
		.get(`/trip/${store.userId}`)
		.then(({ data }) => {
			myTrip.value = data.data;
			console.log(myTrip.value);
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
				<v-tab :value="2">내 경로</v-tab>
				<v-tab :value="3">즐겨찾기한 관광지</v-tab>
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
					<v-container style="height: 70%" v-if="myTrip.length == 0">
						<v-row>
							<v-col>
								<br /><br />
								<h1>아직 직접 만든 경로들이 없어요..</h1>
								<br />
								<h2>
									{{ userInfo.name }}님만 알고있는<br />
									꿀잼🎈 여행지를 소개해보는건 어떨까요?
								</h2>
								<br />
								<router-link :to="{ name: 'path' }"
									><h2>경로 만들러가기</h2></router-link
								> </v-col
							><v-col cols="3">
								<v-img
									src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Sailboat.png"
									alt="Sailboat"
									style="height: 80%; margin: 0 auto"
							/></v-col>
						</v-row>
					</v-container>
					<trip-list :trip-list="myTrip" v-else></trip-list>
				</v-window-item>
				<v-window-item :value="3">
					<bookmark-attr :user-info="userInfo"> </bookmark-attr>
				</v-window-item>

				<v-window-item :value="4">
					<follow-list
						:follow-list="userInfo.followingList"
						msg="Following"
					></follow-list>
				</v-window-item>
				<v-window-item :value="5">
					<follow-list :follow-list="userInfo.followerList" msg="Follower"></follow-list>
				</v-window-item>
			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped></style>
