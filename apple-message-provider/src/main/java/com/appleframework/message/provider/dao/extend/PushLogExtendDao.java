package com.appleframework.message.provider.dao.extend;

import com.appleframework.model.page.Pagination;
import com.appleframework.message.model.PushLogBo;
import com.appleframework.message.model.PushLogSo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PushLogExtendDao {
    
    List<PushLogBo> findPage(@Param("page") Pagination page, @Param("so") PushLogSo so);
        
}