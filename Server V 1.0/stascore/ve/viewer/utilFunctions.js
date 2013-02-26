function popupWindow(mypage,myname,w,h,scroll,top,left){
    settings='width='+w+',height='+h+
             ',top='+top+',left='+left+
             ',scrollbars='+scroll+
             ',location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=yes';
    win=window.open(mypage,myname,settings);
}