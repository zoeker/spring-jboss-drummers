/*******************************************************************************
 * Conny Pemfors 2015-09-11
 * 
 */
var VideoController = (function() {

	var Class = function(methods) {
		var klass = function() {
			this.initialize.apply(this, arguments);
		};

		for ( var property in methods) {
			klass.prototype[property] = methods[property];
		}

		if (!klass.prototype.initialize)
			klass.prototype.initialize = function() {
			};

		return klass;
	};

	var VideoController = {

		BV : undefined,
		isTouch : false,/*Modernizr.touch,*/
		videoWindow : jq(window),
		initialize : function(bv, touch) {
			BV = bv;
			isTouch = touch;
			videoWindow = jq(window);
		},
		initialize : function(touch) {
			isTouch = touch;
			videoWindow = jq(window);
		},
		initVideo : function(e) {
			if (!this.isTouch) {
				// initialize BigVideo
				BV = new jq.BigVideo({
					forceAutoplay : isTouch
				});
				BV.init();
				this.showVideo();

				BV.getPlayer().on('loadeddata', function() {
					this.onVideoLoaded();
				});

				// fix image alignment problem
				this.adjustImagePositioning();
				// repeat fix when window gets resized
				videoWindow.on('resize', this.adjustImagePositioning);
			}
		},
		initVideo : function(e, bigVideo, src, ambientValue) {
			
				// initialize BigVideo
				BV = bigVideo;
				BV.init();
				this.showVideo(src,ambientValue);
				BV.getPlayer().play();
				
				/*BV.getPlayer().on('loadeddata', function() {
					this.onVideoLoaded();
				});*/
				

				// fix image alignment problem
				//this.adjustImagePositioning();
				// repeat fix when window gets resized
				//videoWindow.on('resize', this.adjustImagePositioning);
			
		},
		showVideo : function(src,ambientValue) {
			var divValue = jq('.video-wrapper .screen:nth-child(1)').attr('data-video');
			if(divValue === undefined){
				divValue = src;
			}
			BV.show(divValue, {
				ambient : ambientValue
			});
			jq('.video-wrapper .video-screen .video-big-image').css({
				opacity : 1
			});
		},
		onVideoLoaded : function(e) {
			jq('.video-wrapper .video-screen:nth-child(1)').find('.big-image')
					.animate({
						opacity : 0
					}, 500);
		}

		,

		adjustImagePositioning : function(e) {
			jq(".video-big-image")
					.each(
							function() {
								var $img = jq(this), img = new Image();
								img.src = $img.attr('src');
								var windowWidth = videoWindow.width(), windowHeight = videoWindow
										.height(), r_w = windowHeight
										/ windowWidth, i_w = img.width, i_h = img.height, r_i = i_h
										/ i_w, new_w, new_h, new_left, new_top;

								if (r_w > r_i) {
									new_h = windowHeight;
									new_w = windowHeight / r_i;
								} else {
									new_h = windowWidth * r_i;
									new_w = windowWidth;
								}

								$img.css({
									position : "absolute",
									width : new_w,
									height : new_h,
									left : (windowWidth - new_w) / 2,
									top : ((windowHeight - new_h) / 2)
								});
							});
		}
	};

	/*
	 * var init = function() { var videoController = new VideoController(false);
	 * videoController.initVideo();
	 *  };
	 * 
	 * if (document.addEventListener) { window.addEventListener('load', init,
	 * false);
	 *  // If IE event model is used } else if (document.attachEvent) {
	 * window.attachEvent('onload', init); }
	 */
	
	 function  getVideoController() {
		 var src = 'http://localhost:8080/ModernDrummer/files/brushes.MP4';
		 BV = new jq.BigVideo();
	     BV.init();
	     BV.show(src,{ambient:false});
		 BV.getPlayer().volume(1);
		 
		 VideoController.initVideo(event, BV, src, false);
	 }
	 
	 function  getVideoControllerWithSource(src) {
		 BV = new jq.BigVideo();
	     BV.init();
	     BV.show(src,{ambient:false});
		 BV.getPlayer().volume(1);
	 }
	 

	var API = {};
	API.getVideoController = getVideoController;
	API.getVideoControllerWithSource = getVideoControllerWithSource;
	return API;

}());