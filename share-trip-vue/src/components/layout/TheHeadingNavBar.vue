<script setup="setup"></script>

<template>
    <header>
        <!-- 네비게이션 바 영역 -->

        <nav
            class="navbar navbar-expand-sm"
            style="background-color: rgb(255, 239, 215)">
            <div class="container-fluid">
                <router-link :to="{name : 'main'}">
                    <img
                        src="@/assets/img/logo.png"
                        style="width: 200px; height: 70px; margin-left: 15px; margin-top: 10px">
					</router-link>

                    <ul class="navbar-nav ms-auto">

                        <li class="nav-item">

                            <router-link
                                :to="{name : 'attraction'}"
                                class="btn btn-outline-info"
                                style="font-size: 15px; font-weight: 550; margin-top: 6px; margin-right: 10px">
                                관광지
                            </router-link>
                        </li>

                        <li class="nav-item">
                            <router-link
                                :to="{name : 'board'}"
                                class="btn btn-outline-info"
                                style="font-size: 15px; font-weight: 550; margin-top: 6px; margin-right: 10px">
                                게시판</router-link>
                        </li>

                        <!-- <c:if test="${empty userinfo }"> -->
                        <li class="nav-item" id="login">
                            <router-link
                                :to="{name : 'login'}"
                                class="btn btn-outline-info"
                                data-bs-toggle="modal"
                                data-bs-target="#loginModal"
                                style="font-size: 15px; font-weight: 550; margin-top: 6px; margin-right: 10px">
                                로그인</router-link>
                        </li>

                        <li class="nav-item" style="margin-right: 15px;" id="register">
                            <router-link
                                :to="{name: 'join'}"
                                type="button"
                                class="btn btn-outline-info"
                                style="font-size: 15px; font-weight: 550; margin-top: 6px">
                                회원가입</router-link>
                        </li>
                        <!-- </c:if> -->
                        <!-- <c:if test="${!empty userinfo }"> -->
                        <li class="nav-item" id="mypage">
                            <router-link
                                :to="{name: 'mypage'}"
                                class="btn btn-outline-info"
                                style="font-size: 15px; font-weight: 550; margin-top: 6px; margin-right: 10px">
                                마이페이지</router-link>
                        </li>
                        <li class="nav-item" id="logout">
                            <router-link
                                :to="{name: 'logout'}"
                                class="btn btn-outline-info"
                                style="font-size: 15px; font-weight: 550; margin-top: 6px; margin-right: 10px">
                                로그아웃</router-link>
                        </li>
                        <!-- </c:if> -->

                    </ul>
                </div>
            </nav>
        </header>
        <div
            class="position-absolute w-100"
            style="height: 60%; background-color: rgb(255, 239, 215); z-index: -1"></div>
    </template>

    <style scoped="scoped"></style>