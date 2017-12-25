package com.managesystem.service;

import java.util.List;

public interface DepartmentService {
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int[] batchDelete(List<Integer> ids);
}
