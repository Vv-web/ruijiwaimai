package com.vv.ruiji.service.impl;

import com.vv.ruiji.entity.Dish;
import com.vv.ruiji.dao.DishDao;
import com.vv.ruiji.service.DishService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
@Service("dishService")
public class DishServiceImpl implements DishService {
    @Resource
    private DishDao dishDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dish queryById(Long id) {
        return this.dishDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dish 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Dish> queryByPage(Dish dish, PageRequest pageRequest) {
        long total = this.dishDao.count(dish);
        return new PageImpl<>(this.dishDao.queryAllByLimit(dish, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    @Override
    public Dish insert(Dish dish) {
        this.dishDao.insert(dish);
        return dish;
    }

    /**
     * 修改数据
     *
     * @param dish 实例对象
     * @return 实例对象
     */
    @Override
    public Dish update(Dish dish) {
        this.dishDao.update(dish);
        return this.queryById(dish.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dishDao.deleteById(id) > 0;
    }
}
