var animateDialogs;
$(document).ready(function() {
	$(".hasDatepicker").mask("9999-99-99")
	$('#page_effect').fadeIn(2000);

	// $("body").slimScroll({
	// height: $(window).height()
	// });
	animateDialogs = function(el) {
		debugger;
		var idName = el.id.replace(":", "\\:");
		$("#" + idName).addClass('zoomIn animated');
		setTimeout(function() {
			$("#" + idName).removeClass('zoomIn animated')
		}, 500);
	};
	$(window).scroll(function() {
		if ($(this).scrollTop() == 0) {
			$("#wrapper").removeClass("hidden-topbar");
		}
		if ($(this).scrollTop() > 0) {
			$("#wrapper").addClass("hidden-topbar");
		}
	});
	$(".input-number").attr("type", "number");
});
function calendarMask() {
	$(".hasDatepicker").mask("9999-99-99")
}
var tableToExcel = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,', template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>', base64 = function(
			s) {
		return window.btoa(unescape(encodeURIComponent(s)))
	}, format = function(s, c) {
		return s.replace(/{(\w+)}/g, function(m, p) {
			return c[p];
		})
	}
	return function(table, name) {
		if (!table.nodeType)
			table = document.getElementById(table)
		var ctx = {
			worksheet : name || 'Worksheet',
			table : table.innerHTML
		}
		window.location.href = uri + base64(format(template, ctx))
	}
})();
var print1 = function(id) {
	debugger;
	var contents = document.getElementById(id).innerHTML;
	var frame1 = document.createElement('iframe');
	frame1.name = "frame1";
	frame1.style.position = "absolute";
	frame1.style.top = "-1000000px";
	document.body.appendChild(frame1);
	var frameDoc = frame1.contentWindow ? frame1.contentWindow
			: frame1.contentDocument.document ? frame1.contentDocument.document
					: frame1.contentDocument;
	frameDoc.document.open();
	frameDoc.document.write('<html><head>');
	frameDoc.document
			.write('<style>@media print {.page-break {page-break-before: always !important;}}</style></head><body><table style="width:100%;" cellpadding="0" cellspacing="0">');
	frameDoc.document.write(contents);
	frameDoc.document.write('</table></body></html>');
	frameDoc.document.close();
	setTimeout(function() {
		window.frames["frame1"].focus();
		window.frames["frame1"].print();
		document.body.removeChild(frame1);
	}, 500);
	return false;
}