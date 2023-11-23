<script setup>
const props = defineProps({
	followList: Object,
	msg: String,
});

console.log(props.followList);
</script>

<template>
	<v-card class="mx-auto" max-width="425">
		<v-list lines="two">
			<v-list-subheader>{{ msg }}</v-list-subheader>
			<v-list-item v-if="msg == 'Following'">
				<v-row>
					<v-text-field
						clearable
						label="팔로우할 유저의 ID"
						variant="solo-filled"
						prepend-icon="mdi-account-heart"
					></v-text-field>
					<v-btn icon="mdi-magnify"></v-btn>
				</v-row>
			</v-list-item>
			<v-list-item
				prepend-avatar="https://cdn.vuetifyjs.com/images/lists/1.jpg"
				title="Brunch this weekend?"
			>
				<template v-slot:subtitle>
					<span class="font-weight-bold">Ali Connors</span> &mdash; I'll be in your
					neighborhood doing errands this weekend. Do you want to hang out?
				</template>
			</v-list-item>

			<v-divider inset></v-divider>

			<v-list-item prepend-avatar="https://cdn.vuetifyjs.com/images/lists/2.jpg">
				<template v-slot:title>
					Summer BBQ <span class="text-grey-lighten-1">4</span>
				</template>

				<template v-slot:subtitle>
					<span class="font-weight-bold">to Alex, Scott, Jennifer</span> &mdash; Wish I
					could come, but I'm out of town this weekend.
				</template>
			</v-list-item>

			<v-divider inset></v-divider>

			<v-list-item
				prepend-avatar="https://cdn.vuetifyjs.com/images/lists/3.jpg"
				title="Oui oui"
			>
				<template v-slot:subtitle>
					<span class="font-weight-bold">Sandra Adams</span> &mdash; Do you have Paris
					recommendations? Have you ever been?
				</template>
			</v-list-item>
		</v-list>
	</v-card>
</template>

<style scoped></style>
