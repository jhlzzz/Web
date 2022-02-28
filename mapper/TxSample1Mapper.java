package soo.md.mapper;

import org.apache.ibatis.annotations.Insert;

public interface TxSample1Mapper {
	@Insert("insert into tbl_sample1 values(#{data})")
	int insertCol1(String data);
}
