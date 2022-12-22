package com.vv.ruiji.service;

import com.vv.ruiji.entity.AddressBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 地址管理(AddressBook)表服务接口
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
public interface AddressBookService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AddressBook queryById(Long id);

    /**
     * 分页查询
     *
     * @param addressBook 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<AddressBook> queryByPage(AddressBook addressBook, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    AddressBook insert(AddressBook addressBook);

    /**
     * 修改数据
     *
     * @param addressBook 实例对象
     * @return 实例对象
     */
    AddressBook update(AddressBook addressBook);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
