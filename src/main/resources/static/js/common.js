/*
**
** apiUrl 呼叫之api
** method 呼叫方式
**
callAPI(apiUrl, 'GET')
    .then(data => {
        console.error('[SUCCESS]');
    })
    .catch(error => {
        console.error('[ERROR]');
    });
*/
    function callAPI(apiUrl, method) {
        return new Promise(async (resolve, reject) => {
            try {
                const response = await fetch(apiUrl, {
                    method: method
                });

                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                const contentType = response.headers.get("content-type");
                if (contentType && contentType.includes("application/json")) {
                    const data = await response.json();
                    resolve(data);
                } else {
                    const data = await response.text();
                    resolve(data);
                }
            } catch (error) {
                console.error('Error:', error);
                reject(error);
            }
        });
    }

/*
**
** list id集合
** method 呼叫方式
**
*/
    function getHTMLdata(list){

        var param = {};
        for (var i = 0; i < list.length; i++) {
            var element = document.getElementById(list[i]);
            var data;

            if (element.type === 'text') {
                data = element.value;
            } else if(element.type === 'radio'){
                var target = 'input[name="' + element.name + '"]:checked';
                data = document.querySelector(target).value
                list[i] = element.name;
            }


            param[list[i]] = data;
        }
        console.log("param: ",param);
        return param;
    }

/*
**
** list id集合 radio 隨便放一個選項的id
**
*/
    function clean(list){
        for (var i = 0; i < list.length; i++) {
            var element = document.getElementById(list[i]);

            if (element.type === 'text') {
                element.value = "";
            } else if(element.type === 'radio'){
                document.getElementsByName(element.name)[0].checked = true;
            }
        }
    }