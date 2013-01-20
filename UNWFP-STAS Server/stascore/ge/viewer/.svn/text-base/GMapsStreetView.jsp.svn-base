<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    
<!--THIS IS THE CODE WITH THE DESIRED FUNCTIONALITY-->

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>            
<style type="text/css">
	html, body {
			margin: 0;
			padding: 0;
		   } 
	      input.small 
		   {
			font-size: 50%
		   }

</style>

<style type="text/css">
 
div.container {
	position:relative;
}

div.map {
	position:absolute;
	z-index:1;
	
	}
div.over_map {
	position:relative;
	top:25px;
	right:10px;
	background: url(../images/1x1pixel.gif) repeat;
	z-index:2;
}
</style>

<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=<%=request.getParameter("key")%>"
            type="text/javascript">
</script>
<script type="text/javascript">
    
    var whereweare;
    var panoramaOptions; 
    var myPano;
    
    function initialize() {
      myPano = null;
      whereweare = null;
      panoramaOptions = null;
      var lookAtStr = parent.getCurrentView();
	  var lat = 37.4419;
	  var lon = -122.1419;
	  if(lookAtStr && lookAtStr != '') {
		  lookAtArray = lookAtStr.split(",");
		  try {			  
			  lon = parseFloat(lookAtArray[0]);
			  lat = parseFloat(lookAtArray[1]);
		  } catch (e) {
			  //ignore
		  }
	  }
	  whereweare = new GLatLng(lat, lon);
      panoramaOptions = { latlng:whereweare };
      myPano = new GStreetviewPanorama(document.getElementById("pano"), panoramaOptions);
      
      GEvent.addListener(myPano, "error", errorHandler);   
	  GEvent.addListener(myPano, "initialized", streetViewChanged);
    }
    
    function setMapView(lat, lng) {	  
    	try {
    	   myPano.setLocationAndPOV(new GLatLng(parseFloat(lat), parseFloat(lng)));

    	   
    	 } catch(e) {
    	 	alert('Error ['+e.message+'] while setting the location in streeview');	
    	 }
    	   
    }
    
    function streetViewChanged(location) {
		if(document.getElementById("pano").style.display == "none") {
    	   document.getElementById("disclaimer").style.display = "none";
    	   document.getElementById("pano").style.display = "block";
		}
	}
        
    function errorHandler(errorCode) {    	
    	if(errorCode==600) {	
    		//alert(errorCode);
			if(document.getElementById("disclaimer").style.display == "none") {
    			document.getElementById("disclaimer").style.display = "block";
    			document.getElementById("pano").style.display = "none";
			}
   		}
    }  
    </script>
</head>

<body onload="initialize()" onunload="GUnload()" bgcolor="black">
    <div name="disclaimer" id="disclaimer" 
     style= "width: 100%; height: 100%; 
     float:center; z-index: 0; background-color: black; display: none">
     	<center>
     	<br><br>
     	<font color="white" size="5">Google Street-View does not have image data for the clicked location.</font><br><br>	
     	</center>
     </div>	
    <div name="pano" id="pano" style="width: 100%; height: 300px;float:left;z-index: 0;display: block"></div>
</body>
  
</html>
