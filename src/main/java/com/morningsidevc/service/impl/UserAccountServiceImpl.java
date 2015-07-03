package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.AccountMapper;
import com.morningsidevc.po.gen.Account;
import com.morningsidevc.po.gen.AccountExample;
import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.utils.EncryptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-2, Time: 下午11:47
 */
@Component
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public int validate(String account, String password) {

        AccountExample example = new AccountExample();
        example.createCriteria().andUseremailEqualTo(account.trim());

        List<Account> result = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)) {
            return 0;
        }

        Account userAccount = result.get(0);
        if (userAccount.getUserpassword().equals(EncryptionUtils.md5Hex(password, false, false))) {
            return userAccount.getUserid();
        }

        return 0;
    }

    @Override
    public int create(String email, String password) {
        Account userAccount = new Account();
        userAccount.setUseremail(email);
        userAccount.setUserpassword(EncryptionUtils.md5Hex(password, false, false));
        userAccount.setStatus((byte) 0);

        return accountMapper.insertSelective(userAccount);
    }

    @Override
    public int updatePassword(int userId, String oldPassword, String newPassword) {

        Account userAccount = accountMapper.selectByPrimaryKey(userId);
        if (userAccount == null || !userAccount.getUserpassword().equals(EncryptionUtils.md5Hex(oldPassword, false, false))) {
            return 0;
        }

        userAccount.setUserpassword(EncryptionUtils.md5Hex(newPassword, false, false));

        AccountExample example = new AccountExample();
        example.createCriteria().andUseridEqualTo(userId);

        return accountMapper.updateByExampleSelective(userAccount, example);
    }

    @Override
    public Account loadRyEmail(String email) {

        AccountExample example = new AccountExample();
        example.createCriteria().andUseremailEqualTo(email.trim());

        List<Account> result = accountMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }

        return result.get(0);
    }
}
