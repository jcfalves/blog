package com.swayam.demo.spring.aop.enduser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swayam.demo.spring.aop.enduser.dao.BankDetailDao;
import com.swayam.demo.spring.aop.enduser.model.BankDetail;

@Service
public class BankDetailServiceImpl implements BankDetailService {

    private final BankDetailDao bankDetailDao;

    @Autowired
    public BankDetailServiceImpl(BankDetailDao bankDetailDao) {
	this.bankDetailDao = bankDetailDao;
    }

    @Override
    public List<BankDetail> getBankDetails() {
	return bankDetailDao.getAllBankDetails();
    }

}
