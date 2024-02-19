package com.idealighter.game.core.assertions;

import com.idealighter.game.core.error.ErrorCode;
import com.idealighter.game.core.exception.HuohuaRunTimeException;
import com.idealighter.utils.check.EmptyUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class HuohuaAssert {

  /**
   * 断言 表达式正确.
   * 
   * @Title isTrue.
   * @author cjb
   * @date 2016年7月22日 下午4:33:14
   * @param expression 表达式.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isTrue(boolean expression, ErrorCode errorCode) {
    if (!expression) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }

  /**
   * 断言 表达式正确.
   * 
   * @Title isTrue.
   * @author cjb
   * @date 2016年7月22日 下午4:33:14
   * @param expression 表达式.
   * @param errorCode 错误码.
   * @param args 数组.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isTrue(boolean expression, ErrorCode errorCode, String... args) {
    if (!expression) {
      throw new HuohuaRunTimeException(errorCode, Arrays.asList(args));
    }
  }


  /**
   * 断言 表达式正确.
   * 
   * @Title isTrue.
   * @author cjb
   * @date 2016年7月22日 下午4:33:32
   * @param expression 表达式.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isTrue(boolean expression) {
    isTrue(expression, ErrorCode.BAD_REQUEST);
  }

  /**
   * 断言 对象为null.
   * 
   * @Title isNull.
   * @author cjb
   * @date 2016年7月22日 下午4:34:07
   * @param object 对象.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isNull(Object object, ErrorCode errorCode) {
    if (object != null) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }


  /**
   * 断言 对象为null.
   * 
   * @Title isNull.
   * @author cjb
   * @date 2016年7月22日 下午4:37:03
   * @param object 对象.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isNull(Object object) {
    isNull(object, ErrorCode.BAD_REQUEST);
  }


  /**
   * 断言 对象不为空.
   * 
   * @Title notNull.
   * @author cjb
   * @date 2016年7月22日 下午4:39:40
   * @param object 对象.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notNull(Object object, ErrorCode errorCode) {
    if (object == null) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }

  /**
   * 断言 对象不为空.
   * 
   * @Title notNull.
   * @author cjb
   * @date 2016年7月22日 下午4:40:13
   * @param object 对象.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notNull(Object object) {
    notNull(object, ErrorCode.BAD_REQUEST);
  }


  /**
   * 断言 文本不等于 null 并且不为empty.
   * 
   * @Title hasLength.
   * @author cjb
   * @date 2016年7月22日 下午4:49:45
   * @param text 文本.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void hasLength(String text, ErrorCode errorCode) {
    if (EmptyUtil.stringIsEmpty(text)) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }


  /**
   * 断言 文本不等于 null 并且不为empty.
   * 
   * @Title hasLength.
   * @author cjb
   * @date 2016年7月22日 下午4:51:09
   * @param text 文本.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void hasLength(String text) {
    hasLength(text, ErrorCode.BAD_REQUEST);
  }



  /**
   * 断言 数组不为空.
   * 
   * @Title notEmpty.
   * @author cjb
   * @date 2016年7月22日 下午4:51:49
   * @param array 数组.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notEmpty(Object[] array, ErrorCode errorCode) {
    if (EmptyUtil.arrayIsEmpty(array)) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }


  /**
   * 断言 数组不为空.
   * 
   * @Title notEmpty.
   * @author cjb
   * @date 2016年7月22日 下午4:52:29
   * @param array 数组
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notEmpty(Object[] array) {
    notEmpty(array, ErrorCode.BAD_REQUEST);
  }


  /**
   * 断言 容器不为 null size gt 0.
   * 
   * @Title notEmpty.
   * @author cjb
   * @date 2016年7月22日 下午4:53:55
   * @param collection 容器.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notEmpty(Collection<?> collection, ErrorCode errorCode) {
    if (EmptyUtil.isEmpty(collection)) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }

  /**
   * 断言 容器不为 null size gt 0.
   * 
   * @Title notEmpty.
   * @author cjb
   * @date 2016年7月22日 下午4:54:34
   * @param collection 容器.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notEmpty(Collection<?> collection) {
    notEmpty(collection, ErrorCode.BAD_REQUEST);
  }


  /**
   * 断言 map 不为null 且包含对象.
   * 
   * @Title notEmpty.
   * @author cjb
   * @date 2016年7月22日 下午4:56:28
   * @param map map.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notEmpty(Map<?, ?> map, ErrorCode errorCode) {
    if (EmptyUtil.mapIsEmpty(map)) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }


  /**
   * 断言 map 不为null 且包含对象.
   * 
   * @Title notEmpty.
   * @author cjb
   * @date 2016年7月22日 下午4:57:11
   * @param map map.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void notEmpty(Map<?, ?> map) {
    notEmpty(map, ErrorCode.BAD_REQUEST);
  }

  /**
   * 断言 数组不存在 null 对象.
   * 
   * @Title noNullElements.
   * @author cjb
   * @date 2016年7月22日 下午4:53:01
   * @param array 数组.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void noNullElements(Object[] array, ErrorCode errorCode) {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new HuohuaRunTimeException(errorCode);
        }
      }
    }
  }


  /**
   * 断言 数组不存在 null 对象.
   * 
   * @Title noNullElements.
   * @author cjb
   * @date 2016年7月22日 下午4:53:29
   * @param array 数组.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void noNullElements(Object[] array) {
    noNullElements(array, ErrorCode.BAD_REQUEST);
  }



  /**
   * 断言 oject是clazz的实例.
   * 
   * @Title isInstanceOf.
   * @author cjb
   * @date 2016年7月22日 下午4:57:39
   * @param clazz 类型.
   * @param obj 对象
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isInstanceOf(Class<?> clazz, Object obj) {
    isInstanceOf(clazz, obj, ErrorCode.BAD_REQUEST);
  }


  /**
   * 断言 oject是clazz的实例.
   * 
   * @Title isInstanceOf.
   * @author cjb
   * @date 2016年7月22日 下午4:58:19
   * @param clazz 类型.
   * @param obj 对象.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isInstanceOf(Class<?> clazz, Object obj, ErrorCode errorCode) {
    notNull(clazz, ErrorCode.BAD_REQUEST);
    if (!clazz.isInstance(obj)) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }


  /**
   * 断言 superType 是 subType的超类.
   * 
   * @Title isAssignable.
   * @author cjb
   * @date 2016年7月22日 下午5:00:52
   * @param superType 超类.
   * @param subType 子类.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isAssignable(Class<?> superType, Class<?> subType) {
    isAssignable(superType, subType, ErrorCode.BAD_REQUEST);
  }


  /**
   * 断言 superType 是 subType的超类.
   * 
   * @Title isAssignable.
   * @author cjb
   * @date 2016年7月22日 下午5:01:24
   * @param superType 超类.
   * @param subType 子类.
   * @param errorCode 错误码.
   * @throws HuohuaRunTimeException 回滚异常.
   */
  public static void isAssignable(Class<?> superType, Class<?> subType, ErrorCode errorCode) {
    notNull(superType, ErrorCode.BAD_REQUEST);
    if (subType == null || !superType.isAssignableFrom(subType)) {
      throw new HuohuaRunTimeException(errorCode);
    }
  }
}
