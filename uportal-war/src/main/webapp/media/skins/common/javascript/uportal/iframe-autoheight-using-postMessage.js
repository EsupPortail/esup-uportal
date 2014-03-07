(function ($) {
    var receiveIframeHeight = function(frameWindow, height) {
        $("iframe").each(function () {
            if (this.contentWindow === frameWindow) this.height = height;
        });
    };
    var onmessage = function(e) {
        if (typeof e.data === "string") {
            var m = e.data.match(/^iframeHeight (\d+)$/);
            if (m) receiveIframeHeight(e.source, parseInt(m[1]));
        }
    };

    if (window.addEventListener) {  
        window.addEventListener("message", onmessage, false);  
    } else {  
        window.attachEvent("onmessage", onmessage); // for IE
    }
})(jQuery);

