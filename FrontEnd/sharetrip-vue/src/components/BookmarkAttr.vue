<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
const router = useRouter();
const axios = localAxios();
const userStore = useUserStore();
const props = defineProps({
	userInfo: Object,
});
const attr = ref([]);

onMounted(() => {
	console.log(props.userInfo);

	axios
		.get(`/fav/attr/${props.userInfo.id}`, {
			params: {
				category: 1,
			},
		})
		.then(({ data }) => {
			console.log(data);
			attr.value = data.data;
			attr.value.forEach((at) => {
				at.type = convert[at.contentTypeId];
				at.isBookmark = 1;
			});
		})
		.catch((err) => {
			console.log(err);
		});
});

const headers = [
	{
		align: "start",
		key: "title",
		sortable: true,
		title: "이름",
	},
	{ key: "addr1", title: "🏢" },
	{ key: "type", title: "🚩" },
	{ key: "firstImage", title: "🖼️" },
	{ key: "action", title: "⭐" },
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

const search = ref("");
const mvAttrInfo = (event, { item }) => {
	console.log(item.contentId);
};
const page = [
	{ value: 10, title: "10" },
	{ value: 20, title: "20" },
	{ value: 30, title: "30" },
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
</script>

<template>
	<v-container fluid>
		<v-card flat>
			<template v-slot:text>
				<v-text-field
					v-model="search"
					label="Search"
					prepend-inner-icon="mdi-magnify"
					single-line
					variant="outlined"
					hide-details
				></v-text-field>
			</template>

			<v-data-table
				:headers="headers"
				:items="attr"
				:search="search"
				@click:row="mvAttrInfo"
				hover
				:items-per-page-options="page"
			>
				<template #item.firstImage="{ item }">
					<v-card class="my-2" elevation="2" rounded>
						<v-img
							:src="item.firstImage ? item.firstImage : '/public/nature.png'"
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
									:src="item.firstImage ? item.firstImage : '/public/nature.png'"
									alt=""
								/>
							</v-overlay>
						</v-img>
					</v-card>
				</template>
				<template #item.action="{ item }">
					<v-img
						:src="`/icon/star_${item.isBookmark}.png`"
						width="30"
						cover
						@click="favReg(1, item, item.isBookmark)"
					>
					</v-img>
				</template>
			</v-data-table>
			<!-- <v-overlay v-model="overlay">
				<img :src="imgsrc" alt="" />
			</v-overlay> -->
		</v-card>
	</v-container>
</template>

<style scoped></style>
