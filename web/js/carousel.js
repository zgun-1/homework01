let slideIndex = 0;
showSlides();

function showSlides() {
    let i;
    let slides = document.getElementsByClassName("carousel-item");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}
    slides[slideIndex-1].style.display = "block";
    setTimeout(showSlides, 3000); // Change image every 3 seconds
}

function moveSlide(n) {
    slideIndex = n;
    showSlides();
}

// 当页面加载完成后，设置第一个图片为活跃状态
window.onload = function() {
    let slides = document.getElementsByClassName("carousel-item");
    if (slides.length > 0) {
        slides[0].classList.add("active");
    }
};