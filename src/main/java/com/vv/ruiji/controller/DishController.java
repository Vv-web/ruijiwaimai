package com.vv.ruiji.controller;

import com.vv.ruiji.entity.Dish;
import com.vv.ruiji.service.DishService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜品管理(Dish)表控制层
 *
 * @author makejava
 * @since 2022-06-11 18:06:54
 */
@RestController
@RequestMapping("dish")
public class DishController {
    /**
     * 服务对象
     */
    @Resource
    private DishService dishService;

    /**
     * 分页查询
     *
     * @param dish 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Dish>> queryByPage(Dish dish, PageRequest pageRequest) {
        return ResponseEntity.ok(this.dishService.queryByPage(dish, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Dish> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.dishService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param dish 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Dish> add(Dish dish) {
        return ResponseEntity.ok(this.dishService.insert(dish));
    }

    /**
     * 编辑数据
     *
     * @param dish 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Dish> edit(Dish dish) {
        return ResponseEntity.ok(this.dishService.update(dish));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.dishService.deleteById(id));
    }

}

