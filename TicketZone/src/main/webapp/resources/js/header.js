$(document).ready(function() {
	$("#mainLogo").click(function() {
		window.location.href = "/";
	});
	
	$(window).scroll(function(){
		if($(this).scrollTop() == 0)
			$('.navbar').css('opacity', '.1');
		else
			$('.navbar').css('opacity', '.9');
	});
});