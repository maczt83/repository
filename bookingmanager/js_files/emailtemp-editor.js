$(function(){
			CKEDITOR.replace( 'editor1' );
			CKEDITOR.config.height = 500;   
			
			
			$('#emailTempSelect').change(function(){
				console.log($(this).val());
				if($(this).val() != 0){
					$('#emailTemplateId').val($(this).val());
					$.get( "http://localhost:8080/emailTemplate?id="+$(this).val(), function( data ) {
						
						CKEDITOR.instances.editor1.setData(data);
					});
				}else{
					$('#emailTemplateId').val($(this).val());
					CKEDITOR.instances.editor1.setData("");
				}
			});
			
			$('#saveButton').click(function(e){
				e.preventDefault();
				$.post( "http://localhost:8080/emailTemplate", { html: CKEDITOR.instances.editor1.getData(), emailTemplateId: $('#emailTemplateId').val() }, function( response ){
					if(response=="ok"){
						alert('Template saved');
					}else if(response=="error"){
						alert('Error: Choose a valid template');
					}
				});
				
			});
			
			
			
			/*CKEDITOR.instances.editor1.getData()
			CKEDITOR.instances.editor1.setData("<p>My Text</p>");*/
		});