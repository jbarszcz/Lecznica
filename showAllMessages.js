function showAllMessages(list,limit) {

  var general_list = document.getElementById(list);
  this.limit = limit; 

  var d = new Date();
  var mt = d.getMonth() + 1;

  if (R_mes != "") {
    if (d.getMonth() + 1 < 10) mt = "0" + (d.getMonth() + 1);

    var md = d.getDate();
    if (d.getDate() < 10) md = "0" + d.getDate();

    var mg = d.getHours();
    if (d.getHours() < 10) mg = "0" + d.getHours();

    var mm = d.getMinutes();
    if (d.getMinutes() < 10) mm = "0" + d.getMinutes();

    var ms = d.getSeconds();
    if (d.getSeconds() < 10) ms = "0" + d.getSeconds();

    if (licznik >= this.limit) {
      //$("#general_list").html("");
      general_list.innerHTML = "";
      licznik = 0;
    }

    $(general_list).prepend(
      "<li> [" +
        (licznik + 1) +
        ", " +
        d.getFullYear() +
        "-" +
        mt +
        "-" +
        md +
        ", " +
        mg +
        ":" +
        mm +
        ":" +
        ms +
        "] " +
        R_mes_topic +
        " = " +
        R_mes +
        "</li>"
    );
	licznik++;
    /*
        var parsedMsg = JSON.parse(this.r_message.payloadString);
			
		general_list.innerHTML = '<li> [' + (licznik+1) + ', ' + d.getFullYear() + '-' + mt + '-' + md + ', ' + mg + ':' + mm + ':' + ms + '] ' + 'topic: ' + topic + ' = ' +  parsedMsg.s  + '</li>'+ "<br />" + general_list.innerHTML;	
				
		var x_data =  (mg + ':' + mm + ':' + ms);   //(new Date()).getTime();
		//console.log("@@@@@@@@@@@" + x_data);
				
		point = [x_data, parsedMsg.s];
        hCharts.series[0].addPoint(point, true);  */
  }
}
