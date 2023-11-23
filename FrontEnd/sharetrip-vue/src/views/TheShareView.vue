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
				<v-tab :value="3" v-if="store.userId">내 경로</v-tab>
			</v-tabs>
		</v-row>
		<v-row>
			<v-window v-model="tab" style="width: 100%">
				<v-window-item :value="1"> </v-window-item>
				<v-window-item :value="2">
					<trip-list :trip-list="shareTrip"></trip-list>
				</v-window-item>
				<v-window-item :value="3">
					<trip-list :trip-list="groupTrip"></trip-list>
				</v-window-item>
			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped></style>
