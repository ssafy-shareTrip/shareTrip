<script setup>
import { ref, onMounted } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import { VIcon } from "vuetify/lib/components/index.mjs";
const props = defineProps({
	userInfo: Object,
});

const store = useUserStore();
const axios = localAxios();
const router = useRouter();

const myTrip = ref(null);

onMounted(() => {
	axios
		.get(`/trip/${props.userInfo.id}`)
		.then(({ data }) => {
			myTrip.value = data.data;
			console.log(myTrip.value);
		})
		.catch((err) => {
			console.log(err);
		});
});

const mvTripInfo = (tripNo) => {
	router.push({ name: "path", query: { tripNo: tripNo } });
};

const deleteTrip = (tripNo) => {
	axios
		.delete(`/trip/delete/${tripNo}?userId=${store.userId}`)
		.then(({ data }) => {
			console.log(data);
			myTrip.value = myTrip.value.filter((data) => {
				return data.tripNo != tripNo;
			});
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
				<v-card width="400">
					<template v-slot:title>
						<span @click="mvTripInfo(item.tripNo)"
							>{{ item.title }}
							<v-icon
								icon="mdi-sign-direction"
								style="margin-left: auto"
							></v-icon></span
					></template>

					<template v-slot:subtitle>
						<v-carousel show-arrows="hover">
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
							{{ item.create_time }}
							<v-btn
								variant="plain"
								icon="mdi-trash-can-outline"
								style="margin-left: auto; color: red"
								@click="deleteTrip(item.tripNo)"
							></v-btn>
						</v-row>
					</template>
				</v-card>
			</v-col>
		</v-row>
	</v-container>
</template>

<style scoped></style>
