$(document).ready(function(){

    const html = [
        '<ul>',
        '<li><a href="/CPD.dk">CPD.dk</a></li>',
        '<li><a href="/CPD.dk/pcr-test">PCR-TEST</a></li>',
        '<li><a href="/CPD.dk/vaccine">VACCINE</a></li>',
        '<li><a href="/CPD.dk/locations">LOCATIONS</a></li>',
        '<li><a href="/CPD.dk/info">INFO</a></li>',
        '</ul>'
    ].join('\n');

    $('.topnav').append(html);

});