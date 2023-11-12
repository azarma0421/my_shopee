
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
                const col = ["PKNO","NO","DT","amount","value"];
                tbody = data2table('order_data',col,data);

                addBTN2table(tbody,0,'Check','check',"onClickHandler();");

            })
            .catch(error => {
                console.error('[ERROR] Error in callAPI:', error);
            });
}

function onClickHandler() {
    test1();
    test2();
}

function test1(){
    alert(1);
}
function test2(){
    console.log(2);
}

function handleSubscribeLinks(){
    console.log(3);
}