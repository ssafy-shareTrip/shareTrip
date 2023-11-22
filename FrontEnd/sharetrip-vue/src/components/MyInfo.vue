<script setup>
defineProps(["userInfo"]);
</script>

<template>
	<v-container>
		<v-row align="center">
			<v-col cols="4"
				><v-img
					class="bg-white"
					width="100%"
					:aspect-ratio="1"
					src="https://cdn.vuetifyjs.com/images/parallax/material.jpg"
					style="border-radius: 100%"
					cover
				></v-img
			></v-col>
			<v-col>
				<v-list lines="two" class="rounded-lg" elevation="13">
					<v-list-item>이름 : {{ userInfo.name }}</v-list-item>
					<v-divider :thickness="5" color="info"></v-divider>
					<v-list-item
						>생년월일 :
						<template v-if="userInfo.birth == null">미공개</template>
						<template v-else>{{ userInfo.brith }}</template>
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
</template>

<style scoped></style>
