<%@page import="com.enterprisehorizons.magma.server.util.*,java.util.*"%>
<?xml version="1.0" encoding="iso-8859-1"?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"/>
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE"/>
</head> 
<body>
   <% 
   
    String[] names=request.getRequestURI().split("/");
  
    String swfName=request.getParameter("ctype"); ; 
    boolean isPrc= (request.getParameter("ispercentage")!=null && Boolean.valueOf(request.getParameter("ispercentage"))); 
     
    String dType=request.getParameter("dType");    

    String widthParam=request.getParameter("width");
    String heightParam=request.getParameter("height");
    double widthPerc=Double.valueOf(1);
    double heightPerc=Double.valueOf(1);

    if(isPrc) {
      try{
          if(widthParam!=null)
          {
            widthPerc=(Double.valueOf(widthParam))/100;
           }
         if(heightParam!=null)
          {
           heightPerc=(Double.valueOf(heightParam))/100;
          }
         }
        catch(NumberFormatException ex){
                 
        }
    }
    
    String dataURL=request.getParameter("dataURL");
    
    if(dataURL == null || dataURL.trim().equals("")) {
        String url=request.getParameter("url");
        Map<String,String> parms=ServerUtils.getUrlParameters(url,null);
        dataURL=parms.get("dataURL");
    }
    
   
    double height=Double.valueOf(225);
    double width=Double.valueOf(1000) ; 
     
     
if(dType!=null && (dType.equals("android") || dType.equals("ipad")))
{
 height=Double.valueOf(request.getParameter("dheight"));
 width=Double.valueOf(request.getParameter("dwidth"));
}
       
       if(isPrc) {
             height=height*heightPerc;
             width=width*widthPerc ;   
       } 
    
    
     
%>
 
     <div id="chartdiv" align="center"  style="font-size:30px" > 
         </div>

<%if(dType!=null && dType.equals("android") ) 

{%>
 	
 	<script language="JavaScript" src="android22/FusionCharts.js"></script>
 	<script type="text/javascript" src="android22/FusionCharts.HTML5CanvasModuleFallback.js"></script> 
 	<script type="text/javascript">
   		 FusionCharts._useCanvasCompatibleJSChartModule();
		 FusionCharts.setCurrentRenderer("javascript");
		 FusionCharts._fallbackJSChartWhenNoFlash();
 	</script>
<%} else {%>
	   <script language="JavaScript" src="FusionCharts.js"></script>
 	   <script type="text/javascript" src="FusionCharts.HTML5CanvasModuleFallback.js"></script>
	  <%}%> 
         <script type="text/javascript"> 
           
            // TODO width and height has to be fixed for portal
            var chart = new FusionCharts("<%=swfName.substring(swfName.indexOf("_")+1)%>", "chartId", "<%=(long)width%>", "<%=(long)height%>", "0", "0");
          
               chart.setXMLUrl("<%=dataURL%>");      
                       
               chart.render("chartdiv"); 
           
  	</script> 
     
</body>
</html>