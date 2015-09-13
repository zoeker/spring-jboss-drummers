/*
	BigVideo - The jQuery Plugin for Big Background Video (and Images)
	by John Polacek (@johnpolacek)
	
	Dual licensed under MIT and GPL.

    Dependencies: jQuery, jQuery UI (Slider), Video.js, ImagesLoaded
*/

;(function(jq) {

    jq.BigVideo = function(options) {

        var defaults = {
			// If you want to use a single mp4 source, set as true
			useFlashForFirefox:true,
			// If you are doing a playlist, the video won't play the first time
			// on a touchscreen unless the play event is attached to a user click
			forceAutoplay:false,
			controls:true,
			doLoop:false,
			defaultVolume : 1
        };

        var BigVideo = this,
			player,
			vidEl = '#big-video-vid',
			wrap = jq('<div id="big-video-wrap"></div>'),
			video = jq(''),
			mediaAspect = 16/9,
			vidDur = 0,
			defaultVolume = 1,
			isInitialized = false,
			isSeeking = false,
			isPlaying = false,
			isQueued = false,
			isAmbient = false,
			playlist = [],
			currMediaIndex,
			currMediaType;

        var settings = jq.extend({}, defaults, options);

        // If only using mp4s and on firefox, use flash fallback
        var ua = navigator.userAgent.toLowerCase();
        var isFirefox = ua.indexOf('firefox') != -1;
        if (settings.useFlashForFirefox && (isFirefox)) {
			VideoJS.options.techOrder = ['flash'];
		}


		function updateSize() {
			var windowW = jq(window).width();
			var windowH = jq(window).height();
			var windowAspect = windowW/windowH;
			if (windowAspect < mediaAspect) {
				// taller
				if (currMediaType === 'video') {
					player
						.width(windowH*mediaAspect)
						.height(windowH);
					jq(vidEl)
						.css('top',0)
						.css('left',-(windowH*mediaAspect-windowW)/2)
						.css('height',windowH);
					jq(vidEl+'_html5_api').css('width',windowH*mediaAspect);
					jq(vidEl+'_flash_api')
						.css('width',windowH*mediaAspect)
						.css('height',windowH);
				} else {
					// is image
					jq('#big-video-image')
						.width(windowH*mediaAspect)
						.height(windowH)
						.css('top',0)
						.css('left',-(windowH*mediaAspect-windowW)/2);
				}
			} else {
				// wider
				if (currMediaType === 'video') {
					player
						.width(windowW)
						.height(windowW/mediaAspect);
					jq(vidEl)
						.css('top',-(windowW/mediaAspect-windowH)/2)
						.css('left',0)
						.css('height',windowW/mediaAspect);
					jq(vidEl+'_html5_api').css('width','100%');
					jq(vidEl+'_flash_api')
						.css('width',windowW)
						.css('height',windowW/mediaAspect);
				} else {
					// is image
					jq('#big-video-image')
						.width(windowW)
						.height(windowW/mediaAspect)
						.css('top',-(windowW/mediaAspect-windowH)/2)
						.css('left',0);
				}
			}
		}

		function initPlayControl() {
			// create video controller
			var markup = '<div id="big-video-control-container">';
			markup += '<div id="big-video-control">';
			markup += '<a href="#" id="big-video-control-play"></a>';
			markup += '<div id="big-video-control-middle">';
			markup += '<div id="big-video-control-bar">';
			markup += '<div id="big-video-control-bound-left"></div>';
			markup += '<div id="big-video-control-progress"></div>';
			markup += '<div id="big-video-control-track"></div>';
			markup += '<div id="big-video-control-bound-right"></div>';
			markup += '</div>';
			markup += '</div>';
			markup += '<div id="big-video-control-timer"></div>';
			markup += '</div>';
			markup += '</div>';
			jq('body').append(markup);

			// hide until playVideo
			jq('#big-video-control-container').css('display','none');

			// add events
			jq('#big-video-control-track').slider({
				animate: true,
				step: 0.01,
				slide: function(e,ui) {
					isSeeking = true;
					jq('#big-video-control-progress').css('width',(ui.value-0.16)+'%');
					player.currentTime((ui.value/100)*player.duration());
				},
				stop:function(e,ui) {
					isSeeking = false;
					player.currentTime((ui.value/100)*player.duration());
				}
			});
			jq('#big-video-control-bar').click(function(e) {
				player.currentTime((e.offsetX/jq(this).width())*player.duration());
			});
			jq('#big-video-control-play').click(function(e) {
				e.preventDefault();
				playControl('toggle');
			});
			player.addEvent('timeupdate', function() {
				if (!isSeeking && (player.currentTime()/player.duration())) {
					var currTime = player.currentTime();
					var minutes = Math.floor(currTime/60);
					var seconds = Math.floor(currTime) - (60*minutes);
					if (seconds < 10) seconds='0'+seconds;
					var progress = player.currentTime()/player.duration()*100;
					jq('#big-video-control-track').slider('value',progress);
					jq('#big-video-control-progress').css('width',(progress-0.16)+'%');
					jq('#big-video-control-timer').text(minutes+':'+seconds+'/'+vidDur);
				}
			});
		}

		function playControl(a) {
			var action = a || 'toggle';
			if (action === 'toggle') action = isPlaying ? 'pause' : 'play';
			if (action === 'pause') {
				player.pause();
				jq('#big-video-control-play').css('background-position','-16px');
				isPlaying = false;

			} else if (action === 'play') {
				player.play();
				player.volume(20.0);
				jq('#big-video-control-play').css('background-position','0');
				isPlaying = true;
			}
		}

		function setUpAutoPlay() {
			player.play();
			jq('body').off('click',setUpAutoPlay);
        }

		function nextMedia() {
			currMediaIndex++;
			if (currMediaIndex === playlist.length) currMediaIndex=0;
			playVideo(playlist[currMediaIndex]);
        }

        function playVideo(source) {
			// clear image
			jq(vidEl).css('display','block');
			currMediaType = 'video';
			player.src(source);
			isPlaying = true;
			if (isAmbient) {
				jq('#big-video-control-container').css('display','none');
				player.volume(0);
				doLoop = true;
			} else {
				jq('#big-video-control-container').css('display','block');
				player.volume(defaultVolume);
				doLoop = false;
			}
			jq('#big-video-image').css('display','none');
			jq(vidEl).css('display','block');
        }

        function showPoster(source) {
			// remove old image
			jq('#big-video-image').remove();

			// hide video
			player.pause();
			jq(vidEl).css('display','none');
			jq('#big-video-control-container').css('display','none');

			// show image
			currMediaType = 'image';
			var bgImage = jq('<img id="big-video-image" src='+source+' />');
			wrap.append(bgImage);

			jq('#big-video-image').imagesLoaded(function() {
				mediaAspect = jq('#big-video-image').width() / jq('#big-video-image').height();
				updateSize();
			});
        }

		BigVideo.init = function() {
			if (!isInitialized) {
				// create player
				jq('body').prepend(wrap);
				var autoPlayString = settings.forceAutoplay ? 'autoplay' : '';
				player = jq('<video id="'+vidEl.substr(1)+'" class="video-js vjs-default-skin" preload="auto" data-setup="{}" '+autoPlayString+' webkit-playsinline></video>');
				player.css('position','absolute');
				wrap.append(player);
				player = _V_(vidEl.substr(1), { 'controls': false, 'autoplay': true, 'preload': 'auto' });
				
				// add controls
				if (settings.controls) initPlayControl();
				
				// set initial state
				updateSize();
				isInitialized = true;
				isPlaying = false;

				if (settings.forceAutoplay) {
					jq('body').on('click', setUpAutoPlay);
				}

				jq('#big-video-vid_flash_api')
					.attr('scale','noborder')
					.attr('width','100%')
					.attr('height','100%');
				
				// set events
				jq(window).resize(function() {
					updateSize();
				});

				player.addEvent('loadedmetadata', function(data) {
					if (document.getElementById('big-video-vid_flash_api')) {
						// use flash callback to get mediaAspect ratio
						mediaAspect = document.getElementById('big-video-vid_flash_api').vjs_getProperty('videoWidth')/document.getElementById('big-video-vid_flash_api').vjs_getProperty('videoHeight');
					} else {
						// use html5 player to get mediaAspect
						mediaAspect = jq('#big-video-vid_html5_api').prop('videoWidth')/jq('#big-video-vid_html5_api').prop('videoHeight');
					}
					updateSize();
					var dur = Math.round(player.duration());
					var durMinutes = Math.floor(dur/60);
					var durSeconds = dur - durMinutes*60;
					if (durSeconds < 10) durSeconds='0'+durSeconds;
					vidDur = durMinutes+':'+durSeconds;
				});
				
				player.addEvent('ended', function() {
					if (settings.doLoop) {
						player.currentTime(0);
						player.play();
					}
					if (isQueued) {
						nextMedia();
					}
				});
			}
        };

        BigVideo.show = function(source,options) {
        	if (options === undefined) options = {};
			isAmbient = options.ambient === true;
			if (isAmbient || options.doLoop) settings.doLoop = true;
			if (typeof(source) === 'string') {
				var ext = source.substring(source.lastIndexOf('.')+1);
				if (ext === 'jpg' || ext === 'gif' || ext === 'png') {
					showPoster(source);
				} else {
					if (options.altSource && navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
						source = options.altSource;
					}
					playVideo(source);
					isQueued = false;
				}
			} else {
				playlist = source;
				currMediaIndex = 0;
				playVideo(playlist[currMediaIndex]);
				isQueued = true;
			}
        };

        // Expose Video.js player
        BigVideo.getPlayer = function() {
			return player;
        };
    };

})(jQuery);