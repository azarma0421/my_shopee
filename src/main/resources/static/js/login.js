if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready);
} else {
    ready();
}

function ready() {
    var sign_up_a = document.getElementById('sign_up');
    sign_up_a.addEventListener('click', sign_up);
    var forgot_a = document.getElementById('forgot');
    forgot_a.addEventListener('click', forgot);

    var cancel_btn = document.getElementsByName('Cancel');
    for (var i = 0; i < cancel_btn.length; i++) {
        var button = cancel_btn[i];
        button.addEventListener('click', back);
    }

//
    var sign_up_btn = document.getElementById('sign_up_btn');
    sign_up_btn.addEventListener('click', sign_up_check);

    var forgot_btn = document.getElementById('forgot_btn');
    forgot_btn.addEventListener('click', get_PW);

    var allLinks = document.querySelectorAll('a');
    allLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // 阻止默認行為
        });
    });
}

function sign_up(){
    var block = document.getElementById('login_block');
    block.style.display = 'none';
    block = document.getElementById('forgot_block');
    block.style.display = 'none';
    block = document.getElementById('sign_up_block');
    block.style.display = 'block';
    var header = document.getElementById('card-header');
    header.innerHTML = 'Sign Up';
}

function forgot(){
    var block = document.getElementById('login_block');
    block.style.display = 'none';
    block = document.getElementById('sign_up_block');
    block.style.display = 'none';
    block = document.getElementById('forgot_block');
    block.style.display = 'block';
    var header = document.getElementById('card-header');
    header.innerHTML = 'Forgot Password';
}

function back(){
    var block = document.getElementById('forgot_block');
    block.style.display = 'none';
    block = document.getElementById('sign_up_block');
    block.style.display = 'none';
    block = document.getElementById('login_block');
    block.style.display = 'block';
    var header = document.getElementById('card-header');
    header.innerHTML = 'Sign In';
}

function sign_up_check(){

    var password1 = document.getElementById('s_password').value;
    var password2 = document.getElementById('s_password2').value;
    console.log("password2: ","" == password1);
    if(password1 != password2 || "" == password1 ){
        alert("The password is different!");
        return;
    }
    var list = ['s_username','s_mail','s_password'];
    param = getHTMLdata(list);
    var apiUrl = window.location.href;
    apiUrl = apiUrl.split('?');
    apiUrl = apiUrl[0];
    var jsonParam = JSON.stringify(param);
    apiUrl = makeURL(apiUrl,jsonParam);

    callAPI(apiUrl, 'POST')
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error('[ERROR] Error in callAPI:', error);
        });
}

function get_PW(){
    var list = ['f_username','f_mail'];
    param = getHTMLdata(list);
    var apiUrl = window.location.href;
    apiUrl = apiUrl.split('?');
    apiUrl = apiUrl[0] + "/PW";
    var jsonParam = JSON.stringify(param);
    apiUrl = makeURL(apiUrl,jsonParam);

    get_PW_API(apiUrl, 'GET')
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error('[ERROR] Error in callAPI:', error);
        });

}

function get_PW_API(apiUrl, method) {
    return new Promise(async (resolve, reject) => {
        try {
            const response = await fetch(apiUrl, {
                redirect: 'manual',
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