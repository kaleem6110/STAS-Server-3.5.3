<%@page import="com.spacetimeinsight.stas.config.* , com.enterprisehorizons.magma.server.util.*"%>
<%String baseURL = ServerUtils.getServerContextBaseUrl(request);%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>STAS Mobile</title>
<%@ include file="/common/style.jsp"%>
</head>


<body class="bodybg">
	<table width="100%" cellspacing="0" cellpadding="0">
    	<tr>
        	<td>
            	<table width="1000" cellpadding="0" cellspacing="0">
                	<tr>
                    	<td class="pageTitle paddingTitle">STAS Mobile Client Application</td>
                    </tr>
                    <tr>
                    	<td class="paddingTitleDesc bodytext">STAS Mobile Client Application description...</td>
                    </tr>
                    <tr>
                    	<td style="padding-left:67px;padding-top:10px;"><h3>What's new</h3></td>
                    </tr>
                    <tr>
                    	<td  style="padding-left:67px;" align="left" valign="top" >
                       
                         	
                        	<table class="mobile_installer" width="90%" cellpadding="0" cellspacing="0" >
                            	<tr height="25">
                                	<th>&nbsp;</th>
                                    <th align="left" style="padding-left:5px"><strong class="bodytext">Platform for iPad</strong></th>
                                    <th align="left" style="padding-left:5px"><strong class="bodytext">Platform for Android</strong></th>
                                </tr>
								<tr width="50"></tr>
                                <!-- Row:  Version  -->
                                <tr height="20">
                                	<td><strong class="bodytext">Version</strong></td>
                                    <td class="bodytext" style="padding-right:15px;"><%=GenericConfigurationManager.getInstance().getProperty("mobile","APP_VERSION")%></td>
                                    <td class="bodytext" style="padding-right:15px;">1.1.131</td>
                                </tr>
                                <!-- Row:  Release  -->
                                <tr height="20">
                                	<td><strong class="bodytext">Released</strong></td>
                                    <td class="bodytext" style="padding-right:15px;">November 06,2011</td>
                                    <td class="bodytext" style="padding-right:15px;">November 07,2011</td>
                                </tr> 
                                <!-- Row:  Size  -->   
                                <tr height="20">
                                	<td><strong class="bodytext">Size</strong></td>
                                    <td class="bodytext" style="padding-right:15px;">4.5</td>
                                    <td class="bodytext" style="padding-right:15px;">4.5</td>
                                </tr> 
                                <!-- Row:  Description. Remove the row, if description not required  -->  
                                <tr height="20">
                                	<td valign="top"><strong class="bodytext">Desciption</strong></td>
                                    <td class="bodytext" style="padding-right:15px;">Tablet Installation</td>
                                    <td class="bodytext" style="padding-right:15px;">Android Installation</td>
                                </tr> 
								<tr height="10"><td></td></tr>
                                <!-- Row:  Install Buttons  -->  
                                <tr height="20">
                                	<td><strong class="bodytext">Installer</strong></td>
                                    <td align="left" style="padding-right: 10px;"><a id="tabletInstall" href="itms-services://?action=download-manifest&url=<%=baseURL%>tablet/ios/stasmobileios.plist"><img src="<%=baseURL%>tablet/download1-iPAD.png"></a></td>
                                    <td align="left" style="padding-right: 10px;"><a id="tabletInstall" href="<%=baseURL%>tablet/android/stasmobile.apk"><img src="<%=baseURL%>tablet/download1-android.png"></a></td>
                                </tr>   
                            </table>
                        </td>
                  </tr>
                </table>        
            </td>
        </tr>    
    </table>
	
</body>
