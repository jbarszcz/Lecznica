function changeColorAlert(Stopic,alertIcon,low_alert, high_alert) {
  
  this.Stopic = Stopic;
  console.log(Stopic);
  this.low_alert = low_alert;
  this.high_alert = high_alert;

  var general_alert_icon = document.getElementById(alertIcon);
  if (R_mes != "" && R_mes_topic == Stopic) {
    console.log("@@@" + R_mes);
    var temp = Number(R_mes);

    if (Number.isInteger(temp)) {
      if (R_mes < this.low_alert) {
        general_alert_icon.style.backgroundColor = "#1f00d1";
      } else if (R_mes >= this.low_alert && R_mes <= this.high_alert) {
        general_alert_icon.style.backgroundColor = "#0f9944";
      } else {
        general_alert_icon.style.backgroundColor = "#FF0000";
      }
    } else {
	  console.log("Nierozpoznany typ wiadomości: " + temp);
	  general_alert_icon.style.backgroundColor = "transparent";
    }
  }
  //setTimeout(changeColorAlert( this.low_alert,this.high_alert ),2000);
}
