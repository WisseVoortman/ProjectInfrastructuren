function downfallGraph() {

	var DownfallGraphChart = Highcharts.chart('downfallGraph', {
        chart: {
            zoomType: 'x',
            events: {
                load: function () {

                    // set up the updating of the chart each second
                    var series = this.series[0];
                    setInterval(function () {
                        var x = (new Date()).getTime(), // current time
                        y = Math.round(Math.random()*100);
                        series.addPoint([x, y], true, true);
                    }, 60000);
                }
            }
        },
        boost: {
            useGPUTranslations: true
        },
        title: {
            text: 'Average downfall last hour'
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: 'Downfall in cm'
            },
            min: 0,
            //max: 100
        },
        legend: {
            enabled: true
        },
		tooltip: {
			xDateFormat: '%H:%M'
		},
        plotOptions: {
            series:{
                turboThreshold:24000000,//larger threshold or set to 0 to disable
				marker: {
					enabled: false
				}
            },
            area: {
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
					enabled: false,
                    radius: 0
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                }
            }
        },

        series: [{
            type: 'spline',
            name: 'Rain',
            data: (function () {
            // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -60; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000 * 60,
                        y: Math.round(Math.random()*100)
                    });
                }
				return data;
			}())
        },{type: 'spline',
            name: 'Snow',
            data: (function () {
            // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -60; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000 * 60,
                        y: Math.round(Math.random()*100)
                    });
                }
				return data;
			}())}],
		
		/*exporting: {
			buttons:{
				contextButton: {
					enabled: true,
					menuItems:[
						'printChart',
						'separator',
						'downloadPNG',
						'downloadJPEG',
						'downloadPDF',
						'downloadSVG',
						'separator',
						'downloadCSV',
					]
				}
			}	
		}*/

    });

}
