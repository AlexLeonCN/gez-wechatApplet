package com.gez.mapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TallyDataMapper {
	@Results({
			@Result(column = "TALLY_ID",property = "tallyId"),
			@Result(column = "OPEN_ID",property = "openId"),
			@Result(column = "SORT_ID",property = "sortId"),
			@Result(column = "CREATE",property = "createTime"),
			@Result(column = "AMT",property = "amt"),
			@Result(column = "DESCRIBE",property = "describe"),
			@Result(column = "TRANS_DATE",property = "transDate")
	})
	@Select("select `TALLY_ID`,`OPEN_ID`,`SORT_ID`,`AMT`,`DESCRIBE`,date_format(`TRANS_DATE`,'%Y-%m-%d') as `TRANS_DATE`,date_format(`CREATE`,'%Y-%m-%d %H:%i:%s') AS `CREATE` from gez_def_tally where OPEN_ID = #{openId} order by `TRANS_DATE`,`CREATE` ")
	List<Map<String, Object>> getDefTallyData(@Param("openId")String openId);
//	这个是Oracle的写法 :  select `TALLY_ID`,`OPEN_ID`,`SORT_ID`,`AMT`,`DESCRIBE`,to_char(`TRANS_DATE`,'yyyy-MM-dd') as `TRANS_DATE`, to_char(`CREATE`,'yyyy-MM-dd HH:mm:ss') AS `CREATE` from gez_def_tally where OPEN_ID = #{openId} order by `TRANS_DATE`,`CREATE`

}
