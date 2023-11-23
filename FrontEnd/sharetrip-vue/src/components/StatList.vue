<script setup>
import { ref, watch } from "vue";
const props = defineProps({
	attrList: Object,
	pivot: String,
	count: String,
	name: String,
});

const convert = {
	12: "관광지",
	14: "문화시설",
	15: "축제공연행사",
	25: "여행코스",
	28: "레포츠",
	32: "숙박",
	38: "쇼핑",
	39: "음식점",
};
const attr = ref(props.attrList);
attr.value.forEach((item) => {
	item.type = convert[item.contentTypeId];
});

watch();

// if (props.pivot) {
//   props.attrList.array.forEach(element => {

//   });
// }

const headers = ref([
	{
		align: "start",
		key: "place",
		sortable: true,
		title: props.name,
	},
	{ key: "pivot", title: "" },
	{ key: "title", title: "이름" },
	{ key: "addr1", title: "🏢" },
	{ key: "type", title: "🚩" },
	{ key: "firstImage", title: "🖼️" },
	{ key: "count", title: "집계" },
]);
</script>

<template>
	<v-container style="height: 70%" v-if="attrList.length == 0">
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
		</v-row>
	</v-container>
	<v-container style="height: 70%" v-else>
		<v-data-table :headers="headers" :items="attr" hover items-per-page="-1">
			<template #item.firstImage="{ item }">
				<v-card class="my-2" elevation="2" rounded>
					<v-img
						:src="item.firstImage ? item.firstImage : '/nature.png'"
						height="64"
						cover
					>
						<v-overlay
							activator="parent"
							location-strategy="connected"
							scroll-strategy="block"
							location="start top"
							origin="overlap"
						>
							<img :src="item.firstImage ? item.firstImage : '/nature.png'" alt="" />
						</v-overlay>
					</v-img>
				</v-card>
			</template>
			<template #item.place="{ item }">
				<img :src="`/icon/Medal${item.place}.png`" width="30" />
			</template>
			<template #item.pivot="{ item }" v-if="pivot">
				{{ item[pivot] }}
			</template>
			<template #item.count="{ item }" v-if="pivot">
				{{ item[count] }}
			</template>
		</v-data-table>
	</v-container>
</template>

<style scoped></style>
