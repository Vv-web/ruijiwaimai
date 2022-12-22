package com.vv.ruiji.service.impl;

import com.vv.ruiji.entity.SetmealDish;
import com.vv.ruiji.dao.SetmealDishDao;
import com.vv.ruiji.service.SetmealDishService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 套餐菜品关系(SetmealDish)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:12
 */
@Service("setmealDishService")
public class SetmealDishServiceImpl implements SetmealDishService {
    @Resource
    private SetmealDishDao setmealDishDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SetmealDish queryById(Long id) {
        return this.setmealDishDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param setmealDish 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SetmealDish> queryByPage(SetmealDish setmealDish, PageRequest pageRequest) {
        long total = this.setmealDishDao.count(setmealDish);
        return new PageImpl<>(this.setmealDishDao.queryAllByLimit(setmealDish, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param setmealDish 实例对象
     * @return 实例对象
     */
    @Override
    public SetmealDish insert(SetmealDish setmealDish) {
        this.setmealDishDao.insert(setmealDish);
        return setmealDish;
    }

    /**
     * 修改数据
     *
     * @param setmealDish 实例对象
     * @return 实例对象
     */
    @Override
    public SetmealDish update(SetmealDish setmealDish) {
        this.setmealDishDao.update(setmealDish);
        return this.queryById(setmealDish.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.setmealDishDao.deleteById(id) > 0;
    }
}
