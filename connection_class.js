var connected_flag = 0;
var mqtt;
var reconnectTimeout = 2000;
var host = "127.0.0.1";
var port = 9001;
var user_name = "test";
var password = "test";
var clean_sessions = false;
var sqos = 0;
var out_msg = "";
var R_mes = "";
var R_mes_topic = "";
var mcount = 0;
var topic = "sensors/temperature/1";
var licznik = 0;

function onConnectionLost() {
  console.log("connection lost");
  //document.getElementById("status").innerHTML = "Connection Lost";
  //document.getElementById("messages").innerHTML ="Connection Lost";
  connected_flag = 0;
}
function onFailure(message) {
  console.log("Failed");
  //document.getElementById("messages").innerHTML = "Connection Failed- Retrying";
  setTimeout(MQTTconnect, reconnectTimeout);
}
function onMessageArrived(r_message) {
  R_mes = r_message.payloadString;
  R_mes_topic = r_message.destinationName;

  out_msg = "Message received " + r_message.payloadString + "<br>";
  out_msg =
    out_msg + "Message received Topic " + r_message.destinationName + "<br/>";
  //out_msg="<b>"+out_msg+"</b>";
  //console.log("Message received ",r_message.payloadString);
  console.log(out_msg);
  mcount += 1;
  console.log(mcount);
  console.log(R_mes);
  //document.getElementById("messages").innerHTML =out_msg;
  userDefinedCallback(r_message.payloadString, r_message.destinationName);
}
function onConnected(recon, url) {
  console.log(" in onConnected " + reconn);
}
function onConnect() {
  // Once a connection has been made, make a subscription and send a message.
  //document.getElementById("messages").innerHTML ="Connected to "+host +"on port "+port;
  connected_flag = 1;
  //document.getElementById("status").innerHTML = "Connected";
  console.log("on Connect " + connected_flag);
}
function disconnect() {
  if (connected_flag == 1) 
  {
    mqtt.disconnect();
    //window.location.reload();
    console.log("Disconnect");
  }
}
function MQTTconnect() {
  //document.getElementById("messages").innerHTML ="";

  console.log(
    "connecting to " + host + " " + port + "clean session=" + clean_sessions
  );
  console.log("user " + user_name);
  console.log("password " + password);
  mqtt = new Paho.MQTT.Client(
    host,
    port,
    "web_" +
      Math.random()
        .toString(16)
        .substr(2, 8)
  );
  //document.write("connecting to "+ host);
  var options = {
    //useSSL:true,
    timeout: 3,
    //cleanSession: clean_sessions,
    userName: $("#login").val(),
    password: $("#password").val(),
    onSuccess: onConnect,
    onFailure: onFailure
  };

  mqtt.onConnectionLost = onConnectionLost;
  mqtt.onMessageArrived = onMessageArrived;
  mqtt.onConnected = onConnected;

  mqtt.connect(options); //connect
  return false;
}
function sub_topics(subs, Stopic) {
  //document.getElementById("messages").innerHTML ="";
  console.log("inside sub topics");

  if (connected_flag == 0) {
    out_msg = "<b>Not Connected so can't subscribe</b>";
    console.log(out_msg);
    //document.getElementById("messages").innerHTML = out_msg;
    return false;
  }

  var stopic = document.forms[subs][Stopic].value;
  if(stopic != "")
  {
    
    console.log("Subscribing to topic =" + stopic);
    //console.log("Subscribing to topic ="+topic +" QOS " +sqos);
    //	document.getElementById("status_messages").innerHTML = "Subscribing to topic ="+stopic;
    /*var soptions={
  	qos:sqos,
	  };*/
    //mqtt.subscribe(topic,soptions);
    mqtt.subscribe(stopic);
  }
  else
  {
    console.log("You can't subscribing to empty topic!");
  }
  return false;
}

function unsub_topics(subs, Stopic) {
  //document.getElementById("messages").innerHTML ="";
  console.log("inside unsub topics");

  if (connected_flag == 0) {
    out_msg = "<b>Not Connected so can't unsubscribe</b>";
    console.log(out_msg);
    //document.getElementById("messages").innerHTML = out_msg;
    return false;
  }
  var stopic = document.forms[subs][Stopic].value;
  
  if(stopic != "")
  {
    console.log("Unsubscribing to topic =" + stopic);
    //console.log("Subscribing to topic ="+topic +" QOS " +sqos);
    //	document.getElementById("status_messages").innerHTML = "Subscribing to topic ="+stopic;
    /*var soptions={
	  qos:sqos,
	  };*/
    //mqtt.subscribe(topic,soptions);
    mqtt.unsubscribe(stopic);
  }
  else
  {
    console.log("You can't unsubscribing to empty topic!");
  }
  return false;
}

function sub_topics_char(subs, Stopic) {
  //document.getElementById("messages").innerHTML ="";
  console.log("inside sub topics of char");

  if (connected_flag == 0) {
    out_msg = "<b>Not Connected so can't subscribe</b>";
    console.log(out_msg);
    //document.getElementById("messages").innerHTML = out_msg;
    return false;
  }

  var stopic = document.forms[subs][Stopic].value;
  if(stopic != "")
  {
    
    console.log("Subscribing to topic =" + stopic);
    //console.log("Subscribing to topic ="+topic +" QOS " +sqos);
    //	document.getElementById("status_messages").innerHTML = "Subscribing to topic ="+stopic;
    /*var soptions={
  	qos:sqos,
	  };*/
    //mqtt.subscribe(topic,soptions);
    mqtt.subscribe(stopic);
    addTopics(stopic);
  }
  else
  {
    console.log("You can't subscribing to empty topic!");
  }
  return false;
}

function unsub_topics_char(subs, Stopic) {
  //document.getElementById("messages").innerHTML ="";
  console.log("inside unsub topics of char");

  if (connected_flag == 0) {
    out_msg = "<b>Not Connected so can't unsubscribe</b>";
    console.log(out_msg);
    //document.getElementById("messages").innerHTML = out_msg;
    return false;
  }
  var stopic = document.forms[subs][Stopic].value;
  
  if(stopic != "")
  {
    console.log("Unsubscribing to topic =" + stopic);
    //console.log("Subscribing to topic ="+topic +" QOS " +sqos);
    //	document.getElementById("status_messages").innerHTML = "Subscribing to topic ="+stopic;
    /*var soptions={
	  qos:sqos,
	  };*/
    //mqtt.subscribe(topic,soptions);
    mqtt.unsubscribe(stopic);
    removeTopics(stopic);
  }
  else
  {
    console.log("You can't unsubscribing to empty topic!");
  }
  return false;
}

