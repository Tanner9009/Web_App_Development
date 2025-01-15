
function displayReviews() {
    document.getElementById("reviewsSection").style.display = "block";
    document.getElementById("collectionSection").style.display = "none";
    document.getElementById("wishlistSection").style.display = "none";
}

function displayCollection() {
    document.getElementById("reviewsSection").style.display = "none";
    document.getElementById("collectionSection").style.display = "block";
    document.getElementById("wishlistSection").style.display = "none";
}

function displayWishlist() {
    document.getElementById("reviewsSection").style.display = "none";
    document.getElementById("collectionSection").style.display = "none";
    document.getElementById("wishlistSection").style.display = "block";
}
