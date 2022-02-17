package addr.mvc.model;

import java.util.ArrayList;

import mvc.domain.Address;

public class AddrService {
	private AddrDAO dao;
	
	//SingleTon Object Model - start!
	private static final AddrService instance = new AddrService();
	private AddrService() {
		dao = new AddrDAO();
	}
	public static AddrService getInstance() {
		return instance;
	}
	public ArrayList<Address> listS(){
		return dao.list();
	}
	public boolean insertS(Address dto) {
		return dao.insert(dto);
	}
	public void deleteS(int seq) {
		dao.delete(seq);
	}
}
