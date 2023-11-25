
if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready);
} else {
    ready();
}

function ready() {
// user info
    userId = document.getElementById("uid").value;
    processQuery_R(userId);
}

function change_status(button) {
    var status = button.innerHTML;
    var form = document.getElementById("order_table");
    if(status == 'SHOW'){
        button.innerHTML = 'HIDE';
        form.style.display = 'block';
    } else{
        button.innerHTML = 'SHOW';
        form.style.display = 'none';
    }
}

function processQuery_R(userId){
    var param = {};
    var list = ['NO','Date_Time_S','Date_Time_E','amount','status'];
    param = getHTMLdata(list);
    param['userId'] = userId;

    var apiUrl = window.location.href;
    var jsonParam = JSON.stringify(param);
    apiUrl = makeURL(apiUrl,jsonParam);

    callAPI(apiUrl, 'GET')
            .then(data => {
                console.log('order page: ',data);
                const col = ["NO","NO","DT","amount","value"];
                tbody = data2table('order_data',col,data);

                addBTN2table(tbody,0,'Check','check',"onClickHandler(this.id);");

            })
            .catch(error => {
                console.error('[ERROR] Error in callAPI:', error);
            });
}

function onClickHandler(NO) {
    var param = {};
    param['NO'] = NO;

    var apiUrl = window.location.href;
    apiUrl += "/Detail";
    var jsonParam = JSON.stringify(param);
    apiUrl = makeURL(apiUrl,jsonParam);

    callAPI(apiUrl, 'GET')
        .then(data => {
            const col = ["Name","NP","TP"];
            data2table('table2',col,data);
        })
        .catch(error => {
            console.error('[ERROR] Error in callAPI:', error);
        });
}
