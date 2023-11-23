<script setup>
import KakaoMap from "@/components/kakao/KakaoMap.vue";
// import VSelect from "@/components/common/VSelect.vue";
import { ref, onMounted, watch } from "vue";
import axios from "axios";
// import AttractionDetail from "../components/attraction/AttractionDetail.vue";
import { useRouter, useRoute } from "vue-router";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const router = useRouter();
const route = useRoute();
const sidoList = ref([]);
const selectSido = ref(null);
const selectGugun = ref(null);
const selectType = ref([]);
const keyword = ref("");
const gugunList = ref([
	{
		text: "구군선택",
		value: "",
	},
]);

var attractionList = ref([]);

const selectAttractionElement = ref([]);

onMounted(() => {
	listSido();

	if (route.query.keyword) {
		keyword.value = route.query.keyword;
		search();
	}

	if (route.query.contentTypeId) {
		selectType.value.push(route.query.contentTypeId);
		search();
	}
});

const listSido = () => {
	//시 정보를 가져오는 리스트
	axios

		// .get("http://192.168.31.55:80/sharetrip/map/sido")
		.get("http://localhost:80/sharetrip/map/sido")
		.then(function (data) {
			data = data.data.data;
			console.log(data);

			let options = [];
			options.push({ text: "지역", value: "" });
			options.push({ text: "전체", value: "0" });
			data.forEach((sido) => {
				options.push({ text: sido.sidoName, value: sido.sidoCode });
			});
			sidoList.value = options;
		})
		.catch(function (error) {
			console.log(error);
		});
};

const listGugun = (param) => {
	//군 정보를 가져오는 리스트
	axios
		//.get("http://192.168.31.55:80/sharetrip/map/gugun", { params: param })
		.get("http://localhost:80/sharetrip/map/gugun", { params: param })
		.then(function (data) {
			data = data.data.data;
			console.log(data);

			let options = [];
			options.push({ text: "구군선택", value: "" });
			data.forEach((gugun) => {
				options.push({ text: gugun.gugunName, value: gugun.gugunCode });
			});
			gugunList.value = options;
			selectGugun.value = options[0];
		})
		.catch(function (err) {
			console.log(err);
		});
};

watch(
	() => selectSido.value,
	() => {
		listGugun({ sido: selectSido.value });
	}
);

// const onChangeSido = (key) => {
// 	//시가 바뀌었을 때 호출됨
// 	console.log(key);
// 	selectedSido.value = key;
// 	listGugun({ sido: key });
// };

const search = () => {
	//let url = new URL("http://192.168.31.55:80/sharetrip/map/attr");
	let url = new URL("http://localhost:80/sharetrip/map/attr");

	const params = new URLSearchParams();
	change.value = true;
	if (typeof selectSido.value !== "object" && selectSido.value != 0 && selectSido.value != null) {
		params.append("sido", selectSido.value);

		if (typeof selectGugun.value !== "object" && selectGugun.value !== "") {
			params.append("gugun", selectGugun.value);
		}
	}

	if (selectType.value.length > 0) {
		selectType.value.forEach((id) => {
			params.append("contentTypeId", id);
		});
	}

	if (keyword.value !== "") {
		params.append("keyword", keyword.value);
	}

	if (userStore.userId != null) {
		params.append("userId", userStore.userId);
	}

	url.search = params.toString();

	axios
		.get(url.toString())
		.then(function (data) {
			data = data.data.data;
			console.log(data);
			//검색 성공했으면 다시 마커를 띄워줘야 함
			attractionList.value = data;
			attractionList.value.forEach((data) => {
				data.type = convert[data.contentTypeId];
			});
		})
		.catch(function (error) {
			console.log(error);
		});
};

const change = ref(false);
const changeCenter = (x, y) => {
	if (!move.value) return;

	console.log("센터 바뀐거 어트랙션뷰에서 수신완료");
	let url = new URL("http://localhost:80/sharetrip/map/attr");

	const params = new URLSearchParams();
	change.value = false;
	if (selectType.value.length > 0) {
		selectType.value.forEach((id) => {
			params.append("contentTypeId", id);
		});
	}
	params.append("mapX", x);
	params.append("mapY", y);
	url.search = params.toString();

	axios
		.get(url.toString())
		.then(function (data) {
			data = data.data.data;
			console.log(data);
			//검색 성공했으면 다시 마커를 띄워줘야 함
			attractionList.value = data;
			attractionList.value.forEach((data) => {
				data.type = convert[data.contentTypeId];
			});
		})
		.catch(function (error) {
			console.log(error);
		});
};

const clickSelectAttraction = (event, { item }) => {
	selectAttractionElement.value = item;
};

const mvDet = (contentId) => {
	console.log("상세페이지 이동!");
	router.push({
		name: "attrDet",
		params: {
			idx: contentId,
		},
	});
};

const type = [
	{ text: "관광지", value: 12 },
	{ text: "문화시설", value: 14 },
	{ text: "축제공연행사", value: 15 },
	{ text: "여행코스", value: 25 },
	{ text: "레포츠", value: 28 },
	{ text: "숙박", value: 32 },
	{ text: "쇼핑", value: 38 },
	{ text: "음식점", value: 39 },
];

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

const headers = [
	{
		align: "start",
		key: "title",
		sortable: true,
		title: "이름",
	},
	{ key: "addr1", title: "주소" },
	{ key: "type", title: "분류" },
	{ key: "firstImage", title: "사진" },
	{ key: "isLike", title: "소셜" },
	{ key: "contentId", title: "상세설명" },
];
const page = [
	{ value: 4, title: "4" },
	{ value: 8, title: "8" },
	{ value: 12, title: "12" },
	{ value: 16, title: "16" },
];

const favReg = (category, item, status) => {
	if (userStore.userId == null) return;
	console.log(item, category, status);
	let contentId = item.contentId;
	const url = "http://localhost:80/sharetrip/fav/attr/" + userStore.userId;
	if (status == 0) {
		// 미등록 -> 등록
		axios
			.post("http://localhost:80/sharetrip/fav/attr/" + userStore.userId, {
				contentId: contentId,
				category: category,
			})
			.then(() => {
				console.log("favReg : 성공");
				if (category == 0) {
					// isLike
					item.isLike = 1;
				} else {
					//isBookmark
					item.isBookmark = 1;
				}
			})
			.catch(() => {
				console.log("favReg 등록 : 실패");
			});
	} else {
		//등록 -> 미등록
		axios
			.delete(url, {
				params: {
					contentId: contentId,
					category: category,
				},
			})
			.then(() => {
				console.log("favDel : 성공");
				if (category == 0) {
					// isLike
					item.isLike = 0;
				} else {
					//isBookmark
					item.isBookmark = 0;
				}
			})
			.catch(() => {
				console.log("favDel : 실패");
			});
	}
};

const rail = ref(true);
const slider = ref(250);
const move = ref(true);
</script>

<template>
	<v-row style="position: absolute; width: 90%; top: 100px; z-index: 10">
		<v-col>
			<v-select
				label="시도"
				v-model="selectSido"
				:items="sidoList"
				item-title="text"
				item-value="value"
				variant="solo"
			></v-select> </v-col
		><v-col>
			<v-select
				label="구군"
				v-model="selectGugun"
				:items="gugunList"
				item-title="text"
				item-value="value"
				variant="solo"
			></v-select> </v-col
		><v-col>
			<v-select
				label="분류"
				v-model="selectType"
				clearable
				chips
				:items="type"
				item-title="text"
				item-value="value"
				variant="solo"
				multiple
			></v-select>
		</v-col>
		<v-col>
			<v-text-field
				clearable
				label="검색 키워드"
				v-model="keyword"
				variant="solo-filled"
			></v-text-field>
		</v-col>
		<v-col cols="1">
			<v-switch v-model="move" color="info"></v-switch>
		</v-col>
		<v-col cols="1" style="height: 100%">
			<v-btn icon="mdi-magnify" @click="search"></v-btn>
		</v-col>
	</v-row>
	<KakaoMap
		:attractionList="attractionList"
		:selectAttractionElement="selectAttractionElement"
		:change="change"
		@change-center-position="changeCenter"
	></KakaoMap>
	<v-navigation-drawer
		:rail="rail"
		permanent
		@click="rail = false"
		:width="slider"
		location="bottom"
	>
		<v-list-item>
			<span>조회 관광지</span>
			<v-slider
				v-show="!rail"
				v-model="slider"
				class="align-center"
				:max="500"
				:min="100"
				hide-details
			></v-slider>
			<template v-slot:append>
				<v-btn
					variant="text"
					:icon="rail ? `mdi-chevron-up` : `mdi-chevron-down`"
					@click.stop="rail = !rail"
				></v-btn>
			</template>
		</v-list-item>

		<v-divider></v-divider>
		<v-list-item v-show="!rail">
			<v-data-table
				:headers="headers"
				:items="attractionList"
				:items-per-page-options="page"
				:items-per-page="4"
				@click:row="clickSelectAttraction"
				hover
			>
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
								<img
									:src="item.firstImage ? item.firstImage : '/nature.png'"
									alt=""
								/>
							</v-overlay>
						</v-img>
					</v-card>
				</template>
				<template #item.isLike="{ item }">
					<v-row>
						<v-col>
							<v-img
								:src="`/icon/like_${item.isLike}.png`"
								height="100%"
								cover
								@click="favReg(0, item, item.isLike)"
							>
							</v-img></v-col
						><v-col>
							<v-img
								:src="`/icon/star_${item.isBookmark}.png`"
								height="100%"
								cover
								@click="favReg(1, item, item.isBookmark)"
							>
							</v-img
						></v-col>
					</v-row>
				</template>
				<template #item.contentId="{ item }">
					<v-icon
						icon="mdi-chevron-right"
						style="width: 100%"
						@click="mvDet(item.contentId)"
					></v-icon>
				</template>
			</v-data-table>
		</v-list-item>
	</v-navigation-drawer>
</template>

<style scoped></style>
