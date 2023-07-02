// Cart
let cartIcon = document.querySelector("#cart-icon");
let cart = document.querySelector(".cart");
let closeCart = document.querySelector("#close-cart");

// open cart
cartIcon.onclick = () => {
        cart.classList.add("active");
        console.log(1);
    }
    // close cart
closeCart.onclick = () => {
    cart.classList.remove("active");
}

if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready);
} else {
    // refreshCache();
    ready();
}

function refreshCache() {
    var url = window.location.href;
    var cacheBustedUrl = url + '?timestamp=' + new Date().getTime();

    window.location.href = cacheBustedUrl;
}

function ready() {
    var removeCartButtons = document.getElementsByClassName('cart-remove');
    for (var i = 0; i < removeCartButtons.length; i++) {
        var button = removeCartButtons[i];
        button.addEventListener('click', removeCartItem);
    }

    var quantityInput = document.getElementsByClassName('cart-quantity');
    for (var i = 0 ; i < quantityInput.length ; i++){
        var input = quantityInput[i];
        input.addEventListener("change", quantityChange);
    }

    var addCart = document.getElementsByClassName('add-cart');
    for (var i = 0 ; i < addCart.length ; i++){
        var button = addCart[i];
        button.addEventListener("click", addCartClicked);
    }
}

function addCartClicked(event) {
    var addButton = event.target;
    var productBox = addButton.parentElement;
    var id = productBox.querySelector('.id').value;

//    test
    var userId = 1;

    var url = '/add?id=' + encodeURIComponent(id)
    + '&userId=' + encodeURIComponent(userId);

    $.ajax({
        url: url,
        type: 'GET',
        success: function(response) {
          console.log(response);
          // Perform any further actions with the response data
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
      });
}

//function addCartClicked(event) {
//    var addButton = event.target;
//    var productBox = addButton.parentElement;
//    var addCart = document.getElementsByClassName('cart-content');
//
//    var imageSrc = productBox.querySelector('img.product-img').getAttribute('src');
//    var name = productBox.querySelector('h2').innerText;
//    var price = productBox.querySelector('span').innerText;
//
//// new div1
//    var newDiv = document.createElement('div');
//    newDiv.classList.add('cart-box');
//    addCart[0].appendChild(newDiv);
//
//// new img
//    var newImg = document.createElement('img');
//    newImg.classList.add('cart-img');
//    newImg.setAttribute('src', imageSrc);
//    newDiv.appendChild(newImg);
//
//// new div2 in div1
//    var newDiv2 = document.createElement('div');
//    newDiv2.classList.add('detail-box');
//    newDiv.appendChild(newDiv2);
//
//// new div3 in div2
//    var newDiv3 = document.createElement('div');
//    newDiv3.classList.add('cart-product-title');
//    newDiv3.innerText = name;
//    newDiv2.appendChild(newDiv3);
//
//// new div4 in div2
//    var newDiv4 = document.createElement('div');
//    newDiv4.classList.add('cart-price');
//    newDiv4.innerText = price;
//    newDiv2.appendChild(newDiv4);
//
//// new input in div2
//    var newInput = document.createElement('input');
//    newInput.setAttribute('type', 'number');
//    newInput.setAttribute('value', '1');
//    newInput.classList.add('cart-quantity');
//    newDiv2.appendChild(newInput);
//
//    var newI = document.createElement('i');
//    newI.classList.add('bx');
//    newI.classList.add('bxs-trash-alt');
//    newI.classList.add('cart-remove');
//    newDiv.appendChild(newI);
//}

function removeCartItem(event) {
    var buttonClicked = event.target;
    buttonClicked.parentElement.remove();
    updatetotal();
}

function quantityChange(event){
    var input = event.target;
    if(isNaN(input.value) || input.value <= 0) {
        input.value = 1;
    }
    updatetotal();
}

function quantityChange(event) {
    var button = event.target;
    var shopProducts = button.parentElement;
}

function updatetotal(){
    var cartContent = document.getElementsByClassName('cart-content')[0];
    var cartBoxes = cartContent.getElementsByClassName('cart-box');
    var total = 0;
    for (var i = 0; i < cartBoxes.length; i++) {
        var cartBox = cartBoxes[i];
        var priceElement = cartBox.getElementsByClassName('cart-price')[0];
        var quantityElement = cartBox.getElementsByClassName('cart-quantity')[0];
        var price = parseFloat(priceElement.innerText.replace("$",""));
        var quantity = quantityElement.value;
        total +=  (price * quantity);

        document.getElementsByClassName('total-price')[0].innerText = "$" + total;
    }
}