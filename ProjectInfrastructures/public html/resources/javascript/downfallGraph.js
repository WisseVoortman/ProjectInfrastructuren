var DownfallGraphChart;

function downfallGraph() {

	DownfallGraphChart = Highcharts.chart('downfallGraph', {
        chart: {
            zoomType: 'x'
        },
        boost: {
            useGPUTranslations: true
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                    'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        legend: {
            enabled: true
        },
		yAxis: {
            min: 0
            //max: 100
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
    });

}
