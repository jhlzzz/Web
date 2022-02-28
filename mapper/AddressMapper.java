package soo.md.mapper;

import java.util.List;

import soo.md.domain.Address;

public interface AddressMapper {
	List<Address> list();
	void insert(Address address);
	void delete(long seq);
}
