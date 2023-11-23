<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
import StatList from "./StatList.vue";

const axios = localAxios();

const stat = ref();

onMounted(() => {
	axios
		.get(`/map/stat`)
		.then(({ data }) => {
			stat.value = data.data;
		})
		.catch((err) => {
			console.log(err);
		});
});

const tab = ref(1);
</script>

<template>
	<v-container style="width: 80%; background-color: aliceblue">
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
				<v-tab :value="2">참조순</v-tab>
				<v-tab :value="3">좋아요순</v-tab>
				<v-tab :value="4">북마크순</v-tab>
				<v-tab :value="5">경로포함순</v-tab>
				<v-tab :value="6">성별</v-tab>
				<v-tab :value="7">연령대별</v-tab>
			</v-tabs>
		</v-row>
		<v-row>
			<v-window v-model="tab" style="width: 100%">
				<v-window-item :value="1">
					<v-container style="height: 400px">
						<v-row>
							<v-col>
								<br /><br />
								<h1>여기는 통계를 나타내는 곳!</h1>
								<br />
								<h2>여러 가지 정보로 관광지의 등수를 매겨보았어요!</h2>
								<br />
								<h2>지금 바로 확인하러 가볼까요?</h2> </v-col
							><v-col cols="3">
								<v-img
									src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Objects/Chart%20Increasing.png"
									alt="Chart Increasing"
									style="height: 80%; margin: 0 auto"
							/></v-col>
						</v-row> </v-container
				></v-window-item>
				<v-window-item :value="2">
					<stat-list
						:attrList="stat.readCntOrder"
						count="readcount"
						name="참조순"
					></stat-list>
				</v-window-item>
				<v-window-item :value="3">
					<stat-list
						:attrList="stat.likeOrder"
						count="totalLike"
						name="좋아요순"
					></stat-list>
				</v-window-item>
				<v-window-item :value="4">
					<stat-list
						:attrList="stat.bookmarkOrder"
						count="totalBookmark"
						name="북마크순"
					></stat-list>
				</v-window-item>
				<v-window-item :value="5">
					<stat-list :attrList="stat.pathOrder" count="cnt" name="경로포함순"></stat-list
				></v-window-item>
				<v-window-item :value="6"
					><stat-list
						:attrList="stat.genderOrder"
						pivot="gender"
						count="totalLike"
						name="성별"
					></stat-list>
				</v-window-item>
				<v-window-item :value="7">
					<stat-list
						:attrList="stat.birthOrder"
						pivot="age"
						count="totalLike"
						name="연령별"
					></stat-list>
				</v-window-item>
			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped></style>
