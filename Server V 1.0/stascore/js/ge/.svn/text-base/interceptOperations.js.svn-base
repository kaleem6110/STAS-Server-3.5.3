/*++++++++++++++++++++++++++++++++++++++++++++++
* Intercept Operations -- Space-Time Insight
*+++++++++++++++++++++++++++++++++++++++++++++++*/


/*
Example: 
crudObj.xmlString = <rubberbandregions><rubberbandregion regionTypeId='0' regionName='test_1' label='test_1' regionColor='ff00'  startDate='null' endDate='null' coordinates='-108.29458618164062,31.7445125579834,0.0 -95.37493133544922,41.331539154052734,0.0 -111.5032272338867,40.11515426635742,0.0' viewPort='0gizrH_zk2SAyrognD-gHAA' ecoexpmlIds='null' refreshInterval='0' listType='userList'/></rubberbandregions>

'regionName' in above xmlString is the unique id.


Note: Please use try catch block to handle the exceptions and use any custom alert message in catch block.

 */

/*
* @param crudObj- CRUDInterceptArgs
* @retruns true/false
*
* crudObj.rbLabel - String  Returns the rubber band region label.
* 
*/
function rb_pre_add(crudObj){
	//alert("rb_pre_add :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_pre_add :: xmlString - "+ crudObj.xmlString);
    return true;
}
 
/*
* @param crudObj- CRUDInterceptArgs
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/ 
function rb_post_add(crudObj){
	//alert("rb_post_add :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_post_add :: xmlString - "+ crudObj.xmlString);
}

/*
* @param crudObj- CRUDInterceptArgs
* @retruns true/false
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/
function rb_pre_edit(crudObj){
	//alert("rb_pre_edit :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_pre_edit :: xmlString - "+ crudObj.xmlString);
	return true;

}

/*
* @param crudObj- CRUDInterceptArgs
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/
function rb_post_edit(crudObj){
	//alert("rb_post_edit :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_post_edit :: xmlString - "+ crudObj.xmlString);
}

/*
* If category is selected/send to delete, crudObj.xmlString example is as below. Please modify your checks accordingly.
*
* <rubberbandcategory label="cat1" userId="1" moduleId="4"><rubberbandcategory label="cat2"><rubberbandcategory 
*label="cat3"></rubberbandcategory><rubberbandregions><rubberbandregion regionTypeId='0' regionName='rb2' label='rb2' regionColor='ff00'  startDate='null' endDate='null' 
*coordinates='-123.51902770996094,37.45585250854492,0.0 -123.27691650390626,41.2292594909668,0.0 -128.81455993652344,39.66612243652344,0.0 
*-123.51902770996094,37.45585250854492,0.0' viewPort='0gizrHh0k2SAyrognD-gHAA' ecoexpmlIds='' refreshInterval='0' 
*listType='userList'/></rubberbandregions></rubberbandcategory><rubberbandregions><rubberbandregion regionTypeId='0' regionName='rb1' label='rb1' regionColor='ff00'  
*startDate='null' endDate='null' coordinates='-125.95549011230467,36.056766510009766,0.0 -122.43440246582032,41.824073791503906,0.0 -134.67535400390625,41.39177703857422,0.0 
*-125.95549011230467,36.056766510009766,0.0' viewPort='0gizrH_zk2SAyrognD-gHAA' ecoexpmlIds='' refreshInterval='0' listType='userList'/></rubberbandregions></rubberbandcategory>
* 
* 
* @param crudObj- CRUDInterceptArgs
* @retruns true/false
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/
function rb_pre_delete(crudObj){
	//alert("rb_pre_delete :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_pre_delete :: xmlString - "+ crudObj.xmlString);
	return true;
}

/*
* @param crudObj- CRUDInterceptArgs
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/
function rb_post_delete(crudObj){
	//alert("rb_post_delete :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_post_delete :: xmlString - "+ crudObj.xmlString);
}

/*
* @param crudObj- CRUDInterceptArgs
* @retruns true/false
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/
function rb_pre_load(crudObj){
	//alert("rb_pre_load :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_pre_load :: xmlString - "+ crudObj.xmlString);
    return true;
}

/*
* @param crudObj- CRUDInterceptArgs
*
* crudObj.rbLabel - String  Returns the rubber band region label.
*/
function rb_post_load(crudObj){
	//alert("rb_post_load :: rbLabel - "+ crudObj.rbLabel);
	//alert("rb_post_load :: xmlString - "+ crudObj.xmlString);
}