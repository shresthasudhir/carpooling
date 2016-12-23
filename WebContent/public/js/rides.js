var userId;
$(document).ready(function(){
	
	//$("#loader").hide();
	
	var sessionId = $("#userSession").val();
	
	$.post("GetUser",{sessionId:sessionId}).done(function(result){
		//alert(result.JSONDATA);
		userId = result.JSONDATA;
		
		}).fail(function(xhr,status,exception){
			alert(exception);
			console.log(xhr);
	});
	
	$.ajax({
		url:'GetOfferPost',
		type:'POST',
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success:function(result){
			$.each(result.JSONDATA,function(i,data){
				
				//alert(result.JSONDATA[0].postid);
				
				var myHTML="";
				myHTML+="<input type='hidden' value='"+result.JSONDATA[result.JSONDATA.length - 1].postid+"' class='lastPostId' />";
				myHTML+="<div class='offeredPost'><h2 class='offererName'>"+data.name+"</h2>";
				if(data.userid == userId){
					myHTML+="<span id='spanDelete"+data.postid+"' class='deletePost' delete-postid=\""+data.postid+"\" data-toggle='tooltip' title='Delete Post' >x</span>"
				}
				myHTML+="<pre>"+data.post+"</pre>"+"<button id='btnLike"+data.postid+"' class='btnLike' btn-postid=\""+data.postid+"\">Like</button>" +
						"<input type='hidden' name='txtHidden' value='"+data.postid+"'>" +
								"     "+"<button class='btnComment' btn-postid=\""+data.postid+"\">Comment</button></div>" +
										"<br><br>";
				$("#offeredPostDiv").append(myHTML);
				
			});
		},
		error:function(exception){
			console.log(exception);
		}
	});
	
	$.ajax({
		url:'GetTakePost',
		type:'POST',
		headers:{
			Accept:"application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		success:function(result){
			$.each(result.JSONDATA2,function(i,data){
				
				var myHTML="";
				myHTML+="<input type='hidden' value='"+result.JSONDATA2[result.JSONDATA2.length - 1].postid+"' class='lastPostId' />";
				myHTML+="<div class='offeredPost'><h2 class='offererName'>"+data.name+"</h2>";
				if(data.userid == userId){
					myHTML+="<span id='spanDelete"+data.postid+"' class='deletePost' delete-postid=\""+data.postid+"\" data-toggle='tooltip' title='Delete Post' >x</span>"
				}
				myHTML+="<pre>"+data.post+"</pre>"+"<button id='btnLike"+data.postid+"' class='btnLike' btn-postid=\""+data.postid+"\">Like</button>" +
						"<input type='hidden' name='txtHidden' value='"+data.postid+"'>" +
								"     "+"<button class='btnComment' btn-postid=\""+data.postid+"\">Comment</button></div>" +
										"<br><br>";
				$("#takerPostDiv").append(myHTML);
				
			});
		},
		error:function(exception){
			console.log(exception);
		}
	});
	
	
	$('#insertPostForm').submit(function(e){
		
		//var source = $("input[name=txtBoxSource]").val();
		var destination = $("input[name=txtBoxDestination]").val();
		var destinationLatitude = null;
		var destinationLongitude = null;
		
		var flag = false;
		var geocoder = new google.maps.Geocoder();
		
	    geocoder.geocode({ 'address': destination + ', us' }, function (results, status) {
	    	
	        if (status == google.maps.GeocoderStatus.OK) {
	        	
	        	flag = true;
	        	
	        	destinationLatitude=results[0].geometry.location.lat();
	        	destinationLongitude=results[0].geometry.location.lng();
	        	
	        	$("#destinationLat").val(destinationLatitude);
	    		$("#destinationLong").val(destinationLongitude);
	    		                
	        } else {
	        	alert("Something got wrong " + status);
	            flag = false;
	            e.preventDefault();
	        }
	    });
	    
	    alert(flag);
	    
	    return flag;
	});
	
	
});/*end of document */

$(window).scroll(function (){
	
	  if($(document).height() <= $(window).scrollTop() + $(window).height())
	  {
		$("#loader").show();
		  
		var getActiveDiv = $("li.active").text();

		if(getActiveDiv == "Offers"){
			var autoLoadDiv = "offeredPostDiv";}
		else{
			var autoLoadDiv = "takerPostDiv";}
		
		var lastPostId = $(".lastPostId").val();
		//alert(lastPostId);
		
		var ajaxCall = function(){
			
			$.post("GetMorePost",{posttype:getActiveDiv,lastPostId:lastPostId}).done(function(result){
				
				if(result.JSONDATA3.length == null || result.JSONDATA3.length === undefined || result.JSONDATA3.length == 0){
					alert("No More Post To Load");
					$("#loader").hide();
				}else{
					$(".lastPostId").val(result.JSONDATA3[result.JSONDATA3.length - 1].postid);
					
					$.each(result.JSONDATA3,function(i,data){
					
						var myHTML="";
						myHTML+="<input type='hidden' value='"+result.JSONDATA3[result.JSONDATA3.length - 1].postid+"' class='lastPostId' />";
						myHTML+="<div class='offeredPost'><h2 class='offererName'>"+data.name+"</h2>";
						if(data.userid == userId){
							myHTML+="<span id='spanDelete"+data.postid+"' class='deletePost' delete-postid=\""+data.postid+"\" data-toggle='tooltip' title='Delete Post' >x</span>"
						}
						myHTML+="<pre>"+data.post+"</pre>"+"<button id='btnLike"+data.postid+"' class='btnLike' btn-postid=\""+data.postid+"\">Like</button>" +
								"<input type='hidden' name='txtHidden' value='"+data.postid+"'>" +
										"     "+"<button class='btnComment' btn-postid=\""+data.postid+"\">Comment</button></div>" +
												"<br><br>";
							$("#"+autoLoadDiv).append(myHTML);
						});
					
				}
			
				}).fail(function(xhr,status,exception){
				console.log(xhr);
			});
		}
		
		timer = setTimeout(ajaxCall, 1200);
		//$("#loader").hide();

	  }
	  
});/*end of window.scroll */

$(document.body).on("click",".btnComment",function(){
	var self=$(this);
	var postId = self.attr("btn-postid");
	//alert(postId);
	var commentDivPresent = $("#commentBoxPost"+postId).length;
	
	if(commentDivPresent){
		$("#commentBoxPost"+postId).remove();
	}else{
		
		$.ajax({
			url:'GetComment',
			data:{postId:postId},
			type:'POST',
			success: function(result){
				//alert("success");
				var myHTML = "<div class='commentBoxPost' id='commentBoxPost"+postId+"'>";
				
				$.each(result.JSONDATA4,function(uid,data){
					myHTML +="<h3>"+data.fullName+"</h3>"+
					"<pre>"+data.comment+"</pre>";
				});
				
				myHTML +="<input type='hidden' value='"+postId+"' name='postIdHidden'>" +
						"<textarea class='txtBoxComment' id='txtBoxComment"+postId+"' rows='1' cols='50' placeholder='Comment Here' required></textarea>" +
						"<input id='btnComment"+postId+"' class='commentForm' btn-postid=\""+postId+"\" type='button' value='Comment'/>";
				
				myHTML +="<br><br></div>";
				self.parent().append(myHTML);
			},
			error: function(xhr,status,exception){
				alert("Error");
				console.log(exception);
			}
		});
	
	}/*end of else */
	
});/*end of comment button click */


$(document.body).on("click",".commentForm",function(){
	var self=$(this);
	var postId = self.attr("btn-postid");
	//alert(postId);
	
	var txtBoxComment = $("#txtBoxComment"+postId).val();
	//alert(txtBoxComment);
	
	
	$.ajax({
		url:'InsertComment',
		data:{postId:postId,txtBoxComment:txtBoxComment},
		type:'POST',
		success: function(result){
			//alert("success");
			var myHTML = "";
			
			myHTML +="<h3>"+result.JSONDATA5[result.JSONDATA5.length - 1].fullName+"</h3>"+
			"<pre>"+result.JSONDATA5[result.JSONDATA5.length - 1].comment+"</pre>";
			
			myHTML +="<input type='hidden' value='"+postId+"' name='postIdHidden'>" +
			"<textarea class='txtBoxComment' id='txtBoxComment"+postId+"' rows='1' cols='50' placeholder='Comment Here' required></textarea>" +
			"<input id='btnComment"+postId+"' class='commentForm' btn-postid=\""+postId+"\" type='button' value='Comment'/>";
			
			$("#txtBoxComment"+postId).remove();
			$("#btnComment"+postId).remove();
			
			$("#commentBoxPost"+postId).append(myHTML);
			
		},
		error: function(xhr,status,exception){
			alert("Error");
			console.log(exception);
		}
	});
	
	;
});/*end of comment button click */


$(document.body).on("mouseover",".commentForm",function(){
	var self=$(this);
	$(this).css('background-color', '#515151');
});

$(document.body).on("mouseout",".commentForm",function(){
	var self=$(this);
	$(this).css('background-color', '#f0f0f0');
});


$(document.body).on("click",".btnLike",function(){
	var self=$(this);
	var postId = self.attr("btn-postid");
	var btnId = self.attr("id");
	var btnText = $("#"+btnId).text();
	
	if(btnText == "Like"){
		$.post("InsertLike",{postId:postId}).done(function(result){
			
			if(result.JSONDATA6 == "PostLiked"){
				$("#btnLike"+postId).text("Liked");
				$("#btnLike"+postId).css('background-color','#63a599');
			}
			
			}).fail(function(xhr,status,exception){
				alert(exception);
				console.log(xhr);
		});
	}else{
		alert("You Aleady Like This Post");
	}
});

$(document.body).on("mouseover",".deletePost",function(){
	$(this).css('cursor', 'pointer');
});

$(document.body).on("click",".deletePost",function(){
	var confirmation = confirm("Are you sure you want to delete this post?");
	if(confirmation){
		var self=$(this);
		var postId = self.attr("delete-postid");
		var id = self.attr("id");
		
		$.post("DeletePost",{postId:postId}).done(function(result){
			
			if(result.JSONMESSAGE == "DeleteSuccess"){
				self.parent().remove();
			}
			
			}).fail(function(xhr,status,exception){
				alert(exception);
				console.log(xhr);
		});
	}
});



