var slideIndex = 0;

showSlides();

function showSlides() {
  var dots = document.getElementsByClassName("dot");
  var i;
  var slides = document.getElementsByClassName("mySlides");
  console.log(slides);
  console.log(dots);
  if(slides.length ===0 || dots.length === 0) return;

  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) { slideIndex = 1 }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex - 1].style.display = "block";
  setTimeout(showSlides, 1500); // Change image every 2 seconds
  dots[slideIndex - 1].className += " active";
}

$(window).scroll(function () {
  if ($(this).scrollTop() > 50)  /*height in pixels when the navbar becomes non opaque*/ {
    $('.opaque-navbar').addClass('opaque');
  } else {
    $('.opaque-navbar').removeClass('opaque');
  }
});
