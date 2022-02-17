package jeju.lhcheck.model;

import jeju.lhcheck.domain.LhCheck;

public class LhCheckService {
	public static final LhCheckService INSTANCE = new LhCheckService();
	private LhCheckRepository repository;
	private LhCheckService() {
		repository = new LhCheckRepository();
	}
	public static LhCheckService getInstance() {
		return INSTANCE;
	}
	public void lhInsertS(LhCheck lhcheck) {
		repository.lhInsert(lhcheck);
	}
	public int loveCheckS(LhCheck lhcheck) {
		return repository.loveCheck(lhcheck);
	}
	public int hateCheckS(LhCheck lhcheck) {
		return repository.hateCheck(lhcheck);
	}
	public void loveUpS(LhCheck lhcheck) {
		lhcheck.setLove(1);
		repository.loveUpdate(lhcheck);
	}
	public void loveDownS(LhCheck lhcheck) {
		lhcheck.setLove(0);
		repository.loveUpdate(lhcheck);
	}
	public void hateUpS(LhCheck lhcheck) {
		lhcheck.setHate(1);
		repository.hateUpdate(lhcheck);
	}
	public void hateDownS(LhCheck lhcheck) {
		lhcheck.setHate(0);
		repository.hateUpdate(lhcheck);
	}
}

