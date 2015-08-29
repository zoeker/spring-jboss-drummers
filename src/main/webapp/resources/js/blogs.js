/***
 * Conny Pemfors 2015-08-04
 * 
 */
jq(document).ready(

function() {
	
	var SelectorDataHandler = {
	          
	          setMemberBlogInfo: function(data) {
	            var counter = 0;
	            var blogInfo = '';
	            var displayComments = '';
	            jq.each(data, function(i, item) {
	              
	              
	              blogInfo =  blogInfo + '<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">';
	              blogInfo = blogInfo + 'Title: ' + '</div><div class=\"cellDiv ft-myriad-11\">' + item.bloginfo.title + '</div>';
	              blogInfo = blogInfo + '</div>';
	              
	              blogInfo =  blogInfo + '<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">';
	              blogInfo = blogInfo + 'Message: ' + '</div><div class=\"cellDiv ft-myriad-11\">' + item.bloginfo.message + '</div>';
	              blogInfo = blogInfo + '</div>';
	              
	              blogInfo =  blogInfo + '<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">';
	              blogInfo = blogInfo + 'Topic: ' + '</div><div class=\"cellDiv ft-myriad-11\">' + item.bloginfo.topic + '</div>';
	              blogInfo = blogInfo + '</div>';
	              
	              blogInfo =  blogInfo + '<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">';
	              blogInfo = blogInfo + 'Posted by: ' + '</div><div class=\"cellDiv ft-myriad-11\">' + item.bloginfo.postedBy + '</div>';
	              blogInfo = blogInfo + '</div>';
	              
	              blogInfo =  blogInfo + '<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">';
	              blogInfo = blogInfo + 'Posted date: ' + '</div><div class=\"cellDiv ft-myriad-11\">' + item.bloginfo.postedDate + '</div>';
	              blogInfo = blogInfo + '</div>';
	              
	              var graphics = item.graphics;
	              blogInfo =  blogInfo + '<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">Images</div>';
	              blogInfo =  blogInfo + '<div class=\"cellDiv ft-myriad-11\">';
	              jq.each(graphics, function(i, graphicdata){
	                  
	                blogInfo = blogInfo 
	                + '<div class="memberblog-image member-image-container member-item-shaded image-rounded" >';
	                blogInfo = blogInfo + '<li>';
	                blogInfo = blogInfo + '<div class=p-l-2-f-l>';
	                blogInfo = blogInfo + '<div class="outside-a" style=\"float:left;\">';
	                blogInfo = blogInfo + ' <a class="gallery-image" href="files/'
	                + graphicdata.fileLocation + '" ';
	                blogInfo = blogInfo + ' title="' + graphicdata.fileLocation + '" style="float:left;">';
	                blogInfo = blogInfo + ' <input type="hidden" id="graphicsid" name="graphicsid" value="' +  graphicdata.graphicid+ '"></input>';
	                blogInfo = blogInfo + ' <img  src="files/' + graphicdata.fileLocation + '" class="memberblog-collection-item image-rounded memberblog-item-shaded img-m-size"  />';
	                blogInfo = blogInfo + '</a>';
	                blogInfo = blogInfo + '</div>';
	                blogInfo = blogInfo + '</div>';
	                blogInfo = blogInfo + '</li>';
	                counter = counter + 1;
	                if (counter == 5) {
	                  blogInfo = blogInfo + '<h3 class=divNewLine ></h3>'
	                  counter = 0;
	                }
	                blogInfo = blogInfo + '</div>';
	                
	              });
	              blogInfo = blogInfo + '</div>';
	              
	              //display comments
	              var comments = item.comments;
	              displayComments = displayComments + '<div class="cellDiv ft-myriad-11">Comments</div>';
	              displayComments = displayComments + '<div class="rowDivHeader">';
	              displayComments =  displayComments + '<div class="cellDivHeader ft-myriad-11" >Message</div>';
	              displayComments =   displayComments + '<div class="cellDivHeader ft-myriad-11" >Posted date</div>';
	              displayComments =  displayComments + '<div class="cellDivHeader ft-myriad-11" >Posted by</div>';
	              displayComments =  displayComments + '</div>';
	              jq.each(comments, function(i, commentdata){
	                displayComments =  displayComments + '<div class="rowDiv">';
	                displayComments =  displayComments + '<div class="cellDiv ft-myriad-11">' + commentdata.message   + '</div>';
	                displayComments =  displayComments + '<div class="cellDiv ft-myriad-11">' + commentdata.postedDate   + '</div>';
	                displayComments =  displayComments + '<div class="cellDiv ft-myriad-11">' + commentdata.postedBy   + '</div>';
	                displayComments =  displayComments + '</div>';
	              });
	              

	            });
	            jq(blogInfo).appendTo(".display-blogpost");
	            jq(displayComments).appendTo(".display-comments");
	          },
	          cleanFieldsChecked: function(elementId){
	        	  jq(elementId).prop('checked', false);
	              jq("input#selectedBlogId").val(0);
	              jq(".display-blogpost").children().remove();
	              jq(".display-comments").children().remove();
	          }
	          
	  };
	  
	 
	  var AjaxCallHandler = {
	          
	          getMemberBlog: function(elementId) {
	            var value = elementId.value;
	            var checked = jq(elementId).is(':checked');
	            jq("input#selectedBlogId").val(value);
	            if (checked == 'true' || checked == true) {

	              jq.ajax({url: 'http://localhost:8080/ModernDrummer/rest/blogs/get/json/memberblog/'  + value,
	                        type: 'GET',
	                        async: false,
	                        cache: false,
	                        timeout: 10000,
	                        contentType: "application/json; charset=utf-8",
	                        dataType: "json",
	                        error: function(data) {
	                          alert("Ajax Failure");
	                        },
	                        success: function(data) {

	                          jq(elementId).prop('checked', true);
	                          SelectorDataHandler.setMemberBlogInfo(data);

	                        }
	                      });

	            } else {
	            	SelectorDataHandler.cleanFieldsChecked(elementId);
	              
	            }

	          }
	          
	  };
	  
  
  var FileHandler = {
          fileToStore : undefined,
          addEvent : function(e){
            document.querySelector('#file1').addEventListener('change', function(e) {
              fileToStore = this.files[0];
            });
          }
  };
  
  var bindFunctions = function(){
	  	jq("input[name='memberBlogGroup']").on("change", function(e) {
	      var changeElement = jq(this);
	      
	      jq("input[name=memberBlogGroup]:checked").each(function() {
	        if (changeElement.attr("id") !== jq(this).attr("id")) {
	          jq(this).removeAttr('checked');
	          SelectorDataHandler.cleanFieldsChecked(this);
	        }
	 
	      });
	      AjaxCallHandler.getMemberBlog(this);
	      e.preventDefault();
	      
	  	 });
  };
  
  var init = function() {

    if (jq("#tab-container") !== undefined && jq("#tab-container").length > 0) {
      jq('#tab-container').easytabs();
    }

    if (jq('.file') !== undefined && jq('.file').length > 0) {
      jq('.file').preimage();
    }
    
    bindFunctions();
    
    

   

  };

  if (document.addEventListener) {
    window.addEventListener('load', init, false);

    // If IE event model is used
  } else if (document.attachEvent) {
    window.attachEvent('onload', init);
  }

});