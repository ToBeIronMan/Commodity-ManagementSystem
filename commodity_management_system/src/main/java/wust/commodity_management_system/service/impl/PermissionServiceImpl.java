package wust.commodity_management_system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wust.commodity_management_system.dao.PermissionMapper;
import wust.commodity_management_system.model.Permission;
import wust.commodity_management_system.service.PermissionService;

/**
 * @author yihui.yan
 * @date 2020/3/5/005
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
