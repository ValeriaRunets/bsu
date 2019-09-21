var img=[];
var div=[];
img= document.getElementsByClassName("circle");
div=document.getElementsByClassName("menu");
var inEl = function(i){
    return function(){
        img[i].style.backgroundImage="url('green.jpg')";
    }
};
var outEl = function(i){
    return function(){
        img[i].style.backgroundImage="url('red.png')";
    }
};
for (i=0; i<3; i++) {
    div[i].addEventListener("mouseover", inEl(i));
    div[i].addEventListener("mouseout", outEl(i));
}
