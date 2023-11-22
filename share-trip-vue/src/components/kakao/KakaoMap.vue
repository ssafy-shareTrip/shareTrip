<script setup="setup">
    import {onMounted, watch, ref} from "vue";
    const props = defineProps(
        {attractionList: Array, selectAttractionElement: Object}
    );

    var map;
    const positions = ref([]);
    const markers = ref([]);

    watch(() => props.attractionList.value, () => {
        // 이동할 위도 경도 위치를 생성합니다
        var moveLatLon = new kakao
            .maps
            .LatLng(
                props.selectAttractionElement.latitude,
                props.selectAttractionElement.longitude
            );

        // 지도 중심을 부드럽게 이동시킵니다 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
        map.panTo(moveLatLon);
    }, {deep: true});

    watch(() => props.attractionList.value, () => {
        positions.value = [];
        props
            .attractionList
            .forEach((attraction) => {
                let obj = {};
                obj.latlng = new kakao
                    .maps
                    .LatLng(attraction.latitude, attraction.longitude);
                obj.title = attraction.title;
                obj.addr1= attraction.addr1;
                obj.addr2= attraction.addr2;
                obj.firstImage= attraction.firstImage;

                positions
                    .value
                    .push(obj);
            });
        loadMarkers();
    }, {deep: true});

    onMounted(() => {
        if (window.kakao && window.kakao.maps) {
            initMap();
        } else {
            const script = document.createElement("script");
            script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
            import.meta.env.VITE_KAKAO_MAP_SERVICE_KEY}&libraries=services,clusterer`;
            /* global kakao */
            script.addEventListener("load", () => {
                kakao
                    .maps
                    .load(initMap);
            }); //헤드태그에 추가
            document
                .head
                .appendChild(script);
        }
    });

    const initMap = function () {
        const container = document.getElementById("map");
        const options = {
            center: new kakao
                .maps
                .LatLng(33.450701, 126.570667),
            level: 5
        }; //지도 객체 등록
        map = new kakao
            .maps
            .Map(container, options);
        //지도에 여러개의 마커를 띄움 loadMarkers();
    };

    // 현재 내 위치 기준으로 마커띄우기
    let myCenter = new kakao
        .maps
        .LatLng(33.450701, 126.570667);
    if (navigator.geolocation) {
        navigator
            .geolocation
            .getCurrentPosition((position) => {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                myCenter = new kakao
                    .maps
                    .LatLng(lat, lon);
                new kakao
                    .maps
                    .Marker({map, position: myCenter});
                map.setCenter(myCenter);
            });
    }

    const loadMarkers = () => {
        // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
        deleteMarkers();

        // 마커 이미지를 생성합니다   const imgSrc = require("@/assets/map/markerStar.png"); 마커
        // 이미지의 이미지 크기 입니다   const imgSize = new kakao.maps.Size(24, 35);   const
        // markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize); 마커를 생성합니다
        markers.value = [];
        positions
            .value
            .forEach((position) => {
                const marker = new kakao
                    .maps
                    .Marker({
                        map: map, // 마커를 표시할 지도
                        position: position.latlng, // 마커를 표시할 위치
                        title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
                        clickable: true, //  마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
                        // image: markerImage,  마커의 이미지
                    });
                markers
                    .value
                    .push(marker);

                // 마커에 클릭이벤트를 등록합니다
                kakao
                    .maps
                    .event
                    .addListener(marker, 'click', function () {
                        // 마커 위에 인포윈도우를 표시합니다
                        console.log("클릭!!");

                        // 커스텀 오버레이에 표시할 컨텐츠 입니다 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
                        // 별도의 이벤트 메소드를 제공하지 않습니다 커스텀 오버레이에 표시할 컨텐츠를 생성합니다
                        var content = document.createElement('div');
                        content
                            .classList
                            .add('wrap');

                        var infoDiv = document.createElement('div');
                        infoDiv
                            .classList
                            .add('info');

                        var titleDiv = document.createElement('div');
                        titleDiv
                            .classList
                            .add('title');
                        titleDiv.textContent = position.title;
                        var closeDiv = document.createElement('div');
                        closeDiv
                            .classList
                            .add('close');
                        closeDiv.title = '닫기';
                        closeDiv.addEventListener('click', closeOverlay);

                        titleDiv.appendChild(closeDiv);

                        var bodyDiv = document.createElement('div');
                        bodyDiv
                            .classList
                            .add('body');

                        var imgDiv = document.createElement('div');
                        imgDiv
                            .classList
                            .add('img');

                        var img = document.createElement('img');
                        img.src = position.firstImage;
                        if(position.firstImage === "") img.src= '../icon/basic_map_overlay.png'
                        img.width = 73;
                        img.height = 70;

                        imgDiv.appendChild(img);

                        var descDiv = document.createElement('div');
                        descDiv
                            .classList
                            .add('desc');

                        var ellipsis1Div = document.createElement('div');
                        ellipsis1Div
                            .classList
                            .add('ellipsis');
                        ellipsis1Div.textContent = position.addr1;

                        var ellipsis2Div = document.createElement('div');
                        ellipsis2Div
                            .classList
                            .add('jibun', 'ellipsis');
                        ellipsis2Div.textContent = position.addr2;


                        descDiv.appendChild(ellipsis1Div);
                        descDiv.appendChild(ellipsis2Div);

                        bodyDiv.appendChild(imgDiv);
                        bodyDiv.appendChild(descDiv);

                        infoDiv.appendChild(titleDiv);
                        infoDiv.appendChild(bodyDiv);

                        content.appendChild(infoDiv);

                        // 마커 위에 커스텀 오버레이를 표시합니다
                        var overlay = new kakao
                            .maps
                            .CustomOverlay({content: content, map: map, position: marker.getPosition()});

                        // 커스텀 오버레이를 닫기 위해 호출되는 함수입니다
                        function closeOverlay() {
                            overlay.setMap(null);
                        }

                    });

            });

        // 4. 지도를 이동시켜주기 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
        const bounds = positions
            .value
            .reduce(
                (bounds, position) => bounds.extend(position.latlng),
                new kakao.maps.LatLngBounds()
            );

        map.setBounds(bounds);
    };

    const deleteMarkers = () => {
        if (markers.value.length > 0) {
            markers
                .value
                .forEach((marker) => marker.setMap(null));
        }
    };
</script>

<template>
    <div>

        <div id="map"></div>
    </div>
</template>

<style>
    #map {
        width: 500px;
        height: 400px;
    }

    .wrap {
        position: absolute;
        left: 0;
        bottom: 40px;
        width: 288px;
        height: 132px;
        margin-left: -144px;
        text-align: left;
        overflow: hidden;
        font-size: 12px;
        font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
        line-height: 1.5;
    }
    .wrap * {
        padding: 0;
        margin: 0;
    }
    .wrap .info {
        width: 286px;
        height: 120px;
        border-radius: 5px;
        border-bottom: 2px solid #ccc;
        border-right: 1px solid #ccc;
        overflow: hidden;
        background: #fff;
    }
    .wrap .info:nth-child(1) {
        border: 0;
        box-shadow: 0 1px 2px #888;
    }
    .info .title {
        padding: 5px 0 0 10px;
        height: 30px;
        background: #eee;
        border-bottom: 1px solid #ddd;
        font-size: 18px;
        font-weight: bold;
    }
    .info .close {
        position: absolute;
        top: 10px;
        right: 10px;
        color: #888;
        width: 17px;
        height: 17px;
        background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
    }
    .info .close:hover {
        cursor: pointer;
    }
    .info .body {
        position: relative;
        overflow: hidden;
    }
    .info .desc {
        position: relative;
        margin: 13px 0 0 90px;
        height: 75px;
    }
    .desc .ellipsis {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .desc .jibun {
        font-size: 11px;
        color: #888;
        margin-top: -2px;
    }
    .info .img {
        position: absolute;
        top: 6px;
        left: 5px;
        width: 73px;
        height: 71px;
        border: 1px solid #ddd;
        color: #888;
        overflow: hidden;
    }
    .info:after {
        content: '';
        position: absolute;
        margin-left: -12px;
        left: 50%;
        bottom: 0;
        width: 22px;
        height: 12px;
        background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
    }
    .info .link {
        color: #5085BB;
    }
</style>