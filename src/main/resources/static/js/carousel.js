document.addEventListener("DOMContentLoaded", function() {
    let carouselInner = document.getElementById("carousel-inner");
    let items = Array.from(carouselInner.getElementsByClassName("carousel-item"));
    shuffleArray(items);
    carouselInner.innerHTML = "";
    items.forEach((item, index) => {
        if (index === 0) {
            item.classList.add("active");
        }
        carouselInner.appendChild(item);
    });
});

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}