package jeju.lhcheck.model;

class LhCheckSQL {
	static final String ONE_INSERT = "insert into LHCHECK values(LHC_SEQ.nextval, ?, ?, ?, 0, 0)";
	static final String ONE_LOVE_UPDATE = "update LHCHECK set LOVE=? where CLASSFI=? and CONTENT_NUM=? and NICK=?";
	//static final String ONE_LOVE_DELETE = "delete from LHCHECK where CLASSFI=? and CONTENT_NUM=? and NICK=?";
	static final String ONE_LOVE_CHECK = "select LOVE from LHCHECK where CLASSFI=? and CONTENT_NUM=? and NICK=?";
	static final String ONE_HATE_UPDATE = "update LHCHECK set HATE=? where CLASSFI=? and CONTENT_NUM=? and NICK=?";
	//static final String ONE_HATE_DELETE = "delete from LHCHECK where CLASSFI=? and CONTENT_NUM=? and NICK=? and LOVE_OR_HATE=?";
	static final String ONE_HATE_CHECK = "select HATE from LHCHECK where CLASSFI=? and CONTENT_NUM=? and NICK=?";
}
