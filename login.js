var attempt = 3; // Variable to count number of attempts.
// Below function Executes on click of login button.
function validate() {
  var username = document.getElementById("login").value;
  var password = document.getElementById("password").value;
  if (username == "test" && password == "test") {
    alert("Login successfully");
   
    //window.location = "mqtt/client.html"; // Redirecting to other page.
    //location.href="mqtt/client.html";
    //setTimeout("location.href='mqtt/client.html';",1000);
	
  MQTTconnect();
  $("body").load( "client.html" );
  //console.log("@@@"+connected_flag);
  
 // setTimeout(console.log("@@@"+connected_flag),2000);
    //location.replace("client.html");
    return false;
  } else {
    attempt--; // Decrementing by one.
    alert("You have left " + attempt + " attempt;");
    document.getElementById("login").value = "";
    document.getElementById("password").value = "";
    // Disabling fields after 3 attempts.
    if (attempt == 0) {
      document.getElementById("login").disabled = true;
      document.getElementById("password").disabled = true;
      document.getElementById("submit").disabled = true;
      return false;
    }
  }
}
//window.location.reload()