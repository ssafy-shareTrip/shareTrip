<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import TripList from "@/components/TripList.vue";

const axios = localAxios();
const store = useUserStore();
const shareTrip = ref();
const groupTrip = ref();

onMounted(() => {
	axios
		.get(`/trip/share`)
		.then(({ data }) => {
			shareTrip.value = data.data;
			console.log(data);
		})
		.catch((err) => {
			console.log(err);
		});

	if (store.userId != null) {
		axios
			.get(`/trip/group/${store.userId}`)
			.then(({ data }) => {
				groupTrip.value = data.data;
				console.log(data);
			})
			.catch((err) => {
				console.log(err);
			});
	}
});

const tab = ref(1);
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
				<v-tab :value="1">설명</v-tab>
				<v-tab :value="2">공개 경로</v-tab>
				<v-tab :value="3" v-if="store.userId">그룹 경로</v-tab>
			</v-tabs>
		</v-row>
		<v-row>
			<v-window v-model="tab" style="width: 100%">
				<v-window-item :value="1">
					<v-container style="height: 70%">
						<v-row>
							<v-col>
								<br /><br />
								<h1>여기는 경로게시판!</h1>
								<br />
								<h2>여러 사람들이 만들어 놓은 경로를 볼 수 있어요!</h2>
								<br />
								<h2>지금 바로 확인하러 가볼까요?</h2> </v-col
							><v-col cols="3">
								<v-img
									src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Roller%20Coaster.png"
									alt="Roller Coaster"
									style="height: 80%; margin: 0 auto"
							/></v-col>
						</v-row>
					</v-container>
				</v-window-item>
				<v-window-item :value="2">
					<v-container style="height: 70%" v-if="shareTrip.length == 0">
						<v-row>
							<v-col>
								<br /><br />
								<h1>아직 공개된 경로들이 없어요..</h1>
								<br />
								<h2>직접 멋진 경로를 만들어 공개해보는건 어떨까요?</h2>
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
					<trip-list :trip-list="shareTrip" v-else></trip-list>
				</v-window-item>
				<v-window-item :value="3">
					<v-container style="height: 70%" v-if="groupTrip.length == 0">
						<v-row>
							<v-col>
								<br /><br />
								<h1>내가 그룹에 속한 경로가 없어요..</h1>
								<br />
								<h2>직접 친구들을 초대해 함께 만들어볼까요?</h2>
								<br />
								<router-link :to="{ name: 'path' }"
									><h2>경로 만들러가기</h2></router-link
								> </v-col
							><v-col cols="3">
								<v-img
									src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Activities/Teddy%20Bear.png"
									alt="Teddy Bear"
									style="height: 80%; margin: 0 auto"
							/></v-col>
						</v-row>
					</v-container>
					<trip-list :trip-list="groupTrip" v-else></trip-list>
				</v-window-item>
			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped></style>
