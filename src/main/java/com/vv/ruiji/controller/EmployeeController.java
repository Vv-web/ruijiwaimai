package com.vv.ruiji.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vv.ruiji.common.R;
import com.vv.ruiji.entity.Employee;
import com.vv.ruiji.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static javafx.beans.binding.Bindings.isNotEmpty;

/**
 * 员工信息(Employee)表控制层
 *
 * @author makejava
 * @since 2022-06-11 18:06:55
 */
@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest httpServletRequest,@RequestBody Employee employee){
        // 1 将页面提交的密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 2 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        // 3 如果没有查询到则返回登陆失败
        if (emp == null){
            return R.error("登录失败");
        }
        // 4 密码比对，不一致，返登录 失败
        if (!emp.getPassword().equals(password)) {
            return R.error("登陆失败");
        }
        // 5 查看员工状态，禁用则返回失败
        if (emp.getStatus() == 0){
            return R.error("登陆失败，账号被禁用蜡");
        }
        // 6 登录成功，将用户id存入session并返回登录成功结果
        httpServletRequest.getSession().setAttribute("employee",emp.getId());
        httpServletRequest.setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    /**
     * 退出功能
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page={},pagesize={},name={}",page,pageSize,name);
        Page page1 = new Page(page, pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(org.apache.commons.lang.StringUtils.isNotEmpty(name),Employee::getName,name);
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(page1,lambdaQueryWrapper);

        return R.success(page1);
    }



    /**
     * 分页查询
     *
     * @param employee 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
//    @GetMapping
//    public ResponseEntity<Page<Employee>> queryByPage(Employee employee, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.employeeService.queryByPage(employee, pageRequest));
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.employeeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param employee 实体
     * @return 新增结果
     */
//    @PostMapping
//    public ResponseEntity<Employee> add(Employee employee) {
//        return ResponseEntity.ok(this.employeeService.insert(employee));
//    }
    @PostMapping
    public R<String> save(HttpServletRequest httpServletRequest,@RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Long id = (Long) httpServletRequest.getSession().getAttribute("employee");
        employee.setStatus(1);
        employee.setCreateUser(id);
        employee.setUpdateUser(id);
        employeeService.save(employee);
        return R.success("添加成功");
    }

    /**
     * 编辑数据
     *
     * @param employee 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Employee> edit(Employee employee) {
        return ResponseEntity.ok(this.employeeService.update(employee));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.employeeService.deleteById(id));
    }

}

