(async function () {
    const response = await fetch('/header.html');
    const text = await response.text();
    document.getElementById('header').innerHTML = text;
})();

(async function () {
    const response = await fetch('/menu.html');
    const text = await response.text();
    document.getElementById('menu').innerHTML = text;
})();