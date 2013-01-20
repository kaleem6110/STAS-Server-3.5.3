<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=false&amp;key=<%=request.getParameter("key")%>"
            type="text/javascript"></script>
			<style>
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


    <script type="text/javascript">
 var map=null;
 var trafficOverlayVisible=false;
 var  trafficOverlay=null;
 var streetOverlayVisible=false;
 var  streetOverlay=null;
 var streetViewVisible=false;
 var  streetView=null;
 var eventHandler=null;
 var longitute = -122.1419;
 var latitude = 37.4419;
 var zoomLevel = 13;
 
 	function getCurrentMapView(){
		try {
			if(parent != null && parent.getSTViewerIFrame() != null){
				var currentView = parent.getSTViewerIFrame().getCurrentView();
				var currentViewTokens = currentView.split( "," );
				if(currentViewTokens != null && currentViewTokens.length > 3){
					longitute = parseFloat(currentViewTokens[0]);
					latitude = parseFloat(currentViewTokens[1]);
					var range = parseFloat(currentViewTokens[2]);
					zoomLevel = Math.round(26-(Math.log(range)/Math.log(2)));
				}
			} 
		} catch (e) {
			showAlert('Error ['+e.message+'] occurred in loading Google Maps');
		}
 	}
 
 	function initialize() {
		if(GBrowserIsCompatible()) {
			getCurrentMapView();
			map = new GMap2(document.getElementById("map_canvas"));
			streetView = new GStreetviewPanorama(document.getElementById("map_stview_canvas"));
			map.setCenter(new GLatLng(latitude, longitute), zoomLevel);
			
			map.setUIToDefault();
			map.setMapType(G_HYBRID_MAP);
			var trafficOptions = {incidents:true};
			trafficOverlay = new GTrafficOverlay(trafficOptions);
			streetOverlay=new GStreetviewOverlay();
			trafficOverlayVisible=true;
			map.addOverlay(trafficOverlay);
		}
    }

	function enableTraffic(){
		if(!trafficOverlayVisible){
			trafficOverlayVisible=true;
		 map.addOverlay(trafficOverlay);
		
		}else{
			trafficOverlayVisible=false;
		 map.removeOverlay(trafficOverlay);
		
		}
	}
	
	function enableStreets(){
		if(!streetOverlayVisible){
			streetOverlayVisible=true;
		 map.addOverlay(streetOverlay);
		}else{
			streetOverlayVisible=false;
		 map.removeOverlay(streetOverlay);
		}
	}
	
	function enableStreetView(){
		if(!streetViewVisible){
			streetViewVisible=true;
						document.getElementById("map_stview_canvas").style.display="";
		eventHandler=GEvent.addListener(map,"click", streetViewOnClick);
		// set some default streetview instead of displaying blank
		var loc = new GLatLng(latitude, longitute); 
		streetView.setLocationAndPOV(loc);
		}else{
			streetViewVisible=false;
			
		 GEvent.removeListener(eventHandler);
		 document.getElementById("map_stview_canvas").style.display="none";

		}
	}
	
	function streetViewOnClick(verlay,latlng){
		streetView.setLocationAndPOV(latlng);
	}
	
	function setMapView(north,south,east,west){
		//document.getElementById("text-latlng").innerHTML="----:"+lat+","+lng;
			//alert(" set map view called");
			//map.setCenter(new GLatLng(lat,lng),13);

			// Define the two corners of the bounding box
			var sw = new GLatLng(south, west);
			var ne = new GLatLng(north, east);

			// Create a bounding box
			var bounds = new GLatLngBounds(sw, ne);

			// Center map in the center of the bounding box
			// and calculate the appropriate zoom level
			map.setCenter(bounds.getCenter(), map.getBoundsZoomLevel(bounds));


	}

    </script>
  </head>
  <body onload="initialize()" onunload="GUnload()" bgcolor="black">
  <div id="map_stview_canvas" style="width: 100%; height: 350px;float:left;display: none"></div>
<div class="container">				
				<div id="map_canvas" class="map" style="width: 100%; height: 300px;float:left;z-index: 0">&nbsp;</div>
				<div id="over_map" class="over_map" style="float:right;height: 20px">
				<button onclick="enableTraffic();" name="traffic" style="color: #000000; backgroundColor: white; font: small Arial; border: 1px solid black; padding: 0px; margin: 0px; textAlign: center; fontSize: 12px; cursor: pointer;">Traffic</button>
				<button onclick="enableStreets();" name="streets" style="color: #000000; backgroundColor: white; font: small Arial; border: 1px solid black; padding: 0px; margin: 0px; textAlign: center; fontSize: 12px; cursor: pointer;">Streets</button>
				</div>
</div>

  </body>
</html>
