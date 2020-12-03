let timerId;
document.addEventListener('keydown', (event) => {
    if (event.keyCode === 116) {
        event.preventDefault();
    }
    if (event.keyCode === 91) {
       timerId = setTimeout(() => clearTimeout(timerId), 300);
    }
    if (timerId && event.keyCode === 82) {
        event.preventDefault();
    }
})
