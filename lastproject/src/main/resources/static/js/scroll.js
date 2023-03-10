// Show or hide the scroll to top button depending on the scroll position
function showHideScrollButton() {
    const scrollToTopBtn = document.getElementById("scrollToTopBtn");
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        scrollToTopBtn.style.display = "block";
    } else {
        scrollToTopBtn.style.display = "none";
    }
}

// Scroll to the top of the page when the user clicks the button
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

// Call the showHideScrollButton function when the user scrolls the page
window.onscroll = function() {showHideScrollButton()};