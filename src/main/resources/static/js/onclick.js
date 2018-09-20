	jQuery(document).ready(function($) {
		$("#search-form").submit(function(event) {
			event.preventDefault();
			ajaxSearch();
		});
	});

	function ajaxSearch() {
		search = $("#username").val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/FEH/api/search",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}

	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>" + JSON.stringify([1, 'false', false]) + "</pre>";
		$('#ajax_result').html(json);
	}
