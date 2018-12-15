var hCharts1;

function loadChart1(field_chart,title_Chart,title_X,title_Y)
{
    var general_chart = document.getElementById(field_chart);
    var general_topic_chart = title_Chart;
    var general_topic_chart_x = title_X;
    var general_topic_chart_y = title_Y;
    console.log("load charts");
    
    hCharts1 = Highcharts.chart(general_chart, {
    chart: {
      type: "spline",
      animation: Highcharts.svg, // don't animate in old IE
      marginRight: 10
    },
    title: {
      text: general_topic_chart
    },
    xAxis: {
      title: {
        text: general_topic_chart_x
      },
      type: 'datetime',
      tickPixelInterval: 150
    },
    yAxis: {
      title: {
        text: general_topic_chart_y
      },
      plotLines: [
        {
          value: 0,
          width: 1,
          color: "#808080"
        }
      ]
    },
    tooltip: {
      /*formatter: function() {
        return (
          "<b>" +
          this.series.name +
          "</b><br/>" +
          this.x +
          "<br/>" + //'%H:%M:%S',
          //Highcharts.dateFormat(this.x) + '<br/>' +
          this.y
        );
      }*/

      //headerFormat: '<b>{series.name}</b><br/>',
      headerFormat: '<br/>',
      pointFormat:'{point.x:%H:%M:%S}<br/>{point.y}'
     
    },
    legend: {
      enabled: false
    },
    exporting: {
      enabled: false
    },
    series: [
      {
        //name: "Data",
        data: (function() {
          // generate an array of random data
          var data = [];
          return data;
        })()
      }
    ]
  });
}

function updateChart1(Stopic)
{
  this.Stopic = Stopic;
  console.log(Stopic);

   //hCharts.setTitle({ text: Stopic });
  
  if (R_mes != "" && R_mes_topic == Stopic) 
  {
    console.log("update charts");

    var x_data = (new Date()).getTime();    
    var point = [x_data, Number(R_mes)];

    hCharts1.series[0].addPoint(point, true);

  }
}