package com.techtrade.rads.framework.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techtrade.rads.framework.context.IRadsContext;
import com.techtrade.rads.framework.ui.abstracts.ILookupService;
import com.techtrade.rads.framework.utils.Utils;

public class LookupAlphaNumerics implements ILookupService{

	
	
	@Override
	public Map<String, String> lookupData(IRadsContext ctx,
			String searchString, int from, int noRecords, String lookupParam,
			List<String> additionalFieldsRequired) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> lookupData(IRadsContext ctx, String searchString,
			int from, int noRecords, String lookupParam) {
		// TODO Auto-generated method stub
		return lookupData(ctx, searchString, from, noRecords);
	}

	public List<Object> lookupData(IRadsContext ctx,String searchString, int from, int noRecords) {
		String arr[] = new String [8000] ;
		int c = 0 ;
		List<Object> ans = new ArrayList<Object>();
		for ( char x = 'a' ; x < 'z' ; x ++ ){
			for (int i = 0 ;  i  < 100 ; i ++  ) {
				arr[ c ++] = x + String.valueOf(i) ;
			}
		}
		
		for (int l = from ; l < arr.length ; l ++ )  {
			if (!Utils.isNullString(arr[l]) && arr[l].contains(Utils.getFormattedValue(searchString))) {
				noRecords -- ;
				if (noRecords <= 0 ) break ;
				ans.add(arr[l]);
			}
		}
		
		return ans;
		
	}
	
	@Override
	public IRadsContext generateContext(HttpServletRequest request) {
		return null;
	}	

}
