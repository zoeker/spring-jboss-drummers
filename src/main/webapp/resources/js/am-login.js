/**
 * Conny Pemfors 2014-06-19
 */

jq(document).ready(
        function() {

          var init = function() {

            var requestedParamErrorMessage = jq('errorMessage')
                    .getURLParameter('errorMessage');
            if (requestedParamErrorMessage != undefined
                    && requestedParamErrorMessage.length > 0) {
              jq(".errormessage").append(requestedParamErrorMessage);
            } else {
              jq(".errormessage").append("");
            }

            if (jq("#tab-container") !== undefined  && jq("#tab-container").length > 0) {
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