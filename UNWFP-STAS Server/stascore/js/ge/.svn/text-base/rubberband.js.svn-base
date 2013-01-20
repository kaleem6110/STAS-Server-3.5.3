/* ++++++++++++++++++++++++++++++++++++++++++++++
* Rubberbanding Code -- Space-Time Insight
*
* Created On : 24th May 2011
*++++++++++++++++++++++++++++++++++++++++++++++++
/*
Code for Draw Mode
*/
/* Rubberbanding draw code - start */
    var currentRBPolyPlacemark;
    var initCircleCreation = false;
    var radiusValuePlacemark=null;
    var rbFeaturesArray = new Array();

    function drawRBFeature(type) {
        resetRBFeatures();
        if(type == 'rbpolygon') {
            drawRBPolygon();
        }else if(type == 'rbcircle') {
            initCircleCreation = true;
            drawRBCircle();
        }
    }

    function removeRBFeature(id) {
        resetRBFeatures();
        if(rbFeaturesArray[id]) {
            ge.getFeatures().removeChild(rbFeaturesArray[id]);
            rbFeaturesArray.remove(id);
        }
    }

    function showRBFeature(id, viewportStr, flag) {
		if(rbFeaturesArray[id]) { 
			rbFeaturesArray[id].setVisibility(flag);
            if(radiusValuePlacemark){
				ge.getFeatures().removeChild(radiusValuePlacemark);
			}
        }
        if(flag) {
			setSerializedViewPort(viewportStr);
		}
    }	

    function resetRBFeatures() {
        if(currentRBPolyPlacemark) {
            gex.edit.endEditLineString(currentRBPolyPlacemark.getGeometry().getOuterBoundary());
            currentRBPolyPlacemark = null;
        }       
    }

    function createRBPolygon(id, coordStr, flag,viewPort) {

        idCount++;
        var rbPlacemark = gex.dom.addPolygonPlacemark([], {
             id: 'rb_'+id,
             style: {
              poly: '8000ff00',
              line: { width: 3, color: '#0f0' }
            }
          });
        viewPortArray[rbPlacemark.getId()]=viewPort;
        setPolygonCoordinates(rbPlacemark, coordStr);
        rbPlacemark.setVisibility(flag);
        rbFeaturesArray[rbPlacemark.getId()] = rbPlacemark;
    }

    function drawRBPolygon() {
        try {
        idCount++;
        currentRBPolyPlacemark = gex.dom.addPolygonPlacemark([], {
         id: 'rb_'+idCount,
         style: {
          poly: '8000ff00',
          line: { width: 3, color: '#0f0' }
        }
      });

      var optionsObj = new Object();
      optionsObj.finishCallback = drawRBPolygonFinished;
      gex.edit.drawLineString(currentRBPolyPlacemark.getGeometry().getOuterBoundary(),optionsObj);

        } catch(e) {
             alert('drawRBPolygon'+e.message);
        }
    }

    function drawRBPolygonFinished() {
        try{
            if(currentRBPolyPlacemark) {
                var count=currentRBPolyPlacemark.getGeometry().getOuterBoundary().getCoordinates().getLength();
                if(count<=2) {
                    gex.edit.endEditLineString(currentRBPolyPlacemark.getGeometry().getOuterBoundary());
                    ge.getFeatures().removeChild(currentRBPolyPlacemark);
                    currentRBPolyPlacemark=null;
                    parent.addFeature("", "", "",count+"");
                    return;
                }
                if(currentRBPolyPlacemark!=null) {
                   parent.addFeature(currentRBPolyPlacemark.getId(), getPolygonCoordinates(currentRBPolyPlacemark), "rbpolygon",count+"");
                }
                parent.fireGEEvent('RB_POLYGON', 'click', 'deactivate');
                //add the placemark to the array
                rbFeaturesArray[currentRBPolyPlacemark.getId()] = currentRBPolyPlacemark;
                viewPortArray[currentRBPolyPlacemark.getId()]=getSerializedViewPort();
                gex.edit.endEditLineString(currentRBPolyPlacemark.getGeometry().getOuterBoundary());
            }
            //initializeMarkerMouseListeners(true);
        }
        catch (e) {
            alert('Error in drawing the polygon '+e.message);
        }
        currentRBPolyPlacemark = null;
        //isDrawing = false;
    }

    function getSerializedViewPort() {
        return gex.util.serializeView();
    }

	function setSerializedViewPort(viewPortStr) {
        if(viewPortStr && viewPortStr != '' && viewPortStr!='undefined') {
            gex.util.deserializeView(viewPortStr);
        }
    }

/* Rubberbanding Circle code - start */

    // This function is called to load already existing circles into the list, this function executes when initializing RB regions.
       function createRBCircle(id, coordStr, flag,viewPort,pCcenterCoordinates,pCircumCoordinates, radius,operation){   
     
		var centerCoordinates = pCcenterCoordinates.split(",");
		var circumCoordinates = pCircumCoordinates.split(",");
    
            
        var x1 = parseFloat(centerCoordinates[0]);
        var y1 = parseFloat(centerCoordinates[1]);
        var x2 = parseFloat(circumCoordinates[0]);
        var y2 = parseFloat(circumCoordinates[1]);
	    
      	
       
        if(operation!=null && operation=="RADIUS_CHANGED"){   
			// if user has changed the radius through the text input
            lastSelectedRegion= rbFeaturesArray[id]; 
			createNewCircle(radius, x1, y1, '');		
			
            var geometry = currentRBPolyPlacemark.getGeometry();
               
           if(rbFeaturesArray[id]) {
                rbFeaturesArray[id].setGeometry(currentRBPolyPlacemark.getGeometry());          
                rbFeaturesArray[id].setVisibility(true);    
                google.earth.addEventListener(rbFeaturesArray[id], 'mouseover', circleRegionMouseOverHandler);
                google.earth.addEventListener(rbFeaturesArray[id], 'mouseout', circleRegionMouseOutHandler);
           }
                                     
			lastSelectedRegion.setGeometry(geometry);               
			var rubberBand=new RubberBand(id,getPolygonCoordinates(lastSelectedRegion),viewPortArray[id]);         
			afterEditRubberbandRegionsArray[id] = rubberBand;
			
			ge.getFeatures().removeChild(currentRBPolyPlacemark); 
			
        }  else{
			var term1= (x2-(x1));
            var term2= (y2-(y1));           
           	var term = (term1*term1)+(term2*term2);
            radius = Math.sqrt(term); 
			createNewCircle(radius, x1, y1, id);
			// loac the circle RB to the list
				viewPortArray[currentRBPolyPlacemark.getId()]=viewPort;     
				currentRBPolyPlacemark.setVisibility(flag);
				rbFeaturesArray[currentRBPolyPlacemark.getId()] = currentRBPolyPlacemark;
				google.earth.addEventListener(rbFeaturesArray[currentRBPolyPlacemark.getId()], 'mouseover', circleRegionMouseOverHandler);
				google.earth.addEventListener(rbFeaturesArray[currentRBPolyPlacemark.getId()], 'mouseout', circleRegionMouseOutHandler);
		}      
        currentRBPolyPlacemark= null;
		//lastSelectedRegion=null;
     }
        

    /*
        Creation of circle   
        Registers the Click event Listener on Google window
    */

	function drawRBCircle() {
		createNewCircleFlag=true;
        radius = radiusConst;
        google.earth.addEventListener(ge.getGlobe(), 'click', geMouseClickHandler);
    }
    
    /*      
        It creates a circle with 32 coordinates having defined radius & center
    */  
    function makeCircle(radius, centerLat, centerLng) {
		var ring = ge.createLinearRing('');
        var steps = 32;
        var pi2 = Math.PI * 2;
        for (var i = 0; i < steps; i++) {
			var lat = centerLat + radius * Math.cos(i / steps * pi2);
			var lng = centerLng + radius * Math.sin(i / steps * pi2);
			ring.getCoordinates().pushLatLngAlt(lat, lng, 0);
			if( i==8){
				circumLatitude = lat;
                circumLongitude = lng;
            }
        }
        return ring;
      }
    
    /*
        Mouse event Handler on GE Window, 
        @param: kmlEvent 
    */  
    function geMouseClickHandler(kmlEvent){         
        //loading the circle
		loadCircle(radius, kmlEvent.getLatitude(), kmlEvent.getLongitude());
        //remove the listener after creating circle - success
        google.earth.removeEventListener(ge.getGlobe(), 'click', geMouseClickHandler);                     
    }
    
    /*
        -Creates the circle with the defined radius
        after removing the circle existing
        - creates the center 
        - creates the circumference
    */
        
    function loadCircle(radius, latitude, longitude){
		removeCircleChildren();
        createNewCircle(radius, latitude, longitude, '');
        
        drawPoint(center, latitude, longitude, '');
        var roundedRadius = Math.round((radius*(milesMultipleConst))*Math.pow(10,2))/Math.pow(10,2);
        if(radius*(milesMultipleConst) < 0.10)
        {
            roundedRadiusFeet = Math.round((radius*(milesMultipleConst)*(feetMultipleConst))*Math.pow(10,2))/Math.pow(10,2);   
            drawPoint(circumference, circumLatitude, circumLongitude, roundedRadiusFeet +" feet");
            
        }
        else{
        drawPoint(circumference, circumLatitude, circumLongitude, roundedRadius +" miles");
        }   
    }
    
    /*
        removes the circle & its children
    */  
    function removeCircleChildren(){
        if(currentRBPolyPlacemark)
            ge.getFeatures().removeChild(currentRBPolyPlacemark);
        if(center)
            ge.getFeatures().removeChild(center);
        if(circumference)
            ge.getFeatures().removeChild(circumference);   
        if(initCircleCreation){
            initCircleCreation = false;
            radius = radiusConst;
        }
    }

   
    /*
        -creates the circle  with the defined radius 
    */
    function createNewCircle(radius, latitude, longitude, predefinedId){  
		var targetId = '';
        idCount++;
        if(predefinedId == ''){         
            targetId ='rbcircle_'+idCount;
        }else{
            targetId = 'rbcircle_'+predefinedId;
        }

        currentRBPolyPlacemark = gex.dom.addPolygonPlacemark([], {
			id: targetId,
			style: {
				poly: '8000ff00',
				line: { width: 3, color: '#0f0' }
			}
        });  

        currentRBPolyPlacemark.getGeometry().setOuterBoundary(makeCircle(radius, latitude, longitude));
        ge.getFeatures().appendChild(currentRBPolyPlacemark);
        
        //registers the Listener for Circle placemark
        registerPMListener();
    }
    
    /*
        Add the listeners
        1) mousedown - adds the click listener on the google earth (target object - circle )
        2) mousedown - adds the click listener on the google earth (target object - google window )
    */
    function registerPMListener(){
        google.earth.addEventListener(currentRBPolyPlacemark, 'mousedown', circleClickHandler);
        google.earth.addEventListener(ge.getWindow(), 'mousedown', circleClickHandler);
    }
    
    /*
        removes the click listener on google window
        
    */
    function circleClickHandler(kmlEvent){
        google.earth.removeEventListener(ge.getWindow(), 'click', geMouseClickHandler);     
    }
    
    function circumferenceMoveHandler(kmlEvent){                
	    var radiusCircum =getRadiusParameterised(center.getGeometry().getLatitude(),kmlEvent.getLatitude(),center.getGeometry().getLongitude(),kmlEvent.getLongitude());          var roundedRadius = Math.round((radiusCircum*(milesMultipleConst))*Math.pow(10,2))/Math.pow(10,2);  
        if(radiusCircum*(milesMultipleConst) < 0.10)
        {
			roundedRadiusFeet = Math.round((radiusCircum*(milesMultipleConst)*(feetMultipleConst))*Math.pow(10,2))/Math.pow(10,2);  
            kmlEvent.getTarget().setName(roundedRadiusFeet +" feet");
        }
        else{
            kmlEvent.getTarget().setName(roundedRadius + " miles");
        }
    }
    
    /*
        registers some call back methods
        1) circlePointDropCallBackEvent - event handler - when drop the object
        2) dragCall BackEvent - event handler - when dragging the object
    */  
    function registerDraggableObject(targetObj){
		gex.edit.makeDraggable(targetObj, {
			dropCallback: function() {            
				circlePointDropCallBackEvent(targetObj);
		    },
		    dragCallback: function() {            
				circlePointDragCallBackEvent(targetObj);
		    }
        });
    }
    
    /*
        Drag Handler.
        1) adds the google window - click listener - if its an center change event
        2) adds the radius changes handler - if its an circumference change event
    */
    function circlePointDragCallBackEvent(pointPlacemark){
		if(createNewCircleFlag){
			lastSelectedRegion=null;
			var pointName = pointPlacemark.getName();
        
			if(pointName == ''){          
			    google.earth.addEventListener(ge.getWindow(), 'click', geMouseClickHandler);            
		    }else{
		        radius = getRadius();
		        google.earth.addEventListener(ge.getWindow(), 'click', radiusChangeHandler);
		    }
		}
    }
    
    /*
        Radius changes , calculates the updated radius & pass the same to laodcircle method
        - remove the click listener on the radius change 
    */
    function radiusChangeHandler(kmlEvent){            
        loadCircle(radius, center.getGeometry().getLatitude(), center.getGeometry().getLongitude());
        google.earth.removeEventListener(ge.getWindow(), 'click', radiusChangeHandler);     
    }
   
    /*
        Circumference point changes- so  calculate the updated 
        radius
    */

    function circlePointDropCallBackEvent(pointPlacemark){
        var pointName = pointPlacemark.getName();

        if(pointName != '' ){         
            radius = getRadius();  
        }       
    }

    /*
        Creates the point on the google window
    */
    function drawPoint(pointPlacemark, latitude, longitude, pointName){     
        var pm = gex.dom.addPointPlacemark([latitude, longitude], {
			name: pointName,        
			icon: circleCenterDragPlacemarkUrl          
        });
        if(pointName == ''){
			center = pm;
            google.earth.addEventListener(pm, 'mouseup', geMouseClickHandler);
            google.earth.addEventListener(pm, 'mouseup', circleClickHandler);
        }else{
            circumference = pm;
            google.earth.addEventListener(circumference, 'mouseup', circumferenceMouseUpHandler);
            google.earth.addEventListener(circumference, 'mousedown', circumferenceMouseDownHandler);
		}
        registerDraggableObject(pm);
    }

    function circumferenceMouseDownHandler(kmlEvent){
        google.earth.addEventListener(circumference, 'mousemove', circumferenceMoveHandler);
    }
    
    function circumferenceMouseUpHandler(kmlEvent){
        google.earth.removeEventListener(circumference, 'mousemove', circumferenceMoveHandler);
    }
    
    /*
        calculates the radius
    */  
    function getRadius() {      
        return getRadiusParameterised(center.getGeometry().getLatitude(),circumference.getGeometry().getLatitude(),center.getGeometry().getLongitude(),circumference.getGeometry().getLongitude());
    }

	/*
        This function is used to show the mouseover radius icon on RBCircle
    */
    function circleRegionMouseOverHandler(kmlEvent) {              
		if(!createNewCircleFlag && radiusValuePlacemark==null){              
			var pCircleCoordinates = getPolygonCoordinates(kmlEvent.getTarget());
            var coordinateCircle = pCircleCoordinates.split(" ");
            var count = coordinateCircle.length;
            var pCenterCoordinates = getCircleCenterCoordinates(coordinateCircle[0],coordinateCircle[(count/2)]);
            var pCircumCoordinates = getCircleCircumCoordinates(coordinateCircle[count/4]);
                
            var centerCoordinates = pCenterCoordinates.split(",");
            var circumCoordinates = pCircumCoordinates.split(",");
            var radius = getRadiusParameterised(parseFloat(centerCoordinates[0]),parseFloat(circumCoordinates[0]),parseFloat(centerCoordinates[1]),parseFloat(circumCoordinates[1]));     

            var roundedRadius = Math.round((radius*(milesMultipleConst))*Math.pow(10,2))/Math.pow(10,2);
            if(radius*(milesMultipleConst) < 0.10)
            {
	           roundedRadiusFeet = Math.round((radius*(milesMultipleConst)*(feetMultipleConst))*Math.pow(10,2))/Math.pow(10,2);
               radiusValuePlacemark = gex.dom.addPointPlacemark([parseFloat(circumCoordinates[0]),parseFloat(circumCoordinates[1])], {
               icon: circleCircumDragPlacemarkUrl,
               name: (roundedRadiusFeet + " feet")
		       });
            }
            else{
			  radiusValuePlacemark = gex.dom.addPointPlacemark([parseFloat(circumCoordinates[0]),parseFloat(circumCoordinates[1])], {
              icon: circleCircumDragPlacemarkUrl,
              name: (roundedRadius + " miles")
              });
            }
        }
    }

	/*
        This function is called when the user mouseout the RBCircle Region, it will make the radius palcemark as null
    */
	function circleRegionMouseOutHandler(kmlEvent) {                
		ge.getFeatures().removeChild(radiusValuePlacemark);
        radiusValuePlacemark=null;
    }
    
    function drawRBCircleFinished() {
		google.earth.removeEventListener(ge.getGlobe(), 'click', geMouseClickHandler);
		google.earth.removeEventListener(ge.getWindow(), 'click', radiusChangeHandler);
		radius = radiusConst; 
		try{
			if(currentRBPolyPlacemark) {
				var count=currentRBPolyPlacemark.getGeometry().getOuterBoundary().getCoordinates().getLength();
                if(currentRBPolyPlacemark!=null) {
					if(center){
						ge.getFeatures().removeChild(center);
                    }
                    if(circumference){
						ge.getFeatures().removeChild(circumference);
                    }
                    parent.addFeature(currentRBPolyPlacemark.getId(), getPolygonCoordinates(currentRBPolyPlacemark), "rbcircle",count+"");
                }
                parent.fireGEEvent('RB_CIRCLE', 'click', 'deactivate');
                //add the placemark to the array
                rbFeaturesArray[currentRBPolyPlacemark.getId()] = currentRBPolyPlacemark;
                viewPortArray[currentRBPolyPlacemark.getId()]=getSerializedViewPort();    
                
                createNewCircleFlag=false;          
                google.earth.addEventListener(rbFeaturesArray[currentRBPolyPlacemark.getId()], 'mouseover', circleRegionMouseOverHandler);
                google.earth.addEventListener(rbFeaturesArray[currentRBPolyPlacemark.getId()], 'mouseout', circleRegionMouseOutHandler);      
            }
            else{
				currentRBPolyPlacemark = null;
                parent.addFeature("", "", "",count+"");
                return;
            }
        }
        catch (e) {
            alert('Error in drawing the polygon '+e.message);
        }      
        currentRBPolyPlacemark = null;
    }

/* Rubberbanding Circle code - end */

/* Rubberbanding draw code - end */

/* Rubberbanding edit code - start */

var RUBBERBAND_MODE_EDIT = 'edit'
var RUBBERBAND_MODE_READONLY = 'readonly';
var rubberbandMode = RUBBERBAND_MODE_EDIT;
var selectedRubberBandPlacemark;
var currentRubberBandPolyPlacemark;
var isCompletedListener = false;
var beforeEditRubberbandRegionsArray = new Array();
var afterEditRubberbandRegionsArray = new Array();
var viewPortArray=new Array();
var centreDraggedFlag =false;
var circumferenceDraggedFlag=false;
var radius=.0805;
var radiusConst = .0805;
var globalTargetID ='1';
var milesMultipleConst=62.1371192;
var feetMultipleConst=5280;

   function RubberBand(featureId,coordinates,viewport) {
        this.tagName="rubberbandregion"
        this.coordinates=coordinates;
        this.featureId=featureId;
        this.viewPort=viewport;
        this.newId=-1;
        this.ecoexpmlIds="";
        this.refreshInterval="";
        this.print=function(){
            return "<"+this.tagName+" ecoexpmlIds='"+this.ecoexpmlIds+"' refreshInterval='"+this.refreshInterval+"' featureId='"+this.featureId+"' coordinates='"+this.coordinates+"' viewPort='"+this.viewPort+"' newId='"+this.newId+"'/>";
        }
    }

	/*
		Call this method to enable the Rubberbanding EDIT Mode
    */
    function setRBRegionsEditMode(editModeFlag){

        if(editModeFlag){
            setRubberbandEditMode(RUBBERBAND_MODE_EDIT);
        }else{
            setRubberbandEditMode(RUBBERBAND_MODE_READONLY);
        }
    }

    function setRubberbandEditMode(mode) {
        this.rubberbandMode = mode;
        if(isRubberBandReadOnlyMode()) {
            activateListener(LISTENER_TYPE_DEFAULT_CLICK, 'mouseup');
        } else {
            hideBalloon(null);
            deActivateListener(LISTENER_TYPE_DEFAULT_CLICK, 'mouseup');
        }
        initializeRubberBandMouseListeners(isRubberBandEditMode());
    }

    function saveRBRegionsEditMode(){
                var xmlData="<?xml version='1.0' encoding='UTF-8'?><rubberbandregions>";
                var closeRubberbands="</rubberbandregions>";
				var rubberBand;
						    
					var count=lastSelectedRegion.getGeometry().getOuterBoundary().getCoordinates().getLength();					
					if(count<=3) {
						cancelSaveRubberbanding();
						parent.updateFeatureAll("");
						return;
					}
					rubberBand=afterEditRubberbandRegionsArray[lastSelectedRegion.getId()];
					rubberBand.viewPort=getSerializedViewPort();	
					if(lastSelectedRegion.getId().search("rbcircle_") !=-1){
						// If the selected region is circle, then show the mouse over Icon after saving.
						google.earth.addEventListener(lastSelectedRegion, 'mouseover', circleRegionMouseOverHandler);
	                    google.earth.addEventListener(lastSelectedRegion, 'mouseout', circleRegionMouseOutHandler);      
					}

                if(rubberBand!=null && rubberBand!='undefined') {
                    xmlData=xmlData+rubberBand.print();
                }
                xmlData=xmlData+closeRubberbands;
                changeRubberBandModeEditMode(false);
                resetContentEditing();
                parent.updateFeatureAll(xmlData);
    }

    function getCurrentRubberBandEditingDetails() {
           changeRubberBandModeEditMode(false);
		   if(centerPm){
		   ge.getFeatures().removeChild(centerPm);
		   }
		   if(circumPm){
		   ge.getFeatures().removeChild(circumPm);
		   }
           if(lastSelectedRegion!=null) {
			   var rubberBand=afterEditRubberbandRegionsArray[lastSelectedRegion.getId()];				 
              parent.showEditConfirmation(rubberBand.print());
           } else {
               parent.showEditConfirmation("");
           }
    }

    function resetContentEditing() {       
        lastSelectedRegion=null;
    }

    function cancelSaveRubberbanding() {	
        if(lastSelectedRegion) {			
            var xmlData="<?xml version='1.0' encoding='UTF-8'?><rubberbandregions>";
            var closeRubberbands="</rubberbandregions>";
            var rubberBand=beforeEditRubberbandRegionsArray[lastSelectedRegion.getId()];
			
             if(rubberBand!=null && rubberBand!='undefined') {
                var featureId=rubberBand.featureId;
                var newId=-1;
				var tempId = lastSelectedRegion.getId();
                if(rbFeaturesArray[featureId]) {	
                    newId=resetDeselectedRubberBandFeatures(featureId, getRegionTypeId(tempId));
                }
				if(tempId.search("rbcircle_") !=-1){					
					rubberBand.newId="rbcircle_"+newId;	
				}else{
					rubberBand.newId="rb_"+newId;	
				}
					xmlData=xmlData+rubberBand.print();				

           }
           xmlData=xmlData+closeRubberbands;
           resetContentEditing();
           parent.renamePolygonId(xmlData);
       }
    }
	var regionTypeId;
	function getRegionTypeId(regionId){
			regionTypeId = 1 //POLYGON
		if(regionId.search("circle") >-1){
			regionTypeId = 2; // CIRCLE					
		}

		return regionTypeId; 
	}

    function isRubberBandEditMode() {
        return rubberbandMode == RUBBERBAND_MODE_EDIT;
    }

    function isRubberBandReadOnlyMode() {
        return rubberbandMode == RUBBERBAND_MODE_READONLY;
    }


    function initializeRubberBandMouseListeners(flag) {
        if(flag) {
            google.earth.addEventListener(ge.getWindow(), 'mouseup', rubberbandMouseUpListener);
        } else {
            google.earth.removeEventListener(ge.getWindow(), 'mouseup', rubberbandMouseUpListener);
        }
    }


    /*  @Method Triggered upon choosing the Placemark.
        Identify the target as KmlPolygon, place the kmlPolygon in @paramArray: editRubberbandRegionsArray[].
        Role of @paramArray: editRubberbandRegionsArray[]
               - It binds the coordinates of the original region, so that when the user deselects the region, it gets the original
               coordinates from the array & create the regions once again after removed / released from the google earth.
    */

    var lastSelectedRegion=null;
	//for circle coords
	var centerPm = null;
	var circumPm = null;
    function rubberbandMouseUpListener(kmlEvent) {		
        if(!isRubberBandEditMode()) { //|| isEditable
            return;
        }
		
		var target = kmlEvent.getTarget();
        if(rbFeaturesArray[target.getId()]==null) {
        	kmlEvent.preventDefault();
			return;
        }
		
       // If The circle is getting dragged in edit mode and yet nt saved or cancelled then do nothing and return back
		if(centreDraggedFlag || circumferenceDraggedFlag){
			return;
		}

        var targetType = kmlEvent.getTarget().getGeometry().getType();
        if(targetType == 'KmlPolygon') {

            selectedRubberBandPlacemark = kmlEvent.getTarget();

            if(lastSelectedRegion!=null) {
	               if(lastSelectedRegion.getId()!=selectedRubberBandPlacemark.getId()) {

	                   // time to show confirmation box
	                   var rubberBand=afterEditRubberbandRegionsArray[lastSelectedRegion.getId()];
	                   changeRubberBandModeEditMode(false);
					   if(centerPm!=null)
							ge.getFeatures().removeChild(centerPm);
					   if(circumPm!=null)
						ge.getFeatures().removeChild(circumPm);
	                   parent.showEditConfirmation(rubberBand.print());
	                   return;
	               }
	            }
			}
            if(lastSelectedRegion==null) {

            lastSelectedRegion=selectedRubberBandPlacemark;

            var selectedId=selectedRubberBandPlacemark.getId();
            var backuprubberBand=new RubberBand(selectedId,getPolygonCoordinates(selectedRubberBandPlacemark),viewPortArray[selectedId]);
            var rubberBand=new RubberBand(selectedId,getPolygonCoordinates(selectedRubberBandPlacemark),viewPortArray[selectedId]);

            beforeEditRubberbandRegionsArray[selectedId] = backuprubberBand;
            afterEditRubberbandRegionsArray[selectedId] = rubberBand;
			currentRubberBandPolyPlacemark = selectedRubberBandPlacemark;
			
            resetRubberBandFeatures();

            currentRubberBandPolyPlacemark = kmlEvent.getTarget();
            var id = currentRubberBandPolyPlacemark.getId();
			
			var optionsObj = new Object();
            optionsObj.editCallback = rubberbandPolygonUpdated;
								   
			if(selectedRubberBandPlacemark.getId().search("rbcircle_") ==-1){
				gex.edit.editLineString(currentRubberBandPolyPlacemark.getGeometry().getOuterBoundary(),optionsObj);
			}else{// This else block will execute if the selected region is circle.
			
				// hide the mouseover Icon in edit mode
				if(radiusValuePlacemark){
					radiusValuePlacemark.setVisibility(false);
					google.earth.removeEventListener(kmlEvent.getTarget(), 'mouseover', circleRegionMouseOverHandler);
					google.earth.removeEventListener(kmlEvent.getTarget(), 'mouseout', circleRegionMouseOutHandler);
					radiusValuePlacemark=null;
				}			
				var pCircleCoordinates = getPolygonCoordinates(kmlEvent.getTarget());
				var allCoords = pCircleCoordinates.split(" ");
				var numCoords = allCoords.length;
				var centerCoordinates = getCircleCenterCoordinates(allCoords[0],allCoords[16]);
				var centerCords = centerCoordinates.split(",");
				var circumCoordinates = getCircleCircumCoordinates(allCoords[8]);
				var circumCords = circumCoordinates.split(",");	
				radius = getRadiusParameterised(parseFloat(centerCords[0]),parseFloat(circumCords[0]),parseFloat(centerCords[1]),parseFloat(circumCords[1]));
				centerPm = createPointPlacemark(parseFloat(centerCords[0]), parseFloat(centerCords[1]), '');
				circumPm = createPointPlacemark(parseFloat(circumCords[0]), parseFloat(circumCords[1]), '');
	
				// registers the event listeners for center and circumference
	            registerCenterEditDraggableObject(centerPm, circumPm);
				registerCircumEditDraggableObject(circumPm, centerPm);		
			}
		parent.setCurrentSelectedRegion(currentRubberBandPolyPlacemark.getId());
		}
    }
    /*
		function to calculate radius from center and circumference coordinates
	*/
	function getRadiusParameterised(x1,x2,y1,y2) {
        var latDifference= (x2-(x1));
        var longDifference= (y2-(y1));
        var term = (latDifference*latDifference)+(longDifference*longDifference);
        var radiusParameterised = Math.sqrt(term);
        
        return radiusParameterised;
    }
    /*
		function to create the point placemarks on the circle
    */
	function createPointPlacemark(latitude, longitude, pointName){
        
        var pm = gex.dom.addPointPlacemark([latitude, longitude], {
          id: pointName,        
          icon: circleCenterDragPlacemarkUrl          
        });
         pm.setVisibility(true);
        return pm;
    }

	
	/*
		function to place the center placemark , also add event listeners for drag and drop for the palcemark
	*/
	 function registerCenterEditDraggableObject(targetObj, referObj){	
        gex.edit.makeDraggable(targetObj, {
          dropCallback: function() {						  
				circleCenterPMDropCallBackEvent(targetObj, referObj);			
          },
          dragCallback: function() { 				  
            circleCenterPMDragCallBackEvent(targetObj);
          }       
        });		
    }
	
	/*
		function to place the circumference placemark by calculating radius and make it draggable. Also add event listeners for drag and drop for the palcemark
	*/
	function registerCircumEditDraggableObject(targetObj, referObj){
		gex.edit.makeDraggable(targetObj, {
			dropCallback: function() { 
				radius =  getRadiusParameterised(referObj.getGeometry().getLatitude(),targetObj.getGeometry().getLatitude(),referObj.getGeometry().getLongitude(),targetObj.getGeometry().getLongitude())
	            circleCircumPMDropCallBackEvent(referObj, radius);			
          },
			dragCallback: function() {   				  
				circleCircumPMDragCallBackEvent(targetObj);
          }      
        });
    }
	
	/*
		CallBack Method to be triggered when circumference Placemark dragging stops.
    */
	function circleCircumPMDropCallBackEvent(centerObj, radius){
		setPolygonPlacemarkCoords(centerObj.getGeometry().getLatitude(), centerObj.getGeometry().getLongitude(), radius);
		rubberbandPolygonUpdated();
		circumferenceDraggedFlag=false;
		google.earth.removeEventListener(circumPm, 'mousemove', circumPMMoveHandler);
	}
	
	/*
		CallBack Method to be triggered when circumference Placemark dragging starts.
    */
	function circleCircumPMDragCallBackEvent(targetObj){			
		circumferenceDraggedFlag=true;
		google.earth.addEventListener(circumPm, 'mousemove', circumPMMoveHandler);
			
	}
	
	/*
		Event Handler to update the circumference label while dragging.
    */
	function circumPMMoveHandler(kmlEvent){
                var radius =getRadiusParameterised(centerPm.getGeometry().getLatitude(),kmlEvent.getLatitude(),centerPm.getGeometry().getLongitude(),kmlEvent.getLongitude());                  

                var roundedRadius = Math.round((radius*(milesMultipleConst))*Math.pow(10,2))/Math.pow(10,2);	
				if(radius*(milesMultipleConst) < 0.10)
				{
					roundedRadiusFeet = Math.round((radius*(milesMultipleConst)*(feetMultipleConst))*Math.pow(10,2))/Math.pow(10,2);  
					kmlEvent.getTarget().setName(roundedRadiusFeet +" feet");
				}
				else{
					kmlEvent.getTarget().setName(roundedRadius + " miles");
				}            
	}
	/*
		CallBack Method to be triggered when circle center dragging stops.
    */
	function circleCenterPMDropCallBackEvent(targetObj, referObj){		
		setPolygonPlacemarkCoords(targetObj.getGeometry().getLatitude(), targetObj.getGeometry().getLongitude(), radius);
		var coords = lastSelectedRegion.getGeometry().getOuterBoundary().getCoordinates();
		
		referObj.getGeometry().setLatitude(coords.get(8).getLatitude());
		referObj.getGeometry().setLongitude(coords.get(8).getLongitude());
		rubberbandPolygonUpdated();
		centreDraggedFlag= false;
	}
	
	/*
		CallBack Method to be triggered when circle center dragging starts.
    */
	function circleCenterPMDragCallBackEvent(targetObj){	
		centreDraggedFlag= true;
	}
	
	
	/*
		function to repalce the Polygon placemarks coordinates when dragging..
    */	
	function setPolygonPlacemarkCoords(centerLat, centerLng, radius){		
		lastSelectedRegion.getGeometry().setOuterBoundary(makeCircle(radius, centerLat, centerLng) );		
		ge.getFeatures().appendChild(lastSelectedRegion);
	}
	
	function getCircleCircumCoordinates(coordinate){
		var coordinateArray = coordinate.split(",");
		return coordinateArray[1]+ "," + coordinateArray[0];
	}

	function getCircleCenterCoordinates(firstCoordinate,secondCoordinate){
				var firstCoordinateArray = firstCoordinate.split(",");
				var secondCoordinateArray  = secondCoordinate.split(",");
				var paramCenterLatitude = ((parseFloat(firstCoordinateArray[0]))/2+(parseFloat(secondCoordinateArray[0]))/2);
				var paramCenterLongitude = ((parseFloat(firstCoordinateArray[1]))/2+(parseFloat(secondCoordinateArray[1]))/2);
				return paramCenterLongitude+ "," + paramCenterLatitude;
	}
			

    /*
		CallBack Method to be triggered for saving the updated regions back to server.
    */
    function rubberbandPolygonUpdated() {
		
        if(currentRubberBandPolyPlacemark) {		
            var rubberBand=afterEditRubberbandRegionsArray[currentRubberBandPolyPlacemark.getId()];
            rubberBand.coordinates=getPolygonCoordinates(currentRubberBandPolyPlacemark);
            rubberBand.viewPort=getSerializedViewPort();
            afterEditRubberbandRegionsArray[currentRubberBandPolyPlacemark.getId()] = rubberBand;
        }
    }

    /*
        on double click of the regions, its ends from editing mode of that particular region.
    */
    function resetRubberBandFeatures() {
        if(currentRubberBandPolyPlacemark) {
            gex.edit.endEditLineString(currentRubberBandPolyPlacemark.getGeometry().getOuterBoundary());
        }
    }

    /*
        Called when a region is deselected.
    */
    function resetDeselectedRubberBandFeatures(deSelectedId, regionTypeId) {

        if(currentRubberBandPolyPlacemark) {
            gex.edit.endEditLineString(currentRubberBandPolyPlacemark.getGeometry().getOuterBoundary());
            currentRubberBandPolyPlacemark = null;
        }
        /*
            Check whether the region is deselected in editMode or not?
        */
            ge.getFeatures().removeChild(rbFeaturesArray[deSelectedId]);
            // @Releases the kmlObject from Google Earth. So that, the Id, can be reused
            rbFeaturesArray[deSelectedId].release();
            //rbFeaturesArray[deSelectedId]=null;
            var kmlId = ++idCount; //deSelectedId.substring(3) // we are appending 'rb_', so removing the 'rb_'
            regainRBPolygon(kmlId, beforeEditRubberbandRegionsArray[deSelectedId].coordinates, true,deSelectedId,regionTypeId);
			if(regionTypeId == 1){
				viewPortArray["rb_"+kmlId]=viewPortArray[deSelectedId];
			}else {
				viewPortArray["rbcircle_"+kmlId]=viewPortArray[deSelectedId];
			}
            return kmlId;
    }

    function regainRBPolygon(id, coordStr, flag,oldId,regionTypeId) {
		if(regionTypeId == 1){
		 var rbPlacemark = gex.dom.addPolygonPlacemark([], {
             id: 'rb_'+id,
             style: {
              poly: '8000ff00',
              line: { width: 3, color: '#0f0' }
            }
          });
			}else {
				var rbPlacemark = gex.dom.addPolygonPlacemark([], {
             id: 'rbcircle_'+id,
             style: {
              poly: '8000ff00',
              line: { width: 3, color: '#0f0' }
            }
          });
			}
       
        setPolygonCoordinates(rbPlacemark, coordStr);
        rbPlacemark.setVisibility(flag);
        rbFeaturesArray[rbPlacemark.getId()] = rbPlacemark;
		
		// show the mouse over Icon after saving	
		if(rbPlacemark.getId().search("rbcircle_") !=-1){			
			google.earth.addEventListener(rbFeaturesArray[rbPlacemark.getId()], 'mouseover', circleRegionMouseOverHandler);
			google.earth.addEventListener(rbFeaturesArray[rbPlacemark.getId()], 'mouseout', circleRegionMouseOutHandler);    
		}
					
        beforeEditRubberbandRegionsArray[oldId]= null;
        afterEditRubberbandRegionsArray[oldId]=null;
    }

    function rubberbandCompleteListener(event) {
        try {
            event.preventDefault();
            if(currentRubberBandPolyPlacemark) {
                gex.edit.endEditLineString(currentRubberBandPolyPlacemark.getGeometry().getOuterBoundary());
            }
        }
        catch (e) {
            //alert(e.message);
        }
        removeRubberBandCompleteListener();
    }

    function removeRubberBandCompleteListener() {
        if(isCompletedListener) {
            google.earth.removeEventListener(ge.getWindow(), 'dblclick', rubberbandCompleteListener);
            isCompletedListener = false;
        }
    }

    function resetEditModeRBFeature() {
        if(currentRubberBandPolyPlacemark) {
            ge.getFeatures().removeChild(currentRubberBandPolyPlacemark);
        }

        resetEditModeRBFeatures();
        parent.fireGEEvent('RB_POLYGON', 'click', 'deactivate');
    }

    function changeRubberBandModeEditMode(editModeFlag){
        setRBRegionsEditMode(editModeFlag);
        if(!editModeFlag){
             resetRubberBandFeatures();
             currentRubberBandPolyPlacemark = null;
            }
    }

/* Rubberbanding edit code - start */

/* Rubberbanding Code - End */