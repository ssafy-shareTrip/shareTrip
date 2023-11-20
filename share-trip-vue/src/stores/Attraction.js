import { ref } from 'vue';
import { defineStore } from 'pinia';

export const UseAttractionStore = defineStore('attraction', () => {
    const getLocation = (idx) => {
        console.log(idx)
        
    }

    return {getLocation}
})