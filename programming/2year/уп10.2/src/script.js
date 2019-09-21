var el=[];
el= document.getElementsByClassName("menu");
var inEl = function(i){
    return function(){
        el[i].style.color="#850024";
    }
};
var outEl = function(i){
    return function(){
        el[i].style.color="black";
    }
};
for (i=0; i<4; i++) {
    el[i].addEventListener("mouseover", inEl(i));
    el[i].addEventListener("mouseout", outEl(i));
}