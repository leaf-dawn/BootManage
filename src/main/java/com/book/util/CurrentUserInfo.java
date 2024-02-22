package com.book.util;

import com.book.pojo.po.Reader;

import java.util.Objects;

/**
 * @author 李嘉劲
 * 基于当前线程的reader的获取
 */
public class CurrentUserInfo {
	/**
	 * 当前线程的reader
	 */
	public static final ThreadLocal<Reader> CURRENT_THREAD_USER_INFO = new ThreadLocal<>();

	/**
	 * 设置当前线程的reader对象
	 * @param reader
	 */
	public static void set(Reader reader) {
		CURRENT_THREAD_USER_INFO.set(Objects.requireNonNull(reader));
	}

	/**
	 * 从当前线程获取UserInfo对象
	 * @return 当前线程下的UserInfo对象
	 */
	public static Reader get() {
		return Objects.requireNonNull(CURRENT_THREAD_USER_INFO.get());
	}

	/**
	 * 移除当前线程的Reader对象
	 */
	public static void remove() {
		CURRENT_THREAD_USER_INFO.remove();
	}
}
