jq(document).ready(

function() {
  
  var FileHandler = {
          fileToStore : undefined,
          addEvent : function(e){
            document.querySelector('#file1').addEventListener('change', function(e) {
              fileToStore = this.files[0];
            });
          }
  };

  var init = function() {

    if (jq("#tab-container") !== undefined && jq("#tab-container").length > 0) {
      jq('#tab-container').easytabs();
    }

    if (jq('.file') !== undefined && jq('.file').length > 0) {
      jq('.file').preimage();
    }

   

  };

  if (document.addEventListener) {
    window.addEventListener('load', init, false);

    // If IE event model is used
  } else if (document.attachEvent) {
    window.attachEvent('onload', init);
  }

});