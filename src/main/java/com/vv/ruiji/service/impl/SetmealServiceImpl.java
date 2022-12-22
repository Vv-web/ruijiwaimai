package com.vv.ruiji.service.impl;

import com.vv.ruiji.entity.Setmeal;
import com.vv.ruiji.dao.SetmealDao;
import com.vv.ruiji.service.SetmealService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
@Service("setmealService")
public class SetmealServiceImpl implements SetmealService {
    @Resource
    private SetmealDao setmealDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Setmeal queryById(Long id) {
        return this.setmealDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param setmeal 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Setmeal> queryByPage(Setmeal setmeal, PageRequest pageRequest) {
        long total = this.setmealDao.count(setmeal);
        return new PageImpl<>(this.setmealDao.queryAllByLimit(setmeal, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param setmeal 实例对象
     * @return 实例对象
     */
    @Override
    public Setmeal insert(Setmeal setmeal) {
        this.setmealDao.insert(setmeal);
        return setmeal;
    }

    /**
     * 修改数据
     *
     * @param setmeal 实例对象
     * @return 实例对象
     */
    @Override
    public Setmeal update(Setmeal setmeal) {
        this.setmealDao.update(setmeal);
        return this.queryById(setmeal.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.setmealDao.deleteById(id) > 0;
    }
}
