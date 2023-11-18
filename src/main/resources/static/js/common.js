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
** 將json加入中
**
** url 呼叫之api
** json 呼叫參數
**
*/
    function makeURL(url,json = ""){
        var encodedJson = encodeURIComponent(json);
        apiUrl = url + '?json=' +encodedJson;
        return apiUrl;
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
            } else if(element.type === 'select-one'){
                data = element.value;
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
            } else if(element.type === 'file'){
                element.value = "";
            } else if(element.type === 'select-one'){
                element.value=0;
            }
        }
    }

/*
** auto_append 1 自動append進table 0 返回tbody (暫時移除)
** tableId 目標tableID
** column 資料及欄位ID集合，需對應頁面順序
** data 資料集index
*/
    function data2table(tableId,columnMap,data){

        var table = document.getElementById(tableId);
        //清空tbody
        var tbody = table.querySelector("tbody");

        if(tbody != null){
            while (tbody.firstChild) {
              tbody.removeChild(tbody.firstChild);
            }
        }

        //組成tbody
        for (var i=0;i<data.length;i++){
            var trRow = document.createElement("tr");
            for (var col in columnMap){

                var tdCell = document.createElement("td");
                var colname;
                if(col == ""){
                    colname = "";
                    tdCell.textContent = "";
                } else {
                    colname = columnMap[col];
                    tdCell.textContent = data[i][colname];
                }
                trRow.appendChild(tdCell);
            }
            tbody.appendChild(trRow);
        }
        return tbody;
    }

/*
** tbody
** target 目標欄位，第幾個td
** btn 按鈕value
** btn 按鈕name
** func 按鈕觸發func名稱
**
*/
    function addBTN2table(tbody,target,btn_val,btn_nm,functions){
        var rows = tbody.getElementsByTagName("tr");
          for (var i = 0; i < rows.length; i++) {
            var cells = rows[i].getElementsByTagName("td");
            for (var j = 0; j < cells.length; j++) {
                if(j==target){
                    var button = document.createElement("button");
                    button.id = cells[j].innerText;
                    button.innerText = btn_val;
                    button.type = "button";
                    button.setAttribute("onclick", functions);
                    cells[j].parentNode.replaceChild(button, cells[j]);
                }
            }
          }
    }