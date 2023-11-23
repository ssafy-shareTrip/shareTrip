<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
const axios = localAxios();
const router = useRouter();
const store = useUserStore();

const form = ref(false);
const alert = ref(false);
const user = ref({
	id: "",
	name: "",
	password: "",
	emailId: "",
	emailDomain: "",
});

const rules = [
	(value) => {
		if (value) return true;
		return "반드시 작성해야합니다.";
	},
];

const login = () => {
	axios
		.post("/user/login", user.value)
		.then(({ data }) => {
			console.log(data);
			store.setId(data.userId, data.userName);
			router.replace({ name: "main" });
		})
		.catch((err) => {
			console.log(err);
			alert.value = true;
		});
};
</script>

<template>
	<v-card class="mx-auto px-6 py-8" style="width: 40%; margin-top: 100px" color="blue-lighten-5">
		<v-container
			class="rounded-shaped"
			style="
				width: 100%;
				padding-bottom: 30px;
				margin-bottom: 20px;
				background-image: linear-gradient(to right, rgb(41, 34, 40), rgb(140, 211, 239));
			"
		>
			<router-link :to="{ name: 'main' }" class="navItem">
				<v-img
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Airplane.png"
					width="100"
					style="margin-left: auto"
				/>
				<span
					style="
						font-size: 50px;
						line-height: 30px;
						text-align: center;
						text-decoration: underline;
						font-family: 'PyeongChangPeace-Bold';
						background-image: linear-gradient(
							to right,
							rgb(220, 164, 214),
							rgb(168, 230, 208)
						);
						background-clip: text;
						-webkit-background-clip: text;
						color: transparent;
					"
				>
					&nbsp; ShareTrip
				</span>
			</router-link>
		</v-container>
		<v-form v-model="form" @submit.prevent>
			<v-text-field
				v-model="user.id"
				:rules="rules"
				class="mb-2"
				clearable
				label="아이디"
				placeholder="아이디를 입력하세요."
			></v-text-field>

			<v-text-field
				v-model="user.password"
				:rules="rules"
				clearable
				label="비밀번호"
				type="password"
				placeholder="비밀번호를 입력하세요."
			></v-text-field>

			<br />

			<v-btn
				:disabled="!form"
				block
				color="success"
				size="large"
				variant="elevated"
				@click="login"
			>
				로그인
			</v-btn>
			<v-overlay v-model="alert" contained class="align-center justify-center">
				<v-alert
					title="로그인 실패"
					text="아이디와 비밀번호를 확인하세요."
					type="warning"
					color="orange-lighten-4"
				></v-alert
			></v-overlay>
		</v-form>
	</v-card>
</template>

<style scoped></style>
