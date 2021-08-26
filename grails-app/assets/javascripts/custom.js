var message_timeout = 1000;

$(document).ready(function(){
	$cuadro = $(".cuadro");	
	$cuadro.on("mouseenter", function(){
		$(".fa-5x", this).addClass("icon-anim");
	});
	$cuadro.on("mouseleave", function(){
		$(".fa-5x", this).removeClass("icon-anim");
	});
	$cuadro.on("mouseenter", function(){
		$(".huge", this).addClass("icon-anim");
	});
	$cuadro.on("mouseleave", function(){
		$(".huge", this).removeClass("icon-anim");
	});
});

