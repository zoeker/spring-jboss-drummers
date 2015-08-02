/**
 * Conny Pemfors 2014-06-19
 */
(function(jq) {
  
  jq.fn.getURLParameter = function(elementId)  {
    return decodeURIComponent((new RegExp('[?|&]' + elementId + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
  }
  
})(jQuery);

