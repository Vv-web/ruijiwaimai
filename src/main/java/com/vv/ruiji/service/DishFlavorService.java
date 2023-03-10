package com.vv.ruiji.service;

import com.vv.ruiji.entity.DishFlavor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 菜品口味关系表(DishFlavor)表服务接口
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
public interface DishFlavorService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DishFlavor queryById(Long id);

    /**
     * 分页查询
     *
     * @param dishFlavor 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<DishFlavor> queryByPage(DishFlavor dishFlavor, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param dishFlavor 实例对象
     * @return 实例对象
     */
    DishFlavor insert(DishFlavor dishFlavor);

    /**
     * 修改数据
     *
     * @param dishFlavor 实例对象
     * @return 实例对象
     */
    DishFlavor update(DishFlavor dishFlavor);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
