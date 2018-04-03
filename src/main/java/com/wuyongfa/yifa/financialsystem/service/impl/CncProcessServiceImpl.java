package com.wuyongfa.yifa.financialsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuyongfa.yifa.financialsystem.dao.CncProcessMapper;
import com.wuyongfa.yifa.financialsystem.model.CncProcess;
import com.wuyongfa.yifa.financialsystem.page.Page;
import com.wuyongfa.yifa.financialsystem.service.CncProcessService;

@Service
public class CncProcessServiceImpl implements CncProcessService{

	@Autowired
	private CncProcessMapper cncProcessMapper;

	@Override
	public Page selectByPage(Page page, String orderCode) {
		page.setData(cncProcessMapper.selectByPage(orderCode));
		return page;
	}

	@Override
	public int addProcess(List<CncProcess> cncProcesses,String orderCode) {

		for (CncProcess cncProcess : cncProcesses) {
			cncProcess.setOrderCode(orderCode);
			cncProcess.setStatus("01");
		}

		return cncProcessMapper.batchInsert(cncProcesses);
	}

	@Override
	public int updateCncProcess(CncProcess cncProcess) {

		return cncProcessMapper.updateByPrimaryKeySelective(cncProcess);
	}

	@Override
	public CncProcess selectById(int id) {

		return cncProcessMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteCncProcess(List<Integer> ids) {
		
		return cncProcessMapper.batchDelete(ids);
	}
}
