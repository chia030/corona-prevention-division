$(document).ready(function(){

    const html = [
        '<ul>',
        '<li><img src="https://i.ibb.co/VMQ8rtj/corona-prevention-logo.png" alt="Corona Prevention Division" class="logo"/></li>',
        '<li><a href="/">Home</a></li>',
            '<li><a href="/test">Testing</a></li>',
            '<li><a href="/vaccine">Vaccination</a></li>',
        '</ul>',
        '<div class="nav-shadow"></div>'
    ].join('\n');

    $('.topnav').append(html);

});