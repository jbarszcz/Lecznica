var hCharts3;

function loadChart3(field_chart,title_Chart,title_X,title_Y)
{
    var general_chart = document.getElementById(field_chart);
    var general_topic_chart = title_Chart;
    var general_topic_chart_x = title_X;
    var general_topic_chart_y = title_Y;
    console.log("load charts3");
    hCharts3 = Highcharts.chart(general_chart, {

    title: {
        text: general_topic_chart
    },

    subtitle: {
        text: 'Source: Sensor1'
    },

    xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: { // don't display the dummy year
            month: '%e. %b',
            year: '%b'
        },
        title: {
            text: title_X
        }
    },
   
    yAxis: {
        title: {
            text: general_topic_chart_y
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'
    },

    plotOptions: {
        series: {
            label: {
                connectorAllowed: false
            },
            pointStart: 2010
        }
    },


    series: [
        {
          name: "",
          data: (function() {
            // generate an array of random data
            var data = [];
            return data;
          })()
        }
      ],

    /*series: [{
        name: 'Installation',
        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
    }, {
        name: 'Manufacturing',
        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
    }, {
        name: 'Sales & Distribution',
        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
    }, {
        name: 'Project Development',
        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
    }, {
        name: 'Other',
        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
    }],*/

    responsive: {
        rules: [{
            condition: {
                maxWidth: 500
            },
            chartOptions: {
                legend: {
                    layout: 'horizontal',
                    align: 'center',
                    verticalAlign: 'bottom'
                }
            }
        }]
    }

});
}

function updateChart3(Stopic)
{
  this.Stopic = Stopic;
  console.log(Stopic);

   //hCharts.setTitle({ text: Stopic });
  
  if (R_mes != "" && R_mes_topic == Stopic) 
  {
    console.log("update charts3");

    var x_data = (new Date()).getTime();    
    var point = [x_data, Number(R_mes)];

    hCharts3.series[0].addPoint(point, true);

  }
}