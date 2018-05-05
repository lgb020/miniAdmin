<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="scene" ng-controller="view">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
        <title>${scene.title}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mui.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/iconfont.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper-4.1.0.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/view.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/angular.min.js"></script>
        <script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
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
            <div class="bgm-btn rotate" style="background-image:url(${pageContext.request.contextPath}/img/view/music.svg);">
                <audio src="${scene.music}" autoplay loop id="music"></audio>
            </div>
            <!--问卷信息-->
            <div class="issue" id="issue">
                <i class="iconfont icon-review"></i>
            </div>
            <div id="info" class="mui-popover mui-popover-bottom mui-popover-action ">
                <ul class="mui-table-view">
                    <li class="mui-table-view-cell">
                        <div class="option_name">
                            <label><span class="star">*</span> 名字</label>
                            <input type="text" id="guest" name="guest" placeholder="请输入您的名字">
                        </div>
                        <div class="option_attend">
                            <label><span class="star">*</span> 活动</label>
                            <div class="attend">
                                <div class="mui-radio mui-left">
                                    <input name="attend" type="radio" value="1" checked="checked">
                                    <span>参加</span>
                                </div>
                                <div class="mui-radio mui-left">
                                    <input name="attend" type="radio" value="0">
                                    <span>不参加</span>
                                </div>
                            </div>
                        </div>
                        <div class="option_content">
                            <textarea id="content" name="content" rows="2" placeholder="有什么想说的可以写在这里..."></textarea>
                        </div>
                    </li>
                </ul>
                <ul class="mui-table-view submit">
                    <li class="mui-table-view-cell">
                        <a ng-click="submit()">提交</a>
                    </li>
                </ul>
            </div>
            <input type="hidden" value="${scene.id}" id="sceneId"/>
        </div>
    </body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/mui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper-4.1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/swiper.animate.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/view.js"></script>
</html>
