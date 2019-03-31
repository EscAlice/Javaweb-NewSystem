package com.ns.idao;

import java.io.Serializable;
import java.util.List;
import com.ns.common.MyException;

public interface INsBaseDao<T,ID extends Serializable>{
	void insert(T obj)throws MyException;
	void update(T obj)throws MyException;
	void delete(ID id)throws MyException;
	T selectById(ID id)throws MyException;
	List<T> selectall()throws MyException;
	List<T> selectall(Integer pageNo,Integer pageSize)throws MyException;  //pageNo�ڼ�������0��ʼ��  pageSize������
	int selectAllCount()throws MyException;
}
