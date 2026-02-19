/* ═══════════════════════════════════════════════════════════
   MealBox — Global Interactions
   ═══════════════════════════════════════════════════════════ */

(function () {
    'use strict';

    /* ── Navbar Scroll Effect ──────────────────────────────── */
    const navbar = document.getElementById('navbar');

    if (navbar) {
        const onScroll = () => {
            navbar.classList.toggle('scrolled', window.scrollY > 10);
        };
        window.addEventListener('scroll', onScroll, { passive: true });
        onScroll();
    }

    /* ── Mobile Hamburger Toggle ───────────────────────────── */
    const hamburger = document.getElementById('navHamburger');
    const navMenu   = document.getElementById('navMenu');
    const overlay   = document.getElementById('navOverlay');

    function closeMobileMenu() {
        hamburger?.classList.remove('active');
        navMenu?.classList.remove('active');
        overlay?.classList.remove('active');
        document.body.style.overflow = '';
    }

    function openMobileMenu() {
        hamburger?.classList.add('active');
        navMenu?.classList.add('active');
        overlay?.classList.add('active');
        document.body.style.overflow = 'hidden';
    }

    if (hamburger) {
        hamburger.addEventListener('click', () => {
            const isOpen = navMenu?.classList.contains('active');
            isOpen ? closeMobileMenu() : openMobileMenu();
        });
    }

    overlay?.addEventListener('click', closeMobileMenu);

    // Close mobile menu when a nav link is clicked
    document.querySelectorAll('.nav-menu .nav-link').forEach(link => {
        link.addEventListener('click', closeMobileMenu);
    });

    /* ── Wishlist Toggle ───────────────────────────────────── */
    document.addEventListener('click', (e) => {
        const btn = e.target.closest('.wishlist-btn');
        if (!btn) return;
        e.preventDefault();
        e.stopPropagation();

        btn.classList.toggle('active');
        const icon = btn.querySelector('i');
        if (icon) {
            icon.classList.toggle('fa-regular');
            icon.classList.toggle('fa-solid');
        }

        // Micro-animation
        btn.style.transform = 'scale(1.3)';
        setTimeout(() => { btn.style.transform = ''; }, 200);
    });

    /* ── Quantity Controls ─────────────────────────────────── */
    document.addEventListener('click', (e) => {
        const btn = e.target.closest('.qty-control button');
        if (!btn) return;

        const input = btn.parentNode.querySelector('input[type="number"]');
        if (!input) return;

        if (btn.textContent.trim() === '+') {
            input.stepUp();
        } else if (btn.textContent.trim() === '−' || btn.textContent.trim() === '-') {
            input.stepDown();
        }
    });

    /* ── Delete Confirmations ──────────────────────────────── */
    document.addEventListener('click', (e) => {
        const deleteBtn = e.target.closest('a[onclick*="confirm"]');
        if (deleteBtn) return; // Let inline handler manage it

        // Fallback for links with .delete class without inline onclick
        const delLink = e.target.closest('.table-action-btn.delete:not([onclick])');
        if (delLink) {
            if (!confirm('Are you sure you want to delete this item?')) {
                e.preventDefault();
            }
        }
    });

    /* ── Scroll-Triggered Stagger Animations ───────────────── */
    const staggerContainers = document.querySelectorAll('.stagger');

    if (staggerContainers.length > 0 && 'IntersectionObserver' in window) {
        const observerOptions = {
            root: null,
            rootMargin: '0px 0px -60px 0px',
            threshold: 0.1
        };

        const staggerObserver = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                    staggerObserver.unobserve(entry.target);
                }
            });
        }, observerOptions);

        staggerContainers.forEach(el => staggerObserver.observe(el));
    }

    /* ── Smooth Scroll for Anchor Links ────────────────────── */
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', (e) => {
            const target = document.querySelector(anchor.getAttribute('href'));
            if (target) {
                e.preventDefault();
                target.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }
        });
    });

    /* ── Toast Notification System ─────────────────────────── */
    window.MealBox = window.MealBox || {};

    window.MealBox.toast = function (message, type) {
        type = type || 'info';

        // Create container if it doesn't exist
        let container = document.querySelector('.toast-container');
        if (!container) {
            container = document.createElement('div');
            container.className = 'toast-container';
            document.body.appendChild(container);
        }

        const icons = {
            success: 'fa-circle-check',
            error:   'fa-circle-xmark',
            warning: 'fa-triangle-exclamation',
            info:    'fa-circle-info'
        };

        const toast = document.createElement('div');
        toast.className = 'toast toast-' + type;
        toast.innerHTML =
            '<i class="fa-solid ' + (icons[type] || icons.info) + '"></i>' +
            '<span>' + message + '</span>' +
            '<button class="toast-close" aria-label="Close">&times;</button>';

        container.appendChild(toast);

        // Close on click
        toast.querySelector('.toast-close').addEventListener('click', () => {
            dismissToast(toast);
        });

        // Auto-dismiss after 4 seconds
        setTimeout(() => dismissToast(toast), 4000);
    };

    function dismissToast(toast) {
        if (!toast || toast.dataset.dismissing) return;
        toast.dataset.dismissing = 'true';
        toast.style.animation = 'toastOut 0.3s var(--ease-out) forwards';
        toast.addEventListener('animationend', () => toast.remove());
    }

    /* ── Form Validation UX Enhancement ────────────────────── */
    document.querySelectorAll('.form-input').forEach(input => {
        input.addEventListener('invalid', (e) => {
            input.classList.add('error');
        });

        input.addEventListener('input', () => {
            if (input.classList.contains('error') && input.validity.valid) {
                input.classList.remove('error');
            }
        });
    });

})();
