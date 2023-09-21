package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDaoImpl implements MembershipDao
{

    @Override
    public void addAccount()
    {
        System.out.println(getClass() + ": Doing my DB work: ADDING AN ACCOUNT");

    }
}
