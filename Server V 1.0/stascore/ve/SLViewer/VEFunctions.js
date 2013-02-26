function refreshTileWindows() {
    try {
        for (intLength = 0; intLength <= document.body.childNodes.length - 1; intLength++) {
            if (document.body.childNodes[intLength].childNodes.length > 0) {
                if (document.body.childNodes[intLength].childNodes[0].nodeName == "IFRAME") {
                    var flashMovie = document.body.childNodes[intLength].childNodes[0].contentWindow.document[0];
                    //Addition : minimized window is not getting refresh
                    if (flashMovie != undefined && flashMovie != null) {
                        //                        if (document.body.childNodes[intLength].childNodes[0].contentWindow.checkIsCameraSensitive() == "True") {
                        var flashOuterHtml = document.body.childNodes[intLength].childNodes[0].contentWindow.document[0].outerHTML;
                        var searchText = flashOuterHtml.search('callFlashFromJS="null"');
                        if (searchText > 0) {
                            flashOuterHtml = flashOuterHtml.replace('callFlashFromJS="null"', '');
                            flashMovie.outerHTML = flashOuterHtml;
                        }
                        //Addition ended here
                        else if (flashMovie.callFlashFromJS != undefined && flashMovie.callFlashFromJS != null) {
                            flashMovie.callFlashFromJS('_refresh', '');
                        }
                        //                        }
                    }
                }
            }
        }
    }
    catch (err) { }
}


function _lookat(coordStr) {
    try {
        if (coordStr != null) {
            //We have added parent. because jsp was not containg the apphost object
            var control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.LookatFunction(coordStr);
            }
        }
    }
    catch (err) { alert(err); }
}

function openInfoframe(urlToOpen) {
    try {
        var control = document.getElementById("appHostObject");
        var name = "Info Frame Window";
        if (control != undefined && control != null) {
            control.Content.Page.OpenInfoWindow(name, urlToOpen);
        }
        else {
            control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.OpenInfoWindow(name, urlToOpen);
            }
        }
    } catch (er) { }
}
function openInfoframeById(urlId, urlToOpen) {
    try {
        var control = document.getElementById("appHostObject");
        var name = "Info Frame Window";
        if (control != undefined && control != null) {
            control.Content.Page.OpenInfoWindow(name, urlToOpen);
        }
        else {
            control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.OpenInfoWindow(name, urlToOpen);
            }
        }
    } catch (er) { }
}

function openDashboardFrameById(urlId, urlToOpen, layerId, layerName, dashboardId) {
    try {
        var control = document.getElementById("appHostObject");
        if (control != undefined && control != null) {
            control.Content.Page.OpenDashBoardInfoWindow(layerName, urlToOpen, layerId, layerName, dashboardId);
        }
        else {
            control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.OpenDashBoardInfoWindow(layerName, urlToOpen, layerId, layerName, dashboardId);
            }
        }
    } catch (er) { }
}

function _refreshLink(name) {
    try {
        if (name) {
            var control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.RefreshLinkWithParameters(name);
            }
        }
    } catch (e) {
        alert('Error [' + e.message + '] occurred while refreshing link [' + name + ']');
    }
}


function _setTimeRange(timeStr) {
    var control = parent.document.getElementById("appHostObject");
    if (control != undefined && control != null) {
        control.Content.Page.SetSliderTimer(timeStr);
    }
}


function _getCurrentBoundingBox() {
    var control = parent.document.getElementById("appHostObject");
    if (control != undefined && control != null) {
        var latLonBox = control.Content.Page.GetCurrentViewport();
    }
    return latLonBox;
}