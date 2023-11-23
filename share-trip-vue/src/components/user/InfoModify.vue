<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { localAxios } from "@/util/http-commons";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";


const store = useUserStore();
const axios = localAxios();
const router = useRouter();
const alert = ref(false);
const form = ref(false);
const msg = ref("양식을 다시 확인해주세요");
const user = ref(store.userTotal);

const modify = () => {
	if(!form.value){
		alert.value = true;
		return;
	}

	axios
		.put(`/user/modify/${user.id}`, user.value)
		.then(() => {
			router.replace({name:"mypage"});
		})
		.catch(()=>{})
}

const lenRules = [
	(value) => {
		if (value) return true;
		return "반드시 작성해야합니다.";
	},
	(value) => {
		if (value.length >= 4 && value.length <= 16) return true;
		return "4자 이상 16자 이하로 작성해야합니다.";
	},
];

const chkRules = [
	(value) => {
		if (value == user.value.password) return true;
		return "비밀번호가 다릅니다.";
	},
];

const rules = [
	(value) => {
		if (value) return true;
		return "반드시 작성해야합니다.";
	},
];

const chkPassword = ref("");

</script>
<template>
	<v-row>
		<v-card
			class="mx-auto rounded-shaped"
			style="width: 55%; margin-top: 100px"
			title="회원 정보 수정"
			color="cyan-lighten-5"
		>
			<v-container>
				<v-form v-model="form">
					<v-text-field
						v-model="user.id"
						color="primary"
						label="아이디"
						variant="underlined"
						readonly
					>
					</v-text-field>
					<v-text-field
						v-model="user.name"
						:rules="rules"
						color="primary"
						label="이름"
						variant="underlined"
						readonly
					></v-text-field>
					<v-row>
						<v-col cols="4">
							<v-text-field
								v-model="user.emailId"
								:rules="rules"
								color="primary"
								label="emailId"
								variant="underlined"
								placeholder="example"
								clearable
							></v-text-field>
						</v-col>
						<v-col cols="1">
							<h3 style="line-height: 60px">@</h3>
						</v-col>
						<v-col>
							<v-text-field
								v-model="user.emailDomain"
								:rules="rules"
								color="primary"
								label="emailDomain"
								variant="underlined"
								placeholder="ssafy.com"
								clearable
							></v-text-field>
						</v-col>
					</v-row>
					<v-text-field
						v-model="user.password"
						:rules="lenRules"
						color="primary"
						label="password"
						variant="underlined"
						type="password"
						placeholder="현재 비밀번호 또는 변경할 비밀번호를 입력하세요."
						clearable
					></v-text-field>
					<v-text-field
						v-model="chkPassword"
						:rules="chkRules"
						color="primary"
						label="chkPassword"
						variant="underlined"
						type="password"
						placeholder="비밀번호를 다시 입력해주세요."
						clearable
					></v-text-field>
					<v-text-field
						v-model="user.birth"
						color="primary"
						label="birth"
						variant="underlined"
						type="date"
						clearable
					></v-text-field>
		
				<v-radio-group v-model="user.gender" inline label="성별을 선택하세요">
					<v-radio
						label="Female"
						color="indigo-darken-3"
						value="여성"
					></v-radio>
					<v-radio
						label="male"
						color="indigo-darken-3"
						value="남성"
					></v-radio>
					<v-radio
						label="private"
						color="indigo-darken-3"
						value="비공개"
					></v-radio>
				</v-radio-group>
				</v-form>
			</v-container>

			<v-divider></v-divider>

			<v-card-actions>
				<v-spacer></v-spacer>

				<v-btn color="cyan-darken-4" @click="modify">
					Complete Modifications

					<v-icon icon="mdi-chevron-right" end></v-icon>
				</v-btn>
			</v-card-actions>
		</v-card>
	</v-row>
	<v-overlay v-model="alert" contained class="align-center justify-center">
		<v-alert title="회원 수정 실패" :text="msg" type="info" color="orange-lighten-4"></v-alert
	></v-overlay>
</template>

<style scoped></style>
