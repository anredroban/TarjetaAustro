$(document).ready(function(){
				$('#menu-reportes').click(function(){
						$('.desplegable').not($("ul", this)).slideUp("fast")
						.next()
						$(this).find('ul').slideToggle("fast")
						.end();
					});
				});

$(document).ready(function(){
	$('#menu-catalogos').click(function(){
			$('.desplegable').not($("ul", this)).slideUp("fast")
			.next()
			$(this).find('ul').slideToggle("fast")
			.end();
		});
	});

$(document).ready(function(){
	$('#menu-album').click(function(){
			$('.desplegable').not($("ul", this)).slideUp("fast")
			.next()
			$(this).find('ul').slideToggle("fast")
			.end();
		});
	});

$(document).ready(function(){
	$('#menu-base').click(function(){
			$('.desplegable').not($("ul", this)).slideUp("fast")
			.next()
			$(this).find('ul').slideToggle("fast")
			.end();
		});
	});

$(document).ready(function(){
	$('#menu-accion').click(function(){
			$('.desplegable').not($("ul", this)).slideUp("fast")
			.next()
			$(this).find('ul').slideToggle("fast")
			.end();
		});
	});
$(document).ready(function(){
	$('#menu-subdistribuidor').click(function(){
			$('.desplegable').not($("ul", this)).slideUp("fast")
			.next()
			$(this).find('ul').slideToggle("fast")
			.end();
		});
	});