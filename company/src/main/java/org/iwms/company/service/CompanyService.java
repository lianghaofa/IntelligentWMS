package org.iwms.company.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.iwms.common.core.utils.StringUtil;
import org.iwms.company.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.iwms.company.model.Company;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leung
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;


    public IPage<Company> getCompanyByPage(int pageNum, String name, int pageSize) {
        Page<Company> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "company_name", "company_city", "company_address", "company_contact", "company_manager", "update_time", "create_time");
        queryWrapper.eq("is_delete", 0);
        if (StringUtil.isNotBlank(name)){
            queryWrapper.like("driver_name", name);
        }
        return companyMapper.selectPage(page, queryWrapper);
    }

    public void companyEdit(Company company) {
        // 如果id不为空，则进行更新操作
        if (company.getId() != null) {
            companyMapper.updateById(company);
        } else {
            // 如果id为空，则进行插入操作
            companyMapper.insert(company);
        }
    }

    public void companyAdd(Company company) {
        company.setCreateTime(new Date());
        company.setCreater("11");
        companyMapper.insert(company);
    }

    public Company companySelectById(Long id) {
        return companyMapper.selectById(id);
    }

    public void setDeleteFlagTrue(Company company) {
        company.setDelete(true);
        companyMapper.updateById(company);
    }

    public void setDeleteFlagFalse(Company company) {
        company.setDelete(false);
        companyMapper.updateById(company);
    }

    public List<Company> getCompanys(boolean isDelete) {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("is_delete", isDelete);
        return companyMapper.selectByMap(whereMap);
    }
}
