<script setup>
import { useRoute, useRouter } from "vue-router";
import { onMounted, ref, computed } from "vue";
import { useUserStore } from "@/stores/user";
import { localAxios } from "@/util/http-commons";
import DetailInfo from "@/components/attraction/detail/DetailInfo.vue";
import DetailMap from "@/components/attraction/detail/DetailMap.vue";

const route = useRoute();
const userStore = useUserStore();
const axios = localAxios();

const idx = route.params.idx; //125266
const detail = ref({
	memos: [],
});
const isLike = ref(0);
const isBookmark = ref(0);

onMounted(() => {
	console.log(idx);
	axios
		.get(`/map/attr/${idx}`)
		.then(({ data }) => {
			console.log(data);
			detail.value = data.data;
			isLike.value = detail.value.isLike;
			isBookmark.value = detail.value.isBookmark;
		})
		.catch((err) => {
			console.log(err);
		});
});

const favReg = (category, status) => {
	if (userStore.userId == null) return;
	console.log(category, status);

	const url = "http://localhost:80/sharetrip/fav/attr/" + userStore.userId;
	if (status == "0") {
		// 미등록 -> 등록
		axios
			.post("http://localhost:80/sharetrip/fav/attr/" + userStore.userId, {
				contentId: idx,
				category: category,
			})
			.then(({ data }) => {
				console.log("favReg : 성공");
				if (category == 0) {
					// isLike
					isLike.value = "1";
				} else {
					//isBookmark
					isBookmark.value = "1";
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
					contentId: idx,
					category: category,
				},
			})
			.then(({ data }) => {
				console.log("favDel : 성공");
				if (category == 0) {
					// isLike
					isLike.value = "0";
				} else {
					//isBookmark
					isBookmark.value = "0";
				}
			})
			.catch(() => {
				console.log("favDel : 실패");
			});
	}
};

const tab = ref(1);

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
const selcar = ref(0);
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
				<v-tab :value="1">정보</v-tab>
				<v-tab :value="2">지도</v-tab>
			</v-tabs>
		</v-row>
		<v-row style="padding: 0">
			<v-window v-model="tab">
				<v-window-item :value="1">
					<v-row>
						<v-col cols="4">
							<v-carousel show-arrows="hover" style="height: 100%" v-model="selcar">
								<v-carousel-item
									v-if="detail.firstImage != null"
									:src="detail.firstImage"
									cover
								></v-carousel-item>

								<v-carousel-item
									v-if="detail.firstImage2 != null"
									:src="detail.firstImage2"
									cover
								></v-carousel-item>
							</v-carousel>
						</v-col>
						<v-col>
							<v-row>
								<v-col>
									{{ detail.title }}
								</v-col>
								<v-col cols="1"
									><img
										:src="`/icon/like_${isLike}.png`"
										width="25"
										@click="favReg(0, isLike)"
								/></v-col>
								<v-col cols="1">
									<img
										:src="`/icon/star_${isBookmark}.png`"
										width="25"
										@click="favReg(1, isBookmark)"
									/>
								</v-col>
							</v-row>
							<v-row>
								<v-col>{{ detail.addr1 }} </v-col>
								<v-col>{{ detail.addr2 }}</v-col>
							</v-row>
							<v-row>
								<v-card
									:text="detail.overview"
									variant="outlined"
									style="width: 95%"
								></v-card>
							</v-row>
						</v-col> </v-row
					><v-row>
						<v-data-table :items="detail.memos"></v-data-table>
					</v-row>
				</v-window-item>
				<v-window-item :value="2">
					<DetailMap :map-lat="detail.latitude" :map-lng="detail.longitude"></DetailMap>
				</v-window-item>
			</v-window>
		</v-row>
	</v-container>
</template>

<style scoped></style>
