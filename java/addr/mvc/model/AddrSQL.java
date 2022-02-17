package addr.mvc.model;

class AddrSQL {
	static final String LIST = "select * from address order by seq desc";
	static final String INSERT = "insert into address values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
	static final String DELETE = "delete from ADDRESS where SEQ=?";
}
