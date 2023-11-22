<script setup>
import { ref } from "vue";

const items = ref([
	{
		name: "PlayStation 5",
		manufacturer: "Sony",
		year: 2020,
		sales: "10M",
	},
	{
		name: "Xbox Series X",
		manufacturer: "Microsoft",
		year: 2020,
		sales: "6.5M",
	},
	{
		name: "Nintendo Switch",
		manufacturer: "Nintendo",
		year: 2017,
		sales: "89M",
	},
	{
		name: "PlayStation 4",
		manufacturer: "Sony",
		year: 2013,
		sales: "116M",
	},
	{
		name: "Xbox One",
		manufacturer: "Microsoft",
		year: 2013,
		sales: "50M",
	},
	{
		name: "Nintendo Wii",
		manufacturer: "Nintendo",
		year: 2006,
		sales: "101M",
	},
]);

const f1 = (event, { item }) => {
	console.log(item.name);
};
</script>

<template>
	<div>
		<v-data-table :items="items" @click:row="f1"> </v-data-table>
	</div>
</template>

<style scoped></style>
