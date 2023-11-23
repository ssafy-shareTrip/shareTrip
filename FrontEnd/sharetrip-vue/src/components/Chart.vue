<script setup>
import { Bar } from "vue-chartjs";
import { ref } from "vue";
import {
	Chart as ChartJS,
	Title,
	Tooltip,
	Legend,
	BarElement,
	CategoryScale,
	LinearScale,
} from "chart.js";
import { useChartStore } from "@/stores/chart";

const store = useChartStore();
// const { toggleSidebar } = store;

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const data = ref({
	labels: ["January", "February", "March", "January", "February", "March"],
	datasets: [{ data: [40, 20, 12, 40, 60, 12] }],
});
const options = ref({
	responsive: true,
});
</script>

<!-- api 통신 후 아래 코드 사용하기 -->
<!-- <script>
import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'BarChart',
  components: { Bar },
  data: () => ({
    loaded: false,
    chartData: null
  }),
  async mounted () {
    this.loaded = false

    try {
      const { userlist } = await fetch('/api/userlist')
      this.chartdata = userlist

      this.loaded = true
    } catch (e) {
      console.error(e)
    }
  }
}
</script> -->

<template>
	<v-skeleton-loader type="card">
		<Bar :data="data" :options="options" />
	</v-skeleton-loader>
</template>

<style scoped></style>
