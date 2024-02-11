function appendValue(value) {
    document.getElementById('display').value += value;
}

function calculate() {
    try {
        const result = eval(document.getElementById('display').value);
        document.getElementById('display').value = result;
    } catch (error) {
        showError();
    }
}

function clearDisplay() {
    document.getElementById('display').value = '';
}

function backspace() {
    const display = document.getElementById('display');
    display.value = display.value.slice(0, -1);
}

function showError() {
    document.getElementById('display').value = 'Error';
    setTimeout(() => clearDisplay(), 1000);
}

// Keyboard support
document.addEventListener('keydown', function(event) {
    const key = event.key;
    if (key.match(/[0-9+\-*/.]/)) {
        event.preventDefault();
        appendValue(key);
    } else if (key === 'Enter') {
        event.preventDefault();
        calculate();
    } else if (key === 'Backspace') {
        event.preventDefault();
        backspace();
    }
});
