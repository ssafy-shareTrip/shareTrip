import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore(
	"user",
	() => {
		const userId = ref(null);
		const userName = ref(null);
		const userTotal = ref(null)
		const setId = (id, name) => {
			userId.value = id;
			userName.value = name;
		};
		const setUser = (item) => {
			console.log(item);
			userTotal.value = item;
		};


		return { userId, userName,userTotal, setId ,setUser};
	},
	{ persist: { storage: localStorage } }
);
