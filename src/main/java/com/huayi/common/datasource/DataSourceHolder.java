package com.huayi.common.datasource;

/**
 * 
 * @ClassName: DataSourceHolder
 * @Description: 持有当前线程中使用的数据源标识
 * @author panlei
 * @date 2017年10月17日 下午4:22:14
 *
 */
public class DataSourceHolder {
	/**
	 * 注意：数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
	 */
	private static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static String getDataSource() {
		return holder.get();
	}

	public static void setDataSource(String dataSource) {
		holder.set(dataSource);
	}

	public static void clear() {
		holder.remove();
	}
}
