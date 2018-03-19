<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="scene" ng-controller="view">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
        <title>${scene.title}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mui.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper-4.1.0.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/angular.min.js"></script>
    </head>
    <body>
        <div class="mui-content">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" ng-repeat="pages in info track by $index">
                        <div class="page">
                            <div class="bg" style="background-image: url({{pages.bgUrl}});background-color: {{pages.color}};">
                                <ul class="view">
                                    <li ng-repeat="list in pages track by $index" class="ani" style="{{list.liCss}}"
                                        swiper-animate-effect="{{list.effect}}" swiper-animate-duration="{{list.duration}}" swiper-animate-delay="{{list.delay}}">
                                        <span ng-bind-html="list.content|to_draw" style="{{list.divCss}}"></span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-pagination"></div>
            </div>
            <div class="bgm-btn rotate">
                <audio src="${scene.music}" autoplay loop id="music"></audio>
            </div>
            <input type="hidden" value="${scene.id}" id="sceneId"/>
        </div>
    </body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper-4.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.animate.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/view.js"></script>
</html>
