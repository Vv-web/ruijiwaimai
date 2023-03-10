package com.vv.ruiji.service;

import com.vv.ruiji.entity.SetmealDish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 套餐菜品关系(SetmealDish)表服务接口
 *
 * @author makejava
 * @since 2022-06-11 18:07:12
 */
public interface SetmealDishService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SetmealDish queryById(Long id);

    /**
     * 分页查询
     *
     * @param setmealDish 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SetmealDish> queryByPage(SetmealDish setmealDish, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param setmealDish 实例对象
     * @return 实例对象
     */
    SetmealDish insert(SetmealDish setmealDish);

    /**
     * 修改数据
     *
     * @param setmealDish 实例对象
     * @return 实例对象
     */
    SetmealDish update(SetmealDish setmealDish);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
