package soo.md.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import soo.md.mapper.TxSample1Mapper;
import soo.md.mapper.TxSample2Mapper;

@Service
@Log4j
public class TxSampleServiceImpl implements TxSampleService {
	@Autowired
	private TxSample1Mapper mapper1;
	@Autowired
	private TxSample2Mapper mapper2;
	
	@Transactional
	@Override
	public void addDatas(String data) {
		log.info("TxSampleServiceImpl addDatas() 1");
		mapper1.insertCol1(data); //DML1
		log.info("TxSampleServiceImpl addDatas() 2");		
		mapper2.insertCol2(data); //DML2
		log.info("TxSampleServiceImpl addDatas() 3");
	}

}
