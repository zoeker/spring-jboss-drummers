jq(document).ready(

function() {

  var init = function() {

    if (jq("#tab-container") !== undefined && jq("#tab-container").length > 0) {
      jq('#tab-container').easytabs();
    }

  };

  if (document.addEventListener) {
    window.addEventListener('load', init, false);

    // If IE event model is used
  } else if (document.attachEvent) {
    window.attachEvent('onload', init);
  }

});