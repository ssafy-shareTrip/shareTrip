import { ref } from "vue";
import { defineStore } from "pinia";

export const useUserStore = defineStore(
	"user",
	() => {
		const userId = ref(null);
		const userName = ref(null);

		const setId = (id, name) => {
			userId.value = id;
			userName.value = name;
		};

		return { userId, userName, setId };
	},
	{ persist: { storage: sessionStorage } }
);
