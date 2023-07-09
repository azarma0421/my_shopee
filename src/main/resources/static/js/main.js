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

// item add to  cart
function addCartClicked(event) {
    var addButton = event.target;
    var productBox = addButton.parentElement;
    var pid = productBox.querySelector('.id').value;

//    test
    var userId = 1;

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

// get cart item by userId
function refreshCart(userId) {

    var url = '/cartdetail?userId=' + encodeURIComponent(userId);

    $.ajax({
        url: url,
        type: 'GET',
        success: function(response) {
            refreshCart(response);
          // Perform any further actions with the response data
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
      });
}

// refresh screen cart
function refreshCart(response) {
    // Find the cart content element
    var cartContent = $(".cart-content");

    // Clear the current cart content
    cartContent.empty();

    console.log(response);
    // Iterate over the items in the response and create new cart box elements
    response.forEach(function(item) {
        var cartBox = $("<div>").addClass("cart-box");
        var img = $("<img>").attr("src", item.src).addClass("cart-img");
        var detailBox = $("<div>").addClass("detail-box");
        var title = $("<div>").addClass("cart-product-title").text(item.name);
        var price = $("<div>").addClass("cart-price").text(item.cost);
        var quantity = $("<input>").attr("type", "number").addClass("cart-quantity").val(item.num);
        var removeIcon = $("<i>").addClass("bx bxs-trash-alt cart-remove");

        detailBox.append(title, price, quantity);
        cartBox.append(img, detailBox, removeIcon);
        cartContent.append(cartBox);
    });
}

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