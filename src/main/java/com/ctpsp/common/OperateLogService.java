package com.ctpsp.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 注解实现类
 * Created by sun on 2016-08-10.
 */
@Aspect
@Component
public class OperateLogService {
	private Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired
//	private IAppLogService appLogService;

	//环绕通知方法
//	@Around("execution(* com.ctpsp.controller..*.*(..))")
	public Object doWriteLog(ProceedingJoinPoint pjp) throws Throwable {

		Object object;
//
//		// 拦截的实体类
//		Object target = pjp.getTarget();
//		// 拦截的方法名称
//		String methodName = pjp.getSignature().getName();
//		// 拦截的方法参数
//		Object[] args = pjp.getArgs();
//		// 拦截的放参数类型
//		Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getMethod().getParameterTypes();
//
//		// 获得被拦截的方法
//		Method method = target.getClass().getMethod(methodName, parameterTypes);
//		Class controller = target.getClass();
//
//		if (null != controller && null != method) {
//			// 判断是否包含自定义的注解
//			if (controller.isAnnotationPresent(Api.class) && method.isAnnotationPresent(ApiOperation.class)) {
//				RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//				ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//				HttpServletRequest request = sra.getRequest();
//				CwlStaff staff = (CwlStaff) request.getSession().getAttribute(SysConstant._session_user);
//				if (staff == null) {
//					staff = new CwlStaff();
//				}
//				//需要转换成Json的HashMap
//				Map<String, Object> maps = new HashMap<String, Object>();
//				Map<String, Object> parammaps = new HashMap<String, Object>();
//				// 获取自定义注解实体
//				Api api = (Api) controller.getAnnotation(Api.class);
//				ApiOperation operation = method.getAnnotation(ApiOperation.class);
//
//
//				//日志类实体类
//				CwlAppLog log = new CwlAppLog();
//				log.setStaffId(staff.getStaffId());
//				log.setStaffName(staff.getName());
//				log.setUserIp(WebUtil.getHost(request));
//				log.setLogType("01");//访问日志
//				log.setMethodName(api.value() + "/" + operation.value());
//
//				maps.put("方法名", method.getName());
//				parammaps.put("方法名", method.getName());
//				//循环获得所有参数对象
////                for (int i = 0; i < args.length; i++) {
////                    if (null != args[i]) {
////                        parammaps.put("args[" + i + "]", args[i]);
////                    } else {
////                        parammaps.put("args[" + i + "]", "空参数");
////                    }
////                }
//				maps.put("参数", parammaps);
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				maps.put("操作时间", sdf.format(new Date()));
//				// 获取服务运行结果
//				try {
//					object = pjp.proceed();// 执行该方法
//					maps.put("状态", "成功");
//					log.setStatus("0");
//				} catch (Exception e) {
//					maps.put("状态", "失败");
//					log.setStatus("1");
//					log.setMsg(e.getMessage().substring(0, 254));
//				}
//				//将参数转化为Json字符串
//				log.setExtInfo(new Gson().toJson(maps));
//				log.setInvokeTime(new Date());
//				//记录相关日志
//				if (null != appLogService) {
//					appLogService.save(log);
//				} else {
//					logger.error("注入失败，操作日志停止记录。");
//				}
//
//			} else { // 没有包含该注解则不进行其他处理
//				object = pjp.proceed();// 执行该方法
//			}
//
//		} else { // 不需要拦截，直接运行
			object = pjp.proceed(); // 执行该方法
//		}
		return object;
	}
}
