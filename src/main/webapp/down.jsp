
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>小李跟刀App</title>
</head>

<body>
<style type="text/css">
    * {
        margin: 0;
        padding: 0;
    }

    img {
        max-width: 100%;
        height: auto;
    }

    .drawDownAppText {
        height: 100vh;
        max-width: 100vw;
        font-size: 32px;
        text-align: center;
        padding-top: 20%;
    }

    .drawDownAppText a {
        text-decoration: none;
    }
</style>
<div class="drawDownAppText">
    <a href="akec.apk">点击下载小李跟刀App</a>
</div>
<div></div>
<script type="text/javascript">
    function is_weixin() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }
    var isWeixin = is_weixin();
    var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;

    function loadHtml() {
        var div = document.createElement('div');
        div.id = 'weixin-tip';
        div.innerHTML = '<p><img src="live_weixin.png" alt="微信打开"/></p>';
        document.body.appendChild(div);
    }

    function loadStyleText(cssText) {
        var style = document.createElement('style');
        style.rel = 'stylesheet';
        style.type = 'text/css';
        try {
            style.appendChild(document.createTextNode(cssText));
        } catch (e) {
            style.styleSheet.cssText = cssText; //ie9以下
        }
        var head = document.getElementsByTagName("head")[0]; //head标签之间加上style样式
        head.appendChild(style);
    }
    var cssText = "#weixin-tip{position: fixed; left:0; top:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80); width: 100%; height:100%; z-index: 100;} #weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%;}";
    if (isWeixin) {
        loadHtml();
        loadStyleText(cssText);
    }
    else{
        window.location.href="akec.apk";
    }
</script>
</body>

</html>
