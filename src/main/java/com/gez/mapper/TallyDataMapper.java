package com.gez.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.gez.provider.TallyDataProvider;

@Mapper
public interface TallyDataMapper {
	@Results({
			@Result(column = "TALLY_ID",property = "tallyId"),
			@Result(column = "OPEN_ID",property = "openId"),
			@Result(column = "SORT_ID",property = "sortId"),
			@Result(column = "SORT_NAME",property = "sortName"),
			@Result(column = "CREATE",property = "createTime"),
			@Result(column = "AMT",property = "amt"),
			@Result(column = "DESCRIBE",property = "describe"),
			@Result(column = "TRANS_DATE",property = "transDate"),
			@Result(column = "TRANS_TYPE",property = "transType")
	})
	@Select("select t.`TALLY_ID`,t.`OPEN_ID`,t.`SORT_ID`,s.`SORT_NAME`,t.`AMT`,t.`TRANS_TYPE`,t.`DESCRIBE`,date_format(t.`TRANS_DATE`,'%Y-%m-%d') as `TRANS_DATE`,date_format(t.`CREATE`,'%Y-%m-%d %H:%i:%s') AS `CREATE` "
			+ "from gez_def_tally t LEFT JOIN gez_sort s on t.`SORT_ID` = s.`SORT_ID` where t.`OPEN_ID` = #{openId} order by t.`TRANS_DATE`,t.`CREATE` ")
	List<Map<String, Object>> getDefTallyData(@Param("openId")String openId);
	//这个是Oracle的写法 :  select `TALLY_ID`,`OPEN_ID`,`SORT_ID`,`AMT`,`DESCRIBE`,to_char(`TRANS_DATE`,'yyyy-MM-dd') as `TRANS_DATE`, to_char(`CREATE`,'yyyy-MM-dd HH:mm:ss') AS `CREATE` from gez_def_tally where OPEN_ID = #{openId} order by `TRANS_DATE`,`CREATE`
	//mysql中的date_format函数是将数据库中的timestamp转成字符串输出, 而str_to_date是将字符串转成日期入库
	
	@Insert(
			"INSERT INTO GEZ_DEF_TALLY (`OPEN_ID`, `SORT_ID`, `AMT`, `DESCRIBE`, `CREATE`, `TRANS_DATE` ) values"
			+ "(#{openId}, #{sortId}, #{amt}, #{describe}, now(), str_to_date( #{transDate}, '%Y-%m-%d'))"
					)
	void insertDefTallyData(Map<String, Object> map);
	//这里mysql获取时间的方法是 now(), 还有CURDATE()仅获取日期
	
	@UpdateProvider(type = TallyDataProvider.class, method = "updateDefTallyData")
	void updateDefTallyData(Map<String, Object> map);
	
	@Delete("DELETE FROM GEZ_DEF_TALLY WHERE TALLY_ID = #{tallyId} and OPEN_ID = #{openId} ")
	void deleteDefTallyData(Map<String, Object> map);
}
