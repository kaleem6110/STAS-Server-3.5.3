var xmlHttp;
function getXmlHttpObject() {
var xmlHttpObj = null;
	try {
	  // Firefox, Opera 8.0+, Safari
	  xmlHttpObj=new XMLHttpRequest();
	} catch (e) {
		try {
			xmlHttpObj=new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttpObj=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttpObj;
}


function getXMLDocument (xmlString) {
	var xmlDoc;
	try {// code for IE
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
		xmlDoc.async="false";
		xmlDoc.loadXML(xmlString);
	} catch(e) {// code for Mozilla, Firefox, Opera, etc.
		try {
			var parser=new DOMParser();
			var xmlDoc=parser.parseFromString(xmlString,"text/xml");
		} catch(e) {
			alert(e.message);
			return;
		}
	}
	return xmlDoc;
}