<script setup>
import { ref } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
const props = defineProps({
	tripList: Object,
});

const store = useUserStore();
const axios = localAxios();
const router = useRouter();
console.log(props.tripList);
const myTrip = ref(props.tripList);

const mvTripInfo = (tripNo) => {
	router.push({ name: "path", query: { tripNo: tripNo } });
};

const deleteTrip = (tripNo, index) => {
	if (!confirm("정말 삭제하시겠습니까?")) return;
	axios
		.delete(`/trip/delete/${tripNo}?userId=${store.userId}`)
		.then(({ data }) => {
			console.log(data);
			myTrip.value.splice(index, 1);
		})
		.catch((err) => {
			console.log(err);
		});
};
</script>

<template>
	<v-container fluid>
		<v-row align="center">
			<v-col cols="12" md="4" v-for="(item, index) in myTrip" :key="item.tripNo">
				<v-card style="width: 100%">
					<template v-slot:title> {{ item.title }} </template>

					<template v-slot:subtitle>
						<v-carousel show-arrows="hover" height="300">
							<v-carousel-item
								v-for="attr in item.attractions"
								:key="attr.contentId"
								:src="attr.firstImage ? attr.firstImage : '/nature.png'"
								cover
								><div
									style="
										color: black;
										/* text-align: center; */
										background-color: white;
									"
								>
									{{ attr.title }}
								</div></v-carousel-item
							>
						</v-carousel>
					</template>

					<template v-slot:text>
						<v-row>
							<v-col>
								{{ item.create_time }}
							</v-col>
							<v-col cols="1">
								<v-btn
									variant="plain"
									icon="mdi-sign-direction"
									style="margin-left: auto"
									@click="mvTripInfo(item.tripNo)"
								></v-btn> </v-col
							><v-col cols="1">
								<v-btn
									v-if="item.userId == store.userId"
									variant="plain"
									icon="mdi-trash-can-outline"
									style="color: red"
									@click="deleteTrip(item.tripNo, index)"
								></v-btn
							></v-col>
						</v-row>
					</template>
				</v-card>
			</v-col>
		</v-row>
	</v-container>
</template>

<style scoped></style>
