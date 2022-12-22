package com.vv.ruiji.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vv.ruiji.entity.Employee;
import com.vv.ruiji.dao.EmployeeDao;
import com.vv.ruiji.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author makejava
 * @since 2022-06-11 18:07:11
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao,Employee> implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Employee queryById(Long id) {
        return this.employeeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param employee 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Employee> queryByPage(Employee employee, PageRequest pageRequest) {
        long total = this.employeeDao.count(employee);
        return new PageImpl<>(this.employeeDao.queryAllByLimit(employee, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) {
        this.employeeDao.insert(employee);
        return employee;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee update(Employee employee) {
        this.employeeDao.update(employee);
        return this.queryById(employee.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.employeeDao.deleteById(id) > 0;
    }
}
