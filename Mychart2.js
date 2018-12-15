var hCharts2;

function newDate(days) {
  return moment().add(days, 'd').toDate();
}
/*
var start = new Date();
start.setHours(0,0,0,0);
var end = new Date();
end.setHours(23,59,59,999);
*/
var timeFormat = 'MM/DD/YYYY HH:mm';

var chartColors = {
  red: "rgb(255, 99, 132)",
  orange: "rgb(255, 159, 64)",
  yellow: "rgb(255, 205, 86)",
  green: "rgb(75, 192, 192)",
  blue: "rgb(54, 162, 235)",
  purple: "rgb(153, 102, 255)",
  grey: "rgb(201, 203, 207)"
};

var config = {
  type: "line",
  data: {
    labels: [],//["January", "February", "March", "April", "May", "June", "July"],
    //xAxisID:[],
    datasets: [{}
            
      /*
        {
          label: "My First dataset",
          backgroundColor: chartColors.red,
          borderColor: chartColors.red,
          data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
          ],
          fill: false
        },
        {
          label: "My Second dataset",
          fill: false,
          backgroundColor: chartColors.blue,
          borderColor: chartColors.blue,
          data: [
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor(),
            randomScalingFactor()
          ]
        }*/
    ]
  },
  options: {
    responsive: true,
    title: {
      display: true,
      text: "Chart.js Line Chart"
    },
    tooltips: {
     // mode: "index",
      //intersect: false
      //mode: 'index',
        //    axis: 'y'
        mode: 'point'
    },
    hover: {
      //mode: "nearest",
      mode: "single",
      intersect: true
    },
    scales: {
      /*xAxes: [
          {
            display: true,
            scaleLabel: {
              display: true,
              labelString: "Month"
            }
          }
        ],*/
        xAxes: [{
          /*type:"time",
          time:{
              format: timeFormat,
              tooltipFormat: 'll'
          },*/
          //x: new Date(),

          /*type: 'time',
          time: {
              displayFormats: {
                  quarter: 'MMM YYYY'
              }
          },*/

          /*type: 'time',
          time: {
              minUnit: 'minute',
              unit: 'minute',
              unitStepSize: 30,
              min: start,
              max: end
          },

*/
          display: true,
          scaleLabel: {
              display:     true,
              labelString: 'Date'
          }
/*
          type: 'time',
          time: {
              displayFormats: {
                  quarter: 'MMM YYYY'
              }
          }*/

         /* type: "time",
					display: true,
					scaleLabel: {
						display: true,
						labelString: 'Date'
          }*/

          /*type: 'time',
          time: {
            format: timeFormat,
            // round: 'day'
            tooltipFormat: 'll HH:mm'
          },
          scaleLabel: {
            display: true,
            labelString: 'Date'
          }*/

          /*type: 'time',
        time: {
          displayFormats: {
          	'millisecond': 'MMM DD',
            'second': 'MMM DD',
            'minute': 'MMM DD',
            'hour': 'MMM DD',
            'day': 'MMM DD',
            'week': 'MMM DD',
            'month': 'MMM DD',
            'quarter': 'MMM DD',
            'year': 'MMM DD',
          }
        }*/

        /*type: 'time',
        time: {
        unit: 'day',
        unitStepSize: 1,
        displayFormats: {
           'day': 'MMM DD'
        }
        }*/
       /* type: 'time',
                time: {
                    unit: 'hour'
                }*/


      }],

      yAxes: [
        {
          display: true,
          scaleLabel: {
            display: true,
            labelString: "Value"
          },
          ticks: {
            min: 0,
            max: 100,

            // forces step size to be 5 units
            stepSize: 10
          }
        }
      ]
    }
  }
};

function loadChart2(field_chart) {
  var general_chart = document.getElementById(field_chart);

  hCharts2 = general_chart.getContext("2d");
  myLine = new Chart(hCharts2, config);
  console.log("load chart2!!!!!");
}

/*document
  .getElementById("randomizeData")
  .addEventListener("click", function() {
    config.data.datasets.forEach(function(dataset) {
      dataset.data = dataset.data.map(function() {
        return randomScalingFactor();
      });
    });

    window.myLine.update();
  });*/

var colorNames = Object.keys(chartColors);

function addTopics(Stopic) {

  this.Stopic = Stopic;
  console.log(Stopic);

  var colorName = colorNames[config.data.datasets.length % colorNames.length];
  var newColor = chartColors[colorName];
  var newDataset = {
    label: Stopic,// + config.data.datasets.length,
    backgroundColor: newColor,
    borderColor: newColor,
    data: [],
    fill: false
  };

  /*for (var index = 0; index < config.data.labels.length; ++index) {
    newDataset.data.push(randomScalingFactor());
  }*/

  config.data.datasets.push(newDataset);
  myLine.update();
}

function removeTopics(Stopic) {

  this.Stopic = Stopic;
  console.log(Stopic);

 // config.data.datasets.label
  //var index_of_array = config.data.datasets.label;// .indexOf(Stopic);
 
  var temp_counter = -1;

  config.data.datasets.forEach(function(dataset) {
    
    if(dataset.label == Stopic)
    {
     
    /* while(dataset.data.length > 0) {
        dataset.data.pop();
     }*/
      return false;
    }

    console.log(temp_counter + ":" + dataset.label);
    temp_counter++;
    
  });

  config.data.datasets.splice(temp_counter, 1);
  myLine.update();
}

function addDataTopics(Stopic) {

 
    console.log("update charts2");

    if (config.data.datasets.length > 0) {

      config.data.datasets.forEach(function(dataset) {
    

        /*  config.data.labels.push(moment().format('LTS'));
          dataset.data.push(R_mes);*/
  
    });

  
}
 myLine.update();

  this.Stopic = Stopic;
  console.log("@@@" + this.Stopic);

  if (R_mes != "" && R_mes_topic == Stopic) 
  {
    console.log("update charts2");

    if (config.data.datasets.length > 0) {

      config.data.datasets.forEach(function(dataset) {
    
        if(dataset.label == Stopic)
        {
          
         // var x_data = (new Date()).getTime(); 

          //config.data.labels.push(x_data);
         config.data.labels.push(moment().format('LTS'));
         dataset.data.push(R_mes);

       /*  config.data.datasets.push({
            x: newDate(0),
					y: R_mes
         })*/
        
        /*  config.data.push({
            x: moment().format('LTS'), 
            y: R_mes,
         
        });*/
        //chart.options.scales.xAxes[0].time.unit='week';


      }
    
    });

  }
  }

 myLine.update();
/*
  if (config.data.datasets.length > 0) {
      
    console.log("######" +  moment().format('LTS'));
    var x_data = (new Date()).getTime(); 
    
    config.data.labels.push(moment().format('LTS'));

    config.data.datasets.forEach(function(dataset) {

      var x_data = (new Date()).getTime(); 
      dataset.data.push(randomScalingFactor());
    });

    myLine.update();
  }*/



  /*if (config.data.datasets.length > 0) {
    var month = MONTHS[config.data.labels.length % MONTHS.length];
    config.data.labels.push(month);

    config.data.datasets.forEach(function(dataset) {
      dataset.data.push(randomScalingFactor());
    });

    myLine.update();
  }*/
}

function removeDataTopics() {
  config.data.labels.splice(-1, 1); // remove the label first

  config.data.datasets.forEach(function(dataset) {
    dataset.data.pop();
  });

  myLine.update();
}
