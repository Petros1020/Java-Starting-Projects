(function(){ 

var data =[];   //Make a variable to keep track of the data coming from Firebase
var ref = new Firebase("https://yikesitsmikes.firebaseio.com/activities");
 ref.on("value", function(snapshot){ //Listen to data updates from firebase
		console.log(snapshot.val());
    data = snapshot.val(); //Dhen data updates at Firebase, put it in the data variable
 })

 
//Submit Function
$('#newActivity').submit(function(event){
  var $form = $(this);
  console.log("submit to Firebase");

  $form.find("#saveForm").prop('disabled', true); //make the submit disabled
  
  //get the actual values that we will send to firebase
  var titleToSend = $('#activityTitle').val();
  var descToSend = $('#activityDescription').val();
  var categoryToSend = $('#activityCatagory').val();
  
  console.log(categoryToSend); 
  console.log(titleToSend);
  console.log(descToSend);
  
  var newActivity = { //take the values from the form, and put them in an object
      "description": descToSend,
      "title":titleToSend,
      "type": categoryToSend
  }
  data.push(newActivity); //Put the new object into the data array
  console.log(data);
  ref.set(data, function(err){ //Send the new data to Firebase
    if(err){
      console.log(err);
      alert("All fields must be filled out.");
    }
    else{
      alert("Activity Successfully Submitted!");
    }
  }); 
  return false; 
})


 $("#registerForm").click(function(event){ //Clicked the Register Button
   
    var $form = $(this);
    $form.find('#registerForm').prop('disabled', true); //Disable the button
   
    var login = $("#loginInput").val();//get the value of the login email
    var pass = $("#pwField").val();//get the value of the password
   
    reg.createUser({ //Create User from data
      email: login,
      password: pass
    }, function(error, userData){
      if(error){
        alert("Already Registered or Error with Form");
      }else{
        alert("You registered "+userData.uid);
      }
    })
	return false;  
 }); //End Register Button
  
  
  
 $("#finalloginForm").click(function(event){ //Clicked the login Button
   
    var $form = $(this);
    $form.find('#registerForm').prop('disabled', true); //Disable the button
   
    var login = $("#loginInput").val();//get the value of the login email
    var pass = $("#pwField").val();//get the value of the password
   
    var reg = new Firebase("https://yikesitsmikes.firebaseio.com");
    reg.authWithPassword({
      email: login,
      password: pass
    },function(error, user){
      if(error){
        alert("Email and password combination do not exist")
      }else{
        alert("Logged in with "+user.uid);
         $('#formHolder').css('display', 'block');
      }
    })
   
  return false;  
 });
 
  
  

  
  ////Detect Authication state
  var reg = new Firebase("https://yikesitsmikes.firebaseio.com");
  reg.onAuth(function(authData){
    console.log("info on authentication");
    console.log(reg);
    
    if(authData){ //If Logged in
      
      console.log("You are logged in!");
      $('#register').css('display', 'none');
      $('#loginform').css('display', 'none');
      $('#formHolder').css('width', '100%');
      $('#spacer').css('height', '0');
      $('#logoutbut').css('display', 'block');
      
    }else{
      console.log("You are not logged in"); 
      $('#logoutbut').css('display', 'none');
    }
  })
  

  
  //Logout Function
  $('#logout').click(function(event){
  	console.log("Logging out");
  	var reg = new Firebase("https://yikesitsmikes.firebaseio.com");
 	  reg.unauth();
    alert("You have logged out.")
  })
});




$(function(){
  
  $('.better-forms .gui-input input, .better-forms .gui-input	 select').focusout(function(){
    
    var text_val = $(this).val();
    
    if(text_val === "" || text_val == "0") {
      
      $(this).removeClass('has-value');
      
    } else {
      
      $(this).addClass('has-value');
      
    }
    
  });
});