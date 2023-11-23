<script setup>
import { ref } from "vue";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
const props = defineProps({
	followList: Object,
	msg: String,
});
const store = useUserStore();
const axios = localAxios();
const follow = ref(props.followList);
const idText = ref("");
const unFollow = (item, index) => {
	if (!confirm("정말 삭제하시겠습니까?")) return;
	axios
		.delete(`/user/follow/${item.id}?userId=${store.userId}`)
		.then(({ data }) => {
			console.log(data);
			follow.value.splice(index, 1);
		})
		.catch((err) => {
			console.log(err);
		});
};
const followUser = () => {
	if (idText.value == store.userId) {
		alert.value = true;
		return;
	}
	axios
		.post(`/user/follow/${idText.value}?userId=${store.userId}`)
		.then(({ data }) => {
			console.log(data);
			idText.value = "";
			follow.value.unshift(data.data);
		})
		.catch((err) => {
			console.log(err);
			alert.value = true;
		});
};
const alert = ref(false);
</script>

<template>
	<v-card class="mx-auto" style="width: 70%">
		<v-list lines="two">
			<v-list-subheader>{{ msg }}</v-list-subheader>
			<v-list-item v-if="msg == 'Following'" style="width: 60%; margin: 0 auto">
				<v-row style="padding: 0; margin: 0">
					<v-text-field
						clearable
						v-model="idText"
						label="팔로우할 유저의 ID"
						variant="solo-filled"
						prepend-icon="mdi-account-heart"
					></v-text-field>
					<v-btn icon="mdi-magnify" variant="text" @click="followUser"></v-btn>
				</v-row>
			</v-list-item>
			<v-divider :thickness="6" color="blue lighten-4"></v-divider>
			<v-container style="height: 70%" v-if="follow.length > 0">
				<v-row style="margin: 0" v-for="(item, index) in follow" :key="item.id">
					<v-col>
						<v-list-item
							:prepend-avatar="`https://cataas.com/cat/cute?${index}`"
							:title="`${item.name}(${item.id})`"
							style="margin: 0 20%"
						>
							<template v-slot:subtitle>
								{{ item.emailId }}@{{ item.emailDomain }}
							</template>
							<v-divider :thickness="2"></v-divider>
						</v-list-item>
					</v-col>
					<v-col cols="1" :style="{ visibility: msg != 'Following' ? 'hidden' : '' }"
						><v-icon icon="mdi-delete-empty" color="red" @click="unFollow(item, index)">
						</v-icon
					></v-col>
				</v-row>
			</v-container>
			<v-container style="height: 70%" v-else>
				<v-row>
					<v-col>
						<br /><br />
						<h1>아직 팔로우 리스트가 없어요..</h1>
						<br />
						<h2>서로 팔로우해서 그룹이 되어보아요!</h2>
						<br
					/></v-col>
					<v-col cols="3">
						<v-img
							src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Handshake.png"
							alt="Handshake"
							style="height: 80%; margin: 0 auto"
					/></v-col>
				</v-row>
			</v-container>
		</v-list>
		<v-overlay v-model="alert" contained class="align-center justify-center">
			<v-alert
				title="팔로잉 실패"
				text="아이디를 다시 확인해주세요."
				type="info"
				color="orange-lighten-4"
			></v-alert
		></v-overlay>
	</v-card>
</template>

<style scoped></style>
