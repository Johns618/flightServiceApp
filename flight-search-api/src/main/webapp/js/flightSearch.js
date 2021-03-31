//Author - Joshua Johnson

var originSites;
var destinationSites;

$( document ).ready(function() {
	
	//Sets input fields functionalities
	setInputFields();
		
});


function setInputFields(){
	
	$('#originSearch').attr('disabled', true);
	$('#destinationSearch').attr('disabled', true);
	
	$("#createDatabase" ).on("click", function() {	
	
		createDatabase();
	});	
	
	
	//Origin Search input
	$("#originSearch" ).on("keyup change focus", function() {	
		
		var type =  $('#originSearch').val();		
		var site = "origin";
					
		autofill(type, site);		
		filterFlightTable(type, site);
	});
	
	//Destination Search input
	$("#destinationSearch" ).on("keyup change focus", function() {	
		
		var type =  $('#destinationSearch').val();		
		var site = "destination"
					
		autofill(type, site);		
		filterFlightTable(type, site);
	});
	
	
}


//Autofill functionality
function autofill(type, site) {
  
	if(!type) {
	autofillClearAndHide(site);
    return;
  }

	//Populate autofill	dropdown
	var siteList;
	var onclick;
	
	if(site == 'origin'){
		
		siteList = originSites;	
		onclick = "originSearch.value=this.innerText;autocomplete_result.innerHTML='';autocomplete_result.style.display='none';filterFlightTable(this.innerText,'origin');";
	}
	
	else if(site == 'destination'){
		
		siteList = destinationSites;
		onclick = "destinationSearch.value=this.innerText;autocomplete_destination_result.innerHTML='';autocomplete_destination_result.style.display='none';filterFlightTable(this.innerText,'destination');";
		
	}
		
	var a = new RegExp("^" + type, "i");
	  for(var x = 0, b = document.createDocumentFragment(), c = false; x < siteList.length; x++) {
	    if(a.test(siteList[x])) {
	      c = true;
	      var d = document.createElement("p");
	      d.innerText = siteList[x];
	      d.setAttribute("onclick", onclick);
	      b.appendChild(d);
	    }
	  }
	  
	  if(c == true) {
		  
		  
		  if(site == 'origin'){
			  
			  	autocomplete_result.innerHTML = "";
			    autocomplete_result.style.display = "block";
			    autocomplete_result.appendChild(b);
				
			}
			
			else if(site == 'destination'){
				
				autocomplete_destination_result.innerHTML = "";
				autocomplete_destination_result.style.display = "block";
			    autocomplete_destination_result.appendChild(b);
			}
		  
	    
	    return;
	  }
	  
	  autofillClearAndHide(site);
	}

//Hide Autofill
function autofillClearAndHide(site) {
	
	  if(site == 'origin'){
		  
		  autocomplete_result.innerHTML = "";
		  autocomplete_result.style.display = "none";
			
		}
		
		else if(site == 'destination'){
			
			autocomplete_destination_result.innerHTML = "";
			autocomplete_destination_result.style.display = "none";
		}
	  
	}
	
//Table input filter	
function filterFlightTable(type, site) {
	
	var className;	
	
	if(site == 'origin'){
		
		className = '.originName';		
	}
	
	else if(site == 'destination'){
		
		className = '.destinationName';	
		
	}
	
	var dtable = $('#flightTable').DataTable();
			
	dtable.columns(className).search( 
			type,
			true,
			true
	).draw();
	
	$(".dataTables_length").hide();
	$(".dataTables_filter").hide();
}

//Creates Temporary Database
function createDatabase() {

		var url = "http://localhost:8080/getFlights"
			
			console.log("creating databases...")			
			$('#createDatabase').attr("disabled", true);

		$.ajax({
			type : "POST",
			url : url,
			cache : false,
			async : false,
			statusCode: {
				403: function() {
					timeout();
				}
			},
			contentType : "application/json"
		}).done(function(result) {
			
			console.log("success create db loading data...")
			
			loadData();
			populateAutofillOrigin();
			populateAutofillDestination();
			
			$('#originSearch').attr('disabled', false);
			$('#destinationSearch').attr('disabled', false);
				
			$("#waitMsg").hide();
			$("#createDatabase").hide();
					
		}).fail(function (xhr) {
			
			alert("failed to create db")
			$('#createDatabase').attr("disabled", false);
			
		});
}

//Populate autofill	dropdowns
function populateAutofillOrigin(){
	
	var url = "http://localhost:8080/getAutofill?site=origin"
	
	var siteArray = [];
	
	$.ajax({
		type: "GET",
		url: url,              
		dataType: "json",
		cache: false,
		async: false,
		contentType: "application/json"  }).done(function(result) {
			
			originSites = result;
		});
	
	return siteArray;
}

function populateAutofillDestination(){
	
	var url = "http://localhost:8080/getAutofill?site=destination"
	
	var siteArray = [];
	
	$.ajax({
		type: "GET",
		url: url,              
		dataType: "json",
		cache: false,
		async: false,
		contentType: "application/json"  }).done(function(result) {
			
			destinationSites = result;
		});
	
	return siteArray;
}


//Loads Flight data to Table 
function loadData(){
	
	var url = "http://localhost:8080/getFlights"
	
	var healthTable = $('#flightTable').DataTable({
		"sDom": 'lrtip',
		"bLengthChange": false,
		"processing": true,
		"ajax": {
			"url": url,
			"dataSrc": "",
		},
		"pagingType": "simple",
		"bLengthChange": true, 
		"columnDefs": [{
			"render": function(data,type,row){
				var id = row.id;
				if (id == null){     		    		
					return id +"";    		    		
				}else{
					return id; 
				}

			},
			"targets": 0
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var flt_num = row.flt_num;
				if (flt_num == null){
					return flt_num + "";
				}else{
					return flt_num;

				}   				       		    	
				orderable: false;
			},
			"targets": 1       		
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var created_at = row.created_at;
				if (created_at == null){
					return created_at + "";
				}else{
					return created_at;

				}   				       		    	
				orderable: false;
			},
			"targets": 2       		
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var updated_at = row.updated_at;
				if (updated_at == null){
					return updated_at + "";
				}else{
					return updated_at;

				}   				       		    	
				orderable: false;
			},
			"targets": 3       		
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var out_gmt = row.out_gmt;
				if (out_gmt == null){
					return out_gmt + "";
				}else{
					return out_gmt;

				}   				       		    	
				orderable: false;
			},
			"targets": 4       		
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var in_gmt = row.in_gmt;
				if (in_gmt == null){
					return in_gmt + "";
				}else{
					return in_gmt;

				}   				       		    	
				orderable: false;
			},
			"targets": 5       		
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var off_gmt = row.off_gmt;
				if (off_gmt == null){
					return off_gmt + "";
				}else{
					return off_gmt;

				}   				       		    	
				orderable: false;
			},
			"targets": 6       		
		},
		{
			"orderable": false,	
			"render": function(data,type,row){

				var on_gmt = row.on_gmt;
				if (on_gmt == null){
					return on_gmt + "";
				}else{
					return on_gmt;

				}   				       		    	
				orderable: false;
			},
			"targets": 7       		
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var flight_identifier = row.flight_identifier;
				if (flight_identifier == null){
					return flight_identifier + "";
				}else{
					return flight_identifier;

				}   				       		    	
				orderable: false;
			},
			"targets": 8       		
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var scheduled_origin_gate = row.scheduled_origin_gate;
				if (scheduled_origin_gate == null){
					return scheduled_origin_gate + "";
				}else{
					return scheduled_origin_gate;

				}   				       		    	
				orderable: false;
			},
			"targets": 9       		
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var scheduled_destination_gate = row.scheduled_destination_gate;
				if (scheduled_destination_gate == null){
					return scheduled_destination_gate + "";
				}else{
					return scheduled_destination_gate;

				}   				       		    	
				orderable: false;
			},
			"targets": 10       		
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var destination = row.destination;
				if (destination == null){
					return destination + "";
				}else{
					return destination;

				}   				       		    	
				orderable: false;
			},
			"targets": 11,
			"className": "destination"
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var origin = row.origin;
				if (origin == null){
					return origin + "";
				}else{
					return origin;

				}   				       		    	
				orderable: false;
			},
			"targets": 12,
			"className": "origin"
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var destination_full_name = row.destination_full_name;
				if (destination_full_name == null){
					return destination_full_name + "";
				}else{
					return destination_full_name;

				}   				       		    	
				orderable: false;
			},
			"targets": 13,
			"className": "destinationName"
		},
		{
			"visible": true,
			"orderable": false,	
			"render": function(data,type,row){

				var origin_full_name = row.origin_full_name;
				if (origin_full_name == null){
					return origin_full_name + "";
				}else{
					return origin_full_name;

				}   				       		    	
				orderable: false;
			},
			"targets": 14,
			"className": "originName"
		},
		],
	} );
	
	$(".dataTables_length").hide();
	$(".dataTables_filter").hide();
}







 

