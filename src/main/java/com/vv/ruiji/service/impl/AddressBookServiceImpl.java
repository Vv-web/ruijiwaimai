package com.vv.ruiji.service.impl;

import com.vv.ruiji.entity.AddressBook;
import com.vv.ruiji.dao.AddressBookDao;
import com.vv.ruiji.service.AddressBookService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 地址管理(AddressBook)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
@Service("addressBookService")
public class AddressBookServiceImpl implements AddressBookService {
    @Resource
    private AddressBookDao addressBookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AddressBook queryById(Long id) {
        return this.addressBookDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param addressBook 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<AddressBook> queryByPage(AddressBook addressBook, PageRequest pageRequest) {
        long total = this.addressBookDao.count(addressBook);
        return new PageImpl<>(this.addressBookDao.queryAllByLimit(addressBook, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    @Override
    public AddressBook insert(AddressBook addressBook) {
        this.addressBookDao.insert(addressBook);
        return addressBook;
    }

    /**
     * 修改数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    @Override
    public AddressBook update(AddressBook addressBook) {
        this.addressBookDao.update(addressBook);
        return this.queryById(addressBook.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.addressBookDao.deleteById(id) > 0;
    }
}
