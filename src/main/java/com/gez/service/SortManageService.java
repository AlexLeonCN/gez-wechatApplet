package com.gez.service;

import java.util.List;
import java.util.Map;
/**
 *  用于账单的类别的增删改查操作
 */
public interface SortManageService {
	List<Map<String, Object>> getSorts(Map<String, Object> map);
}
