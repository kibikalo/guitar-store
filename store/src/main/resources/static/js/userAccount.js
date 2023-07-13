const button1 = document.getElementById('information-button');
const button2 = document.getElementById('update-information-button');
const content1 = document.getElementById('information');
const content2 = document.getElementById('update-information');

button1.addEventListener('click', function() {
    content1.style.display = 'block';
    content2.style.display = 'none';
});

button2.addEventListener('click', function() {
    content1.style.display = 'none';
    content2.style.display = 'block';
});
