package com.spacetimeinsight.datasource.custom;

import java.util.Date;
import java.util.List;

import com.enterprisehorizons.conversion.rss.RSSFeed;
import com.enterprisehorizons.conversion.rss.RSSFeedRecord;
import com.enterprisehorizons.magma.datamashup.BaseGeoDataDriver;
import com.enterprisehorizons.magma.datamashup.IDataSource;
import com.enterprisehorizons.util.SearchCriteria;
import com.enterprisehorizons.util.SearchCriteriaHelper;

public class CustomRSSFeedDataDriver extends BaseGeoDataDriver {

	private RSSFeed feed = null;

	public CustomRSSFeedDataDriver(CustomRSSFeedDataSource datasource) {
		super(datasource);
	}

	public void initialize(IDataSource datasource) {
		if (datasource == null) {
			datasource = getDataSource();
		}
		if (datasource != null) {
			CustomRSSFeedDataSource rssFeedDatasource = (CustomRSSFeedDataSource) datasource;
			feed = new RSSFeed(rssFeedDatasource.getFeedUrl(), rssFeedDatasource.getUserId(), rssFeedDatasource.getPassword(), rssFeedDatasource
					.getAuthenticationType());
			setDataMetaData(getDataMetaData(feed.getAttributeNames(), feed.getAttributeTypes()));
		}
		super.initialize(datasource);
	}

	@Override
	public Object getDataElementValue(String dataElementName, Object dataRow) {
		if (dataRow != null && dataElementName != null) {
			if (dataRow instanceof RSSFeedRecord) {
				if (CustomRSSFeedDataSource.COORDINATES_DATAELEMENT.equals(dataElementName)) {
					return ((RSSFeedRecord) dataRow).getCoordinates();
				}
				return ((RSSFeedRecord) dataRow).getAttributeValue(dataElementName);
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.enterprisehorizons.magma.data.IDataDriver#getData()
	 */
	public List getData() {
		setCustomSearchCriteria();
		
		return feed != null ? feed.getRecordsAsList() : null;
	}
	
	/**
	 * functionality taking care for searching the data based on user inputs. 
	 */
	void setCustomSearchCriteria(){
		//retrieve the search criteria
		SearchCriteria sc = getSearchCriteria();
		SearchCriteriaHelper sch  = null;
		Object[][] timeSearch = null;
		if(sc != null){
			//get the search criteria for specified property
			sch = sc.getSearchCritera("Updated_Date");
			timeSearch = sch.getSearchConditions();
			//set the start date & enddate
			setTimeSpan((Date)timeSearch[0][2], (Date)timeSearch[1][2]);
		}
	}
	
	
	@Override
	public boolean supportsSearch() {
		// TODO Auto-generated method stub
		return true;
	}
}
