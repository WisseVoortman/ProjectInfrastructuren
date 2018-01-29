
Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

Highcharts.chart('downfallGraph', {
        chart: {
            zoomType: 'x',
			events: {
				load: function () {

					// set up the updating of the chart each second
					var series = this.series[0];
					setInterval(function () {
						var x = (new Date()).getTime(), // current time
                        y = Math.round(Math.random()*1000);
						series.addPoint([x, y], true, true);
					}, 1000);
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
                text: 'Downfall in mm'
            },
			min: 0
		
        },
        legend: {
            enabled: false
        },
        plotOptions: {
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
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },

        series: [{
            type: 'area',
            name: 'Downfall',
			data: (function () {
            // generate an array of random data
				var data = [],
					time = (new Date()).getTime(),
					i;

				for (i = -999; i <= 0; i += 1) {
					data.push({
						x: time + i * 1000,
						y: Math.round(Math.random()*1000)
					});
				}
            return data;
			}())
        }]
    });
