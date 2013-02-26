    var auxObjects = [];
    var sessionId;
    var currentNetworkLinkName = null;
    var map = null;
    var hasMovedToLocation = false;
    var layersArr = new Array();
    var screenOverlaysArr = new Array();
    var lastClickedLayer = null;
    var lastClickedBtn = null;
    
    var controlNames = new Array();
    var windowMinimizeStatus =false;
    
    function GetMap(){
        map = new VEMap('map3d');
        map.LoadMap(new VELatLong(40.3130432088809,-96.416015625), 3, 'a', false, VEMapMode.Mode3D, true);
        map.AttachEvent("onchangeview", viewChanged);
        //map.AttachEvent("onclick", shapeInfo);
        //map.AttachEvent("onmouseup", globeWindowEventListener);
        map.ClearInfoBoxStyles();
        addTileLayer();
        //sends the viewformat to the server
        setTimeout("viewChanged(null);",2000);
        
    }


    function getHighest(val1,val2,val3,val4) {
        var val5 = val1 > val2 ? val1 : val2;
        var val6 = val3 > val4 ? val3 : val4;
        return val5 >  val6 ? val5 : val6;
    }

    function getLowest(val1,val2,val3,val4) {
        var val5 = val1 < val2 ? val1 : val2;
        var val6 = val3 < val4 ? val3 : val4;
        return val5 <  val6 ? val5 : val6;
    }

    function viewChanged(mapEvent){
        view = map.GetMapView();
        topleft = view.TopLeftLatLong;
        bottomright = view.BottomRightLatLong;
        
        var mode = map.GetMapMode();
        if (mode == VEMapMode.Mode2D) {
            bboxWest = topleft.Longitude < bottomright.Longitude ? topleft.Longitude : bottomright.Longitude; 
            bboxNorth = topleft.Latitude > bottomright.Latitude ? topleft.Latitude : bottomright.Latitude; 
            bboxSouth = topleft.Latitude < bottomright.Latitude ? topleft.Latitude : bottomright.Latitude; 
            bboxEast = topleft.Longitude > bottomright.Longitude ? topleft.Longitude : bottomright.Longitude; 
        } else {        
            topright = view.TopRightLatLong;        
            bottomleft = view.BottomLeftLatLong;
            bboxWest = getLowest(bottomright.Longitude,bottomleft.Longitude,topleft.Longitude,topright.Longitude);
            bboxNorth = getHighest(bottomright.Latitude,bottomleft.Latitude,topleft.Latitude,topright.Latitude);
            bboxSouth = getLowest(bottomright.Latitude,bottomleft.Latitude,topleft.Latitude,topright.Latitude);
            bboxEast = getHighest(bottomright.Longitude,bottomleft.Longitude,topleft.Longitude,topright.Longitude);
        }
        altitude = map.GetAltitude();
        tilt = 90 + map.GetPitch();
        tiltRad = tilt*Math.PI/180;
        lookatRange = altitude/Math.cos(tiltRad);
        heading = map.GetHeading();
        queryString = "bboxWest="+bboxWest+"&bboxSouth="+bboxSouth+"&bboxEast="+bboxEast+"&bboxNorth="+bboxNorth+
                      "&lookatRange="+lookatRange+"&lookatTilt="+tilt+"&lookatHeading="+heading;
        sendToFlash("viewFormat",queryString);
    }

    function refreshAuxWindows() {
        for(t=0; t<auxObjects.length; t++){
            try{
                refreshAuxWindow(auxObjects[t].name);
            }catch(err1){
                alert(err1);
            }
        }
    }

    function kmlLoadingComplete(name){
        //refresh the auxilary windows after the kml loading is complete
        if(name == lastClickedLayer) {
            refreshAuxWindows();
        }
    }
    function addLayer(name,description){
        if(layersArr[name]!=null){
            removeLayer(name);
        }
        hasMovedToLocation = true;
        layer1 = new VEShapeLayer();
        layer1.SetTitle(description);
        layersArr[name] = layer1;
        map.AddShapeLayer(layer1);
    
        /*
        //Works only in 2D...
        var clusterOptions = new VEClusteringOptions();
        clusterOptions.Icon = new VECustomIconSpecification();
        clusterOptions.Icon.Image = "http1/31/2009//localhost:8090"+<%=ServerUtils.getContextName(request)%>+"/images/placemark_circle_highlight.png";
        layer1.SetClusteringConfiguration(VEClusteringType.Grid,clusterOptions);*/
    }

    function removeLayer(layerName){
        var layer = layersArr[layerName];
        map.DeleteShapeLayer(layer);
        deleteControlsinLayer(layerName);
        layersArr[layerName]=null;
    }
    
    function gotoLookat(latitude,longitude,range,tilt,heading){
        setTimeout("gotoLookat2('"+latitude+"','"+longitude+"','"+range+"','"+tilt+"','"+heading+"')",2000);
        //setTimeout("addControl2('"+overLays[j].name+"','"+layerNamesArr[i]+"')", 2000);
    }
    function gotoLookat2(latitude,longitude,range,tilt,heading){
         map.SetCenter(new VELatLong(latitude, longitude));
         map.SetHeading(heading);
         pitch = tilt - 90;
         tiltRad = tilt*Math.PI/180;
         altitude = range*Math.cos(tiltRad);
         map.SetAltitude(altitude);
         map.SetPitch(pitch);
    }
    
    function zoomToView(topLeftLat,topLeftLng,bottomRightLat,bottomRightLng){
        viewRect = new VELatLongRectangle(new VELatLong(topLeftLat,topLeftLng),new VELatLong(bottomRightLat,bottomRightLng),
                                          new VELatLong(topLeftLat,bottomRightLng),new VELatLong(bottomRightLat,topLeftLng));
        map.SetMapView(viewRect);
    }
    
    var shapesArr = new Array();
    //Adds placemarks to an array instead of layer in order to support Buk Add
    function addPlacemarkItem(name,description,coordinatesString,style,layerName){
        //get the shapes array
        var shapes = shapesArr[layerName];
        if(shapes==null) shapes = new Array();
        shapesArr[layerName] = shapes;
    
        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Pushpin, pointsArr[0]);
        shape.SetTitle(name);
        shape.SetDescription(description);

        if(!iconStyleArr[style]){
            style = style+"_normal";
        }
        if(style!=null && iconStyleArr[style]!=null){
            custIcon = new VECustomIconSpecification();
            if(iconStyleArr[style].color!=null && iconStyleArr[style].color!="")
                custIcon.BackColor = getVEColor(iconStyleArr[style].color);
            custIcon.Image = getFormattedIconUrl(iconStyleArr[style]);
            custIcon.TextContent = name;
            shape.SetCustomIcon(custIcon);
        }
        shapes.push(shape);
    }
    
    //Adds the placemarks array to the layer
    function loadShapesArray(layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;
        var shapes = shapesArr[layerName];
        if(shapes==null) return;
        layer.AddShape(shapes);
    }
    
    function addPlacemark(name,description,coordinatesString,style,layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;

        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Pushpin, pointsArr[0]);
        shape.SetTitle(name);
        shape.SetDescription(description);

        if(!iconStyleArr[style]){
            style = style+"_normal";
        }
        if(style!=null && iconStyleArr[style]!=null){
            //works only for 3d, change when adding code for 3d
            custIcon = new VECustomIconSpecification();
            if(iconStyleArr[style].color!=null && iconStyleArr[style].color!="")
                custIcon.BackColor = getVEColor(iconStyleArr[style].color);
            custIcon.Image = getFormattedIconUrl(iconStyleArr[style]);
            shape.SetCustomIcon(custIcon);
        }
        layer.AddShape(shape);

        if(!hasMovedToLocation){
            map.SetCenter(new VELatLong(latitude, longitude));
            hasMovedToLocation = true;
        }
    }
      
    function addStyledPlacemark(name,description,coordinatesString,icon,scale,color,layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;

        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Pushpin, pointsArr[0]);
        shape.SetTitle(name);
        shape.SetDescription(description);

        custIcon = new VECustomIconSpecification();
        if(color!=null && color!="")
            custIcon.BackColor = getVEColor(color);
        custIcon.Image = getFormattedIconUrl2(icon,scale,color);
        shape.SetCustomIcon(custIcon);

        layer.AddShape(shape);
        if(!hasMovedToLocation){
            map.SetCenter(new VELatLong(latitude, longitude));
            hasMovedToLocation = true;
        }
    }

    function addPolygon(name,description,coordinatesString,style,layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;
        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Polygon, pointsArr);
        shape.SetTitle(name);
        shape.SetDescription(description);
        shape.SetLineToGround(true);
        shape.HideIcon();
         
        if(style!=null){
            if(lineStyleArr[style]!=null){
                shape.SetLineWidth(lineStyleArr[style].width < 2 ? 2 : lineStyleArr[style].width);
                shape.SetLineColor(getVEColor(lineStyleArr[style].color));
            }
            if(polyStyleArr[style]!=null){
                shape.SetFillColor(getVEColor(polyStyleArr[style].color));
            }
        }
        layer.AddShape(shape);
        if(!hasMovedToLocation){
            map.SetCenter(pointsArr[0]);
            hasMovedToLocation = true;
        }
    }

    function addStyledPolygon(name,description,coordinatesString,lineColor,fillColor,layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;
        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Polygon, pointsArr);
        shape.SetTitle(name);
        shape.SetDescription(description);
        shape.SetLineToGround(true);
        shape.HideIcon();
         
        if(lineColor!=null){
            shape.SetLineColor(getVEColor(lineColor));
        }
        if(fillColor!=null){
            shape.SetFillColor(getVEColor(lineColor));
        }
        layer.AddShape(shape);
        if(!hasMovedToLocation){
            map.SetCenter(pointsArr[0]);
            hasMovedToLocation = true;
        }
    }

    function addPolyline(name,description,coordinatesString,style,layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;
        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Polyline, pointsArr);
        shape.SetTitle(name);
        shape.SetDescription(description);
        shape.HideIcon();
        if(style!=null){
            if(lineStyleArr[style]!=null){
                shape.SetLineColor(getVEColor(lineStyleArr[style].color));
            }
        }
        layer.AddShape(shape);
        if(!hasMovedToLocation){
            map.SetCenter(pointsArr[0]);
            hasMovedToLocation = true;
        }
    }
      
    function addStyledPolyline(name,description,coordinatesString,lineColor,layerName){
        var layer = layersArr[layerName];
        if(layer==null) return;
        var pointsArr = getCoordinatesArray(coordinatesString);
        var shape = new VEShape(VEShapeType.Polyline, pointsArr);
        shape.SetTitle(name);
        shape.SetDescription(description);
        shape.HideIcon();
        if(lineColor!=null){
            shape.SetLineColor(getVEColor(lineColor));
        }

        layer.AddShape(shape);
        if(!hasMovedToLocation){
            map.SetCenter(pointsArr[0]);
            hasMovedToLocation = true;
        }
    }
      
    function addModel(coordinatesString,layerName,modelUrl,heading,tilt,roll,scaleX,scaleY,scaleZ){
        var layer = layersArr[layerName];
        if(layer==null) return;
        var pointsArr = getCoordinatesArray(coordinatesString);
        if(modelUrl.indexOf("http:")==-1){
            modelUrl = coreServerUrl+modelUrl;
        }
        var modelOrientation = null;
        var modelScale = null;           
        var modelSpec = new VEModelSourceSpecification(VEModelFormat.OBJ, modelUrl, layer);
        
        if(heading != null || tilt != null || roll != null){
            if(heading == null) {
                heading = 0;
            }
            if(tilt == null) {
                tilt = 0;
            }
            if(roll == null) {
                roll = 0;
            }
            modelOrientation = new VEModelOrientation(heading, tilt, roll);
        }
    
        if(scaleX!=null || scaleY!=null || scaleZ!=null){
            if(scaleX < 1 || scaleX == null) {
                scaleX = 1;
            }
            if(scaleY < 1 || scaleY == null) {
                scaleY = 1;
            }
            if(scaleZ < 1 || scaleZ == null) {
                scaleZ = 1;
            }
            modelScale = new VEModelScale(scaleX,scaleY,scaleZ);
        }
        map.Import3DModel(modelSpec, null, pointsArr[0], modelOrientation, modelScale);
    }
         
    function addTileLayer(){
        var tileSourceSpec = new VETileSourceSpecification("imageOverlay", tileServerUrl);
        tileSourceSpec.NumServers = 1;
        tileSourceSpec.MinZoomLevel = 1;
        tileSourceSpec.MaxZoomLevel = 19;
        tileSourceSpec.Opacity = 1;
        tileSourceSpec.ZIndex = 100;
        map.AddTileLayer(tileSourceSpec, true);
    }
      
    function refreshTileLayer(){
        map.DeleteTileLayer("imageOverlay");
        addTileLayer();
    }

    function getCoordinatesArray(coordinatesString){
        var coordinatesArr = coordinatesString.split(" ");
        var pointsArr = new Array();
        for(i=0;i<coordinatesArr.length;i++){
            coord = coordinatesArr[i].split(",");
            pointsArr.push(new VELatLong(coord[1]*1,coord[0]*1,coord[2]*1));
        }
        return pointsArr;
    }

    function showNetworkLinkDialog(){
        popupWindow("networkLinkWindow.html","NetworkLink",300,200,"no",100,100);
    }

    function networkLinkCallback(nwLinkUrl){
        sendToFlash("NetworkLink",nwLinkUrl);
    }

    function getFlashMovieObject(movieName){        
        if (window.document[movieName]){
            return window.document[movieName];
        }
        if (navigator.appName.indexOf("Microsoft Internet")==-1){
          if (document.embeds && document.embeds[movieName])
            return document.embeds[movieName];
        }else{
            return document.getElementById(movieName);
        }
    }

    function sendToFlash(method,data) {
        var flashMovie=getFlashMovieObject("veTree");
        flashMovie.sendTextToFlash(method,data);
    }

    function sendDescription(desc){
        alert(desc);
    }

    var lineStyleArr    = new Array();
    var iconStyleArr    = new Array();
    var listStyleArr    = new Array();
    var labelStyleArr   = new Array();
    var polyStyleArr    = new Array();
    var balloonStyleArr = new Array();

    function addLineStyle(id,color,width){
        lineStyleArr[id] = new LineStyle(color,width);
    }

    function addIconStyle(id,color,scale,heading,icon){
        iconStyleArr[id] = new IconStyle(color,scale,heading,icon);
    }

    function addListStyle(id,listItemType,bgColor,ItemIcon){
        listStyleArr[id] = new ListStyle(listItemType,bgColor,ItemIcon);
    }

    function addLabelStyle(id,color,scale){
        labelStyleArr[id] = new LabelStyle(color,scale);
    }

    function addPolyStyle(id,color){
        polyStyleArr[id] = new PolyStyle(color);
    }

    function addBalloonStyle(id,bgColor,text){
        balloonStyleArr[id] = new BalloonStyle(bgColor,text);
    }

    function getVEColor(htmlColor){
        //KML color is of the form aabbggrr
        Input = htmlColor.toUpperCase();
        var a = parseInt(Input.substring(0, 2),16);
        var b = parseInt(Input.substring(2, 4),16);
        var g = parseInt(Input.substring(4, 6),16);
        var r = parseInt(Input.substring(6, 8),16);
        var alpha = Math.round(a/256*10)/10;
        return new VEColor(r, g, b, alpha);
    }

    function showOverlayInfo(overlayName,description){
        hRefLink = "infoWindow.html?infoHtml="+escape(description);
        popupWindow(hRefLink,"infoWindow",300,200,"no",100,100);
    }

    function addOverlay(overlayName,top,left,topUnit,leftUnit,iconPath,description,layerName){
        var imgObj = document.createElement("img");
        imgObj.src = iconPath;
        
        var mapDiv = document.getElementById("map3d");
        if(topUnit=="fraction"){
            top = mapDiv.offsetHeight*(1-top);
        }   
        if(leftUnit=="fraction"){
            left = mapDiv.offsetWidth*left;
        }   
       
        var overLays = screenOverlaysArr[layerName];
        if(overLays==null){
            screenOverlaysArr[layerName] = [{name:overlayName,top:top,left:left,img:imgObj}];
        }else{
            overLays.push({name:overlayName,top:top,left:left,img:imgObj});
            screenOverlaysArr[layerName] = overLays;
        }
        controlNames.push(overlayName);
         addControl(overlayName,layerName);
    }
      
    function deleteControlsinLayer(layerName){
        var overLays = screenOverlaysArr[layerName];
        if(overLays!=null){
            for(i=0;i<overLays.length;i++){
                deleteControl(overLays[i].name);
            }
        }
    }
      
    function getOverlay(overlayName,layerName){
        var overLays = screenOverlaysArr[layerName];
        if(overLays!=null){
            for(i=0;i<overLays.length;i++){
                if(overLays[i].name==overlayName)
                    return overLays[i];
            }
        }
        return null;
    }
      
    function getFormattedIconUrl(iconStyle){
        var query = "?";
        query = query + "file="+iconStyle.icon;
        if(iconStyle.scale !=null){
            query = query + "&scale="+iconStyle.scale;
        }
        if(iconStyle.color !=null){
            query = query + "&color="+iconStyle.color;
        }
        return formatterUrl + query;
    }

    function getFormattedIconUrl2(icon,scale,color){
        var query = "?";
        query = query + "file="+icon;
        if(scale !=null){
            query = query + "&scale="+scale;
        }
        if(color !=null){
            query = query + "&color="+color;
        }
        return formatterUrl + query;
    }

    function addControl(controlName,layerName){
        setTimeout("addControl2('"+controlName+"','"+layerName+"')", 1000);
    }
         
    function addControl2(controlName,layerName){
        var overlay = getOverlay(controlName,layerName);
        if(overlay==null)alert(controlName+" overlay is not loaded");
        var el = overlay.img;
        w = el.width;
        h = el.height;

        el.id = controlName;
        el.style.top = overlay.top-(h/2);
        el.style.left = overlay.left-(w/2);

        if(layersArr[layerName]==null) return;

        map.AddControl(el,100);
        addShim(el);
    }

    function addShim(el){
        var shim = document.createElement("iframe");
        shim.id = el.id+"shim";
        shim.frameBorder = "0";
        shim.scrolling="no";
        shim.style.position = "absolute";
        shim.style.zIndex = "1";
        shim.style.top  = el.offsetTop;
        shim.style.left = el.offsetLeft;
        shim.width  = el.offsetWidth;
        shim.height = el.offsetHeight;
        shim.allowtransparency = "true";
        shim.style.filter= "chroma(color='#FFFFFF')";
        el.shimElement = shim;
        el.parentNode.insertBefore(shim, el);
    }
         
    function deleteControl(controlName){
        var myControl = document.getElementById(controlName);
        if (myControl!=null){
            map.DeleteControl(myControl);
        }else{
            return;
        }
        var myShim = document.getElementById(controlName+"shim");
        if (myShim!=null){
            myShim.parentNode.removeChild(myShim);
        }
        myShim = null;
    }
         
    function submitLocation(address){
        map.Find(null,address);
    }
      
    function createVETreeObject(){
        var flashvars = {};
        var params = {serverUrl: serverUrl,
                      coreServerUrl:coreServerUrl,
                      adminServerUrl: adminServerUrl,
                      groupId: groupId,
                      domainId: domainId,
                      moduleId: moduleId};
                      
        var attributes = {
            id: "searchWin",
            name: "search",
            style: "border: 1px solid silver; height: 499px; width: 300px; position:absolute; left:10px;top:215px;"
        };
    
        var veTreeUrl = "VETree2.swf";
        swfobject.embedSWF(veTreeUrl, "veTree", "300", "500", "9.0.0",flashvars, params, attributes);
    }
    

    function addAuxObject(kmlName,name,url){
        for(i=0;i<auxObjects.length;i++){
            if(auxObjects[i].kmlName!=kmlName){
                auxObjects.pop();
            }else if((auxObjects[i].kmlName==kmlName) && (auxObjects[i].name==name)){
                return;             
            }
        }
        auxObjects.push({kmlName:kmlName,name:name,url:url});
        lastClickedLayer = kmlName;
        refreshButtons();
    }

    function refreshButtons(){
        windowMinimizeStatus = false;
        buttonDiv = document.getElementById('buttonWin');
        innerHtmlCode = "<table width='100%'>";
        for(i=0;i<auxObjects.length;i++){
            var convertUrl = auxObjects[i].url.replace(/"/g,"");
            convertUrl = convertUrl.replace(/'/g,'');
            innerHtmlCode+= "<td align='left'><input type='button' class='btnGray' onmouseover=\"hov(this,'btnGray btnhov')\" onmouseout=\"hov(this,'btnGray')\" onclick=\"outsideWindow( this, '" + auxObjects[i].name + "','"+auxObjects[i].name +"','"+convertUrl+"' );\"  name='button"+auxObjects[i].name+"' id='button"+auxObjects[i].name+"'  value='"+auxObjects[i].name+"'/></td>";
            if((i+1) % 1 == 0){
                innerHtmlCode +="</tr><tr>"; 
            }

        }
        innerHtmlCode+="</tr></table>"
        buttonDiv.innerHTML = innerHtmlCode;
    }
    
    function clearButtons(){
        try{
            for(h=0;h<auxObjects.length;h++){               
                try{                        
                    swfobject.removeSWF(auxObjects[h].name+'AuxWin');
                }catch(er){}
        } 
        auxObjects = new Array();
        var buttonDiv = document.getElementById('buttonWin');
        buttonDiv.innerHTML = "";
        refreshButtons();
        try{
            for(l=0;l< windowObjects.length;l++){                   
                try{
                    windowObjects[l].destroy();
                }catch(er){
                }
            }
        }catch(err1){
        }
        
        try{
                infoFrameWin.destroy();
        }catch(e1r1){
        }
    
        try{
                document.getElementById('dock').innerHTML='';
            }catch(err1){
            }
        } catch(e) {
            alert("[clearButtons], ["+e.name+"]:["+e.message+"]");
        }
    }
    
    function refreshAuxWindow(winName) {
        try {
            setProperSessionId();
            for(i=0;i<auxObjects.length;i++){
                if(auxObjects[i].name==winName){
                    var flashMovie=getFlashMovieObject(winName+'auxWindow1');
                    if(flashMovie == null) {
                        currentNetworkLinkName = auxObjects[i].kmlName;
                        createAuxObject(winName+'auxWindow1',winName,auxObjects[i].url);
                    } else {
                        try {
                            flashMovie.callFlashFromJS('_refresh','');
                        } catch(e) {
                            //ignore if the window does not support this property.
                        }

                    }
                }
            }
        } catch(e) {
            alert(e.message);
        }
    }
    
    function setSessionId(sessId){
        sessionId = sessId;
    }

    function toggleLayer(layer,switchOn){
        if(layer=="Traffic") {
            if(switchOn){
                showTraffic();
            } else{
                clearTraffic();
            }
        }
    }

    function showTraffic(){
        map.LoadTraffic(true);
        map.ShowTrafficLegend(50,50);
        map.SetTrafficLegendText("The traffic legend");
    }

    function clearTraffic(){
        map.ClearTraffic();
    }

    
    function setProperSessionId(){
        try{
            for(i=0;i<auxObjects.length;i++){
                tmpUrl = auxObjects[i].url;
                if(tmpUrl.indexOf(sessionId)!= -1)continue;
                sessionidPos = tmpUrl.indexOf("ecosid=");
                if(sessionidPos!=-1){
                    firstPart = tmpUrl.substring(0,sessionidPos+7);
                    secondPart = tmpUrl.substring(tmpUrl.indexOf("&",sessionidPos));
                    auxObjects[i].url = firstPart+sessionId+secondPart
                }
            }
        }catch(er1){}
    }   
    

    function createAuxObject(windowId,name,url){
        var flashvars = {};
        var params = {};
        var attributes = {
            id: name+"AuxWin",
            name: name+"AuxWin",
            quality: "high",
            bgcolor: "#FFFFFFFF"
        };
        swfobject.embedSWF(url, windowId, "100%", "500", "9.0.0",flashvars, params, attributes);
    }
   
//***********Call Back Functions
    function _lookat(coordStr) {
        if(coordStr != null) {
            var coords = coordStr.split(",");
            var lat =  coords[1];
            var lon =  coords[0];
            var altitude = coords[3];
            var zoom = map.GetZoomLevel();
            if(zoom < 14) zoom = 14;
                
            map.SetCenterAndZoom(new VELatLong(lat, lon), zoom);
            map.SetAltitude(altitude);
        }
    }
    
    function _refresh() {
        if(currentNetworkLinkName) {
            sendToFlash("refreshLink",currentNetworkLinkName);
        }
    }

    function changeButtonStyle(loc,cls){
        if(loc.className) {
              loc.className=cls;
        }
    }

    function hov(loc,cls){
        if(lastClickedBtn != null && lastClickedBtn == loc) {
            return;
        }
        changeButtonStyle(loc,cls);
    }

    var windowCount=0;
    var windowRow=new Array();
    var windowNames = new Array();
    var windowObjects = new Array();
    var objCount = 0;
    
    function outsideWindow(obj, windowName, windowTitle, urlToVisit) {
        try{
            var windowLeft=0;
            var windowTop=0;
            if(windowNames.length == 0){
                windowNames[windowCount] = windowName;
                windowRow[windowCount] = 0;
                windowLeft = 0;
                windowCount++;
            }

            for(i=0; i< windowNames.length; i++){
                if(windowNames[i] == windowName){
                    windowLeft = (i % 2 )* 550; 
                    windowTop =windowRow[i]
                }
            }

            var windowPresentStatus =false;
            for(i=0; i< windowNames.length; i++){
                if(windowNames[i] == windowName){
                    windowPresentStatus = true;
                    break;
                }
            }

            if(!windowPresentStatus){
                windowNames[windowCount] = windowName;
                if((windowCount-1) % 2 == 1){
                    windowRow[windowCount] =  (windowCount/2) * 550;
                    windowTop = windowRow[windowCount];
                }else{
                        windowRow[windowCount]=windowRow[windowCount-1];
                        windowTop = windowRow[windowCount];
                }

                windowLeft = (windowCount % 2)*550;
                windowCount++;
            }


            if(urlToVisit.indexOf(".swf") == -1 ){

                    win = new Window({id: windowName, className: "alphacube",  title:windowTitle,  width:500, height:500, top:windowTop, left: windowLeft,url:urlToVisit , parent:$('container')});             
             }else{
                 win = new Window({id: windowName, className: "alphacube",  title:windowTitle,  width:500, height:500, top:windowTop, left: windowLeft, parent:$('container')});
                win.getContent().innerHTML = '<div id="'+windowName+'" >    <div id="'+windowName+'auxWindow1" style="display:none; overflow:hidden;"></div></div>';    
             } 
            win.setDestroyOnClose();
            win.show();
            win.setConstraint(true, {})
            win.toFront();
            windowObjects[objCount++] = win;

            createAuxObject(windowName+'auxWindow1',windowName,urlToVisit);
        }catch(Er){
        }
        
    }
    function veTreeWin(){
        var veTreeWin = new Window({id: "veTreeWin", className: "alphacube", title: "Layers", width:300, height:700,top:30,left:0,closable:false, wiredDrag: true}); 
        veTreeWin.getContent().innerHTML = "<div id='veTree'></div><div id='buttonWin' style='border : solid 2px #ffffff; background : #ffffff; color : #ffffff; padding : 4px; width : 300px; height : 200px; overflow : auto; '></div>"; 
        veTreeWin.setDestroyOnClose(); 
        veTreeWin.show();
        veTreeWin.toFront();
        createVETreeObject();
    }

    var map3dWin;
    function map3dWin(){
        map3dWin = new Window({id: "map3dWin", className: "alphacube", title: "Virtual Earth", width:907, height:700,top:30,left:320,closable:false, wiredDrag: true, zIndex:0}); 
        map3dWin.getContent().innerHTML = "<div id='map3d' style='height: 100%;width: 100%'></div>"; 
        map3dWin.setZIndex(1);
        map3dWin.setOpacity(1.0);
        map3dWin.setDestroyOnClose(); 
        map3dWin.show();  
        setTimeout("GetMap()",2000);
    }

    var infoFrameWin;
    function openInfoframe(urlToOpen){
        try{
            infoFrameWin.destroy();
        }catch(er){}
        try{
            infoFrameWin = new Window({id: "infoFrameWin", className: "alphacube", title: "Info Frame Window", width:300, height:200,top:0,right:0, url: urlToOpen, parent:$('container')}); 
            infoFrameWin.setDestroyOnClose(); 
            infoFrameWin.show();
            infoFrameWin.toFront();   
        }
        catch(ef){}
    }


    function HideControl(controlName){
        var myControl = document.getElementById(controlName);
        if (myControl!=null){
               map.HideControl(myControl);
        }else{
               return;
        }
    }

    function hideControlMap3d(){
        try{
            if(windowMinimizeStatus){
                for(i = 0; i < controlNames.length; i++){
                    HideControl(controlNames[i]);      
                }              
            }
        }catch(er1){}
    }
    
    function showControl(){
        for(i = 0; i < controlNames.length; i++){
            ShowControl(controlNames[i]);      
        }
    }
    
    function ShowControl(controlName){
        var myControl = document.getElementById(controlName);
        if (myControl!=null){
            map.ShowControl(myControl);
            windowMinimizeStatus = false;
            }
        else{
               return;
        }           
    }