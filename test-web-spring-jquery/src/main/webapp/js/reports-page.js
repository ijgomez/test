	$(document).ready(function() {
		
		$('#example').dataTable({
			"processing" : true,
			"stateSave" : true,
			"ajax": {
	            "url": "reports/list",
	            "dataSrc": ""
	        },
	        "columns": [
	            { "data": "name" },
	            { "data": "description" },
	            { "data": "engine" },
	            { "data": "format" },
	            { "data": "programable" },
	            { "data": "lastExecute" },
	            {
	                "data": "id",
	                "class": "center",
	                "render": function ( data, type, row ) {
	                    return "<a href='reports/exe/" + data + "'><span class='glyphicon glyphicon-cog'/></a> / <a href='reports/edit/" + data + "'><span class='glyphicon glyphicon-edit'/></a> / <a href='reports/del/" + data + "'><span class='glyphicon glyphicon-trash'/></a>";
	                },
	                "sortable" : false,
	                "searchable" : false
	            }
	        ],
	        "initComplete": function () {
	            var api = this.api();
	            //api.$('td').click( function () {
	            //	if (this.className != ' center') {
				//		api.search( this.innerHTML ).draw();
	            //	}
	            //} );

	        }
		});
		
		var table = $('#example').DataTable();
	    $('#example tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	        	$(this).removeClass('selected');
	        	
	        } else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	        }
	    } );

	    $('#reloadButton').click( function () {
	        alert( 'reload table...' );
	    } );
	    
	    $('#addButton').click( function () {
	        alert( 'new report...' );
	    } );

	    $('#editButton').click( function () {
	    	//table.rows('.selected').data().length
	        alert('Selected report ' + table.cell('.selected', 0).data() + ' for edit...' );
	    } );
	    
	    $('#deleteButton').click( function () {
	    	//table.rows('.selected').data().length
	        alert('Selected report ' + table.cell('.selected', 0).data() + ' for delete...' );
	    } );
		
	} );
