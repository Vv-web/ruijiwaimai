package com.vv.ruiji.service.impl;

import com.vv.ruiji.entity.DishFlavor;
import com.vv.ruiji.dao.DishFlavorDao;
import com.vv.ruiji.service.DishFlavorService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
@Service("dishFlavorService")
public class DishFlavorServiceImpl implements DishFlavorService {
    @Resource
    private DishFlavorDao dishFlavorDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DishFlavor queryById(Long id) {
        return this.dishFlavorDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param dishFlavor 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<DishFlavor> queryByPage(DishFlavor dishFlavor, PageRequest pageRequest) {
        long total = this.dishFlavorDao.count(dishFlavor);
        return new PageImpl<>(this.dishFlavorDao.queryAllByLimit(dishFlavor, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param dishFlavor 实例对象
     * @return 实例对象
     */
    @Override
    public DishFlavor insert(DishFlavor dishFlavor) {
        this.dishFlavorDao.insert(dishFlavor);
        return dishFlavor;
    }

    /**
     * 修改数据
     *
     * @param dishFlavor 实例对象
     * @return 实例对象
     */
    @Override
    public DishFlavor update(DishFlavor dishFlavor) {
        this.dishFlavorDao.update(dishFlavor);
        return this.queryById(dishFlavor.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.dishFlavorDao.deleteById(id) > 0;
    }
}
