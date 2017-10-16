/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;

/**
 *
 * @author apitz_000
 */
public class LoggingAdvice {

    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Exception x) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }

    }
    
    
    public void exceptionEntry (JoinPoint jp, Exception x) {
        Signature sig= jp.getSignature();
        String stringSig = sig.toString();
        String method = sig.getName();
        String args = Arrays.toString(jp.getArgs());
        String exceptionEntry = method+" | "+stringSig+" | "+args+" | "+x.getMessage();
    
        try {
            auditDao.writeAuditEntry(exceptionEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
