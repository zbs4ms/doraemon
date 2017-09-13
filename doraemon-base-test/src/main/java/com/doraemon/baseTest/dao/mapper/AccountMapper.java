package com.doraemon.baseTest.dao.mapper;

import com.doraemon.base.dao.base.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by righteyte on 16/6/16.
 */

@Repository
public interface AccountMapper extends MyMapper<com.doraemon.baseTest.dao.models.Account> {



    @Select({
            "SELECT * FROM account where id = #{id}"
    })
    com.doraemon.baseTest.dao.models.Account findAccountById(@Param("id") Long id);

    @Select({
            "SELECT * FROM account order by create_time desc"
    })
    List<com.doraemon.baseTest.dao.models.Account> selectManager();


    @Select({
            "<script>SELECT * FROM `account` WHERE 1 = 1 " +
                    "<if test = \"query != null \"> and( name like CONCAT('%',#{query},'%') or account like CONCAT('%',#{query},'%') or department like CONCAT('%',#{query},'%'))</if>"+
                    "and type = #{type}" +
                    "</script>"
    })
    List<com.doraemon.baseTest.dao.models.Account> conditionFindAccount(@Param("query") String query, @Param("type") Integer type);


    @Select({
            "SELECT * FROM account WHERE type = 0 AND company_id = #{companyId}"
    })
    com.doraemon.baseTest.dao.models.Account findManagerIdByCompanyId(@Param("companyId") Long companyId);



    @Select({
            "SELECT * FROM account WHERE email = #{email}"
    })
    List<com.doraemon.baseTest.dao.models.Account> findAccountByEmail(@Param("email") String email);

    @Select({
            "SELECT * FROM account WHERE account = #{account}"
    })
    List<com.doraemon.baseTest.dao.models.Account> findAccountByAccountName(@Param("account") String account);

    @Select({
            "SELECT COUNT(*) FROM account WHERE company_id = #{companyId}"
    })
    Integer findSubAccount(@Param("companyId") Long companyId);
}
