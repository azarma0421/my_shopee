// Cart
let cartIcon = document.querySelector("#cart-icon");
let cart = document.querySelector(".cart");
let closeCart = document.querySelector("#close-cart");

// user info
const role = document.getElementById("role").value;
const userId = document.getElementById("uid").value;


// open cart
cartIcon.onclick = () => {
        cart.classList.add("active");
    }
    // close cart
closeCart.onclick = () => {
    cart.classList.remove("active");
}

if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready);
} else {
    ready();
}

// unused
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

// error
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

// show setting btn
    var form = document.getElementById("setting_form");
    if(role == 'ROLE_ADMIN'){
        form.style.display = "block";
    }else{
        form.style.display = "none";
    }

    var btn_buy = document.getElementById('btn-buy');
    btn_buy.addEventListener("click", processBuy);
}

// add item to  cart
function addCartClicked(event) {
    var addButton = event.target;
    var productBox = addButton.parentElement;
    var pid = productBox.querySelector('.id').value;

    var url = '/cart?pid=' + encodeURIComponent(pid)
    + '&userId=' + encodeURIComponent(userId);

    $.ajax({
        url: url,
        type: 'PUT',
        success: function(response) {
            refreshCart(userId);
          // Perform any further actions with the response data
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
      });
}

// buy them all
function processBuy() {
    var url = '/Checkout?userId=' + encodeURIComponent(userId);
    $.ajax({
        url: url,
        type: 'PUT',
        success: function(response) {
            refreshCart(userId);
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
      });
}

// delete item from cart
function delFromCart(event) {
    var delButton = event.target;
    var productBox = delButton.parentElement;
    var pid = productBox.querySelector('.id').value;

    var url = '/cart?pid=' + encodeURIComponent(pid)
    + '&userId=' + encodeURIComponent(userId);

    $.ajax({
        url: url,
        type: 'DELETE',
        success: function(response) {
            refreshCart(userId);
          // Perform any further actions with the response data
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
      });
}

// get cart item by userId
function refreshCart(userId) {

    var url = '/cartdetail?userId=' + encodeURIComponent(userId);

    $.ajax({
        url: url,
        type: 'GET',
        success: function(response) {
            buildCart(response);
          // Perform any further actions with the response data
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
      });
}

// refresh screen cart
function buildCart(response) {
    // Find the cart content element
    var cartContent = $(".cart-content");
//    var total = $(".total-price");
    var cart_footer = $("#cart-footer");

    // Clear the current cart content
    cartContent.empty();
//    total.empty();

    // Iterate over the items in the response and create new cart box elements
    response.forEach(function(item) {
        var cartBox = $("<div>").addClass("cart-box");
        var pid = $("<input>").attr("type", "hidden").addClass("id").val(item.id);
        var img = $("<img>").attr("src", item.src).addClass("cart-img");
        var detailBox = $("<div>").addClass("detail-box");
        var title = $("<div>").addClass("cart-product-title").text(item.name);
        var price = $("<div>").addClass("cart-price").text(item.cost);
        var quantity = $("<input>").attr("type", "number").addClass("cart-quantity").val(item.num);
        var removeIcon = $("<i>").addClass("bx bxs-trash-alt cart-remove");

        detailBox.append(title, price, quantity);
        cartBox.append(pid,img, detailBox, removeIcon);
        cartContent.append(cartBox);
    });

    cart_footer.empty();
    if(response.length === 0 ){
        console.log("response is empty");

        var msg = $("<div>").text("No items in the cart.");
        cartContent.append(msg);
        cart_footer.empty();
    }else{
        console.log("response is not empty");
        ready();

        var total = $("<div>").addClass("total");
        var total_title = $("<div>").addClass("total-title").text("Total");
        var total_price = $("<div>").addClass("total-price").text("Total");
        var Buy_Button = $("<button>").attr("type", "button").addClass("btn-buy").text("But Now");

        total.append(total_title,total_price);
        cart_footer.append(total,Buy_Button);
        total.text(response[0].total);
        $("#cart").append(cart_footer);
    }
}

function removeCartItem(event) {
    var buttonClicked = event.target;
    delFromCart(event);
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

    console.log("cartBoxes: ",cartBoxes.length);
    for (var i = 0; i < cartBoxes.length; i++) {
        var cartBox = cartBoxes[i];
        var priceElement = cartBox.getElementsByClassName('cart-price')[0];
        var quantityElement = cartBox.getElementsByClassName('cart-quantity')[0];
        var price = parseFloat(priceElement.innerText.replace("$",""));
        var quantity = quantityElement.value;
        total +=  (price * quantity);
    }
}