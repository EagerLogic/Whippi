/* 
 * Whippi frontend
 * version: 0.1a
 */


window.Whippi = {

    init: function (modelStr) {
        Whippi.model = JSON.parse(modelStr).model;
    },

    callBackendMethod: function (methodName, params) {
        var data = {};
        data.methodName = methodName;
        data.params = params;
        data.model = Whippi.model;
        data.title = document.title;

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
        xmlhttp.open("POST", window.location.href);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify(data));
        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (xmlhttp.status == 200) {
                    var resp = JSON.parse(xmlhttp.responseText);
                    if (resp.redirect != null) {
                        window.location.href = resp.redirect;
                        return;
                    }
                    
                    if (resp.title != null) {
                        document.title = resp.title;
                    }
                    
                    Whippi.model = resp.model;
                    Whippi.render(resp.html);
                } else {
                    console.error("Network request failed!");
                }
            }
        }
    },

    render: function (html) {
        var root = document.getElementById("whippiRoot");
        root.innerHTML = html;
        // TODO keep scroll bar positions
    }

};


