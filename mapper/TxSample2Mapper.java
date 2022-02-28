package soo.md.mapper;

import org.apache.ibatis.annotations.Insert;

public interface TxSample2Mapper {
	@Insert("insert into tbl_sample2 values(#{data})")
	int insertCol2(String data);
}
