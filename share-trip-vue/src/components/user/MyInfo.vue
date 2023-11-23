<script setup>
defineProps({
	userInfo: Object,
});
</script>

<template>
	<v-container>
		<v-row align="center">
			<v-col cols="4"
				><v-img
					class="bg-white"
					width="100%"
					:aspect-ratio="1"
					src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Smilies/Cat%20with%20Wry%20Smile.png"
					alt="Cat with Wry Smile"
					style="border-radius: 100%"
					cover
				></v-img>
			</v-col>
			<v-col>
				<v-list lines="two" class="rounded-lg" elevation="13">
					<v-list-item>이름 : {{ userInfo.name }}</v-list-item>
					<v-divider :thickness="5" color="info"></v-divider>
					<v-list-item>
						<v-row>
							<v-col>
								생년월일 :
								<template v-if="userInfo.birth == null">비공개</template>
								<template v-else>{{ userInfo.birth }}</template> </v-col
							><v-col>
								성별 :
								<template v-if="userInfo.gender == null">비공개</template>
								<template v-else>{{ userInfo.gender }}</template>
							</v-col>
						</v-row>
					</v-list-item>
					<v-divider :thickness="5" color="info"></v-divider>
					<v-list-item>
						<v-row>
							<v-col> 팔로우 : {{ userInfo.follower }} </v-col
							><v-col>팔로워 : {{ userInfo.following }}</v-col>
						</v-row>
					</v-list-item>
				</v-list>
			</v-col>
		</v-row>
	</v-container>
	<div class="d-flex flex-row-reverse mb-6">
		<router-link :to="{ name: 'infoMod' }" class="mr-5 text-overline">
			회원 정보 수정<v-icon icon="mdi-chevron-right" end></v-icon>
		</router-link>
	</div>
</template>

<style scoped>
a {
	text-decoration: none;
	color: #00796b;
}
</style>
