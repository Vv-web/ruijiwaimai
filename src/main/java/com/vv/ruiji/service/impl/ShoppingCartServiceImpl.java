package com.vv.ruiji.service.impl;

import com.vv.ruiji.entity.ShoppingCart;
import com.vv.ruiji.dao.ShoppingCartDao;
import com.vv.ruiji.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:12
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartDao shoppingCartDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShoppingCart queryById(Long id) {
        return this.shoppingCartDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param shoppingCart 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ShoppingCart> queryByPage(ShoppingCart shoppingCart, PageRequest pageRequest) {
        long total = this.shoppingCartDao.count(shoppingCart);
        return new PageImpl<>(this.shoppingCartDao.queryAllByLimit(shoppingCart, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart insert(ShoppingCart shoppingCart) {
        this.shoppingCartDao.insert(shoppingCart);
        return shoppingCart;
    }

    /**
     * 修改数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        this.shoppingCartDao.update(shoppingCart);
        return this.queryById(shoppingCart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.shoppingCartDao.deleteById(id) > 0;
    }
}
