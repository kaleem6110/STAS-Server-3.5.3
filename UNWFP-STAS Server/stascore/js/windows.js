// Confirm Dialog using  Window JS


function openConfirmDialog(data2Show, typeName) {
    Dialog.confirm("<center>"+data2Show +"</center>", 
                {windowParameters: {width:300, height:100}, okLabel: MSG_YES, cancelLabel: MSG_NO, 
                    onOk:function(win){
                            if(typeName == 'update'){
                                updateDS();
                            }else if(typeName == 'delete'){
                                deleteDS();
                            }                                   
                        },
                    cancel:function(win) {return false},
                    ok:function(win) { return true}
                });
}

//Alert Dialog using Window Js
function openAlertDialog(data2Show) {
        Dialog.alert(data2Show, 
            {windowParameters: {top:100,left:800}, okLabel: '+MSG_OK+', 
                ok:function(win) { return true}
            });
    }

function openDialog() {
        Dialog.alert(MSG_LOADING+'<center><img src="/stas/images/portal/indicator.gif"></center>', 
            {windowParameters: {top:400,left:400}, okLabel: "",width:400,height:100, 
                ok:function(win) { return true}
            });
    
    Dialog.closeInfo(); 
    }

var timeout ;
function waitDialog(data2Show){
        Dialog.info("<center><font color='gray'>"+MSG_WAIT+"</font>",
                        {windowParameters: {width:300, height:100},  showProgress: true,top:300,left:450});
    timeout=3;
//  infoTimeout();
    // setTimeout("infoTimeout()", 1000)
}


function waitDone(){
    
    /*while(timeout > 0){
        timeout--;
        waitDone();
    }  */
     Dialog.closeInfo();
}

function infoTimeout() {
      timeout--;
      if (timeout >0) {
        Dialog.setInfoMessage("Test of info panel, it will close <br>in " + timeout + "s ...")
        //infoTimeout();
  //        setTimeout("infoTimeout()", 1000)
    }
      else
        Dialog.closeInfo()
    }

    dojo.require("dijit.Dialog");

    var progressDlg;
    var emptyDlg;
    var uploadDlg;
    var promptDlg;
	 var  confDtls;
    dojo.addOnLoad(function(){
        // create the dialog:
        progressDlg = new dijit.Dialog({
            title:'+MSG_LOADING+',
            style: "width: 450px;height:150px"
        });     
        //create the upload dialog
        uploadDlg = new dijit.Dialog({
            title:'+MSG_LOADING+',
            style: "width: 525px;height:480px"          
        }); 
        
         emptyDlg = new dijit.Dialog({
            title: '+MSG_LOADING+',
            style: "width: 300px;height:150px"
        }); 

        promptDlg = new dijit.Dialog({
            title: '+MSG_LOADING+',
            style: "width: 400px;height:200px"
        }); 
		    confDtls = new dijit.Dialog({
            title:'+MSG_LOADING+',
            style: "width: 350px;height:125px"
        }); 

    });
   
    function showProgressDialog(data2Display, dialogTitle){
        // set the content of the dialog:
        if(progressDlg != null) {
            progressDlg.attr("title",  dialogTitle);
            progressDlg.attr("content", "<center><table ><tr><td align=center><img src='/stas/images/portal/loading.gif' /></tr><tr><td align='center'>"+data2Display+"</td></tr></table></center>");
            progressDlg.show();
        }
    }

    function hideProgressDialog(){
        if(progressDlg != null) {
            progressDlg.hide();
        }
    }

    function showMultiFileDialog(dialogTitle, fileTypes){
        if(fileTypes == null) {
            fileTypes = '';
        }
        // set the content of the dialog:
        if(uploadDlg != null) {
            uploadDlg.attr("title",  dialogTitle);
            //alert(fileTypes);
            uploadDlg.attr("content", "<center class='tablebg'><iframe src='/stas/multiUploadAction.do?flag=show&type=Dialog&fileExtensions="+fileTypes+"' frameborder='0' scrolling='no' width='500px' height='400px'></iframe> <br><button dojoType=dijit.form.Button  type='button'  onclick='hideUploadDialog(\""+fileTypes+"\");'>"+ MSG_OK+"</button>  <button dojoType=dijit.form.Button type='submit' >"+MSG_CANCEL+"</button>");
            uploadDlg.show();
        }
    }

    function hideUploadDialog(fileTypes){
        if(uploadDlg != null) {         
            uploadDlg.hide();
        }
		loadData('LoadFilePaths', fileTypes.split(':')[1], setFilePaths, 'insert');
    }

	 function showEmptyDialog(data2Display, dialogTitle){
        // set the content of the dialog:
        if(emptyDlg != null) {          
            emptyDlg.attr("title",  dialogTitle);
            emptyDlg.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>    <button dojoType=dijit.form.Button type='submit'>"+ MSG_OK+"</button></td></td></tr></table></center>");
            emptyDlg.show();
        }
    }

    function hideEmptyDialog(){
        if(emptyDlg != null) {
            emptyDlg.hide();
        }
    }


    function showPromptDialog(data2Display, dialogTitle){
        
        // set the content of the dialog:
        if(promptDlg != null) {         
            promptDlg.attr("title",  dialogTitle);
            promptDlg.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td>Name:  <input type='text'  id='jobName' name='jobName'  class='medium1'   dojoType='dijit.form.ValidationTextBox' required='true' trim='true' ucfirst='true'  />  </td></tr><tr><td align='center'>  <input type='button' id='yes' value='Yes' onclick=\"hidePromptDialog();createTable(dojo.byId('jobName'))\"></input><button dojoType='dijit.form.Button' type='button' id='no' onclick='hidePromptDialog()'>No</button></td></td></tr></table></center>");
            promptDlg.show();
        }
    }

    function hidePromptDialog(){
        if(promptDlg != null) {
            promptDlg.hide();
        }
        try{
            ge.getWindow().setVisibility(true);
        }catch(er){}
    }

    function showCategoryManagerDialog(){       
        // set the content of the dialog:
		 uploadDlgEcoMap = new dijit.Dialog({
            title: MSG_LOADING,
            style: "width: 480px;height:480px"          
        }); 
        if(uploadDlgEcoMap != null) {
            uploadDlgEcoMap.attr("title",  MSG_CATEGORYMANAGER);            
			
            uploadDlgEcoMap.attr("content", "<iframe src='/stas/categoryManagerAction.do?operation=onLoad'  frameborder='1' scrolling='no' width='435px' height='425px'/>");
			
            uploadDlgEcoMap.show();
        }
    }   
	
	 function hideCategoryManager(){
        if(uploadDlgEcoMap != null) {         
            uploadDlgEcoMap.hide();
        }		
    }


   function confirmationDialog(data2Display){
	  var  dialogTitle = POPUP_ALERT;
       // set the content of the dialog:
        if(confDtls != null) { 
            confDtls.attr("title",  dialogTitle);
            confDtls.attr("content", "<center><table ><tr><td align=center></tr><tr><td align='center'>"+data2Display+"</td></tr><tr><td align='center'>  <button dojoType='dijit.form.Button' onClick='dodelete()' type='button'>Yes</button> <button dojoType='dijit.form.Button' onClick='confDtls.hide()' type='button'>No</button></td></td></tr></table></center>");
            confDtls.show();
           }
		}