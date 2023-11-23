User
<script setup="setup">
    import {ref} from "vue";
    import {useRouter} from "vue-router";

    const router = useRouter();
    const keyword = ref("");
    const dialog = ref(false);

    const MainPagesearch = () => {
        if (keyword.value === "") {
            // 검색어가 없는 경우
            dialog.value = true; // 경고창을 엽니다.

        } else {
            // 검색어가 있는 경우
            console.log("main keyword", keyword.value);
            router.push({
                name: 'attr',
                query: {
                    keyword: keyword.value
                }
            });

        }
    };

	const contentTypeIdClickBtn= (parmas)=>{

		router.push({
                name: 'attr',
                query: {
                    contentTypeId: parmas
                }
            });

	}

</script>

<template>
    <!-- 메인 컨테이너 -->
    <!-- 큰 배경 이미지 -->
    <v-img
        :src="`/icon/mainPageImage.jpg`"
        alt="Background Image"
        >
        <!-- 이미지 위에 검색창 -->
        <v-row justify="center" >
            <v-col cols="4" md="4" class="text-center" style="margin-left: 150px;" >
                <br/><br/>
                <v-text-field v-model="keyword" label="검색어 입력" variant="solo-filled" style="background-color: transparent; opacity: 0.5;"></v-text-field>
                
    <!-- 경고창을 위한 v-dialog -->
    <v-fade-transition hide-on-leave="hide-on-leave">
        <v-card
            v-if="dialog"
            append-icon="$close"
            class="mx-auto"
            elevation="16"
            max-width="500"
            title="경고">
            <template v-slot:append>
                <v-btn icon="$close" variant="text" @click="dialog = false"></v-btn>
            </template>

            <v-divider></v-divider>

            <div class="py-12 text-center">
                <v-icon class="mb-6" color="red" icon="mdi-check-circle-outline" size="128"></v-icon>

                <div class="text-h4 font-weight-bold">검색어를 입력해주세요.</div>
            </div>

            <v-divider></v-divider>

            <div class="pa-4 text-end">
                <v-btn
                    class="text-none"
                    color="medium-emphasis"
                    min-width="92"
                    rounded="rounded"
                    variant="outlined"
                    @click="dialog = false">
                    Close
                </v-btn>
            </div>
        </v-card>
    </v-fade-transition>
            </v-col>
            <v-col cols="1" style="height: 100%">
                <br/><br/>
                <v-btn icon="mdi-magnify" @click="MainPagesearch"></v-btn>
            </v-col>
        </v-row>
        
        

        <v-row justify="center" class="my-4">

            <v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(12)">
                관광지
            </v-btn>

			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(14)">
                문화시설
            </v-btn>

			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(15)">
                축제공연행사
            </v-btn>

			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(25)">
                여행코스
            </v-btn>

        </v-row>

        <v-row justify="center">
            
			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(28)">
                레포츠
            </v-btn>

			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(32)">
                숙박
            </v-btn>

			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(38)">
                쇼핑
            </v-btn>

			<v-btn height="100" width="100" class="custom-btn" @click="contentTypeIdClickBtn(39)">
                음식점
            </v-btn>

        </v-row>
    </v-img>
</template>

<style scoped="scoped">

    .v-btn {
        margin: 4px;
    }

    .custom-btn {
        background-color: white;
        opacity: 0.7
    }

    .v-text-field{

        margin-bottom: 300px;
    }

</style>