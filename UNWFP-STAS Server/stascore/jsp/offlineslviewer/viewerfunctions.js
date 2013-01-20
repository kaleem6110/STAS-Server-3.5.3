function refreshTileWindows() {
    try {
        for (intLength = 0; intLength <= document.body.childNodes.length - 1; intLength++) {
            if (document.body.childNodes[intLength].childNodes.length > 0) {
                if (document.body.childNodes[intLength].childNodes[0].nodeName == "IFRAME") {
                    var flashMovie = document.body.childNodes[intLength].childNodes[0].contentWindow.document[0];
                    //Addition : minimized window is not getting refresh
                    if (flashMovie != undefined && flashMovie != null) {
                        //if (document.body.childNodes[intLength].childNodes[0].contentWindow.checkIsCameraSensitive() == "True") {
                        var flashOuterHtml = document.body.childNodes[intLength].childNodes[0].contentWindow.document[0].outerHTML;
                        if (flashOuterHtml.search('ArtifactSelectionDashboard.swf') <= 0) {
                            var searchText = flashOuterHtml.search('callFlashFromJS="null"');
                            if (searchText > 0) {
                                flashOuterHtml = flashOuterHtml.replace('callFlashFromJS="null"', '');
                                flashMovie.outerHTML = flashOuterHtml;
                            }
                            //Addition ended here
                            else if (flashMovie.callFlashFromJS != undefined && flashMovie.callFlashFromJS != null) {
                                flashMovie.callFlashFromJS('_refresh', '');
                            }
                        }
                        //}
                    }
                }
            }
        }
    }
    catch (err) { }
}


function reloadWindows(layerId, header, url) {
    try {
        for (intLength = 0; intLength <= document.body.childNodes.length - 1; intLength++) {
            if (document.body.childNodes[intLength].childNodes.length > 0) {
                if (document.body.childNodes[intLength].childNodes[0].nodeName == "IFRAME") {
                    if (document.body.childNodes[intLength].childNodes[0].contentWindow.hdnLayerId != null && document.body.childNodes[intLength].childNodes[0].contentWindow.hdnName != null) {
                        if (document.body.childNodes[intLength].childNodes[0].contentWindow.hdnLayerId.value == layerId &&
                document.body.childNodes[intLength].childNodes[0].contentWindow.hdnName.value == header) {
                            var id = "iFrame" + "_" + layerId + "_" + header;
                            parent.frames[id].window.location.href = url;
                            setTimeout("reloadWindows()", 10000);
                            }
                    }
                }
            }
        }
    }
    catch (err) { alert(err); }
}


function _lookat(coordStr) {
    try {
        if (coordStr != null) {
            //We have added parent. because jsp was not containg the apphost object
            var control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.LookatFunction(coordStr, null);
            }
        }
    }
    catch (err) { alert(err); }
}

function _lookat(coordStr, rangeStr) {
    try {
        if (coordStr != null) {
            //We have added parent. because jsp was not containg the apphost object
            var control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.LookatFunction(coordStr, rangeStr);
            }
        }
    }
    catch (err) { alert(err); }
}

function _setHighlightPlacemarkStyle(highlightPlacemarkStyle) {
    try {
        if (highlightPlacemarkStyle != null) {
            //We have added parent. because jsp was not containg the apphost object
            var control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.SetHighlightPlacemarkStyle(highlightPlacemarkStyle);
            }
        }
    }
    catch (err) { alert(err); }
}


function _highlight(highlightPlacemarkCoords, dashboardId, layerId) {
    try {
        if (highlightPlacemarkCoords != null) {
            //We have added parent. because jsp was not containg the apphost object
            var control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.DashBoardHighlightPlacemark(highlightPlacemarkCoords, dashboardId, layerId);
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
            control.Content.Page.OpenDashBoardInfoWindow(urlId, urlToOpen, layerId, layerName, dashboardId);
        }
        else {
            control = parent.document.getElementById("appHostObject");
            if (control != undefined && control != null) {
                control.Content.Page.OpenDashBoardInfoWindow(urlId, urlToOpen, layerId, layerName, dashboardId);
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
    try {
        var control = parent.document.getElementById("appHostObject");
        if (control != undefined && control != null) {
            control.Content.Page.SetSliderTimer(timeStr);
        }
    }
    catch (err) { alert(err); }
}


function _getCurrentBoundingBox() {
    try {
        var control = parent.document.getElementById("appHostObject");
        if (control != undefined && control != null) {
            var latLonBox = control.Content.Page.GetCurrentViewport();
        }
        return latLonBox;
    }
    catch (err) { alert(err); }
}

function openDashboardHelpWindow(ecoexpml, ecosid, artifactName, dashboardId, dashboardName) {
    try {
        var url = 'jsp/help/dashboardHelpContent.jsp?ecoexpml=' + ecoexpml + '&ecosid=' + ecosid + '&artifactName=' + artifactName + '&dashboardId=' + dashboardId + '&dashboardName=' + dashboardName;
        var control = parent.document.getElementById("appHostObject");
        if (control != undefined && control != null) {
            control.Content.Page.OpenDashboardHelpWindow(url, ecoexpml, ecosid, artifactName, dashboardId, dashboardName);
        }
    }
    catch (err) { alert(err); }
}

function showControls(dashboardDivId, flag) {
    try {
        var control = parent.document.getElementById("appHostObject");
        if (control != undefined && control != null) {
            control.Content.Page.ShowDashboardControls(dashboardDivId, flag);
        }
    }
    catch (err) { alert(err); }
}

function showOnlyPrimaryControls(layerId, header, flag) {
    try {
        for (intLength = 0; intLength <= document.body.childNodes.length - 1; intLength++) {
            if (document.body.childNodes[intLength].childNodes.length > 0) {
                if (document.body.childNodes[intLength].childNodes[0].nodeName == "IFRAME") {
                    if (document.body.childNodes[intLength].childNodes[0].contentWindow.hdnLayerId != null && document.body.childNodes[intLength].childNodes[0].contentWindow.hdnName != null) {
                        if (document.body.childNodes[intLength].childNodes[0].contentWindow.hdnLayerId.value == layerId &&
                document.body.childNodes[intLength].childNodes[0].contentWindow.hdnName.value == header) {
                            var flashMovie = document.body.childNodes[intLength].childNodes[0].contentWindow.document[0];
                            //Addition : minimized window is not getting refresh
                            if (flashMovie != undefined && flashMovie != null) {
                                //if (document.body.childNodes[intLength].childNodes[0].contentWindow.checkIsCameraSensitive() == "True") {
                                var flashOuterHtml = document.body.childNodes[intLength].childNodes[0].contentWindow.document[0].outerHTML;
                                if (flashOuterHtml.search('ArtifactSelectionDashboard.swf') <= 0) {
                                    var searchText = flashOuterHtml.search('callFlashFromJS="null"');
                                    if (searchText > 0) {
                                        flashOuterHtml = flashOuterHtml.replace('callFlashFromJS="null"', '');
                                        flashMovie.outerHTML = flashOuterHtml;
                                    }
                                    //Addition ended here
                                    else if (flashMovie.callFlashFromJS != undefined && flashMovie.callFlashFromJS != null) {
                                        flashMovie.callFlashFromJS('_showOnlyPrimaryControls', flag);
                                    }
                                }
                            }
                            //}
                        }
                    }
                }
            }
        }
    }
    catch (err) { alert(err); }
}

function refreshDashboards(layerName, dashboardId) {
    try {
        var control = parent.document.getElementById("appHostObject");
        if (control != undefined && control != null) {
            control.Content.Page.RefreshDashboards(layerName, dashboardId);
        }
    }
    catch (err) { alert(err); }
}

function popup(url, windowname) {
    try {
        var width = screen.width / 2;
        var height = screen.height / 2;
        var left = (screen.width / 4);
        var top = (screen.height / 4);
        var params = 'width=' + width + ', height=' + height;
        params += ', top=' + top + ', left=' + left;
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=yes';
        params += ', scrollbars=no';
        params += ', status=no';
        params += ', toolbar=no';
        popWin = window.open(url, windowname, params);
        if (window.focus) { popWin.focus() }
    }
    catch (e) {
        alert(e.Message);
    }
}