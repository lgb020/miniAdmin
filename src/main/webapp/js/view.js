var scene = angular.module("scene", []);
scene.controller("view", function($scope, $http) {
    mui.init();
    var root = "http://www.hsfeng.cn/scene/";
    var id = document.getElementById("sceneId").value;
    //获取场景单页信息
    $scope.info = new Array(); //场景
    $scope.pageInfo = function(id) {
        $http({
            method: "GET",
            url: root + "s/list.html",
            params: {
                v: "1.0",
                sceneId: id
            }
        }).then(function successCallback(response) {
            for(var i = 0; i < response.data.length; i++) {
                var page = new Array();
                page.bgUrl = response.data[i].background;
                page.color = response.data[i].bgcolor;
                $scope.info.push(page);
                var content = JSON.parse(response.data[i].content); //对内容反序列化
                for(var j = 0; j < content.length; j++) {
                    $scope.info[i].push(content[j]);
                }
            }
        });
    }
    $scope.pageInfo(id); //场景单页信息

    //点击音乐
    mui(".mui-content").on("tap", ".bgm-btn", function() {
        var result = this.classList.contains("rotate");
        var music = document.getElementById("music");
        if(result) {
            music.pause();
            this.classList.remove("rotate");
        } else {
            music.play();
            this.classList.add("rotate");
        }
    });

    mui(".mui-content").on("tap", "#issue", function() {
        mui("#info").popover("toggle");
    });

    $scope.submit = function() {
        var guest = document.getElementById("guest").value;
        var radio = document.getElementsByName("attend");
        var isAttend;
        for(i = 0; i < radio.length; i++) {
            if(radio[i].checked) {
                isAttend = radio[i].value;
            }
        }
        var content = document.getElementById("content").value;
        var sceneId = document.getElementById("sceneId").value;
        if(guest!=""){
            $http({
                method: "GET",
                url: root + "s/manage/collection.html",
                params: {
                    v: "1.0",
                    sceneId: sceneId,
                    guest:guest,
                    isAttend:isAttend,
                    content:content,
                    ip: returnCitySN.cip
                }
            }).then(function successCallback(response) {
                mui("#info").popover("toggle");
                if(response.data>0){
                    mui.toast("提交成功");
                }else{
                    mui.toast("请勿重复提交数据");
                }
            });
        }else{
            mui.toast("名字不能为空");
        }
    }
});

scene.filter("to_draw", ["$sce", function($sce) {
    return function(text) {
        return $sce.trustAsHtml(text);
    }
}]);

var swiper = new Swiper(".swiper-container", {
    direction: "vertical",
    resistanceRatio: 0,
    observer: true,
    observeParents: true,
    pagination: {
        el: ".swiper-pagination",
    },
    on: {
        init: function() {
            swiperAnimateCache(this);
            swiperAnimate(this);
        },
        slideChangeTransitionStart: function() {
            swiperAnimate(this);
        }
    }
});

setTimeout(function(){
    swiperAnimate(swiper);
},1000);