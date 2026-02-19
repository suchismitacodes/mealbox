// Products.js — MealBox products page interactivity

document.addEventListener('DOMContentLoaded', function () {

    // Wishlist toggle
    document.querySelectorAll('.wishlist-icon').forEach(function (icon) {
        icon.addEventListener('click', function () {
            if (this.style.color === 'rgb(255, 68, 68)') {
                this.style.color = '#ccc';
                this.title = 'Add to wishlist';
            } else {
                this.style.color = '#ff4444';
                this.title = 'Remove from wishlist';
            }
        });
    });

    // Smooth scroll to category headings
    document.querySelectorAll('.category h2').forEach(function (heading) {
        heading.style.cursor = 'pointer';
        heading.addEventListener('click', function () {
            this.closest('.category').scrollIntoView({ behavior: 'smooth' });
        });
    });
});
