package com.gez.provider;
import java.util.Map;

import com.gez.utils.Utils;

public class TallyDataProvider {
	
	public String updateDefTallyData(Map<String, Object> map) {
		String sql = "UPDATE GEZ_DEF_TALLY SET ";
		if (!Utils.isNullOrEmpty(map.get("sortId"))) {
			sql += "`SORT_ID` = #{sortId},";
		}
		if (!Utils.isNullOrEmpty(map.get("transDate"))) {
			sql += "`TRANS_DATE` = str_to_date(#{transDate},'%Y-%m-%d'),";
		}
		if (!Utils.isNullOrEmpty(map.get("amt"))) {
			sql += "`AMT` = #{amt},";
		}
		sql += "`CREATE` = str_to_date(now(),'%Y-%m-%d %H:%i:%s'),";
		sql += "`DESCRIBE` = #{describe}";
		sql += "WHERE `TALLY_ID` = #{tallyId} AND `OPEN_ID`=#{openId} ";
		return sql;
	}
}
